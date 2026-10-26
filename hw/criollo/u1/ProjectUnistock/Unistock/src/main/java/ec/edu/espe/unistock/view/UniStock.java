package ec.edu.espe.unistock.view;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.model.Role;
import ec.edu.espe.unistock.model.User;
import ec.edu.espe.unistock.service.ProductService;
import ec.edu.espe.unistock.service.SaleService;
import ec.edu.espe.unistock.service.StationeryService;
import ec.edu.espe.unistock.service.StockAlertService;
import ec.edu.espe.unistock.service.UniformService;
import ec.edu.espe.unistock.service.UserService;

public class UniStock {

    private final ConsoleAdapter console = new ConsoleAdapter();
    private final UniformService uniformService = new UniformService();
    private final StationeryService stationeryService = new StationeryService();
    private final StockAlertService stockAlertService = new StockAlertService();

    private final ProductService productService = new ProductService(uniformService, stationeryService);
    private final SaleService saleService = new SaleService(productService);
    private final UserService userService = new UserService();

    private final ProductMenuView productMenuView = new ProductMenuView(productService, console);
    private final CatalogMenuView catalogMenuView = new CatalogMenuView(uniformService, stationeryService, console);

    public static void main(String[] args) {
        new UniStock().run();
    }

    private void run() {
        System.out.println("===== UniStock by Byte & Fabric Devs =====");
        boolean running = true;
        while (running) {
            User user = login();
            if (user == null) {
                running = false;
            } else {
                openSession(user);
            }
        }
        System.out.println("Hasta pronto.");
    }

    private User login() {
        System.out.println("\n--- Iniciar sesion (usuario vacio para salir) ---");
        String username = console.readText("Usuario: ");
        if (username.isBlank()) {
            return null;
        }
        String password = console.readText("Contrasena: ");
        User user = userService.authenticate(username, password);
        if (user == null) {
            System.out.println("Credenciales incorrectas.");
            return login();
        }
        return user;
    }

    private void openSession(User user) {
        if (user.getRole() == Role.ADMINISTRATOR) {
            checkLowStockAlerts();
        }
        boolean active = true;
        while (active) {
            printMainMenu(user);
            int option = console.readInt("Opcion: ");
            active = handleOption(user, option);
        }
    }

    private void printMainMenu(User user) {
        Role role = user.getRole();
        System.out.println("\n===== MENU PRINCIPAL - " + user + " =====");
        System.out.println("1. Ver / buscar productos");
        if (role.canManageProducts()) {
            System.out.println("2. Registrar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("7. Gestionar catalogos");
            System.out.println("8. Consultar alertas de stock bajo");
            System.out.println("9. Actualizar inventario (Reabastecer)");
        }
        if (role.canRegisterSales()) {
            System.out.println("4. Registrar venta");
            System.out.println("5. Ver ventas realizadas");
        }
        if (role.canManageUsers()) {
            System.out.println("6. Gestionar usuarios");
        }
        System.out.println("0. Cerrar sesion");
    }

    private boolean handleOption(User user, int option) {
        Role role = user.getRole();
        switch (option) {
            case 1 ->
                productMenuView.listProducts();
            case 2 ->
                runIfAllowed(role.canManageProducts(), productMenuView::registerProduct);
            case 3 ->
                runIfAllowed(role.canManageProducts(), productMenuView::removeProduct);
            case 7 ->
                runIfAllowed(role.canManageProducts(), catalogMenuView::manageCatalogs);
            case 8 ->
                runIfAllowed(role.canManageProducts(), this::checkLowStockAlerts);
            case 9 ->
                runIfAllowed(role.canManageProducts(), productMenuView::updateStockManual);
            case 0 -> {
                return false;
            }
            default ->
                System.out.println("Opcion invalida.");
        }
        return true;
    }

    private void runIfAllowed(boolean allowed, Runnable action) {
        if (!allowed) {
            System.out.println("Acceso denegado para su rol.");
            return;
        }
        try {
            action.run();
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void checkLowStockAlerts() {
        var lowStockProducts = stockAlertService.getLowStockProducts(productService.getProducts());
        if (!lowStockProducts.isEmpty()) {
            System.out.println("\n--- ALERTA DE STOCK BAJO ---");
            lowStockProducts.resetIteration();
            while (lowStockProducts.hasNext()) {
                var p = lowStockProducts.next();
                System.out.println("Producto: " + p.getName() + " (ID: " + p.getId() + ") - Stock: " + p.getStock());
            }
        }
    }
}
