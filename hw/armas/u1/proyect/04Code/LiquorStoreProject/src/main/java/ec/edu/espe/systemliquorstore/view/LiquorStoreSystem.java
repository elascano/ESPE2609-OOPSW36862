package ec.edu.espe.systemliquorstore.view;

import ec.edu.espe.systemliquorstore.model.*;
import ec.edu.espe.systemliquorstore.persistence.JsonStorage;
import ec.edu.espe.systemliquorstore.model.StoreData;
import ec.edu.espe.systemliquorstore.util.Validation;
import ec.edu.espe.systemliquorstore.service.StoreService;
import ec.edu.espe.systemliquorstore.report.CsvReport;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class LiquorStoreSystem {
    private final Scanner scanner = new Scanner(System.in, "UTF-8");
    private final JsonStorage storage;
    private final CsvReport reports;
    private final ArrayList<Product> products = new ArrayList<>();
    private final ArrayList<StockMovement> movements = new ArrayList<>();
    private final ArrayList<Supplier> suppliers = new ArrayList<>();
    private final ArrayList<PurchaseOrder> orders = new ArrayList<>();
    private final ArrayList<Sale> sales = new ArrayList<>();
    private final ArrayList<Customer> customers = new ArrayList<>();
    private final ArrayList<CustomerPayment> payments = new ArrayList<>();
    private final ArrayList<DailyCashRecord> cashRecords = new ArrayList<>();
    private final StoreData data = new StoreData(products, movements, suppliers, orders,
            sales, customers, payments, cashRecords);
    private final StoreService service = new StoreService(data);
    private int nextSupplierId = 1;
    private int nextOrderId = 1;
    private int nextSaleId = 1;
    private int nextCustomerId = 1;
    private int nextPaymentId = 1;
    private boolean running = true;
    private StoreData lastSavedData;

    public LiquorStoreSystem() {
        this("data", "reports");
    }

    public LiquorStoreSystem(String dataDirectory, String reportDirectory) {
        storage = new JsonStorage(dataDirectory);
        reports = new CsvReport(reportDirectory);
    }

    public static void main(String[] args) {
        String dataDirectory = args.length > 0 ? args[0] : "data";
        String reportDirectory = args.length > 1 ? args[1] : "reports";
        new LiquorStoreSystem(dataDirectory, reportDirectory).run();
    }

    public void run() {
        System.out.println("SISTEMA DE LICORERÍA");
        System.out.println("Los datos se guardan automáticamente en archivos JSON separados.");
        System.out.println("Escriba /cancelar para abandonar una operación y volver al menú principal.");
        if (!loadData()) {
            scanner.close();
            return;
        }
        while (running) {
            try {
                showMainMenu();
                int option = readInt("Opción: ", 0, 11);
                switch (option) {
                    case 1:
                        productsMenu();
                        break;
                    case 2:
                        showLowStockAlerts();
                        break;
                    case 3:
                        suppliersMenu();
                        break;
                    case 4:
                        registerSale();
                        break;
                    case 5:
                        customersMenu();
                        break;
                    case 6:
                        cashMenu();
                        break;
                    case 7:
                        showProfitReport();
                        break;
                    case 8:
                        showSalesReport();
                        break;
                    case 9:
                        showBestSellers();
                        break;
                    case 10:
                        loadDemoData();
                        break;
                    case 11:
                        reportsMenu();
                        break;
                    case 0:
                        if (readYesNo("¿Desea salir? (s/n): ")) {
                            running = false;
                        }
                        break;
                    default:
                        break;
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("No se pudo realizar la operación: " + exception.getMessage());
            } catch (IllegalStateException exception) {
                System.out.println(exception.getMessage());
            }
        }
        System.out.println("Programa finalizado.");
        scanner.close();
    }

    private boolean loadData() {
        try {
            boolean existing = storage.exists();
            StoreData data = existing ? storage.load() : new StoreData();
            StoreData snapshot = storage.copy(data);
            if (!existing) {
                storage.save(data);
            }
            applyData(data);
            lastSavedData = snapshot;
            System.out.println(existing ? "Datos cargados desde " + storage.getFilePath()
                    : "Se crearon los archivos JSON con registros vacíos: " + storage.getFilePath());
            return true;
        } catch (IOException exception) {
            System.out.println("No se pudo iniciar el programa: " + exception.getMessage());
            System.out.println("Los archivos existentes no fueron reemplazados. Revise los datos y vuelva a intentar.");
            return false;
        }
    }

    private void commitChanges() {
        try {
            StoreData snapshot = storage.copy(new StoreData(products, movements, suppliers,
                    orders, sales, customers, payments, cashRecords));
            storage.save(snapshot);
            lastSavedData = snapshot;
        } catch (IOException exception) {
            try {
                applyData(storage.copy(lastSavedData));
            } catch (IOException restoreException) {
                running = false;
                throw new IllegalStateException("No se pudo recuperar el estado anterior. El programa se cerrará.");
            }
            throw new IllegalStateException("No se guardó la operación: " + exception.getMessage()
                    + " Se restauraron los datos anteriores.");
        }
    }

    private void applyData(StoreData data) {
        products.clear();
        products.addAll(data.getProducts());
        movements.clear();
        movements.addAll(data.getMovements());
        suppliers.clear();
        suppliers.addAll(data.getSuppliers());
        orders.clear();
        orders.addAll(data.getOrders());
        sales.clear();
        sales.addAll(data.getSales());
        customers.clear();
        customers.addAll(data.getCustomers());
        payments.clear();
        payments.addAll(data.getPayments());
        cashRecords.clear();
        cashRecords.addAll(data.getCashRecords());
        refreshCounters();
    }

    private void refreshCounters() {
        nextSupplierId = 1;
        for (Supplier supplier : suppliers) {
            if (nextSupplierId != 0 && supplier.getSupplierId() >= nextSupplierId) {
                nextSupplierId = nextAvailableId(supplier.getSupplierId());
            }
        }
        nextOrderId = 1;
        for (PurchaseOrder order : orders) {
            if (nextOrderId != 0 && order.getOrderId() >= nextOrderId) {
                nextOrderId = nextAvailableId(order.getOrderId());
            }
        }
        nextSaleId = 1;
        for (Sale sale : sales) {
            if (nextSaleId != 0 && sale.getSaleId() >= nextSaleId) {
                nextSaleId = nextAvailableId(sale.getSaleId());
            }
        }
        nextCustomerId = 1;
        for (Customer customer : customers) {
            if (nextCustomerId != 0 && customer.getCustomerId() >= nextCustomerId) {
                nextCustomerId = nextAvailableId(customer.getCustomerId());
            }
        }
        nextPaymentId = 1;
        for (CustomerPayment payment : payments) {
            if (nextPaymentId != 0 && payment.getPaymentId() >= nextPaymentId) {
                nextPaymentId = nextAvailableId(payment.getPaymentId());
            }
        }
    }

    private int nextAvailableId(int id) {
        return id == Integer.MAX_VALUE ? 0 : id + 1;
    }

    private void requireAvailableId(int id, String records) {
        if (id <= 0) {
            throw new IllegalStateException("No quedan identificadores disponibles para " + records + ".");
        }
    }

    private void showMainMenu() {
        System.out.println("\n========== MENÚ PRINCIPAL ==========");
        System.out.println("1. Productos e inventario");
        System.out.println("2. Alertas de stock bajo");
        System.out.println("3. Proveedores y pedidos");
        System.out.println("4. Registrar venta");
        System.out.println("5. Clientes, deudas y pagos");
        System.out.println("6. Caja diaria");
        System.out.println("7. Ganancia bruta y margen");
        System.out.println("8. Consultar ventas por vendedor");
        System.out.println("9. Productos más vendidos");
        System.out.println("10. Cargar datos ficticios de demostración");
        System.out.println("11. Exportar reportes CSV");
        System.out.println("0. Salir");
    }

    private void productsMenu() {
        int option;
        do {
            System.out.println("\n--- PRODUCTOS E INVENTARIO ---");
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Actualizar datos de un producto");
            System.out.println("4. Registrar entrada de stock");
            System.out.println("5. Registrar salida de stock");
            System.out.println("6. Consultar movimientos de inventario");
            System.out.println("7. Eliminar producto");
            System.out.println("0. Volver");
            option = readInt("Opción: ", 0, 7);
            switch (option) {
                case 1:
                    registerProduct();
                    break;
                case 2:
                    showProducts();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    recordStockMovement(true);
                    break;
                case 5:
                    recordStockMovement(false);
                    break;
                case 6:
                    showMovements();
                    break;
                case 7:
                    deleteProduct();
                    break;
                default:
                    break;
            }
        } while (option != 0 && running);
    }

    private void registerProduct() {
        String code = readText("Código único del producto: ").toUpperCase(Locale.ROOT);
        if (findProduct(code) != null) {
            throw new IllegalArgumentException("Ya existe un producto con ese código.");
        }
        String name = readText("Nombre: ");
        double cost = readMoney("Costo unitario (USD): ", true);
        double price = readMoney("Precio de venta de la etiqueta (USD): ", false);
        int stock = readInt("Stock inicial: ", 0, Integer.MAX_VALUE);
        int minimum = readInt("Stock mínimo: ", 0, Integer.MAX_VALUE);
        Product product = new Product(code, name, cost, price, stock, minimum);
        String date = stock > 0 ? readDate("Fecha del stock inicial") : LocalDate.now().toString();
        if (!readYesNo("¿Registrar producto? (s/n): ")) {
            System.out.println("Registro cancelado.");
            return;
        }
        StockMovement movement = stock > 0 ? product.createStockMovement(nextMovementId(), date, "ENTRY", stock) : null;
        service.addProduct(product);
        if (movement != null) {
            movements.add(movement);
        }
        commitChanges();
        System.out.println("Producto registrado: " + product.getProductCode());
        showProductAlert(product);
    }

    private void showProducts() {
        if (products.isEmpty()) {
            System.out.println("Todavía no hay productos registrados.");
            return;
        }
        System.out.printf("%-12s %-30s %12s %12s %10s %10s%n", "Código", "Producto", "Costo USD", "P.V.P. USD", "Stock", "Mínimo");
        for (Product product : products) {
            System.out.printf(Locale.US, "%-12s %-30.30s %12.2f %12.2f %10d %10d%n", product.getProductCode(), product.getName(), product.getPurchaseCost(), product.getSellingPrice(), product.getStock(), product.getMinimumStock());
            showProductAlert(product);
        }
    }

    private void updateProduct() {
        Product product = selectProduct();
        String name = readText("Nuevo nombre: ");
        double cost = readMoney("Nuevo costo unitario (USD): ", true);
        double price = readMoney("Nuevo precio de etiqueta (USD): ", false);
        int minimum = readInt("Nuevo stock mínimo: ", 0, Integer.MAX_VALUE);
        if (!readYesNo("¿Actualizar estos datos? (s/n): ")) {
            System.out.println("Actualización cancelada.");
            return;
        }
        service.updateProduct(product.getProductCode(), name, cost, price, minimum);
        commitChanges();
        System.out.println("Producto actualizado. Las ventas anteriores conservan sus precios originales.");
        showProductAlert(product);
    }

    private void recordStockMovement(boolean entry) {
        Product product = selectProduct();
        int quantity = readInt("Cantidad de unidades: ", 1, Integer.MAX_VALUE);
        String date = readDate("Fecha del movimiento");
        if (!entry && quantity > product.getStock()) {
            throw new IllegalArgumentException("Stock insuficiente. Disponible: " + product.getStock());
        }
        StockMovement movement = product.createStockMovement(nextMovementId(), date, entry ? "ENTRY" : "EXIT", quantity);
        if (!readYesNo("¿Confirmar movimiento? (s/n): ")) {
            System.out.println("Movimiento cancelado.");
            return;
        }
        if (entry) {
            product.increaseStock(quantity);
        } else {
            product.decreaseStock(quantity);
        }
        movements.add(movement);
        commitChanges();
        System.out.println("Movimiento registrado. Stock disponible: " + product.getStock());
        showProductAlert(product);
    }

    private void showMovements() {
        if (movements.isEmpty()) {
            System.out.println("Todavía no hay movimientos de inventario.");
            return;
        }
        System.out.printf("%-8s %-12s %-14s %-12s %12s%n", "ID", "Fecha", "Código", "Tipo", "Cantidad");
        for (StockMovement movement : movements) {
            System.out.printf("%-8d %-12s %-14s %-12s %12d%n", movement.getMovementId(), movement.getDate(), movement.getProductCode(), "ENTRY".equals(movement.getMovementType()) ? "Entrada" : "Salida", movement.getQuantity());
        }
    }

    private void showLowStockAlerts() {
        System.out.println("\n--- ALERTAS DE STOCK BAJO ---");
        if (products.isEmpty()) {
            System.out.println("Registre productos para consultar las alertas.");
            return;
        }
        boolean found = false;
        for (Product product : products) {
            if (product.isLowStock()) {
                showProductAlert(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Todos los productos superan su stock mínimo.");
        }
    }

    private void showProductAlert(Product product) {
        if (product.isLowStock()) {
            System.out.println("ALERTA: " + product.getProductCode() + " - " + product.getName() + ". Stock: " + product.getStock() + ", mínimo: " + product.getMinimumStock());
        }
    }

    private void suppliersMenu() {
        int option;
        do {
            System.out.println("\n--- PROVEEDORES Y PEDIDOS ---");
            System.out.println("1. Registrar proveedor");
            System.out.println("2. Listar proveedores");
            System.out.println("3. Crear pedido de compra");
            System.out.println("4. Consultar pedidos y sus productos");
            System.out.println("5. Confirmar recepción de un pedido");
            System.out.println("6. Actualizar proveedor");
            System.out.println("7. Eliminar proveedor");
            System.out.println("0. Volver");
            option = readInt("Opción: ", 0, 7);
            switch (option) {
                case 1:
                    registerSupplier();
                    break;
                case 2:
                    showSuppliers();
                    break;
                case 3:
                    createOrder();
                    break;
                case 4:
                    showOrders();
                    break;
                case 5:
                    receiveOrder();
                    break;
                case 6:
                    updateSupplier();
                    break;
                case 7:
                    deleteSupplier();
                    break;
                default:
                    break;
            }
        } while (option != 0 && running);
    }

    private void registerSupplier() {
        requireAvailableId(nextSupplierId, "proveedores");
        String name = readText("Nombre del proveedor: ");
        String contact = readText("Contacto: ");
        Supplier supplier = new Supplier(nextSupplierId, name, contact);
        if (readYesNo("¿Registrar proveedor? (s/n): ")) {
            service.addSupplier(supplier);
            nextSupplierId = nextAvailableId(nextSupplierId);
            commitChanges();
            System.out.println("Proveedor registrado con ID " + supplier.getSupplierId());
        } else {
            System.out.println("Registro cancelado.");
        }
    }

    private void showSuppliers() {
        if (suppliers.isEmpty()) {
            System.out.println("Todavía no hay proveedores registrados.");
            return;
        }
        for (Supplier supplier : suppliers) {
            System.out.println(supplier.getSupplierId() + ". " + supplier.getName() + " | " + supplier.getContact());
        }
    }

    private void createOrder() {
        requireAvailableId(nextOrderId, "pedidos");
        if (suppliers.isEmpty() || products.isEmpty()) {
            System.out.println("Primero registre al menos un proveedor y un producto.");
            return;
        }
        showSuppliers();
        Supplier supplier = findSupplier(readInt("ID del proveedor: ", 1, Integer.MAX_VALUE));
        if (supplier == null) {
            throw new IllegalArgumentException("No existe ese proveedor.");
        }
        PurchaseOrder order = supplier.createPurchaseOrder(nextOrderId, readDate("Fecha del pedido"));
        showProducts();
        while (true) {
            String code = readLine("Código del producto (Enter para terminar): ");
            if (code.isEmpty()) {
                if (order.getItems().isEmpty()) {
                    System.out.println("El pedido necesita al menos un producto.");
                    continue;
                }
                break;
            }
            Product product = findProduct(code);
            if (product == null) {
                System.out.println("No existe ese producto.");
                continue;
            }
            int quantity = readInt("Cantidad solicitada: ", 1, Integer.MAX_VALUE);
            order.addProduct(product, quantity);
            System.out.println("Producto añadido al pedido.");
        }
        printOrder(order);
        if (readYesNo("¿Registrar este pedido pendiente? (s/n): ")) {
            orders.add(order);
            nextOrderId = nextAvailableId(nextOrderId);
            commitChanges();
            System.out.println("Pedido registrado con ID " + order.getOrderId());
        } else {
            System.out.println("Pedido cancelado.");
        }
    }

    private void showOrders() {
        if (orders.isEmpty()) {
            System.out.println("Todavía no hay pedidos registrados.");
            return;
        }
        for (PurchaseOrder order : orders) {
            printOrder(order);
        }
    }

    private void printOrder(PurchaseOrder order) {
        Supplier supplier = findSupplier(order.getSupplierId());
        System.out.println("\nPedido " + order.getOrderId() + " | Fecha: " + order.getOrderDate() + " | Proveedor: " + (supplier == null ? order.getSupplierId() : supplier.getName()) + " | Estado: " + (order.isPending() ? "Pendiente" : "Recibido"));
        if (!order.isPending()) {
            System.out.println("Fecha de recepción: " + order.getReceivedDate());
        }
        for (PurchaseOrderItem item : order.getItems()) {
            Product product = findProduct(item.getProductCode());
            System.out.println("  " + item.getProductCode() + " - " + (product == null ? "Producto" : product.getName()) + ": " + item.getQuantity() + " unidades");
        }
    }

    private void receiveOrder() {
        if (orders.isEmpty()) {
            System.out.println("No hay pedidos para recibir.");
            return;
        }
        showOrders();
        PurchaseOrder order = findOrder(readInt("ID del pedido recibido: ", 1, Integer.MAX_VALUE));
        if (order == null) {
            throw new IllegalArgumentException("No existe ese pedido.");
        }
        if (!order.isPending()) {
            throw new IllegalStateException("Este pedido ya fue recibido; no se volverá a aumentar el stock.");
        }
        String date = readDate("Fecha de recepción completa");
        if (readYesNo("¿Confirma que recibió todos los productos del pedido? (s/n): ")) {
            order.receive(products, movements, date);
            commitChanges();
            System.out.println("Pedido recibido. El inventario fue actualizado una sola vez.");
            System.out.println("Si pagó en efectivo, registre ese pago como gasto en Caja diaria.");
            showLowStockAlerts();
        } else {
            System.out.println("Recepción cancelada. El pedido sigue pendiente.");
        }
    }

    private void registerSale() {
        requireAvailableId(nextSaleId, "ventas");
        if (products.isEmpty()) {
            System.out.println("Primero registre productos para poder vender.");
            return;
        }
        System.out.println("\n--- NUEVA VENTA ---");
        System.out.println("Vendedor: 1. Propietario | 2. Empleado");
        String seller = readInt("Vendedor responsable: ", 1, 2) == 1 ? "OWNER" : "EMPLOYEE";
        String date = readDate("Fecha de la venta");
        System.out.println("Forma de pago: 1. Contado | 2. Crédito");
        boolean credit = readInt("Forma de pago: ", 1, 2) == 2;
        Sale sale;
        if (credit) {
            Customer customer = selectCustomer();
            sale = customer.createCreditSale(nextSaleId, date, seller);
        } else {
            sale = new Sale(nextSaleId, date, seller, "CASH");
        }
        showProducts();
        while (true) {
            String code = readLine("Código del producto (Enter para terminar): ");
            if (code.isEmpty()) {
                if (sale.getItems().isEmpty()) {
                    System.out.println("La venta necesita al menos un producto.");
                    continue;
                }
                break;
            }
            Product product = findProduct(code);
            if (product == null) {
                System.out.println("No existe ese producto.");
                continue;
            }
            int quantity = readInt("Cantidad: ", 1, Integer.MAX_VALUE);
            long requested = (long) sale.getProductQuantity(product.getProductCode()) + quantity;
            if (requested > product.getStock()) {
                System.out.println("Stock insuficiente. Disponible: " + product.getStock() + ". Ya añadido a esta venta: " + sale.getProductQuantity(product.getProductCode()));
                continue;
            }
            sale.addProduct(product, quantity);
            System.out.printf(Locale.US, "Producto añadido. Subtotal: %.2f USD%n", sale.calculateSubtotal());
        }
        if (readYesNo("¿Aplicar un descuento autorizado por el propietario? (s/n): ")) {
            sale.applyDiscount(readPercentage());
        }
        printSale(sale);
        if (sale.calculateTotal() <= 0) {
            throw new IllegalArgumentException("La venta debe tener un total mayor que cero después del descuento.");
        }
        String birthDate;
        int age;
        while (true) {
            birthDate = readLine("Fecha de nacimiento del cliente (AAAA-MM-DD): ");
            try {
                age = sale.calculateAge(birthDate);
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        System.out.println("Edad calculada: " + age + (age == 1 ? " año." : " años."));
        if (age < 18) {
            System.out.println("Cliente menor de edad. La venta no puede continuar.");
            System.out.println("Venta cancelada. El inventario, las deudas y la caja no fueron modificados.");
            return;
        }
        sale.confirmAge(birthDate);
        System.out.println("Cliente mayor de edad. Puede continuar con la venta.");
        if (!readYesNo("¿Confirmar y registrar la venta? (s/n): ")) {
            System.out.println("Venta cancelada. El inventario, las deudas y la caja no fueron modificados.");
            return;
        }
        sale.complete(products, movements);
        sales.add(sale);
        nextSaleId = nextAvailableId(nextSaleId);
        commitChanges();
        System.out.println("VENTA REGISTRADA CORRECTAMENTE");
        printSale(sale);
        if (credit) {
            Customer customer = findCustomer(sale.getCustomerId());
            System.out.printf(Locale.US, "Saldo pendiente de %s: %.2f USD%n", customer.getName(), customer.getOutstandingBalance(sales, payments));
            System.out.println("La venta a crédito no aumenta el efectivo de caja hasta recibir un pago.");
        }
        showLowStockAlerts();
    }

    private void printSale(Sale sale) {
        System.out.println("\n--- RESUMEN DE VENTA " + sale.getSaleId() + " ---");
        System.out.println("Fecha: " + sale.getDate() + " | Vendedor: " + sellerLabel(sale.getSeller()) + " | Pago: " + paymentLabel(sale.getPaymentType()));
        if (sale.getCustomerId() > 0) {
            Customer customer = findCustomer(sale.getCustomerId());
            System.out.println("Cliente: " + (customer == null ? sale.getCustomerId() : customer.getName()));
        }
        System.out.printf("%-12s %-28s %8s %12s %12s%n", "Código", "Producto", "Cantidad", "Precio USD", "Importe USD");
        for (SaleItem item : sale.getItems()) {
            System.out.printf(Locale.US, "%-12s %-28.28s %8d %12.2f %12.2f%n", item.getProductCode(), item.getProductName(), item.getQuantity(), item.getUnitSellingPrice(), item.calculateSubtotal());
        }
        System.out.printf(Locale.US, "Subtotal: %.2f USD%n", sale.calculateSubtotal());
        System.out.printf(Locale.US, "Descuento: %.2f %% | Importe descontado: %.2f USD%n", sale.getDiscountPercentage(), sale.getDiscountAmount());
        System.out.printf(Locale.US, "TOTAL: %.2f USD%n", sale.calculateTotal());
    }

    private void customersMenu() {
        int option;
        do {
            System.out.println("\n--- CLIENTES, DEUDAS Y PAGOS ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Consultar saldos de clientes");
            System.out.println("3. Consultar compras a crédito e historial de pagos");
            System.out.println("4. Registrar pago de una deuda");
            System.out.println("5. Actualizar cliente");
            System.out.println("6. Eliminar cliente");
            System.out.println("0. Volver");
            option = readInt("Opción: ", 0, 6);
            switch (option) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    showCustomers();
                    break;
                case 3:
                    showCustomerHistory();
                    break;
                case 4:
                    registerCustomerPayment();
                    break;
                case 5:
                    updateCustomer();
                    break;
                case 6:
                    deleteCustomer();
                    break;
                default:
                    break;
            }
        } while (option != 0 && running);
    }

    private void registerCustomer() {
        requireAvailableId(nextCustomerId, "clientes");
        Customer customer = new Customer(nextCustomerId, readText("Nombre del cliente: "));
        if (readYesNo("¿Registrar cliente? (s/n): ")) {
            service.addCustomer(customer);
            nextCustomerId = nextAvailableId(nextCustomerId);
            commitChanges();
            System.out.println("Cliente registrado con ID " + customer.getCustomerId());
        } else {
            System.out.println("Registro cancelado.");
        }
    }

    private void showCustomers() {
        if (customers.isEmpty()) {
            System.out.println("Todavía no hay clientes registrados.");
            return;
        }
        System.out.printf("%-8s %-35s %15s%n", "ID", "Cliente", "Deuda USD");
        for (Customer customer : customers) {
            System.out.printf(Locale.US, "%-8d %-35.35s %15.2f%n", customer.getCustomerId(), customer.getName(), customer.getOutstandingBalance(sales, payments));
        }
    }

    private void showCustomerHistory() {
        Customer customer = selectCustomer();
        System.out.println("\nCompras a crédito de " + customer.getName() + ":");
        boolean hasSales = false;
        for (Sale sale : sales) {
            if (sale.isCompleted() && "CREDIT".equals(sale.getPaymentType()) && sale.getCustomerId() == customer.getCustomerId()) {
                printSale(sale);
                hasSales = true;
            }
        }
        if (!hasSales) {
            System.out.println("No tiene compras a crédito registradas.");
        }
        System.out.println("Historial de pagos:");
        boolean hasPayments = false;
        for (CustomerPayment payment : payments) {
            if (payment.getCustomerId() == customer.getCustomerId()) {
                System.out.printf(Locale.US, "Pago %d | %s | %.2f USD%n", payment.getPaymentId(), payment.getDate(), payment.getAmount());
                hasPayments = true;
            }
        }
        if (!hasPayments) {
            System.out.println("No tiene pagos registrados.");
        }
        System.out.printf(Locale.US, "SALDO PENDIENTE: %.2f USD%n", customer.getOutstandingBalance(sales, payments));
    }

    private void registerCustomerPayment() {
        requireAvailableId(nextPaymentId, "pagos");
        Customer customer = selectCustomer();
        double balance = customer.getOutstandingBalance(sales, payments);
        System.out.printf(Locale.US, "Saldo pendiente: %.2f USD%n", balance);
        if (balance <= 0) {
            System.out.println("El cliente no tiene una deuda pendiente.");
            return;
        }
        double amount;
        do {
            amount = readMoney("Monto que recibe en efectivo (USD): ", false);
            if (amount > balance) {
                System.out.println("El pago no puede superar el saldo pendiente.");
            }
        } while (amount > balance);
        String date = readDate("Fecha del pago");
        CustomerPayment payment = customer.createPayment(nextPaymentId, date, amount);
        if (readYesNo("¿Registrar el pago recibido? (s/n): ")) {
            customer.registerPayment(payment, sales, payments);
            nextPaymentId = nextAvailableId(nextPaymentId);
            commitChanges();
            System.out.printf(Locale.US, "Pago registrado. Saldo pendiente: %.2f USD%n", customer.getOutstandingBalance(sales, payments));
        } else {
            System.out.println("Pago cancelado.");
        }
    }

    private void cashMenu() {
        int option;
        do {
            System.out.println("\n--- CAJA DIARIA ---");
            System.out.println("1. Registrar apertura de caja");
            System.out.println("2. Registrar gasto en efectivo");
            System.out.println("3. Registrar o corregir conteo físico");
            System.out.println("4. Consultar cierre y diferencia de caja");
            System.out.println("5. Listar fechas con apertura registrada");
            System.out.println("0. Volver");
            option = readInt("Opción: ", 0, 5);
            switch (option) {
                case 1:
                    openCash();
                    break;
                case 2:
                    registerExpense();
                    break;
                case 3:
                    recordCashCount();
                    break;
                case 4:
                    showCashReport(selectCashRecord());
                    break;
                case 5:
                    showCashDates();
                    break;
                default:
                    break;
            }
        } while (option != 0 && running);
    }

    private void openCash() {
        String date = readDate("Fecha de apertura");
        if (findCashRecord(date) != null) {
            throw new IllegalStateException("Ya hay una apertura de caja para esa fecha.");
        }
        double opening = readMoney("Efectivo de apertura (USD): ", true);
        DailyCashRecord record = new DailyCashRecord(date, opening);
        if (readYesNo("¿Registrar apertura? (s/n): ")) {
            cashRecords.add(record);
            commitChanges();
            System.out.println("Apertura de caja registrada para " + date);
        } else {
            System.out.println("Apertura cancelada.");
        }
    }

    private void registerExpense() {
        DailyCashRecord record = selectCashRecord();
        String description = readText("Descripción del gasto: ");
        double amount = readMoney("Monto pagado en efectivo (USD): ", false);
        if (readYesNo("¿Registrar gasto? (s/n): ")) {
            record.addExpense(description, amount);
            commitChanges();
            System.out.printf(Locale.US, "Gasto registrado. Total de gastos del día: %.2f USD%n", record.getTotalExpenses());
        } else {
            System.out.println("Gasto cancelado.");
        }
    }

    private void recordCashCount() {
        DailyCashRecord record = selectCashRecord();
        double amount = readMoney("Dinero contado físicamente (USD): ", true);
        if (readYesNo("¿Guardar este conteo? (s/n): ")) {
            record.recordCount(amount);
            commitChanges();
            System.out.println("Conteo guardado correctamente.");
            showCashReport(record);
        } else {
            System.out.println("Conteo cancelado.");
        }
    }

    private void showCashDates() {
        if (cashRecords.isEmpty()) {
            System.out.println("Todavía no hay aperturas de caja.");
            return;
        }
        for (DailyCashRecord record : cashRecords) {
            System.out.printf(Locale.US, "%s | Apertura: %.2f USD | Conteo: %s%n", record.getDate(), record.getOpeningCash(), record.isCashCountRecorded() ? "Registrado" : "Pendiente");
        }
    }

    private void showCashReport(DailyCashRecord record) {
        double cashSales = 0;
        double receivedPayments = 0;
        for (Sale sale : sales) {
            if (sale.isCompleted() && "CASH".equals(sale.getPaymentType()) && record.getDate().equals(sale.getDate())) {
                cashSales += sale.getTotalAmount();
            }
        }
        for (CustomerPayment payment : payments) {
            if (record.getDate().equals(payment.getDate())) {
                receivedPayments += payment.getAmount();
            }
        }
        System.out.println("\n--- CIERRE DE CAJA: " + record.getDate() + " ---");
        System.out.printf(Locale.US, "Apertura:           %.2f USD%n", record.getOpeningCash());
        System.out.printf(Locale.US, "Ventas de contado:  %.2f USD%n", Validation.roundMoney(cashSales));
        System.out.printf(Locale.US, "Pagos de deudas:    %.2f USD%n", Validation.roundMoney(receivedPayments));
        System.out.printf(Locale.US, "Gastos en efectivo: %.2f USD%n", record.getTotalExpenses());
        for (CashExpense expense : record.getExpenses()) {
            System.out.printf(Locale.US, "  %s: %.2f USD%n", expense.getDescription(), expense.getAmount());
        }
        System.out.printf(Locale.US, "EFECTIVO ESPERADO:  %.2f USD%n", record.calculateExpectedCash(sales, payments));
        if (!record.isCashCountRecorded()) {
            System.out.println("Conteo físico pendiente. Regístrelo para calcular la diferencia.");
            return;
        }
        double difference = record.calculateDifference(sales, payments);
        System.out.printf(Locale.US, "Efectivo contado:   %.2f USD%n", record.getCountedCash());
        System.out.printf(Locale.US, "DIFERENCIA:         %.2f USD%n", difference);
        if (difference > 0) {
            System.out.println("Resultado: sobrante de efectivo.");
        } else if (difference < 0) {
            System.out.println("Resultado: faltante de efectivo.");
        } else {
            System.out.println("Resultado: caja cuadrada.");
        }
    }

    private void showProfitReport() {
        String start = readDate("Fecha inicial del reporte");
        String end = readEndDate(start);
        double totalRevenue = 0;
        double totalCost = 0;
        int count = 0;
        System.out.println("\n--- GANANCIA BRUTA Y MARGEN ---");
        for (Sale sale : sales) {
            if (sale.isCompleted() && inRange(sale.getDate(), start, end)) {
                double cost = 0;
                for (SaleItem item : sale.getItems()) {
                    cost += item.calculatePurchaseCost();
                }
                System.out.printf(Locale.US, "Venta %d | %s | Importe: %.2f USD | Ganancia: %.2f USD | Margen: %.2f %%%n", sale.getSaleId(), sale.getDate(), sale.getTotalAmount(), sale.calculateGrossProfit(), sale.calculateMargin());
                totalRevenue += sale.getTotalAmount();
                totalCost += cost;
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No hay ventas completadas en ese período.");
            return;
        }
        totalRevenue = Validation.roundMoney(totalRevenue);
        totalCost = Validation.roundMoney(totalCost);
        double profit = Validation.roundMoney(totalRevenue - totalCost);
        System.out.printf(Locale.US, "Total vendido: %.2f USD | Costo: %.2f USD | GANANCIA BRUTA: %.2f USD%n", totalRevenue, totalCost, profit);
        System.out.printf(Locale.US, "MARGEN GLOBAL: %.2f %%%n", Validation.roundMoney(profit / totalRevenue * 100));
        System.out.println("El margen mostrado es el real; el 20% es una referencia y no modifica los precios de etiqueta.");
        System.out.println("La ganancia bruta no descuenta los gastos de operación de la tienda.");
    }

    private void showSalesReport() {
        System.out.println("\nVendedor: 0. Todos | 1. Propietario | 2. Empleado");
        int filter = readInt("Vendedor a consultar: ", 0, 2);
        String start = readDate("Fecha inicial del reporte");
        String end = readEndDate(start);
        int count = 0;
        double total = 0;
        for (Sale sale : sales) {
            boolean sellerMatches = filter == 0 || (filter == 1 && "OWNER".equals(sale.getSeller())) || (filter == 2 && "EMPLOYEE".equals(sale.getSeller()));
            if (sale.isCompleted() && sellerMatches && inRange(sale.getDate(), start, end)) {
                printSale(sale);
                total += sale.getTotalAmount();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No hay ventas que coincidan con el vendedor y las fechas seleccionadas.");
        } else {
            System.out.printf(Locale.US, "Ventas encontradas: %d | Total vendido: %.2f USD%n", count, Validation.roundMoney(total));
        }
    }

    private void showBestSellers() {
        String start = readDate("Fecha inicial del reporte");
        String end = readEndDate(start);
        ArrayList<Product> ranked = new ArrayList<>();
        int[] units = new int[products.size()];
        for (Product product : products) {
            int sold = product.getUnitsSold(sales, start, end);
            if (sold > 0) {
                units[ranked.size()] = sold;
                ranked.add(product);
            }
        }
        if (ranked.isEmpty()) {
            System.out.println("No hay productos vendidos en ese período.");
            return;
        }
        for (int i = 0; i < ranked.size() - 1; i++) {
            for (int j = i + 1; j < ranked.size(); j++) {
                if (units[j] > units[i] || (units[j] == units[i] && ranked.get(j).getProductCode().compareTo(ranked.get(i).getProductCode()) < 0)) {
                    int savedUnits = units[i];
                    units[i] = units[j];
                    units[j] = savedUnits;
                    Product savedProduct = ranked.get(i);
                    ranked.set(i, ranked.get(j));
                    ranked.set(j, savedProduct);
                }
            }
        }
        System.out.println("\n--- PRODUCTOS MÁS VENDIDOS: " + start + " a " + end + " ---");
        System.out.printf("%-8s %-12s %-35s %12s%n", "Puesto", "Código", "Producto", "Unidades");
        for (int i = 0; i < ranked.size(); i++) {
            Product product = ranked.get(i);
            System.out.printf("%-8d %-12s %-35.35s %12d%n", i + 1, product.getProductCode(), product.getName(), units[i]);
        }
        System.out.println("Incluye ventas de contado y a crédito. Los pagos de deudas no cuentan como nuevas ventas.");
    }

    private void loadDemoData() {
        if (!products.isEmpty() || !suppliers.isEmpty() || !orders.isEmpty() || ! sales.isEmpty() || !customers.isEmpty() || !payments.isEmpty() || !cashRecords.isEmpty() || ! movements.isEmpty()) {
            throw new IllegalStateException("La demostración solo se carga al iniciar con todos los registros vacíos.");
        }
        if (!readYesNo("¿Cargar productos, un proveedor y un cliente ficticios? (s/n): ")) {
            return;
        }
        String date = LocalDate.now().toString();
        products.add(new Product("P001", "Cerveza de demostración", 8, 10, 20, 5));
        products.add(new Product("P002", "Vino de demostración", 12, 15, 10, 3));
        products.add(new Product("P003", "Agua de demostración", 0.50, 0.75, 3, 3));
        for (Product product : products) {
            movements.add(product.createStockMovement(nextMovementId(), date, "ENTRY", product.getStock()));
        }
        suppliers.add(new Supplier(nextSupplierId, "Proveedor de demostración", "Contacto de demostración"));
        nextSupplierId = nextAvailableId(nextSupplierId);
        customers.add(new Customer(nextCustomerId, "Cliente de demostración"));
        nextCustomerId = nextAvailableId(nextCustomerId);
        commitChanges();
        System.out.println("Datos ficticios cargados. Cliente ID 1 y proveedor ID 1.");
        showProducts();
        showLowStockAlerts();
    }

    private void deleteProduct() {
        Product product = selectProduct();
        if (readYesNo("¿Eliminar este producto y sus movimientos de inventario? (s/n): ")) {
            service.deleteProduct(product.getProductCode());
            commitChanges();
            System.out.println("Producto eliminado.");
        }
    }

    private void updateSupplier() {
        showSuppliers();
        int id = readInt("ID del proveedor: ", 1, Integer.MAX_VALUE);
        String name = readText("Nuevo nombre: ");
        String contact = readText("Nuevo contacto: ");
        if (readYesNo("¿Actualizar proveedor? (s/n): ")) {
            service.updateSupplier(id, name, contact);
            commitChanges();
            System.out.println("Proveedor actualizado.");
        }
    }

    private void deleteSupplier() {
        showSuppliers();
        int id = readInt("ID del proveedor: ", 1, Integer.MAX_VALUE);
        if (readYesNo("¿Eliminar proveedor? (s/n): ")) {
            service.deleteSupplier(id);
            commitChanges();
            System.out.println("Proveedor eliminado.");
        }
    }

    private void updateCustomer() {
        Customer customer = selectCustomer();
        String name = readText("Nuevo nombre: ");
        if (readYesNo("¿Actualizar cliente? (s/n): ")) {
            service.updateCustomer(customer.getCustomerId(), name);
            commitChanges();
            System.out.println("Cliente actualizado.");
        }
    }

    private void deleteCustomer() {
        Customer customer = selectCustomer();
        if (readYesNo("¿Eliminar cliente? (s/n): ")) {
            service.deleteCustomer(customer.getCustomerId());
            commitChanges();
            System.out.println("Cliente eliminado.");
        }
    }

    private void reportsMenu() {
        System.out.println("\n--- EXPORTAR REPORTES CSV ---");
        System.out.println("1. Inventario de productos");
        System.out.println("2. Ventas por fechas");
        System.out.println("3. Saldos de clientes");
        System.out.println("0. Volver");
        int option = readInt("Opción: ", 0, 3);
        try {
            java.nio.file.Path file;
            switch (option) {
                case 1:
                    file = reports.exportInventory(data);
                    break;
                case 2:
                    String start = readDate("Fecha inicial");
                    String end = readEndDate(start);
                    file = reports.exportSales(data, start, end);
                    break;
                case 3:
                    file = reports.exportCustomerBalances(data);
                    break;
                default:
                    return;
            }
            System.out.println("Reporte guardado en: " + file);
        } catch (IOException exception) {
            System.out.println("No se pudo guardar el reporte CSV. Revise la carpeta de reportes.");
        }
    }

    private Product selectProduct() {
        if (products.isEmpty()) {
            throw new IllegalStateException("Primero registre al menos un producto.");
        }
        showProducts();
        Product product = findProduct(readText("Código del producto: "));
        if (product == null) {
            throw new IllegalArgumentException("No existe ese producto.");
        }
        return product;
    }

    private Customer selectCustomer() {
        if (customers.isEmpty()) {
            throw new IllegalStateException("Primero registre el cliente en Clientes, deudas y pagos.");
        }
        showCustomers();
        Customer customer = findCustomer(readInt("ID del cliente: ", 1, Integer.MAX_VALUE));
        if (customer == null) {
            throw new IllegalArgumentException("No existe ese cliente.");
        }
        return customer;
    }

    private DailyCashRecord selectCashRecord() {
        String date = readDate("Fecha de la caja");
        DailyCashRecord record = findCashRecord(date);
        if (record == null) {
            throw new IllegalStateException("Primero registre la apertura de caja para " + date + ".");
        }
        return record;
    }

    private Product findProduct(String code) {
        for (Product product : products) {
            if (product.getProductCode().equalsIgnoreCase(code.trim())) {
                return product;
            }
        }
        return null;
    }

    private Supplier findSupplier(int id) {
        for (Supplier supplier : suppliers) {
            if (supplier.getSupplierId() == id) {
                return supplier;
            }
        }
        return null;
    }

    private Customer findCustomer(int id) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == id) {
                return customer;
            }
        }
        return null;
    }

    private PurchaseOrder findOrder(int id) {
        for (PurchaseOrder order : orders) {
            if (order.getOrderId() == id) {
                return order;
            }
        }
        return null;
    }

    private DailyCashRecord findCashRecord(String date) {
        for (DailyCashRecord record : cashRecords) {
            if (record.getDate().equals(date)) {
                return record;
            }
        }
        return null;
    }

    private int nextMovementId() {
        int maximum = 0;
        for (StockMovement movement : movements) {
            if (movement.getMovementId() > maximum) {
                maximum = movement.getMovementId();
            }
        }
        if (maximum == Integer.MAX_VALUE) {
            throw new IllegalStateException("No se pueden generar más movimientos de inventario.");
        }
        return maximum + 1;
    }

    private String sellerLabel(String seller) {
        return "OWNER".equals(seller) ? "Propietario" : "Empleado";
    }

    private String paymentLabel(String paymentType) {
        return "CASH".equals(paymentType) ? "Contado" : "Crédito";
    }

    private boolean inRange(String date, String start, String end) {
        return date.compareTo(start) >= 0 && date.compareTo(end) <= 0;
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            running = false;
            throw new IllegalStateException("Entrada finalizada.");
        }
        String value = scanner.nextLine().trim();
        if (value.equalsIgnoreCase("/cancelar")) {
            throw new IllegalStateException("Operación cancelada.");
        }
        return value;
    }

    private String readText(String prompt) {
        while (true) {
            String value = readLine(prompt);
            if (Validation.isText(value)) {
                return value;
            }
            System.out.println("El texto no puede estar vacío.");
        }
    }

    private int readInt(String prompt, int minimum, int maximum) {
        while (true) {
            try {
                int value = Integer.parseInt(readLine(prompt));
                if (value >= minimum && value <= maximum) {
                    return value;
                }
                System.out.println("Ingrese un entero entre " + minimum + " y " + maximum + ".");
            } catch (NumberFormatException exception) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private double readMoney(String prompt, boolean allowZero) {
        while (true) {
            try {
                double value = Double.parseDouble(readLine(prompt).replace(',', '.'));
                if (Validation.isMoney(value) && (value > 0 || (allowZero && value == 0))) {
                    return value;
                }
                System.out.println(allowZero ? "Ingrese un monto no negativo con hasta dos decimales." : "Ingrese un monto mayor que cero con hasta dos decimales.");
            } catch (NumberFormatException exception) {
                System.out.println("Ingrese un número válido. Ejemplo: 8.50");
            }
        }
    }

    private double readPercentage() {
        while (true) {
            try {
                double value = Double.parseDouble(readLine("Descuento autorizado (0 a menos de 100): ").replace(',', '.'));
                if (Validation.isFinite(value) && value >= 0 && value < 100) {
                    return value;
                }
                System.out.println("El porcentaje debe estar entre 0 y menos de 100.");
            } catch (NumberFormatException exception) {
                System.out.println("Ingrese un porcentaje válido.");
            }
        }
    }

    private String readDate(String description) {
        while (true) {
            String value = readLine(description + " (AAAA-MM-DD, Enter = hoy): ");
            if (value.isEmpty()) {
                return LocalDate.now().toString();
            }
            if (Validation.isValidDate(value)) {
                return value;
            }
            System.out.println("Fecha inválida. Use AAAA-MM-DD y una fecha que exista.");
        }
    }

    private String readEndDate(String start) {
        while (true) {
            String end = readDate("Fecha final del reporte");
            if (end.compareTo(start) >= 0) {
                return end;
            }
            System.out.println("La fecha final no puede ser anterior a " + start + ".");
        }
    }

    private boolean readYesNo(String prompt) {
        while (true) {
            String value = readLine(prompt).toLowerCase(Locale.ROOT);
            if (value.equals("s") || value.equals("si") || value.equals("sí")) {
                return true;
            }
            if (value.equals("n") || value.equals("no")) {
                return false;
            }
            System.out.println("Responda s o n.");
        }
    }
}
