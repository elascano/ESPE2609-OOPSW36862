package ec.edu.espe.provstock.persistence;

import ec.edu.espe.provstock.controller.AccessManager;
import ec.edu.espe.provstock.controller.CustomerManager;
import ec.edu.espe.provstock.controller.Inventory;
import ec.edu.espe.provstock.controller.ProductCatalog;
import ec.edu.espe.provstock.controller.PurchasingManager;
import ec.edu.espe.provstock.controller.SalesManager;
import ec.edu.espe.provstock.model.Customer;
import ec.edu.espe.provstock.model.Product;
import ec.edu.espe.provstock.model.Purchase;
import ec.edu.espe.provstock.model.PurchaseDetail;
import ec.edu.espe.provstock.model.Role;
import ec.edu.espe.provstock.model.Sale;
import ec.edu.espe.provstock.model.SaleDetail;
import ec.edu.espe.provstock.model.StockMovement;
import ec.edu.espe.provstock.model.Supplier;
import ec.edu.espe.provstock.model.User;
import java.io.IOException;
import java.io.UncheckedIOException;
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
 * Persistence: saves the data of the control classes in CSV files and reads them again
 * when the program starts. Every file has a header line and one record per line, with
 * the fields separated by ";". The relations are saved with the key of the other object
 * (username, identification number, product code, RUC, sale or purchase number).
 *
 * The stock of the products is NOT saved: it is rebuilt from the saved stock movements,
 * so it is always equal to the sum of its movements (FR-03.3, N4).
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class FileManager {

    public static final String DATA_FOLDER = "data";

    private static final String SEPARATOR = ";";
    private static final String USERS = "users.csv";
    private static final String CUSTOMERS = "customers.csv";
    private static final String PRODUCTS = "products.csv";
    private static final String SUPPLIERS = "suppliers.csv";
    private static final String SALES = "sales.csv";
    private static final String SALE_DETAILS = "sale_details.csv";
    private static final String PURCHASES = "purchases.csv";
    private static final String PURCHASE_DETAILS = "purchase_details.csv";
    private static final String MOVEMENTS = "movements.csv";
    private static final String SETTINGS = "settings.csv";

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
        return Files.exists(folder.resolve(USERS));
    }

    /**
     * Writes ALL the data again. It is called after every operation of the menu, so the
     * files always have the current data: what was there, what is new and what changed.
     */
    public void saveAll() {
        try {
            Files.createDirectories(folder);
            saveUsers();
            saveCustomers();
            saveProducts();
            saveSuppliers();
            saveSales();
            savePurchases();
            saveMovements();
            saveSettings();
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudieron guardar los datos: " + e.getMessage(), e);
        }
    }

    /**
     * Reads all the files. The order matters: first the objects that others point to
     * (users, customers, products, suppliers), then sales and purchases, and at the end
     * the stock movements, which rebuild the stock.
     */
    public void loadAll() {
        try {
            Map<String, User> users = loadUsers();
            Map<String, Customer> customers = loadCustomers();
            Map<String, Product> products = loadProducts();
            Map<String, Supplier> suppliers = loadSuppliers();
            Map<Integer, Sale> sales = loadSales(users, customers, products);
            Map<Integer, Purchase> purchases = loadPurchases(users, suppliers, products);
            List<StockMovement> movements = loadMovements(users, products, sales, purchases);

            accessManager.restoreUsers(new ArrayList<>(users.values()));
            customerManager.restoreCustomers(new ArrayList<>(customers.values()));
            productCatalog.restoreProducts(new ArrayList<>(products.values()));
            purchasingManager.restoreData(new ArrayList<>(suppliers.values()), new ArrayList<>(purchases.values()));
            salesManager.restoreData(new ArrayList<>(sales.values()), loadVatRate());
            inventory.restoreMovements(movements);
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudieron leer los datos: " + e.getMessage(), e);
        }
    }

    // =====================================================================
    // Save
    // =====================================================================
    private void saveUsers() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id;username;fullName;role;passwordHash;salt;active");
        for (User user : accessManager.getUsers()) {
            lines.add(join(user.getId(), user.getUsername(), user.getFullName(), user.getRole().getName(),
                    user.getPasswordHash(), user.getSalt(), user.isActive()));
        }
        write(USERS, lines);
    }

    private void saveCustomers() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id;identificationType;identificationNumber;name;phone;email;address;active");
        for (Customer customer : customerManager.getAllCustomers()) {
            lines.add(join(customer.getId(), customer.getIdentificationType(), customer.getIdentificationNumber(),
                    customer.getName(), customer.getPhone(), customer.getEmail(), customer.getAddress(),
                    customer.isActive()));
        }
        write(CUSTOMERS, lines);
    }

    private void saveProducts() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id;code;name;description;category;brand;unitOfMeasure;location;purchaseCost;salePrice;minimumStock;active");
        for (Product product : productCatalog.getAllProducts()) {
            lines.add(join(product.getId(), product.getCode(), product.getName(), product.getDescription(),
                    product.getCategory(), product.getBrand(), product.getUnitOfMeasure(), product.getLocation(),
                    product.getPurchaseCost(), product.getSalePrice(), product.getMinimumStock(), product.isActive()));
        }
        write(PRODUCTS, lines);
    }

    private void saveSuppliers() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id;ruc;name;contactPerson;phone;email;address;active");
        for (Supplier supplier : purchasingManager.getAllSuppliers()) {
            lines.add(join(supplier.getId(), supplier.getRuc(), supplier.getName(), supplier.getContactPerson(),
                    supplier.getPhone(), supplier.getEmail(), supplier.getAddress(), supplier.isActive()));
        }
        write(SUPPLIERS, lines);
    }

    private void saveSales() throws IOException {
        List<String> sales = new ArrayList<>();
        List<String> details = new ArrayList<>();
        sales.add("number;dateTime;registeredBy;customer;paymentMethod;vatRate;status;voidReason;voidDateTime;voidedBy");
        details.add("saleNumber;productCode;quantity;unitPrice");
        for (Sale sale : salesManager.getAllSales()) {
            sales.add(join(sale.getNumber(), sale.getDateTime(), sale.getRegisteredBy().getUsername(),
                    sale.getCustomer().getIdentificationNumber(), sale.getPaymentMethod(), sale.getVatRate(),
                    sale.getStatus(), sale.getVoidReason(), sale.getVoidDateTime(),
                    sale.getVoidedBy() == null ? null : sale.getVoidedBy().getUsername()));
            for (SaleDetail detail : sale.getDetails()) {
                details.add(join(sale.getNumber(), detail.getProduct().getCode(), detail.getQuantity(),
                        detail.getUnitPrice()));
            }
        }
        write(SALES, sales);
        write(SALE_DETAILS, details);
    }

    private void savePurchases() throws IOException {
        List<String> purchases = new ArrayList<>();
        List<String> details = new ArrayList<>();
        purchases.add("number;dateTime;registeredBy;supplierRuc;supplierInvoiceNumber");
        details.add("purchaseNumber;productCode;quantity;unitCost");
        for (Purchase purchase : purchasingManager.getPurchases(null, null)) {
            purchases.add(join(purchase.getNumber(), purchase.getDateTime(), purchase.getRegisteredBy().getUsername(),
                    purchase.getSupplier().getRuc(), purchase.getSupplierInvoiceNumber()));
            for (PurchaseDetail detail : purchase.getDetails()) {
                details.add(join(purchase.getNumber(), detail.getProduct().getCode(), detail.getQuantity(),
                        detail.getUnitCost()));
            }
        }
        write(PURCHASES, purchases);
        write(PURCHASE_DETAILS, details);
    }

    private void saveMovements() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id;dateTime;productCode;type;quantity;saleNumber;purchaseNumber;user;reason");
        for (StockMovement movement : inventory.getAllMovements()) {
            lines.add(join(movement.getId(), movement.getDateTime(), movement.getProduct().getCode(),
                    movement.getType(), movement.getQuantity(),
                    movement.getSale() == null ? null : movement.getSale().getNumber(),
                    movement.getPurchase() == null ? null : movement.getPurchase().getNumber(),
                    movement.getUser().getUsername(), movement.getReason()));
        }
        write(MOVEMENTS, lines);
    }

    private void saveSettings() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("key;value");
        lines.add(join("vatRate", salesManager.getVatRate()));
        write(SETTINGS, lines);
    }

    // =====================================================================
    // Load
    // =====================================================================
    private Map<String, User> loadUsers() throws IOException {
        Map<String, User> users = new LinkedHashMap<>();
        for (String[] f : read(USERS, 7)) {
            Role role = accessManager.findRole(f[3]);
            if (role == null) {
                throw new IOException(USERS + ": rol desconocido " + f[3]);
            }
            users.put(f[1], new User(toInt(f[0]), f[1], f[2], role, f[4], f[5], Boolean.parseBoolean(f[6])));
        }
        return users;
    }

    private Map<String, Customer> loadCustomers() throws IOException {
        Map<String, Customer> customers = new LinkedHashMap<>();
        for (String[] f : read(CUSTOMERS, 8)) {
            Customer customer = new Customer(toInt(f[0]), f[1], f[2], f[3], f[4], f[5], f[6]);
            if (!Boolean.parseBoolean(f[7]) && !customer.isFinalConsumer()) {
                customer.deactivate();
            }
            customers.put(f[2], customer);
        }
        return customers;
    }

    private Map<String, Product> loadProducts() throws IOException {
        Map<String, Product> products = new LinkedHashMap<>();
        for (String[] f : read(PRODUCTS, 12)) {
            Product product = new Product(toInt(f[0]), f[1], f[2], f[3], f[4], f[5], f[6], f[7],
                    toDouble(f[8]), toDouble(f[9]), toDouble(f[10]));
            if (!Boolean.parseBoolean(f[11])) {
                product.deactivate();
            }
            products.put(f[1], product);
        }
        return products;
    }

    private Map<String, Supplier> loadSuppliers() throws IOException {
        Map<String, Supplier> suppliers = new LinkedHashMap<>();
        for (String[] f : read(SUPPLIERS, 8)) {
            Supplier supplier = new Supplier(toInt(f[0]), f[1], f[2], f[3], f[4], f[5], f[6]);
            if (!Boolean.parseBoolean(f[7])) {
                supplier.deactivate();
            }
            suppliers.put(f[1], supplier);
        }
        return suppliers;
    }

    private Map<Integer, Sale> loadSales(Map<String, User> users, Map<String, Customer> customers,
            Map<String, Product> products) throws IOException {
        //first the lines of every sale, grouped by sale number
        Map<Integer, List<String[]>> detailsBySale = new LinkedHashMap<>();
        for (String[] f : read(SALE_DETAILS, 4)) {
            detailsBySale.computeIfAbsent(toInt(f[0]), k -> new ArrayList<>()).add(f);
        }

        Map<Integer, Sale> sales = new LinkedHashMap<>();
        for (String[] f : read(SALES, 10)) {
            int number = toInt(f[0]);
            Sale sale = new Sale(find(customers, f[3], SALES), find(users, f[2], SALES), toDouble(f[5]));
            sale.setPaymentMethod(f[4]);
            for (String[] d : detailsBySale.getOrDefault(number, new ArrayList<>())) {
                sale.restoreDetail(find(products, d[1], SALE_DETAILS), toDouble(d[2]), toDouble(d[3]));
            }
            sale.calculateTotal();
            sale.setNumber(number);
            sale.setDateTime(LocalDateTime.parse(f[1]));
            if (Sale.VOIDED.equals(f[6])) {
                sale.restoreVoid(f[7], LocalDateTime.parse(f[8]), find(users, f[9], SALES));
            }
            sales.put(number, sale);
        }
        return sales;
    }

    private Map<Integer, Purchase> loadPurchases(Map<String, User> users, Map<String, Supplier> suppliers,
            Map<String, Product> products) throws IOException {
        Map<Integer, List<String[]>> detailsByPurchase = new LinkedHashMap<>();
        for (String[] f : read(PURCHASE_DETAILS, 4)) {
            detailsByPurchase.computeIfAbsent(toInt(f[0]), k -> new ArrayList<>()).add(f);
        }

        Map<Integer, Purchase> purchases = new LinkedHashMap<>();
        for (String[] f : read(PURCHASES, 5)) {
            int number = toInt(f[0]);
            Purchase purchase = new Purchase(find(suppliers, f[3], PURCHASES), find(users, f[2], PURCHASES), f[4]);
            for (String[] d : detailsByPurchase.getOrDefault(number, new ArrayList<>())) {
                purchase.restoreDetail(find(products, d[1], PURCHASE_DETAILS), toDouble(d[2]), toDouble(d[3]));
            }
            purchase.calculateTotal();
            purchase.setNumber(number);
            purchase.setDateTime(LocalDateTime.parse(f[1]));
            purchases.put(number, purchase);
        }
        return purchases;
    }

    private List<StockMovement> loadMovements(Map<String, User> users, Map<String, Product> products,
            Map<Integer, Sale> sales, Map<Integer, Purchase> purchases) throws IOException {
        List<StockMovement> movements = new ArrayList<>();
        for (String[] f : read(MOVEMENTS, 9)) {
            Sale sale = f[5].isEmpty() ? null : find(sales, toInt(f[5]), MOVEMENTS);
            Purchase purchase = f[6].isEmpty() ? null : find(purchases, toInt(f[6]), MOVEMENTS);
            movements.add(new StockMovement(toInt(f[0]), LocalDateTime.parse(f[1]), find(products, f[2], MOVEMENTS),
                    f[3], toDouble(f[4]), sale, purchase, find(users, f[7], MOVEMENTS), orNull(f[8])));
        }
        return movements;
    }

    private double loadVatRate() throws IOException {
        for (String[] f : read(SETTINGS, 2)) {
            if ("vatRate".equals(f[0])) {
                return toDouble(f[1]);
            }
        }
        return salesManager.getVatRate();
    }

    // =====================================================================
    // Helpers
    // =====================================================================

    /**
     * Joins the values of one record. null is saved as an empty field, and the
     * separator or line breaks inside a text are changed so the line is not broken.
     */
    private static String join(Object... values) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                line.append(SEPARATOR);
            }
            if (values[i] != null) {
                line.append(values[i].toString().replace(SEPARATOR, ",").replace("\r", " ").replace("\n", " "));
            }
        }
        return line.toString();
    }

    /**
     * Writes the file in a temporary file first and then replaces the old one,
     * so a failure in the middle never leaves a half-written file.
     */
    private void write(String fileName, List<String> lines) throws IOException {
        Path file = folder.resolve(fileName);
        Path temporary = folder.resolve(fileName + ".tmp");
        Files.write(temporary, lines, StandardCharsets.UTF_8);
        Files.move(temporary, file, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Reads a file without its header. If the file does not exist, it returns no records.
     */
    private List<String[]> read(String fileName, int fields) throws IOException {
        List<String[]> records = new ArrayList<>();
        Path file = folder.resolve(fileName);
        if (!Files.exists(file)) {
            return records;
        }
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).isBlank()) {
                continue;
            }
            String[] values = lines.get(i).split(SEPARATOR, -1);
            if (values.length != fields) {
                throw new IOException(fileName + ", línea " + (i + 1) + ": se esperaban " + fields
                        + " campos y hay " + values.length);
            }
            records.add(values);
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

    /**
     * An empty field means "no value" (for example, a movement without a reason).
     */
    private static String orNull(String text) {
        return text.isEmpty() ? null : text;
    }

    private static int toInt(String text) {
        return Integer.parseInt(text.trim());
    }

    private static double toDouble(String text) {
        return Double.parseDouble(text.trim());
    }
}
