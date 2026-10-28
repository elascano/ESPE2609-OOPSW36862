package ec.edu.espe.provstock.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import ec.edu.espe.provstock.controller.AccessManager;
import ec.edu.espe.provstock.controller.CustomerManager;
import ec.edu.espe.provstock.controller.Inventory;
import ec.edu.espe.provstock.controller.ProductCatalog;
import ec.edu.espe.provstock.controller.PurchasingManager;
import ec.edu.espe.provstock.controller.SalesManager;
import ec.edu.espe.provstock.model.Customer;
import ec.edu.espe.provstock.model.Product;
import ec.edu.espe.provstock.model.ProductInfo;
import ec.edu.espe.provstock.model.Purchase;
import ec.edu.espe.provstock.model.PurchaseDetail;
import ec.edu.espe.provstock.model.Role;
import ec.edu.espe.provstock.model.Sale;
import ec.edu.espe.provstock.model.SaleDetail;
import ec.edu.espe.provstock.model.StockMovement;
import ec.edu.espe.provstock.model.Supplier;
import ec.edu.espe.provstock.model.User;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Persistence: the database of ProvStock is a set of JSON files in the "data" folder,
 * ONE FILE PER CLASS of the model (user.json, role.json, customer.json, product.json,
 * supplier.json, sale.json, saleDetail.json, purchase.json, purchaseDetail.json,
 * stockMovement.json) plus settings.json for the VAT rate.
 *
 * Every file is a JSON array with one object per record. The relations are saved with
 * the key of the other object (role name, username, identification number, product code,
 * RUC, sale or purchase number), like a foreign key in a database.
 *
 * The JSON is written and read with the Gson library (com.google.gson).
 *
 * The stock of the products is NOT saved: it is rebuilt from the saved stock movements,
 * so it is always equal to the sum of its movements (FR-03.3, N4).
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class FileManager {

    public static final String DATA_FOLDER = "data";

    private static final String USER = "user.json";
    private static final String ROLE = "role.json";
    private static final String CUSTOMER = "customer.json";
    private static final String PRODUCT = "product.json";
    private static final String SUPPLIER = "supplier.json";
    private static final String SALE = "sale.json";
    private static final String SALE_DETAIL = "saleDetail.json";
    private static final String PURCHASE = "purchase.json";
    private static final String PURCHASE_DETAIL = "purchaseDetail.json";
    private static final String STOCK_MOVEMENT = "stockMovement.json";
    private static final String SETTINGS = "settings.json";

    //pretty printing makes the files easy to read; nulls are written so every record has all its fields
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().serializeNulls()
            .disableHtmlEscaping().create();

    private final Path folder;

    //associations: the control classes whose data is saved
    private final AccessManager accessManager;
    private final CustomerManager customerManager;
    private final ProductCatalog productCatalog;
    private final Inventory inventory;
    private final SalesManager salesManager;
    private final PurchasingManager purchasingManager;

    public FileManager(AccessManager accessManager, CustomerManager customerManager, ProductCatalog productCatalog,
            Inventory inventory, SalesManager salesManager, PurchasingManager purchasingManager) {
        this.folder = Paths.get(DATA_FOLDER);
        this.accessManager = accessManager;
        this.customerManager = customerManager;
        this.productCatalog = productCatalog;
        this.inventory = inventory;
        this.salesManager = salesManager;
        this.purchasingManager = purchasingManager;
    }

    /**
     * @return true if the data was saved before (the users file exists)
     */
    public boolean hasSavedData() {
        return Files.exists(folder.resolve(USER));
    }

    /**
     * Writes ALL the data again. It is called after every operation of the menu, so the
     * files always have the current data: what was there, what is new and what changed.
     */
    public void saveAll() {
        try {
            Files.createDirectories(folder);
            saveRoles();
            saveUsers();
            saveCustomers();
            saveProducts();
            saveSuppliers();
            saveSales();
            savePurchases();
            saveStockMovements();
            saveSettings();
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudieron guardar los datos: " + e.getMessage(), e);
        }
    }

    /**
     * Reads all the JSON files. The order matters: first the objects that others point to
     * (roles, users, customers, products, suppliers), then sales and purchases with their
     * details, and at the end the stock movements, which rebuild the stock.
     */
    public void loadAll() {
        try {
            restoreAll(readAllFiles());
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudieron leer los datos: " + e.getMessage(), e);
        } catch (RuntimeException e) {
            //for example, a field that is missing or has the wrong type in a JSON file
            throw new UncheckedIOException("No se pudieron leer los datos: " + e.getMessage(), new IOException(e));
        }
    }

    private LoadedData readAllFiles() throws IOException {
        LoadedData data = new LoadedData();
        data.roles = loadRoles();
        data.users = loadUsers(data.roles);
        data.customers = loadCustomers();
        data.products = loadProducts();
        data.suppliers = loadSuppliers();
        data.sales = loadSales(data);
        data.purchases = loadPurchases(data);
        data.movements = loadStockMovements(data);
        return data;
    }

    private void restoreAll(LoadedData data) throws IOException {
        accessManager.restoreRoles(new ArrayList<>(data.roles.values()));
        accessManager.restoreUsers(new ArrayList<>(data.users.values()));
        customerManager.restoreCustomers(new ArrayList<>(data.customers.values()));
        productCatalog.restoreProducts(new ArrayList<>(data.products.values()));
        purchasingManager.restoreData(new ArrayList<>(data.suppliers.values()),
                new ArrayList<>(data.purchases.values()));
        salesManager.restoreData(new ArrayList<>(data.sales.values()), loadVatRate());
        inventory.restoreMovements(data.movements);
    }

    /**
     * The objects read from the files, kept by their key so the relationships can be rebuilt.
     */
    private static class LoadedData {

        private Map<String, Role> roles;
        private Map<String, User> users;
        private Map<String, Customer> customers;
        private Map<String, Product> products;
        private Map<String, Supplier> suppliers;
        private Map<Integer, Sale> sales;
        private Map<Integer, Purchase> purchases;
        private List<StockMovement> movements;
    }

    // =====================================================================
    // Save: every object becomes a JsonObject, every list a JsonArray
    // =====================================================================
    private void saveRoles() throws IOException {
        JsonArray array = new JsonArray();
        for (Role role : accessManager.getRoles()) {
            JsonObject json = new JsonObject();
            json.addProperty("id", role.getId());
            json.addProperty("name", role.getName());
            JsonArray permissions = new JsonArray();
            for (String permission : role.getPermissions()) {
                permissions.add(permission);
            }
            json.add("permissions", permissions);
            array.add(json);
        }
        write(ROLE, array);
    }

    private void saveUsers() throws IOException {
        JsonArray array = new JsonArray();
        for (User user : accessManager.getUsers()) {
            JsonObject json = new JsonObject();
            json.addProperty("id", user.getId());
            json.addProperty("username", user.getUsername());
            json.addProperty("fullName", user.getFullName());
            json.addProperty("role", user.getRole().getName());
            json.addProperty("passwordHash", user.getPasswordHash());
            json.addProperty("salt", user.getSalt());
            json.addProperty("active", user.isActive());
            array.add(json);
        }
        write(USER, array);
    }

    private void saveCustomers() throws IOException {
        JsonArray array = new JsonArray();
        for (Customer customer : customerManager.getAllCustomers()) {
            JsonObject json = new JsonObject();
            json.addProperty("id", customer.getId());
            json.addProperty("identificationType", customer.getIdentificationType());
            json.addProperty("identificationNumber", customer.getIdentificationNumber());
            json.addProperty("name", customer.getName());
            json.addProperty("phone", customer.getPhone());
            json.addProperty("email", customer.getEmail());
            json.addProperty("address", customer.getAddress());
            json.addProperty("active", customer.isActive());
            array.add(json);
        }
        write(CUSTOMER, array);
    }

    private void saveProducts() throws IOException {
        JsonArray array = new JsonArray();
        for (Product product : productCatalog.getAllProducts()) {
            JsonObject json = new JsonObject();
            json.addProperty("id", product.getId());
            json.addProperty("code", product.getCode());
            json.addProperty("name", product.getName());
            json.addProperty("description", product.getDescription());
            json.addProperty("category", product.getCategory());
            json.addProperty("brand", product.getBrand());
            json.addProperty("unitOfMeasure", product.getUnitOfMeasure());
            json.addProperty("location", product.getLocation());
            json.addProperty("purchaseCost", product.getPurchaseCost());
            json.addProperty("salePrice", product.getSalePrice());
            json.addProperty("minimumStock", product.getMinimumStock());
            json.addProperty("active", product.isActive());
            array.add(json);
        }
        write(PRODUCT, array);
    }

    private void saveSuppliers() throws IOException {
        JsonArray array = new JsonArray();
        for (Supplier supplier : purchasingManager.getAllSuppliers()) {
            JsonObject json = new JsonObject();
            json.addProperty("id", supplier.getId());
            json.addProperty("ruc", supplier.getRuc());
            json.addProperty("name", supplier.getName());
            json.addProperty("contactPerson", supplier.getContactPerson());
            json.addProperty("phone", supplier.getPhone());
            json.addProperty("email", supplier.getEmail());
            json.addProperty("address", supplier.getAddress());
            json.addProperty("active", supplier.isActive());
            array.add(json);
        }
        write(SUPPLIER, array);
    }

    /**
     * The sales go to sale.json and their lines to saleDetail.json; every line keeps
     * the number of its sale (saleNumber) to know which sale it belongs to.
     */
    private void saveSales() throws IOException {
        JsonArray sales = new JsonArray();
        JsonArray details = new JsonArray();
        for (Sale sale : salesManager.getAllSales()) {
            sales.add(toJson(sale));
            for (SaleDetail detail : sale.getDetails()) {
                JsonObject line = new JsonObject();
                line.addProperty("saleNumber", sale.getNumber());
                line.addProperty("productCode", detail.getProduct().getCode());
                line.addProperty("quantity", detail.getQuantity());
                line.addProperty("unitPrice", detail.getUnitPrice());
                line.addProperty("subtotal", detail.calculateSubtotal());
                details.add(line);
            }
        }
        write(SALE, sales);
        write(SALE_DETAIL, details);
    }

    private static JsonObject toJson(Sale sale) {
        JsonObject json = new JsonObject();
        json.addProperty("number", sale.getNumber());
        json.addProperty("dateTime", formatDateTime(sale.getDateTime()));
        json.addProperty("registeredBy", sale.getRegisteredBy().getUsername());
        json.addProperty("customer", sale.getCustomer().getIdentificationNumber());
        json.addProperty("paymentMethod", sale.getPaymentMethod());
        json.addProperty("vatRate", sale.getVatRate());
        json.addProperty("subtotal", sale.getSubtotal());
        json.addProperty("vatAmount", sale.getVatAmount());
        json.addProperty("total", sale.getTotal());
        json.addProperty("status", sale.getStatus());
        json.addProperty("voidReason", sale.getVoidReason());
        json.addProperty("voidDateTime", formatDateTime(sale.getVoidDateTime()));
        json.addProperty("voidedBy", sale.getVoidedBy() == null ? null : sale.getVoidedBy().getUsername());
        return json;
    }

    /**
     * The purchases go to purchase.json and their lines to purchaseDetail.json.
     */
    private void savePurchases() throws IOException {
        JsonArray purchases = new JsonArray();
        JsonArray details = new JsonArray();
        for (Purchase purchase : purchasingManager.getPurchases(null, null)) {
            purchases.add(toJson(purchase));
            for (PurchaseDetail detail : purchase.getDetails()) {
                JsonObject line = new JsonObject();
                line.addProperty("purchaseNumber", purchase.getNumber());
                line.addProperty("productCode", detail.getProduct().getCode());
                line.addProperty("quantity", detail.getQuantity());
                line.addProperty("unitCost", detail.getUnitCost());
                line.addProperty("subtotal", detail.calculateSubtotal());
                details.add(line);
            }
        }
        write(PURCHASE, purchases);
        write(PURCHASE_DETAIL, details);
    }

    private static JsonObject toJson(Purchase purchase) {
        JsonObject json = new JsonObject();
        json.addProperty("number", purchase.getNumber());
        json.addProperty("dateTime", formatDateTime(purchase.getDateTime()));
        json.addProperty("registeredBy", purchase.getRegisteredBy().getUsername());
        json.addProperty("supplierRuc", purchase.getSupplier().getRuc());
        json.addProperty("supplierInvoiceNumber", purchase.getSupplierInvoiceNumber());
        json.addProperty("total", purchase.getTotal());
        return json;
    }

    private void saveStockMovements() throws IOException {
        JsonArray array = new JsonArray();
        for (StockMovement movement : inventory.getAllMovements()) {
            JsonObject json = new JsonObject();
            json.addProperty("id", movement.getId());
            json.addProperty("dateTime", formatDateTime(movement.getDateTime()));
            json.addProperty("productCode", movement.getProduct().getCode());
            json.addProperty("type", movement.getType());
            json.addProperty("quantity", movement.getQuantity());
            json.addProperty("saleNumber", movement.getSale() == null ? null : movement.getSale().getNumber());
            json.addProperty("purchaseNumber",
                    movement.getPurchase() == null ? null : movement.getPurchase().getNumber());
            json.addProperty("user", movement.getUser().getUsername());
            json.addProperty("reason", movement.getReason());
            array.add(json);
        }
        write(STOCK_MOVEMENT, array);
    }

    private void saveSettings() throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("vatRate", salesManager.getVatRate());
        write(SETTINGS, json);
    }

    // =====================================================================
    // Load: every JsonObject of the array becomes an object of the model
    // =====================================================================
    private Map<String, Role> loadRoles() throws IOException {
        Map<String, Role> roles = new LinkedHashMap<>();
        for (JsonObject json : readArray(ROLE)) {
            List<String> permissions = new ArrayList<>();
            for (JsonElement permission : json.getAsJsonArray("permissions")) {
                permissions.add(permission.getAsString());
            }
            Role role = new Role(readInt(json, "id"), readText(json, "name"), permissions);
            roles.put(role.getName(), role);
        }
        //if role.json does not exist yet, the three fixed roles of AccessManager are used
        if (roles.isEmpty()) {
            for (Role role : accessManager.getRoles()) {
                roles.put(role.getName(), role);
            }
        }
        return roles;
    }

    private Map<String, User> loadUsers(Map<String, Role> roles) throws IOException {
        Map<String, User> users = new LinkedHashMap<>();
        for (JsonObject json : readArray(USER)) {
            Role role = find(roles, readText(json, "role"), USER);
            User user = new User(readInt(json, "id"), readText(json, "username"), readText(json, "fullName"), role,
                    readText(json, "passwordHash"), readText(json, "salt"), json.get("active").getAsBoolean());
            users.put(user.getUsername(), user);
        }
        return users;
    }

    private Map<String, Customer> loadCustomers() throws IOException {
        Map<String, Customer> customers = new LinkedHashMap<>();
        for (JsonObject json : readArray(CUSTOMER)) {
            Customer customer = new Customer(readInt(json, "id"), readText(json, "identificationType"),
                    readText(json, "identificationNumber"), readText(json, "name"), readText(json, "phone"),
                    readText(json, "email"), readText(json, "address"));
            if (!json.get("active").getAsBoolean() && !customer.isFinalConsumer()) {
                customer.deactivate();
            }
            customers.put(customer.getIdentificationNumber(), customer);
        }
        return customers;
    }

    private Map<String, Product> loadProducts() throws IOException {
        Map<String, Product> products = new LinkedHashMap<>();
        for (JsonObject json : readArray(PRODUCT)) {
            Product product = new Product(readInt(json, "id"), readText(json, "code"), readProductInfo(json));
            if (!json.get("active").getAsBoolean()) {
                product.deactivate();
            }
            products.put(product.getCode(), product);
        }
        return products;
    }

    private Map<String, Supplier> loadSuppliers() throws IOException {
        Map<String, Supplier> suppliers = new LinkedHashMap<>();
        for (JsonObject json : readArray(SUPPLIER)) {
            Supplier supplier = new Supplier(readInt(json, "id"), readText(json, "ruc"), readText(json, "name"),
                    readText(json, "contactPerson"), readText(json, "phone"), readText(json, "email"), readText(json, "address"));
            if (!json.get("active").getAsBoolean()) {
                supplier.deactivate();
            }
            suppliers.put(supplier.getRuc(), supplier);
        }
        return suppliers;
    }

    private Map<Integer, Sale> loadSales(LoadedData data) throws IOException {
        Map<Integer, List<JsonObject>> detailsBySale = groupLines(SALE_DETAIL, "saleNumber");
        Map<Integer, Sale> sales = new LinkedHashMap<>();
        for (JsonObject json : readArray(SALE)) {
            int number = readInt(json, "number");
            Sale sale = new Sale(find(data.customers, readText(json, "customer"), SALE),
                    find(data.users, readText(json, "registeredBy"), SALE), readDouble(json, "vatRate"));
            sale.setPaymentMethod(readText(json, "paymentMethod"));
            for (JsonObject line : detailsBySale.getOrDefault(number, new ArrayList<>())) {
                sale.restoreDetail(find(data.products, readText(line, "productCode"), SALE_DETAIL),
                        readDouble(line, "quantity"), readDouble(line, "unitPrice"));
            }
            //the totals are calculated again from the lines, not copied from the file
            sale.calculateTotal();
            sale.setNumber(number);
            sale.setDateTime(LocalDateTime.parse(readText(json, "dateTime")));
            restoreVoid(sale, json, data);
            sales.put(number, sale);
        }
        return sales;
    }

    private void restoreVoid(Sale sale, JsonObject json, LoadedData data) throws IOException {
        if (Sale.VOIDED.equals(readText(json, "status"))) {
            sale.restoreVoid(readText(json, "voidReason"), LocalDateTime.parse(readText(json, "voidDateTime")),
                    find(data.users, readText(json, "voidedBy"), SALE));
        }
    }

    private Map<Integer, Purchase> loadPurchases(LoadedData data) throws IOException {
        Map<Integer, List<JsonObject>> detailsByPurchase = groupLines(PURCHASE_DETAIL, "purchaseNumber");
        Map<Integer, Purchase> purchases = new LinkedHashMap<>();
        for (JsonObject json : readArray(PURCHASE)) {
            int number = readInt(json, "number");
            Purchase purchase = new Purchase(find(data.suppliers, readText(json, "supplierRuc"), PURCHASE),
                    find(data.users, readText(json, "registeredBy"), PURCHASE),
                    readText(json, "supplierInvoiceNumber"));
            for (JsonObject line : detailsByPurchase.getOrDefault(number, new ArrayList<>())) {
                purchase.restoreDetail(find(data.products, readText(line, "productCode"), PURCHASE_DETAIL),
                        readDouble(line, "quantity"), readDouble(line, "unitCost"));
            }
            purchase.calculateTotal();
            purchase.setNumber(number);
            purchase.setDateTime(LocalDateTime.parse(readText(json, "dateTime")));
            purchases.put(number, purchase);
        }
        return purchases;
    }

    /**
     * Reads the lines of a detail file grouped by the number of their sale or purchase.
     */
    private Map<Integer, List<JsonObject>> groupLines(String fileName, String numberField) throws IOException {
        Map<Integer, List<JsonObject>> linesByNumber = new LinkedHashMap<>();
        for (JsonObject line : readArray(fileName)) {
            linesByNumber.computeIfAbsent(readInt(line, numberField), k -> new ArrayList<>()).add(line);
        }
        return linesByNumber;
    }

    private List<StockMovement> loadStockMovements(LoadedData data) throws IOException {
        List<StockMovement> movements = new ArrayList<>();
        for (JsonObject json : readArray(STOCK_MOVEMENT)) {
            Sale sale = isNull(json, "saleNumber") ? null
                    : find(data.sales, readInt(json, "saleNumber"), STOCK_MOVEMENT);
            Purchase purchase = isNull(json, "purchaseNumber") ? null
                    : find(data.purchases, readInt(json, "purchaseNumber"), STOCK_MOVEMENT);
            movements.add(new StockMovement(readInt(json, "id"), LocalDateTime.parse(readText(json, "dateTime")),
                    find(data.products, readText(json, "productCode"), STOCK_MOVEMENT), readText(json, "type"),
                    readDouble(json, "quantity"), sale, purchase,
                    find(data.users, readText(json, "user"), STOCK_MOVEMENT), readText(json, "reason")));
        }
        return movements;
    }

    private double loadVatRate() throws IOException {
        if (!Files.exists(folder.resolve(SETTINGS))) {
            return salesManager.getVatRate();
        }
        JsonObject json = read(SETTINGS).getAsJsonObject();
        return isNull(json, "vatRate") ? salesManager.getVatRate() : readDouble(json, "vatRate");
    }

    // =====================================================================
    // Helpers
    // =====================================================================

    /**
     * Writes the JSON in a temporary file first and then replaces the old one,
     * so a failure in the middle never leaves a half-written file.
     */
    private void write(String fileName, JsonElement json) throws IOException {
        Path file = folder.resolve(fileName);
        Path temporary = folder.resolve(fileName + ".tmp");
        try (Writer writer = Files.newBufferedWriter(temporary, StandardCharsets.UTF_8)) {
            GSON.toJson(json, writer);
        }
        Files.move(temporary, file, StandardCopyOption.REPLACE_EXISTING);
    }

    private JsonElement read(String fileName) throws IOException {
        try (Reader reader = Files.newBufferedReader(folder.resolve(fileName), StandardCharsets.UTF_8)) {
            return JsonParser.parseReader(reader);
        } catch (JsonParseException e) {
            throw new IOException(fileName + ": el JSON no es válido (" + e.getMessage() + ")", e);
        }
    }

    /**
     * Reads a file that has a JSON array. If the file does not exist, it returns no records.
     */
    private List<JsonObject> readArray(String fileName) throws IOException {
        List<JsonObject> records = new ArrayList<>();
        if (!Files.exists(folder.resolve(fileName))) {
            return records;
        }
        JsonElement json = read(fileName);
        if (!json.isJsonArray()) {
            throw new IOException(fileName + ": se esperaba una lista [ ... ]");
        }
        for (JsonElement element : json.getAsJsonArray()) {
            records.add(element.getAsJsonObject());
        }
        return records;
    }

    private static <K, V> V find(Map<K, V> map, K key, String fileName) throws IOException {
        V value = map.get(key);
        if (value == null) {
            throw new IOException(fileName + ": no existe " + key);
        }
        return value;
    }

    private static ProductInfo readProductInfo(JsonObject json) {
        return new ProductInfo(readText(json, "name"), readText(json, "category"), readText(json, "unitOfMeasure"))
                .withDescription(readText(json, "description")).withBrand(readText(json, "brand"))
                .withLocation(readText(json, "location"))
                .withPrices(readDouble(json, "purchaseCost"), readDouble(json, "salePrice"))
                .withMinimumStock(readDouble(json, "minimumStock"));
    }

    private static boolean isNull(JsonObject json, String name) {
        return !json.has(name) || json.get(name).isJsonNull();
    }

    /**
     * A missing field or a null field means "no value" (for example, a movement without a reason).
     */
    private static String readText(JsonObject json, String name) {
        return isNull(json, name) ? null : json.get(name).getAsString();
    }

    private static int readInt(JsonObject json, String name) {
        return json.get(name).getAsInt();
    }

    private static double readDouble(JsonObject json, String name) {
        return json.get(name).getAsDouble();
    }

    private static String formatDateTime(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.toString();
    }
}
