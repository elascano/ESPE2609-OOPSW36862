package ec.edu.espe.unistock.view;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.model.Role;
import ec.edu.espe.unistock.model.Sale;
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
            System.out.println("5. Ver / Exportar ventas realizadas");
        }
        if (role.canManageUsers()) {
            System.out.println("6. Gestionar usuarios / Exportar reporte");
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
            case 4 ->
                runIfAllowed(role.canRegisterSales(), () -> registerSaleMenu(user));
            case 5 ->
                runIfAllowed(role.canRegisterSales(), this::viewSalesMenu);
            case 6 ->
                runIfAllowed(role.canManageUsers(), this::manageUsersMenu);
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

    private void registerSaleMenu(User seller) {
        System.out.println("\n--- REGISTRAR VENTA ---");
        Sale currentSale = saleService.startSale(seller);
        boolean adding = true;

        while (adding) {
            int productId = console.readInt("ID del producto a vender (0 para finalizar agregados): ");
            if (productId == 0) {
                adding = false;
                break;
            }
            int quantity = console.readInt("Cantidad: ");

            try {
                saleService.addItem(currentSale, productId, quantity);
                System.out.println("Producto agregado a la venta.");
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        if (currentSale.isEmpty()) {
            System.out.println("Venta cancelada (sin items).");
            return;
        }

        System.out.println("\nSubtotal: $" + currentSale.subtotal());
        System.out.println("IVA (15%): $" + currentSale.tax());
        System.out.println("Total: $" + currentSale.total());

        String confirm = console.readText("¿Confirmar venta? (S/N): ");
        if (confirm.equalsIgnoreCase("S")) {
            try {
                saleService.confirmSale(currentSale);
                System.out.println("Venta #" + currentSale.getId() + " registrada con exito.");
            } catch (IllegalStateException e) {
                System.out.println("Error al procesar la venta: " + e.getMessage());
            }
        } else {
            System.out.println("Venta cancelada.");
        }
    }

    private void viewSalesMenu() {
        System.out.println("\n--- CONSULTA DE VENTAS ---");
        System.out.println("1. Listar historial de ventas");
        System.out.println("2. Exportar reporte de ventas a CSV");
        int option = console.readInt("Opcion: ");

        switch (option) {
            case 1 -> {
                var sales = saleService.getSales();
                if (sales.isEmpty()) {
                    System.out.println("No hay ventas registradas.");
                    return;
                }
                sales.resetIteration();
                while (sales.hasNext()) {
                    var s = sales.next();
                    System.out.println("Venta #" + s.getId() + " | Fecha: " + s.getDate() + " | Vendedor: " + s.getSeller().getUsername() + " | Total: $" + s.total());
                }
            }
            case 2 -> {
                String fileName = console.readText("Nombre del archivo CSV (ej: ventas.csv): ");
                if (fileName.isEmpty()) {
                    fileName = "ventas.csv";
                }
                saleService.exportReportCSV(fileName);
                System.out.println("Proceso de exportacion de ventas finalizado.");
            }
            default -> System.out.println("Opcion invalida.");
        }
    }

    private void manageUsersMenu() {
        System.out.println("\n--- GESTION DE USUARIOS ---");
        System.out.println("1. Registrar nuevo usuario");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Exportar reporte de usuarios a CSV");
        int option = console.readInt("Opcion: ");

        switch (option) {
            case 1 -> {
                String username = console.readText("Nombre de usuario: ");
                String password = console.readText("Contrasena: ");
                System.out.println("1. ADMINISTRATOR  2. SALESPERSON  3. CUSTOMER");
                int roleOption = console.readInt("Rol: ");
                Role role = switch (roleOption) {
                    case 1 -> Role.ADMINISTRATOR;
                    case 2 -> Role.SALESPERSON;
                    default -> Role.CUSTOMER;
                };

                try {
                    userService.registerUser(username, password, role);
                    System.out.println("Usuario registrado exitosamente.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error al registrar usuario: " + e.getMessage());
                }
            }
            case 2 -> {
                var users = userService.getUsers();
                if (users.isEmpty()) {
                    System.out.println("No hay usuarios registrados.");
                    return;
                }
                users.resetIteration();
                while (users.hasNext()) {
                    System.out.println(" - " + users.next());
                }
            }
            case 3 -> {
                String fileName = console.readText("Nombre del archivo CSV (ej: usuarios.csv): ");
                if (fileName.isEmpty()) {
                    fileName = "usuarios.csv";
                }
                userService.exportReportCSV(fileName);
                System.out.println("Proceso de exportacion de usuarios finalizado.");
            }
            default -> System.out.println("Opcion invalida.");
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