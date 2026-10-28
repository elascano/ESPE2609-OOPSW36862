package ec.edu.espe.provstock.view;

import ec.edu.espe.provstock.controller.AccessManager;
import ec.edu.espe.provstock.controller.CustomerManager;
import ec.edu.espe.provstock.controller.Inventory;
import ec.edu.espe.provstock.controller.ProductCatalog;
import ec.edu.espe.provstock.controller.PurchasingManager;
import ec.edu.espe.provstock.controller.ReportGenerator;
import ec.edu.espe.provstock.controller.SalesManager;
import ec.edu.espe.provstock.controller.StockMonitor;
import ec.edu.espe.provstock.model.Customer;
import ec.edu.espe.provstock.model.InventoryReport;
import ec.edu.espe.provstock.model.Product;
import ec.edu.espe.provstock.model.Purchase;
import ec.edu.espe.provstock.model.PurchaseDetail;
import ec.edu.espe.provstock.model.PurchaseReport;
import ec.edu.espe.provstock.model.Role;
import ec.edu.espe.provstock.model.Sale;
import ec.edu.espe.provstock.model.SaleDetail;
import ec.edu.espe.provstock.model.SalesReport;
import ec.edu.espe.provstock.model.SlowMovingProductsReport;
import ec.edu.espe.provstock.model.StockMovement;
import ec.edu.espe.provstock.model.Supplier;
import ec.edu.espe.provstock.model.User;
import ec.edu.espe.provstock.persistence.FileManager;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Console menu of ProvStock: Prov&Tec sales and inventory system.
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class ProvStockApp {

    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private static final InputReader input = new InputReader();

    //control classes (Diagram B)
    private static ProductCatalog productCatalog;
    private static StockMonitor stockMonitor;
    private static Inventory inventory;
    private static AccessManager accessManager;
    private static CustomerManager customerManager;
    private static SalesManager salesManager;
    private static PurchasingManager purchasingManager;
    private static ReportGenerator reportGenerator;

    //persistence: saves and loads the data in CSV files
    private static FileManager fileManager;

    public static void main(String[] args) {
        productCatalog = new ProductCatalog();
        stockMonitor = new StockMonitor(productCatalog);
        inventory = new Inventory(stockMonitor, productCatalog);
        accessManager = new AccessManager();
        customerManager = new CustomerManager();
        salesManager = new SalesManager(inventory, customerManager, accessManager);
        purchasingManager = new PurchasingManager(inventory, accessManager);
        reportGenerator = new ReportGenerator(salesManager, purchasingManager, productCatalog);
        fileManager = new FileManager(accessManager, customerManager, productCatalog, inventory, salesManager,
                purchasingManager);

        if (fileManager.hasSavedData()) {
            try {
                fileManager.loadAll();
                System.out.println("Datos cargados desde la carpeta \"" + FileManager.DATA_FOLDER + "\".");
            } catch (RuntimeException e) {
                //the files are not overwritten, so no data is lost
                System.out.println("Error al leer los datos: " + e.getMessage());
                System.out.println("Revise los archivos de la carpeta \"" + FileManager.DATA_FOLDER + "\".");
                return;
            }
        } else {
            //first run: the sample data is loaded only once and saved
            loadSampleData();
            fileManager.saveAll();
            System.out.println("Primera ejecución: se crearon los datos de prueba en la carpeta \""
                    + FileManager.DATA_FOLDER + "\".");
        }

        System.out.println("==============================================");
        System.out.println("   ProvStock - Prov&Tec Materiales Eléctricos");
        System.out.println("   ByteWorks, ESPE");
        System.out.println("==============================================");
        System.out.println("Usuarios de prueba (si no se cambiaron sus contraseñas):");
        System.out.println("  owner     / Owner2026     (Propietario)");
        System.out.println("  vendedor  / Vendedor2026  (Vendedor)");
        System.out.println("  bodeguero / Bodega2026    (Bodeguero)");

        int option;
        do {
            System.out.println("\n----------- INICIO -----------");
            System.out.println("1. Iniciar sesión");
            System.out.println("0. Salir");
            option = input.readInt("Elija una opción: ");
            switch (option) {
                case 1:
                    if (login()) {
                        mainMenu();
                    }
                    break;
                case 0:
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 0);
    }

    // =====================================================================
    // UC-01 Log in, UC-02 Log out
    // =====================================================================
    private static boolean login() {
        String username = input.readText("Usuario: ");
        String password = input.readText("Contraseña: ");

        if (!accessManager.login(username, password)) {
            System.out.println("Usuario o contraseña incorrectos, o la cuenta está inactiva.");
            return false;
        }

        User user = accessManager.getCurrentUser();
        System.out.println("\nBienvenido/a, " + user.getFullName() + " (" + user.getRole().getName() + ")");

        //UC-01 includes UC-17: check the minimum stock when the user logs in
        if (accessManager.checkPermission(Role.RECEIVE_ALERTS)) {
            int alerts = stockMonitor.countAlerts();
            if (alerts > 0) {
                System.out.println("¡ATENCIÓN! Hay " + alerts + " producto(s) con stock bajo. "
                        + "Revise la opción \"Alertas de stock\".");
            }
        }
        return true;
    }

    /**
     * The menu shows only the options that the role of the user is allowed to use.
     */
    private static void mainMenu() {
        while (true) {
            List<String> labels = new ArrayList<>();
            List<Runnable> actions = new ArrayList<>();

            addOption(labels, actions, Role.VIEW_PRODUCTS, "Buscar productos", ProvStockApp::searchProducts);
            addOption(labels, actions, Role.MANAGE_CUSTOMERS, "Gestionar clientes", ProvStockApp::manageCustomers);
            addOption(labels, actions, Role.REGISTER_SALE, "Registrar venta", ProvStockApp::registerSale);
            addOption(labels, actions, Role.VOID_SALE, "Anular venta", ProvStockApp::voidSale);
            addOption(labels, actions, Role.VIEW_SALES_HISTORY, "Historial de ventas", ProvStockApp::salesHistory);
            addOption(labels, actions, Role.UPDATE_VAT_RATE, "Actualizar tasa de IVA", ProvStockApp::updateVatRate);
            addOption(labels, actions, Role.MANAGE_PRODUCTS, "Gestionar productos", ProvStockApp::manageProducts);
            addOption(labels, actions, Role.VIEW_INVENTORY, "Consultar inventario", ProvStockApp::viewInventory);
            addOption(labels, actions, Role.ADJUST_STOCK, "Ajustar stock", ProvStockApp::adjustStock);
            addOption(labels, actions, Role.MANAGE_SUPPLIERS, "Gestionar proveedores", ProvStockApp::manageSuppliers);
            addOption(labels, actions, Role.REGISTER_PURCHASE, "Registrar compra", ProvStockApp::registerPurchase);
            addOption(labels, actions, Role.RECEIVE_ALERTS, "Alertas de stock", ProvStockApp::stockAlerts);
            addOption(labels, actions, Role.GENERATE_REPORTS, "Generar reportes", ProvStockApp::generateReports);
            addOption(labels, actions, Role.MANAGE_USERS, "Gestionar usuarios", ProvStockApp::manageUsers);
            addOption(labels, actions, null, "Cambiar mi contraseña", ProvStockApp::changePassword);

            System.out.println("\n----------- MENÚ PRINCIPAL -----------");
            String header = "Usuario: " + accessManager.getCurrentUser().getUsername()
                    + " | IVA actual: " + percent(salesManager.getVatRate());
            //UC-01 and UC-18: the roles that receive alerts see the number of active alerts
            if (accessManager.checkPermission(Role.RECEIVE_ALERTS)) {
                header = header + " | Alertas de stock: " + stockMonitor.countAlerts();
            }
            System.out.println(header);
            for (int i = 0; i < labels.size(); i++) {
                System.out.printf("%2d. %s%n", i + 1, labels.get(i));
            }
            System.out.println(" 0. Cerrar sesión");

            int option = input.readInt("Elija una opción: ");
            if (option == 0) {
                accessManager.logout();
                System.out.println("Sesión cerrada.");
                return;
            }
            if (option < 1 || option > labels.size()) {
                System.out.println("Opción no válida.");
                continue;
            }
            runSafely(actions.get(option - 1));
        }
    }

    private static void addOption(List<String> labels, List<Runnable> actions, String permission,
            String label, Runnable action) {
        if (permission == null || accessManager.checkPermission(permission)) {
            labels.add(label);
            actions.add(action);
        }
    }

    /**
     * If an action fails (invalid data, no permission, etc.) the message is shown
     * and the program goes back to the menu.
     */
    private static void runSafely(Runnable action) {
        try {
            action.run();
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            //persistence: after every operation the files are updated with the current data
            saveData();
        }
    }

    private static void saveData() {
        try {
            fileManager.saveAll();
        } catch (RuntimeException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // =====================================================================
    // UC-03 Change password
    // =====================================================================
    private static void changePassword() {
        String current = input.readText("Contraseña actual: ");
        String newPassword = input.readText("Nueva contraseña (mínimo 8 caracteres): ");
        String confirm = input.readText("Confirme la nueva contraseña: ");
        if (accessManager.changeOwnPassword(current, newPassword, confirm)) {
            System.out.println("Contraseña cambiada correctamente.");
        } else {
            System.out.println("No se cambió la contraseña: la actual es incorrecta, la nueva tiene menos "
                    + "de 8 caracteres o la confirmación no coincide.");
        }
    }

    // =====================================================================
    // UC-04 Manage user accounts
    // =====================================================================
    private static void manageUsers() {
        while (true) {
            System.out.println("\n--- Gestionar usuarios ---");
            System.out.println("1. Crear usuario");
            System.out.println("2. Actualizar usuario");
            System.out.println("3. Desactivar usuario");
            System.out.println("4. Restablecer contraseña");
            System.out.println("5. Listar usuarios");
            System.out.println("0. Volver");
            int option = input.readInt("Elija una opción: ");
            switch (option) {
                case 1:
                    runSafely(ProvStockApp::createUser);
                    break;
                case 2:
                    runSafely(ProvStockApp::updateUser);
                    break;
                case 3:
                    runSafely(ProvStockApp::deactivateUser);
                    break;
                case 4:
                    runSafely(ProvStockApp::resetUserPassword);
                    break;
                case 5:
                    listUsers();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void createUser() {
        String username = input.readText("Nombre de usuario: ");
        String fullName = input.readText("Nombre completo: ");
        Role role = readRole();
        String password = input.readText("Contraseña inicial (mínimo 8 caracteres): ");
        User user = accessManager.createUser(username, fullName, role, password);
        System.out.println("Usuario creado: " + user.getUsername() + " (id " + user.getId() + ")");
    }

    private static void updateUser() {
        User user = findUser();
        if (user == null) {
            return;
        }
        user.setFullName(input.readTextOrKeep("Nombre completo", user.getFullName()));
        if (input.readYesNo("¿Cambiar el rol (actual: " + user.getRole().getName() + ")?")) {
            user.setRole(readRole());
        }
        accessManager.updateUser(user);
        System.out.println("Usuario actualizado.");
    }

    private static void deactivateUser() {
        User user = findUser();
        if (user != null && input.readYesNo("¿Desactivar a " + user.getUsername() + "?")) {
            accessManager.deactivateUser(user);
            System.out.println("Usuario desactivado.");
        }
    }

    private static void resetUserPassword() {
        User user = findUser();
        if (user != null) {
            String password = input.readText("Nueva contraseña (mínimo 8 caracteres): ");
            accessManager.resetPassword(user, password);
            System.out.println("Contraseña restablecida.");
        }
    }

    private static void listUsers() {
        System.out.printf("%-4s %-14s %-28s %-16s %s%n", "Id", "Usuario", "Nombre", "Rol", "Estado");
        for (User user : accessManager.getUsers()) {
            System.out.printf("%-4d %-14s %-28s %-16s %s%n", user.getId(), user.getUsername(), user.getFullName(),
                    user.getRole().getName(), user.isActive() ? "Activo" : "Inactivo");
        }
    }

    private static User findUser() {
        String username = input.readText("Nombre de usuario: ");
        User user = accessManager.findUser(username);
        if (user == null) {
            System.out.println("No existe el usuario " + username);
        }
        return user;
    }

    private static Role readRole() {
        String[] names = new String[accessManager.getRoles().size()];
        for (int i = 0; i < names.length; i++) {
            names[i] = accessManager.getRoles().get(i).getName();
        }
        return accessManager.findRole(input.readOption("Rol:", names, false));
    }

    // =====================================================================
    // UC-05 Manage customers
    // =====================================================================
    private static void manageCustomers() {
        while (true) {
            System.out.println("\n--- Gestionar clientes ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Actualizar cliente");
            System.out.println("3. Desactivar cliente");
            System.out.println("4. Buscar clientes (identificación o nombre)");
            System.out.println("0. Volver");
            int option = input.readInt("Elija una opción: ");
            switch (option) {
                case 1:
                    runSafely(ProvStockApp::registerCustomer);
                    break;
                case 2:
                    runSafely(ProvStockApp::updateCustomer);
                    break;
                case 3:
                    if (accessManager.checkPermission(Role.DEACTIVATE_CUSTOMERS)) {
                        runSafely(ProvStockApp::deactivateCustomer);
                    } else {
                        System.out.println("Solo el propietario puede desactivar clientes.");
                    }
                    break;
                case 4:
                    searchCustomers();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * @return the new customer, or null if it was not registered
     */
    private static Customer registerCustomer() {
        String type = input.readOption("Tipo de identificación:", Customer.IDENTIFICATION_TYPES, false);
        String number = input.readText("Número de identificación: ");
        String name = input.readText("Nombre o razón social: ");
        String phone = input.readOptionalText("Teléfono (opcional): ");
        String email = input.readOptionalText("Correo (opcional): ");
        String address = input.readOptionalText("Dirección (opcional): ");

        Customer customer = new Customer(0, type, number, name, phone, email, address);
        if (!customer.validateIdentification()) {
            System.out.println("La identificación no es válida para el tipo " + type + ".");
            return null;
        }
        if (!customer.validate()) {
            System.out.println("Datos no válidos: revise el teléfono y el correo.");
            return null;
        }
        if (!customerManager.registerCustomer(customer)) {
            System.out.println("Ya existe un cliente con la identificación " + number + ".");
            return null;
        }
        System.out.println("Cliente registrado con id " + customer.getId() + ".");
        return customer;
    }

    private static void updateCustomer() {
        Customer customer = customerManager.findByIdentification(input.readText("Identificación del cliente: "));
        if (customer == null) {
            System.out.println("No existe el cliente.");
            return;
        }
        System.out.println(formatCustomer(customer));
        String name = input.readTextOrKeep("Nombre", customer.getName());
        String phone = input.readTextOrKeep("Teléfono", customer.getPhone());
        String email = input.readTextOrKeep("Correo", customer.getEmail());
        String address = input.readTextOrKeep("Dirección", customer.getAddress());
        customer.update(name, phone, email, address);
        customerManager.updateCustomer(customer);
        System.out.println("Cliente actualizado.");
    }

    private static void deactivateCustomer() {
        Customer customer = customerManager.findByIdentification(input.readText("Identificación del cliente: "));
        if (customer == null) {
            System.out.println("No existe el cliente.");
            return;
        }
        if (input.readYesNo("¿Desactivar a " + customer.getName() + "?")) {
            customerManager.deactivateCustomer(customer);
            System.out.println("Cliente desactivado.");
        }
    }

    /**
     * UC-05 A1: the user searches by identification number or by part of the name.
     */
    private static void searchCustomers() {
        String text = input.readOptionalText("Identificación o nombre (Enter para ver todos): ");
        Customer found = text.isEmpty() ? null : customerManager.findByIdentification(text);
        List<Customer> customers;
        if (found != null) {
            customers = new ArrayList<>();
            customers.add(found);
        } else {
            customers = customerManager.searchByName(text);
        }
        if (customers.isEmpty()) {
            System.out.println("No se encontraron clientes.");
        }
        for (Customer customer : customers) {
            System.out.println(formatCustomer(customer));
        }
    }

    private static String formatCustomer(Customer customer) {
        return String.format("  [%d] %-24s %-30s %s", customer.getId(), customer.getIdentification(),
                customer.getName(), customer.getEmail() == null ? "" : customer.getEmail());
    }

    // =====================================================================
    // UC-06 Search products, UC-07 Manage products
    // =====================================================================
    private static void searchProducts() {
        String text = input.readOptionalText("Texto a buscar (código, nombre, marca; Enter para todos): ");
        String category = input.readOption("Categoría:", Product.CATEGORIES, true);
        printProducts(productCatalog.search(text, category));
    }

    private static void manageProducts() {
        while (true) {
            System.out.println("\n--- Gestionar productos ---");
            System.out.println("1. Registrar producto");
            System.out.println("2. Actualizar producto");
            System.out.println("3. Desactivar producto");
            System.out.println("4. Listar productos activos");
            System.out.println("0. Volver");
            int option = input.readInt("Elija una opción: ");
            switch (option) {
                case 1:
                    runSafely(ProvStockApp::registerProduct);
                    break;
                case 2:
                    runSafely(ProvStockApp::updateProduct);
                    break;
                case 3:
                    runSafely(ProvStockApp::deactivateProduct);
                    break;
                case 4:
                    printProducts(productCatalog.getActiveProducts());
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void registerProduct() {
        String code = input.readText("Código: ").toUpperCase();
        if (productCatalog.findByCode(code) != null) {
            System.out.println("Ya existe un producto con el código " + code + ".");
            return;
        }
        String name = input.readText("Nombre: ");
        String description = input.readOptionalText("Descripción (opcional): ");
        String category = input.readOption("Categoría:", Product.CATEGORIES, false);
        String brand = input.readOptionalText("Marca (opcional): ");
        String unit = input.readOption("Unidad de medida:", Product.UNITS_OF_MEASURE, false);
        String location = input.readOptionalText("Ubicación en bodega (opcional): ");
        double purchaseCost = input.readNonNegativeDouble("Costo de compra: ");
        double salePrice = input.readPositiveDouble("Precio de venta: ");
        double minimumStock = input.readNonNegativeDouble("Stock mínimo: ");
        double initialStock = input.readNonNegativeDouble("Stock inicial: ");

        Product product = new Product(0, code, name, description, category, brand, unit, location,
                purchaseCost, salePrice, minimumStock);
        if (!productCatalog.registerProduct(product)) {
            System.out.println("No se pudo registrar el producto: revise los datos.");
            return;
        }
        //AS-05, FR-05.4: the initial stock is loaded with an adjustment
        if (initialStock > 0) {
            inventory.adjustStock(product, initialStock, "Inventario inicial", accessManager.getCurrentUser());
        }
        System.out.println("Producto registrado con id " + product.getId() + ".");
    }

    private static void updateProduct() {
        Product product = findProduct();
        if (product == null) {
            return;
        }
        System.out.println(formatProduct(product));
        System.out.println("Presione Enter para mantener el valor actual.");
        String name = input.readTextOrKeep("Nombre", product.getName());
        String description = input.readTextOrKeep("Descripción", product.getDescription());
        String category = product.getCategory();
        if (input.readYesNo("¿Cambiar la categoría (actual: " + category + ")?")) {
            category = input.readOption("Categoría:", Product.CATEGORIES, false);
        }
        String brand = input.readTextOrKeep("Marca", product.getBrand());
        String unit = product.getUnitOfMeasure();
        if (input.readYesNo("¿Cambiar la unidad (actual: " + unit + ")?")) {
            unit = input.readOption("Unidad de medida:", Product.UNITS_OF_MEASURE, false);
        }
        String location = input.readTextOrKeep("Ubicación", product.getLocation());
        double purchaseCost = input.readDouble("Costo de compra", product.getPurchaseCost());
        double salePrice = input.readDouble("Precio de venta", product.getSalePrice());
        double minimumStock = input.readDouble("Stock mínimo", product.getMinimumStock());

        product.update(name, description, category, brand, unit, location, purchaseCost, salePrice, minimumStock);
        productCatalog.updateProduct(product);
        System.out.println("Producto actualizado. (El stock solo cambia con ventas, compras o ajustes.)");
    }

    private static void deactivateProduct() {
        Product product = findProduct();
        if (product != null && input.readYesNo("¿Desactivar " + product.getName() + "?")) {
            productCatalog.deactivateProduct(product);
            System.out.println("Producto desactivado.");
        }
    }

    private static Product findProduct() {
        String code = input.readText("Código del producto: ");
        Product product = productCatalog.findByCode(code);
        if (product == null) {
            System.out.println("No existe el producto " + code + ".");
        }
        return product;
    }

    private static void printProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No se encontraron productos.");
            return;
        }
        System.out.printf("%-10s %-28s %-16s %10s %-6s %9s  %-10s%n", "Código", "Nombre", "Categoría", "Stock",
                "Unidad", "Precio", "Ubicación");
        for (Product product : products) {
            System.out.println(formatProduct(product));
        }
    }

    private static String formatProduct(Product product) {
        //UC-06: the list shows the location of the product in the warehouse
        return String.format("%-10s %-28s %-16s %10.2f %-6s %9.2f  %-10s%s", product.getCode(),
                cut(product.getName(), 28), cut(product.getCategory(), 16), product.getCurrentStock(),
                product.getUnitOfMeasure(), product.getSalePrice(), location(product),
                product.isLowStock() ? "  (stock bajo)" : "");
    }

    // =====================================================================
    // UC-08 Register sale (includes UC-06 and UC-11, extended by UC-05)
    // =====================================================================
    private static void registerSale() {
        Customer customer = chooseCustomerForSale();
        if (customer == null) {
            return;
        }
        Sale sale = salesManager.createSale(customer);
        System.out.println("Venta para: " + customer.getName() + " | IVA " + percent(sale.getVatRate()));

        while (true) {
            System.out.println("\n--- Nueva venta (" + sale.getDetails().size() + " producto(s), total $"
                    + String.format("%.2f", sale.getTotal()) + ") ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Quitar producto");
            System.out.println("3. Ver detalle");
            System.out.println("4. Confirmar venta");
            System.out.println("0. Cancelar venta");
            int option = input.readInt("Elija una opción: ");
            switch (option) {
                case 1:
                    runSafely(() -> addProductToSale(sale));
                    break;
                case 2:
                    runSafely(() -> {
                        Product product = findProduct();
                        if (product != null) {
                            sale.removeProduct(product);
                            System.out.println("Producto quitado.");
                        }
                    });
                    break;
                case 3:
                    printSale(sale);
                    break;
                case 4:
                    if (confirmSale(sale)) {
                        return;
                    }
                    break;
                case 0:
                    System.out.println("Venta cancelada.");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Enter = "Consumidor Final". If the customer does not exist, it can be
     * registered in the middle of the sale (UC-05 extends UC-08).
     */
    private static Customer chooseCustomerForSale() {
        String number = input.readOptionalText("Identificación del cliente (Enter = Consumidor Final): ");
        if (number.isEmpty()) {
            return customerManager.getFinalConsumer();
        }
        Customer customer = customerManager.findByIdentification(number);
        if (customer != null) {
            if (!customer.isActive()) {
                System.out.println("El cliente está inactivo.");
                return null;
            }
            return customer;
        }
        System.out.println("El cliente no está registrado.");
        if (input.readYesNo("¿Desea registrarlo ahora?")) {
            return registerCustomer();
        }
        if (input.readYesNo("¿Vender a Consumidor Final?")) {
            return customerManager.getFinalConsumer();
        }
        return null;
    }

    private static void addProductToSale(Sale sale) {
        //UC-08 includes UC-06: search the product
        String text = input.readOptionalText("Buscar producto (código o nombre, Enter para ver todos): ");
        List<Product> found = productCatalog.search(text, null);
        if (found.isEmpty()) {
            System.out.println("No se encontraron productos.");
            return;
        }
        printProducts(found);
        Product product = found.size() == 1 ? found.get(0) : findProduct();
        if (product == null) {
            return;
        }
        double quantity = input.readPositiveDouble("Cantidad (" + product.getUnitOfMeasure() + "): ");
        sale.addProduct(product, quantity);
        System.out.println("Agregado: " + product.getName() + " x " + quantity);
    }

    private static boolean confirmSale(Sale sale) {
        if (sale.getDetails().isEmpty()) {
            System.out.println("Agregue al menos un producto.");
            return false;
        }
        sale.setPaymentMethod(input.readOption("Forma de pago:", Sale.PAYMENT_METHODS, false));
        printSale(sale);
        if (!input.readYesNo("¿Confirmar la venta?")) {
            return false;
        }
        if (!salesManager.registerSale(sale)) {
            System.out.println("No se pudo registrar la venta: revise el stock y los datos.");
            return false;
        }
        System.out.println("Venta Nº " + sale.getNumber() + " registrada correctamente.");

        //UC-11 includes UC-17: warn if a product reached its minimum stock
        for (SaleDetail detail : sale.getDetails()) {
            if (stockMonitor.checkProduct(detail.getProduct())) {
                System.out.println("  Aviso: " + detail.getProduct().getName() + " quedó con stock bajo ("
                        + detail.getProduct().getCurrentStock() + ").");
            }
        }
        return true;
    }

    private static void printSale(Sale sale) {
        System.out.println("------------------------------------------------------------------");
        if (sale.getNumber() != 0) {
            System.out.println("Venta Nº " + sale.getNumber() + "   " + sale.getDateTime().format(DATE_TIME)
                    + "   Estado: " + sale.getStatus());
        }
        System.out.println("Cliente: " + sale.getCustomer().getName() + " (" + sale.getCustomer().getIdentification()
                + ")");
        System.out.println("Vendedor: " + sale.getRegisteredBy().getFullName() + "   Pago: " + sale.getPaymentMethod());
        System.out.printf("%-10s %-26s %10s %10s %10s%n", "Código", "Producto", "Cantidad", "P. unit.", "Subtotal");
        for (SaleDetail detail : sale.getDetails()) {
            System.out.printf("%-10s %-26s %10.2f %10.2f %10.2f%n", detail.getProduct().getCode(),
                    cut(detail.getProduct().getName(), 26), detail.getQuantity(), detail.getUnitPrice(),
                    detail.calculateSubtotal());
        }
        sale.calculateTotal();
        System.out.printf("%60s %10.2f%n", "Subtotal:", sale.getSubtotal());
        System.out.printf("%60s %10.2f%n", "IVA " + percent(sale.getVatRate()) + ":", sale.getVatAmount());
        System.out.printf("%60s %10.2f%n", "TOTAL:", sale.getTotal());
        if (sale.isVoided()) {
            System.out.println("ANULADA el " + sale.getVoidDateTime().format(DATE_TIME) + " por "
                    + sale.getVoidedBy().getUsername() + ". Motivo: " + sale.getVoidReason());
        }
        System.out.println("------------------------------------------------------------------");
    }

    // =====================================================================
    // UC-09 Void sale, UC-10 Update VAT rate, UC-16 View sales history
    // =====================================================================
    private static void voidSale() {
        Sale sale = salesManager.findSaleByNumber(input.readInt("Número de venta: "));
        if (sale == null) {
            System.out.println("No existe esa venta.");
            return;
        }
        printSale(sale);
        if (sale.isVoided()) {
            System.out.println("La venta ya está anulada.");
            return;
        }
        String reason = input.readText("Motivo de la anulación: ");
        if (input.readYesNo("¿Anular la venta Nº " + sale.getNumber() + "? Los productos volverán al stock")) {
            salesManager.voidSale(sale, reason);
            System.out.println("Venta anulada y stock restaurado.");
        }
    }

    private static void updateVatRate() {
        System.out.println("Tasa de IVA actual: " + percent(salesManager.getVatRate()));
        double newRate = input.readNonNegativeDouble("Nueva tasa en porcentaje (por ejemplo 15): ");
        if (input.readYesNo("¿Cambiar el IVA a " + newRate + "%? Solo afecta a las ventas nuevas")) {
            salesManager.updateVatRate(newRate / 100.0);
            System.out.println("Tasa de IVA actualizada a " + percent(salesManager.getVatRate()) + ".");
        }
    }

    private static void salesHistory() {
        System.out.println("Filtros (presione Enter para no usar un filtro):");
        LocalDate start = input.readOptionalDate("Desde");
        LocalDate end = input.readOptionalDate("Hasta");

        User salesperson = null;
        String username = input.readOptionalText("Usuario del vendedor (Enter para todos): ");
        if (!username.isEmpty()) {
            salesperson = accessManager.findUser(username);
            if (salesperson == null) {
                System.out.println("No existe el usuario " + username + ".");
                return;
            }
        }

        Customer customer = null;
        String identification = input.readOptionalText("Identificación del cliente (Enter para todos): ");
        if (!identification.isEmpty()) {
            customer = customerManager.findByIdentification(identification);
            if (customer == null) {
                System.out.println("No existe el cliente " + identification + ".");
                return;
            }
        }

        String status = input.readOption("Estado:", new String[]{Sale.REGISTERED, Sale.VOIDED}, true);

        List<Sale> sales = salesManager.filterSales(start, end, salesperson, customer, status);
        if (sales.isEmpty()) {
            System.out.println("No hay ventas con esos filtros.");
            return;
        }
        System.out.printf("%-6s %-17s %-26s %-12s %-11s %10s%n", "Nº", "Fecha", "Cliente", "Vendedor", "Estado",
                "Total");
        for (Sale sale : sales) {
            System.out.printf("%-6d %-17s %-26s %-12s %-11s %10.2f%n", sale.getNumber(),
                    sale.getDateTime().format(DATE_TIME), cut(sale.getCustomer().getName(), 26),
                    sale.getRegisteredBy().getUsername(), sale.getStatus(), sale.getTotal());
        }
        int number = input.readInt("Número de venta para ver el detalle (0 para volver): ");
        if (number != 0) {
            Sale sale = salesManager.findSaleByNumber(number);
            if (sale == null) {
                System.out.println("No existe esa venta.");
            } else {
                printSale(sale);
            }
        }
    }

    // =====================================================================
    // UC-12 View inventory, UC-13 Adjust stock, UC-18 Receive stock alerts
    // =====================================================================
    private static void viewInventory() {
        //UC-12 2a: the list can be filtered by name or category
        String text = input.readOptionalText("Filtrar por código o nombre (Enter para todos): ");
        String category = input.readOption("Categoría:", Product.CATEGORIES, true);
        if (text.isEmpty() && (category == null || category.isBlank())) {
            printProducts(inventory.getStockOverview());
        } else {
            printProducts(productCatalog.search(text, category));
        }
        String code = input.readOptionalText("Código para ver sus movimientos (Enter para volver): ");
        if (code.isEmpty()) {
            return;
        }
        Product product = productCatalog.findByCode(code);
        if (product == null) {
            System.out.println("No existe el producto " + code + ".");
            return;
        }
        List<StockMovement> movements = inventory.getMovementsByProduct(product);
        System.out.println("Movimientos de " + product.getName() + ":");
        System.out.printf("%-4s %-17s %-12s %10s %-12s %s%n", "Id", "Fecha", "Tipo", "Cantidad", "Usuario",
                "Referencia");
        for (StockMovement movement : movements) {
            String reference;
            if (movement.getSale() != null) {
                reference = "Venta Nº " + movement.getSale().getNumber();
            } else if (movement.getPurchase() != null) {
                reference = "Compra Nº " + movement.getPurchase().getNumber();
            } else {
                reference = movement.getReason();
            }
            System.out.printf("%-4d %-17s %-12s %+10.2f %-12s %s%n", movement.getId(),
                    movement.getDateTime().format(DATE_TIME), movement.getType(), movement.getQuantity(),
                    movement.getUser().getUsername(), reference);
        }
        System.out.println("Stock actual: " + product.getCurrentStock() + " " + product.getUnitOfMeasure());
    }

    private static void adjustStock() {
        Product product = findProduct();
        if (product == null) {
            return;
        }
        System.out.println("Stock actual de " + product.getName() + ": " + product.getCurrentStock());
        double quantity = input.readDouble("Cantidad a ajustar (positiva suma, negativa resta): ");
        String reason = input.readText("Motivo del ajuste: ");
        inventory.adjustStock(product, quantity, reason, accessManager.getCurrentUser());
        System.out.println("Stock ajustado. Nuevo stock: " + product.getCurrentStock());
        if (stockMonitor.checkProduct(product)) {
            System.out.println("Aviso: el producto quedó con stock bajo.");
        }
    }

    private static void stockAlerts() {
        List<Product> products = stockMonitor.getLowStockProducts();
        if (products.isEmpty()) {
            System.out.println("No hay alertas: todos los productos están sobre su stock mínimo.");
            return;
        }
        System.out.println("Productos con stock igual o menor al mínimo (" + stockMonitor.countAlerts() + "):");
        //UC-18: each alert shows the current stock, the minimum stock and the location
        System.out.printf("%-10s %-30s %10s %10s %-6s  %-10s%n", "Código", "Producto", "Stock", "Mínimo", "Unidad",
                "Ubicación");
        for (Product product : products) {
            System.out.printf("%-10s %-30s %10.2f %10.2f %-6s  %-10s%n", product.getCode(), cut(product.getName(), 30),
                    product.getCurrentStock(), product.getMinimumStock(), product.getUnitOfMeasure(),
                    location(product));
        }
    }

    // =====================================================================
    // UC-14 Manage suppliers, UC-15 Register purchase
    // =====================================================================
    private static void manageSuppliers() {
        while (true) {
            System.out.println("\n--- Gestionar proveedores ---");
            System.out.println("1. Registrar proveedor");
            System.out.println("2. Actualizar proveedor");
            System.out.println("3. Desactivar proveedor");
            System.out.println("4. Buscar proveedores");
            System.out.println("0. Volver");
            int option = input.readInt("Elija una opción: ");
            switch (option) {
                case 1:
                    runSafely(ProvStockApp::registerSupplier);
                    break;
                case 2:
                    runSafely(ProvStockApp::updateSupplier);
                    break;
                case 3:
                    runSafely(ProvStockApp::deactivateSupplier);
                    break;
                case 4:
                    printSuppliers(purchasingManager.searchSuppliers(
                            input.readOptionalText("Nombre o RUC (Enter para todos): ")));
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void registerSupplier() {
        String ruc = input.readText("RUC: ");
        String name = input.readText("Nombre o razón social: ");
        String contact = input.readOptionalText("Persona de contacto (opcional): ");
        String phone = input.readOptionalText("Teléfono (opcional): ");
        String email = input.readOptionalText("Correo (opcional): ");
        String address = input.readOptionalText("Dirección (opcional): ");

        Supplier supplier = new Supplier(0, ruc, name, contact, phone, email, address);
        if (!supplier.validateRuc()) {
            System.out.println("El RUC no es válido.");
        } else if (!supplier.validate()) {
            System.out.println("Datos no válidos: revise el teléfono y el correo.");
        } else if (!purchasingManager.registerSupplier(supplier)) {
            System.out.println("Ya existe un proveedor con el RUC " + ruc + ".");
        } else {
            System.out.println("Proveedor registrado con id " + supplier.getId() + ".");
        }
    }

    private static void updateSupplier() {
        Supplier supplier = findSupplier();
        if (supplier == null) {
            return;
        }
        String name = input.readTextOrKeep("Nombre", supplier.getName());
        supplier.setContactPerson(input.readTextOrKeep("Persona de contacto", supplier.getContactPerson()));
        String phone = input.readTextOrKeep("Teléfono", supplier.getPhone());
        String email = input.readTextOrKeep("Correo", supplier.getEmail());
        String address = input.readTextOrKeep("Dirección", supplier.getAddress());
        supplier.update(name, phone, email, address);
        purchasingManager.updateSupplier(supplier);
        System.out.println("Proveedor actualizado.");
    }

    private static void deactivateSupplier() {
        Supplier supplier = findSupplier();
        if (supplier != null && input.readYesNo("¿Desactivar a " + supplier.getName() + "?")) {
            purchasingManager.deactivateSupplier(supplier);
            System.out.println("Proveedor desactivado.");
        }
    }

    private static Supplier findSupplier() {
        String ruc = input.readText("RUC del proveedor: ");
        Supplier supplier = purchasingManager.findByRuc(ruc);
        if (supplier == null) {
            System.out.println("No existe el proveedor con RUC " + ruc + ".");
        }
        return supplier;
    }

    private static void printSuppliers(List<Supplier> suppliers) {
        if (suppliers.isEmpty()) {
            System.out.println("No se encontraron proveedores.");
            return;
        }
        for (Supplier supplier : suppliers) {
            System.out.printf("  [%d] RUC %-14s %-30s %s%n", supplier.getId(), supplier.getRuc(),
                    supplier.getName(), supplier.getContactPerson() == null ? "" : supplier.getContactPerson());
        }
    }

    private static void registerPurchase() {
        printSuppliers(purchasingManager.searchSuppliers(""));
        Supplier supplier = findSupplier();
        if (supplier == null) {
            return;
        }
        if (!supplier.isActive()) {
            System.out.println("El proveedor está inactivo.");
            return;
        }
        //UC-15: the supplier invoice number is optional
        String invoice = input.readOptionalText("Número de factura del proveedor (opcional): ");
        Purchase purchase = new Purchase(supplier, accessManager.getCurrentUser(), invoice);

        while (true) {
            System.out.println("\n--- Nueva compra a " + supplier.getName() + " (" + purchase.getDetails().size()
                    + " producto(s), total $" + String.format("%.2f", purchase.getTotal()) + ") ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Quitar producto");
            System.out.println("3. Ver detalle");
            System.out.println("4. Confirmar compra");
            System.out.println("0. Cancelar compra");
            int option = input.readInt("Elija una opción: ");
            switch (option) {
                case 1:
                    runSafely(() -> {
                        //UC-15 includes UC-06: search the product
                        String text = input.readOptionalText("Buscar producto (Enter para ver todos): ");
                        List<Product> found = productCatalog.search(text, null);
                        printProducts(found);
                        if (found.isEmpty()) {
                            return;
                        }
                        Product product = found.size() == 1 ? found.get(0) : findProduct();
                        if (product != null) {
                            double quantity = input.readPositiveDouble("Cantidad (" + product.getUnitOfMeasure() + "): ");
                            double unitCost = input.readDouble("Costo unitario", product.getPurchaseCost());
                            purchase.addProduct(product, quantity, unitCost);
                            System.out.println("Agregado: " + product.getName() + " x " + quantity);
                        }
                    });
                    break;
                case 2:
                    runSafely(() -> {
                        Product product = findProduct();
                        if (product != null) {
                            purchase.removeProduct(product);
                            System.out.println("Producto quitado.");
                        }
                    });
                    break;
                case 3:
                    printPurchase(purchase);
                    break;
                case 4:
                    if (purchase.getDetails().isEmpty()) {
                        System.out.println("Agregue al menos un producto.");
                        break;
                    }
                    printPurchase(purchase);
                    if (input.readYesNo("¿Confirmar la compra?")) {
                        if (purchasingManager.registerPurchase(purchase)) {
                            System.out.println("Compra Nº " + purchase.getNumber()
                                    + " registrada. El stock fue actualizado.");
                            return;
                        }
                        System.out.println("No se pudo registrar la compra: revise los datos.");
                    }
                    break;
                case 0:
                    System.out.println("Compra cancelada.");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void printPurchase(Purchase purchase) {
        System.out.println("------------------------------------------------------------------");
        String invoice = purchase.getSupplierInvoiceNumber();
        System.out.println("Proveedor: " + purchase.getSupplier().getName() + "   Factura: "
                + (invoice.isEmpty() ? "(sin factura)" : invoice));
        System.out.printf("%-10s %-26s %10s %10s %10s%n", "Código", "Producto", "Cantidad", "C. unit.", "Subtotal");
        for (PurchaseDetail detail : purchase.getDetails()) {
            System.out.printf("%-10s %-26s %10.2f %10.2f %10.2f%n", detail.getProduct().getCode(),
                    cut(detail.getProduct().getName(), 26), detail.getQuantity(), detail.getUnitCost(),
                    detail.calculateSubtotal());
        }
        System.out.printf("%60s %10.2f%n", "TOTAL:", purchase.calculateTotal());
        System.out.println("------------------------------------------------------------------");
    }

    // =====================================================================
    // UC-19 Generate reports, UC-20 Export report to PDF
    // =====================================================================
    private static void generateReports() {
        while (true) {
            System.out.println("\n--- Generar reportes ---");
            System.out.println("1. Reporte de ventas");
            System.out.println("2. Reporte de inventario");
            System.out.println("3. Reporte de compras por proveedor");
            System.out.println("4. Reporte de productos sin movimiento");
            System.out.println("0. Volver");
            int option = input.readInt("Elija una opción: ");
            switch (option) {
                case 1:
                    runSafely(() -> {
                        SalesReport report = reportGenerator.generateSalesReport(
                                input.readDate("Desde"), input.readDate("Hasta"));
                        System.out.println(report);
                        exportPdf("reporte_ventas", report::exportToPdf);
                    });
                    break;
                case 2:
                    runSafely(() -> {
                        InventoryReport report = reportGenerator.generateInventoryReport();
                        System.out.println(report);
                        exportPdf("reporte_inventario", report::exportToPdf);
                    });
                    break;
                case 3:
                    runSafely(() -> {
                        PurchaseReport report = reportGenerator.generatePurchaseReport(
                                input.readDate("Desde"), input.readDate("Hasta"));
                        System.out.println(report);
                        exportPdf("reporte_compras", report::exportToPdf);
                    });
                    break;
                case 4:
                    runSafely(() -> {
                        SlowMovingProductsReport report = reportGenerator.generateSlowMovingReport(
                                input.readDate("Desde"), input.readDate("Hasta"));
                        System.out.println(report);
                        exportPdf("reporte_sin_movimiento", report::exportToPdf);
                    });
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * UC-20 extends UC-19: the report can be exported after it is shown on the screen.
     */
    private static void exportPdf(String baseName, Consumer<String> exporter) {
        if (!input.readYesNo("¿Exportar este reporte a PDF?")) {
            return;
        }
        String fileName = input.readOptionalText("Nombre del archivo (Enter = " + baseName + ".pdf): ");
        if (fileName.isEmpty()) {
            fileName = baseName + ".pdf";
        }
        if (!fileName.toLowerCase().endsWith(".pdf")) {
            fileName = fileName + ".pdf";
        }
        exporter.accept(fileName);
        System.out.println("PDF guardado en: " + new File(fileName).getAbsolutePath());
    }

    // =====================================================================
    // Helpers
    // =====================================================================
    private static String percent(double rate) {
        return String.format("%.0f%%", rate * 100);
    }

    /**
     * The location is optional, so "-" is shown when the product has none.
     */
    private static String location(Product product) {
        String location = product.getLocation();
        return location == null || location.isBlank() ? "-" : cut(location, 10);
    }

    private static String cut(String text, int length) {
        if (text == null) {
            return "";
        }
        return text.length() <= length ? text : text.substring(0, length - 1) + ".";
    }

    /**
     * Sample data so the system can be tested from the first run. It is loaded using
     * the same methods of the control classes, so all the rules are checked.
     */
    private static void loadSampleData() {
        accessManager.login(AccessManager.DEFAULT_OWNER_USERNAME, AccessManager.DEFAULT_OWNER_PASSWORD);
        User owner = accessManager.getCurrentUser();

        accessManager.createUser("vendedor", "María López", accessManager.findRole(Role.SALESPERSON),
                "Vendedor2026");
        accessManager.createUser("bodeguero", "Pedro Ramírez", accessManager.findRole(Role.WAREHOUSE_CLERK),
                "Bodega2026");

        addSampleProduct("CAB-001", "Cable THHN 12 AWG", "Cable de cobre sólido", "CABLES", "Electrocables",
                "METER", "Pasillo A1", 0.45, 0.70, 100, 500, owner);
        addSampleProduct("CAB-002", "Cable gemelo 2x14 AWG", "Cable flexible doble", "CABLES", "Electrocables",
                "METER", "Pasillo A2", 0.35, 0.55, 100, 80, owner);
        addSampleProduct("ILU-001", "Foco LED 9W luz blanca", "Rosca E27", "LIGHTING", "Sylvania",
                "UNIT", "Pasillo B1", 1.20, 2.50, 20, 60, owner);
        addSampleProduct("INT-001", "Interruptor simple", "Blanco, empotrable", "SWITCHES_AND_OUTLETS", "Veto",
                "UNIT", "Pasillo C1", 1.10, 2.00, 15, 40, owner);
        addSampleProduct("PRO-001", "Breaker 1 polo 20A", "Riel DIN", "PROTECTION_DEVICES", "Schneider",
                "UNIT", "Pasillo D1", 4.50, 7.80, 10, 8, owner);
        addSampleProduct("HER-001", "Alicate universal 8 pulgadas", "Mango aislado", "TOOLS", "Stanley",
                "UNIT", "Pasillo E1", 6.00, 10.50, 5, 12, owner);

        customerManager.registerCustomer(new Customer(0, Customer.CEDULA, "1712345675", "Juan Pérez",
                "0991234567", "juan.perez@mail.com", "Quito, Av. Amazonas"));
        customerManager.registerCustomer(new Customer(0, Customer.RUC, "1701234567001", "Instalaciones Andes",
                "022345678", "ventas@andes.com", "Sangolquí"));

        purchasingManager.registerSupplier(new Supplier(0, "1790012345001", "Electrocables S.A.",
                "Ana Torres", "022987654", "ventas@electrocables.com", "Quito"));
        purchasingManager.registerSupplier(new Supplier(0, "0992345678001", "Distribuidora Eléctrica del Pacífico",
                "Luis Mora", "042123456", "pedidos@depacifico.com", "Guayaquil"));

        accessManager.logout();
    }

    private static void addSampleProduct(String code, String name, String description, String category,
            String brand, String unit, String location, double cost, double price, double minimum,
            double initialStock, User owner) {
        Product product = new Product(0, code, name, description, category, brand, unit, location,
                cost, price, minimum);
        productCatalog.registerProduct(product);
        inventory.adjustStock(product, initialStock, "Inventario inicial", owner);
    }
}
