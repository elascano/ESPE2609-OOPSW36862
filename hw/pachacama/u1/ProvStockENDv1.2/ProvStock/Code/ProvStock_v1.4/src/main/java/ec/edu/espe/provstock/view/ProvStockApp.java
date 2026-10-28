package ec.edu.espe.provstock.view;

import ec.edu.espe.provstock.controller.AccessManager;
import ec.edu.espe.provstock.controller.StockMonitor;
import ec.edu.espe.provstock.model.Role;
import ec.edu.espe.provstock.model.User;
import ec.edu.espe.provstock.persistence.FileManager;

/**
 * Console application of ProvStock: Prov&Tec sales and inventory system.
 * It loads the data, logs the user in (UC-01) and opens the main menu.
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class ProvStockApp {

    private final AppContext context = new AppContext();
    private final ConsoleHelper console = new ConsoleHelper(context);
    private final MainMenu mainMenu = new MainMenu(context, console);

    public static void main(String[] args) {
        ProvStockApp app = new ProvStockApp();
        if (app.loadData()) {
            app.printWelcome();
            app.run();
        }
    }

    public ProvStockApp() {
        addMainMenuOptions();
    }

    /**
     * The order of the options is the order in which they appear in the main menu.
     */
    private void addMainMenuOptions() {
        ProductMenu products = new ProductMenu(context, console);
        CustomerMenu customers = new CustomerMenu(context, console);
        SalesMenu sales = new SalesMenu(context, console, customers);
        InventoryMenu inventory = new InventoryMenu(context, console);
        PurchaseMenu purchases = new PurchaseMenu(context, console);
        UserMenu users = new UserMenu(context, console);

        mainMenu.addOption(Role.VIEW_PRODUCTS, "Buscar productos", products::searchProducts);
        mainMenu.addOption(Role.MANAGE_CUSTOMERS, "Gestionar clientes", customers::manageCustomers);
        mainMenu.addOption(Role.REGISTER_SALE, "Registrar venta", sales::registerSale);
        mainMenu.addOption(Role.VOID_SALE, "Anular venta", sales::voidSale);
        mainMenu.addOption(Role.VIEW_SALES_HISTORY, "Historial de ventas", sales::salesHistory);
        mainMenu.addOption(Role.UPDATE_VAT_RATE, "Actualizar tasa de IVA", sales::updateVatRate);
        mainMenu.addOption(Role.MANAGE_PRODUCTS, "Gestionar productos", products::manageProducts);
        mainMenu.addOption(Role.VIEW_INVENTORY, "Consultar inventario", inventory::viewInventory);
        mainMenu.addOption(Role.ADJUST_STOCK, "Ajustar stock", inventory::adjustStock);
        mainMenu.addOption(Role.MANAGE_SUPPLIERS, "Gestionar proveedores", purchases::manageSuppliers);
        mainMenu.addOption(Role.REGISTER_PURCHASE, "Registrar compra", purchases::registerPurchase);
        mainMenu.addOption(Role.RECEIVE_ALERTS, "Alertas de stock", inventory::stockAlerts);
        mainMenu.addOption(Role.GENERATE_REPORTS, "Generar reportes", new ReportMenu(context, console)::generateReports);
        mainMenu.addOption(Role.MANAGE_USERS, "Gestionar usuarios", users::manageUsers);
        mainMenu.addOption(null, "Cambiar mi contraseña", users::changePassword);
    }

    /**
     * Loads the JSON files. On the first run the sample data is created and saved.
     *
     * @return false if the files have an error (they are not overwritten, so no data is lost)
     */
    private boolean loadData() {
        FileManager fileManager = context.getFileManager();
        if (!fileManager.hasSavedData()) {
            new SampleData(context).load();
            fileManager.saveAll();
            System.out.println("Primera ejecución: se crearon los datos de prueba en la carpeta \""
                    + FileManager.DATA_FOLDER + "\".");
            return true;
        }
        try {
            fileManager.loadAll();
            System.out.println("Datos cargados desde la carpeta \"" + FileManager.DATA_FOLDER + "\".");
            return true;
        } catch (RuntimeException e) {
            System.out.println("Error al leer los datos: " + e.getMessage());
            System.out.println("Revise los archivos de la carpeta \"" + FileManager.DATA_FOLDER + "\".");
            return false;
        }
    }

    private void printWelcome() {
        System.out.println("==============================================");
        System.out.println("   ProvStock - Prov&Tec Materiales Eléctricos");
        System.out.println("   ByteWorks, ESPE");
        System.out.println("==============================================");
        System.out.println("Usuarios de prueba (si no se cambiaron sus contraseñas):");
        System.out.println("  owner     / Owner2026     (Propietario)");
        System.out.println("  vendedor  / Vendedor2026  (Vendedor)");
        System.out.println("  bodeguero / Bodega2026    (Bodeguero)");
    }

    private void run() {
        int option;
        do {
            System.out.println("\n----------- INICIO -----------");
            System.out.println("1. Iniciar sesión");
            System.out.println("0. Salir");
            option = context.getInput().readInt("Elija una opción: ");
            switch (option) {
                case 1 -> {
                    if (login()) {
                        mainMenu.show();
                    }
                }
                case 0 -> System.out.println("¡Hasta pronto!");
                default -> console.showInvalidOption();
            }
        } while (option != 0);
    }

    // =====================================================================
    // UC-01 Log in
    // =====================================================================
    private boolean login() {
        String username = context.getInput().readText("Usuario: ");
        String password = context.getInput().readText("Contraseña: ");
        AccessManager accessManager = context.getAccessManager();
        if (!accessManager.login(username, password)) {
            System.out.println("Usuario o contraseña incorrectos, o la cuenta está inactiva.");
            return false;
        }
        User user = accessManager.getCurrentUser();
        System.out.println("\nBienvenido/a, " + user.getFullName() + " (" + user.getRole().getName() + ")");
        warnLowStockAtLogin();
        return true;
    }

    /**
     * FR-08.1: the minimum stock is checked when a user that receives alerts logs in.
     */
    private void warnLowStockAtLogin() {
        StockMonitor stockMonitor = context.getStockMonitor();
        if (context.getAccessManager().checkPermission(Role.RECEIVE_ALERTS) && stockMonitor.countAlerts() > 0) {
            System.out.println("¡ATENCIÓN! Hay " + stockMonitor.countAlerts() + " producto(s) con stock bajo. "
                    + "Revise la opción \"Alertas de stock\".");
        }
    }
}
