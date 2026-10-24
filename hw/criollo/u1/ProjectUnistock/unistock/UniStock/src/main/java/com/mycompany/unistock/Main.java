package com.mycompany.unistock;

import com.mycompany.unistock.model.Product;
import com.mycompany.unistock.model.Role;
import com.mycompany.unistock.model.Sale;
import com.mycompany.unistock.model.SaleItem;
import com.mycompany.unistock.model.User;
import com.mycompany.unistock.service.ProductService;
import com.mycompany.unistock.service.SaleService;
import com.mycompany.unistock.service.UserService;
import com.mycompany.unistock.structure.SimpleList;
import com.mycompany.unistock.service.StationeryService;
import com.mycompany.unistock.service.StockAlertService;
import com.mycompany.unistock.service.UniformService;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    private final Scanner input = new Scanner(System.in);
    private final UniformService uniformService = new UniformService();
    private final StationeryService stationeryService = new StationeryService();
    private final StockAlertService stockAlertService = new StockAlertService();
    
    private final ProductService productService = new ProductService(uniformService, stationeryService);
    private final SaleService saleService = new SaleService(productService);
    private final UserService userService = new UserService();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println("===== UniStock - Byte y Fabric Devs =====");
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

    // ---------- Authentication and main menu ----------

    private User login() {
        System.out.println("\n--- Iniciar sesion (usuario vacio para salir) ---");
        String username = readText("Usuario: ");
        if (username.isBlank()) {
            return null;
        }
        String password = readText("Contrasena: ");
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
            int option = readInt("Opcion: ");
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
            System.out.println("7. Gestionar catalogos (Tallas, Colegios, Marcas, Tipos de Articulo)");
            System.out.println("8. Consultar alertas de stock bajo");
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
            case 1 -> listProducts();
            case 2 -> runIfAllowed(role.canManageProducts(), this::registerProduct);
            case 3 -> runIfAllowed(role.canManageProducts(), this::removeProduct);
            case 4 -> runIfAllowed(role.canRegisterSales(), () -> registerSale(user));
            case 5 -> runIfAllowed(role.canRegisterSales(), this::listSales);
            case 6 -> runIfAllowed(role.canManageUsers(), this::manageUsers);
            case 7 -> runIfAllowed(role.canManageProducts(), this::manageCatalogs);
            case 8 -> runIfAllowed(role.canManageProducts(), this::checkLowStockAlerts);
            case 0 -> {
                return false;
            }
            default -> System.out.println("Opcion invalida.");
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

    // ---------- US001: product registration ----------

    private void registerProduct() {
        System.out.println("1. Uniforme  2. Papeleria / Util escolar");
        int type = readInt("Categoria: ");
        String name = readText("Nombre: ");
        BigDecimal price = readMoney("Precio: ");
        int stock = readInt("Stock inicial: ");

        Product product;
        if (type == 1) {
            String size = readText("Talla: ");
            String school = readText("Colegio: ");
            product = productService.registerUniform(name, price, stock, size, school);
        } else if (type == 2) {
            String brand = readText("Marca del producto: ");
            String stationeryType = readText("Tipo de articulo / material: ");
            product = productService.registerStationery(name, price, stock, brand, stationeryType);
        } else {
            System.out.println("Categoria invalida.");
            return;
        }

        if (product != null) {
            System.out.println("Producto registrado: " + product);
        } else {
            System.out.println("No se pudo registrar el producto. Verifique los catalogos en la opcion 7.");
        }
    }

    private void removeProduct() {
        int id = readInt("ID del producto a eliminar: ");
        boolean removed = productService.removeProduct(id);
        System.out.println(removed ? "Producto eliminado." : "No se encontro el producto.");
    }

    private void listProducts() {
        SimpleList<Product> products = productService.getProducts();
        if (products.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        products.resetIteration();
        while (products.hasNext()) {
            System.out.println(products.next());
        }
    }

    // ---------- US003 & US004: catalog management ----------

    private void manageCatalogs() {
        boolean inCatalogsMenu = true;
        while (inCatalogsMenu) {
        System.out.println("\n--- GESTION DE CATALOGOS DEL SISTEMA ---");
        System.out.println("1. Registrar Talla de Uniforme");
        System.out.println("2. Registrar Institucion");
        System.out.println("3. Registrar Marca del Producto del Util escolar");
        System.out.println("4. Registrar Tipo de Articulo o Material");
        System.out.println("5. Listar Catalogos Registrados");
        System.out.println("0. Volver al menu principal");
        int option = readInt("Opcion: ");

        switch (option) {
            case 1 -> {
                String size = readText("Ingrese nueva talla ej: S, M, L, XL: ");
                uniformService.registerSize(size);
                System.out.println("Talla registrada en el catalogo.");
            }
            case 2 -> {
                String school = readText("Ingrese nuevo nombre de la institucion: ");
                uniformService.registerSchool(school);
                System.out.println("Colegio registrado en el catalogo.");
            }
            case 3 -> {
                String brand = readText("Ingrese marca de utiles (ej: Norma, Faber-Castell, BIC): ");
                stationeryService.registerBrand(brand);
                System.out.println("Marca registrada en el catalogo.");
            }
            case 4 -> {
                String type = readText("Ingrese tipo de articulo o material (ej: Cuaderno, Lapiz, Borrador): ");
                stationeryService.registerType(type);
                System.out.println("Tipo de articulo registrado en el catalogo.");
            }
            case 5 -> listCatalogs();
            case 0 -> inCatalogsMenu = false;
            default -> System.out.println("Opcion invalida.");
        }
       }
    }

    private void listCatalogs() {
        System.out.println("\n--- CATALOGOS ACTUALMENTE REGISTRADOS ---");
        System.out.println("Tallas de Uniformes:");
        SimpleList<String> sizes = uniformService.getRegisteredSizes();
        sizes.resetIteration();
        while (sizes.hasNext()) System.out.println("  - " + sizes.next());

        System.out.println("Colegios / Instituciones:");
        SimpleList<String> schools = uniformService.getRegisteredSchools();
        schools.resetIteration();
        while (schools.hasNext()) System.out.println("  - " + schools.next());

        System.out.println("Marcas de Utiles Escolars:");
        SimpleList<String> brands = stationeryService.getRegisteredBrands();
        brands.resetIteration();
        while (brands.hasNext()) System.out.println("  - " + brands.next());

        System.out.println("Tipos de Articulos o Materiales:");
        SimpleList<String> types = stationeryService.getRegisteredTypes();
        types.resetIteration();
        while (types.hasNext()) System.out.println("  - " + types.next());
    }

    // ---------- US005: sales module ----------

    private void registerSale(User seller) {
        Sale sale = saleService.startSale(seller);
        boolean adding = true;
        while (adding) {
            listProducts();
            int productId = readInt("ID del producto (0 para terminar): ");
            if (productId == 0) {
                adding = false;
            } else {
                addItemSafely(sale, productId);
            }
        }
        if (sale.isEmpty()) {
            System.out.println("Venta cancelada: no se agregaron productos.");
            return;
        }
        saleService.confirmSale(sale);
        printReceipt(sale);
    }

    private void addItemSafely(Sale sale, int productId) {
        try {
            int quantity = readInt("Cantidad: ");
            saleService.addItem(sale, productId, quantity);
            System.out.println("Producto agregado.");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void printReceipt(Sale sale) {
        System.out.println("\n----- COMPROBANTE DE VENTA #" + sale.getId() + " -----");
        System.out.println("Fecha: " + sale.getDate().withNano(0));
        System.out.println("Vendedor: " + sale.getSeller().getUsername());
        SimpleList<SaleItem> items = sale.getItems();
        items.resetIteration();
        while (items.hasNext()) {
            SaleItem item = items.next();
            System.out.printf("%-25s x%-3d $%8.2f%n",
                    item.getProduct().getName(), item.getQuantity(), item.subtotal());
        }
        System.out.printf("Subtotal: $%.2f%n", sale.subtotal());
        System.out.printf("IVA (15%%): $%.2f%n", sale.tax());
        System.out.printf("TOTAL:    $%.2f%n", sale.total());
    }

    private void listSales() {
        SimpleList<Sale> sales = saleService.getSales();
        if (sales.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        sales.resetIteration();
        while (sales.hasNext()) {
            Sale sale = sales.next();
            System.out.printf("Venta #%d | %s | Total: $%.2f%n",
                    sale.getId(), sale.getSeller().getUsername(), sale.total());
        }
    }

    // ---------- US008: low stock alerts ----------

    private void checkLowStockAlerts() {
    SimpleList<Product> lowStockProducts = stockAlertService.getLowStockProducts(productService.getProducts());
    if (!lowStockProducts.isEmpty()) {
        System.out.println("\n--- ALERTA DE STOCK BAJO (Limite: " + stockAlertService.getThreshold() + " unidades) ---");
        lowStockProducts.resetIteration();
        while (lowStockProducts.hasNext()) {
            Product p = lowStockProducts.next();
            System.out.println("Producto: " + p.getName() + " (ID: " + p.getId() + ") - Stock restante: " + p.getStock() + " unidades.");
        }
        System.out.println("---------------------------------------------------\n");
    }
}

    // ---------- US009: users and roles ----------

    private void manageUsers() {
        System.out.println("1. Registrar usuario  2. Eliminar usuario  3. Listar usuarios");
        int option = readInt("Opcion: ");
        switch (option) {
            case 1 -> registerUser();
            case 2 -> removeUser();
            case 3 -> listUsers();
            default -> System.out.println("Opcion invalida.");
        }
    }

    private void registerUser() {
        String username = readText("Usuario: ");
        String password = readText("Contrasena: ");
        System.out.println("1. Administrador  2. Vendedor  3. Cliente");
        Role role = switch (readInt("Rol: ")) {
            case 1 -> Role.ADMINISTRATOR;
            case 2 -> Role.SALESPERSON;
            case 3 -> Role.CUSTOMER;
            default -> throw new IllegalArgumentException("Rol invalido.");
        };
        userService.register(username, password, role);
        System.out.println("Usuario registrado.");
    }

    private void removeUser() {
        String username = readText("Usuario a eliminar: ");
        if (username.equals("admin")) {
            System.out.println("No se puede eliminar el administrador principal.");
            return;
        }
        System.out.println(userService.remove(username) ? "Usuario eliminado." : "No se encontro el usuario.");
    }

    private void listUsers() {
        SimpleList<User> users = userService.getUsers();
        users.resetIteration();
        while (users.hasNext()) {
            System.out.println("- " + users.next());
        }
    }

    // ---------- Input helpers ----------

    private String readText(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim();
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readText(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    private BigDecimal readMoney(String prompt) {
        while (true) {
            try {
                return new BigDecimal(readText(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un valor numerico valido.");
            }
        }
    }
}