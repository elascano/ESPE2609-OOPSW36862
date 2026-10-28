package ec.edu.espe.provstock.view;

import ec.edu.espe.provstock.controller.AccessManager;
import ec.edu.espe.provstock.controller.CustomerManager;
import ec.edu.espe.provstock.controller.SalesManager;
import ec.edu.espe.provstock.controller.StockMonitor;
import ec.edu.espe.provstock.model.Customer;
import ec.edu.espe.provstock.model.Product;
import ec.edu.espe.provstock.model.Sale;
import ec.edu.espe.provstock.model.SaleDetail;
import ec.edu.espe.provstock.model.SaleFilter;
import java.util.List;

/**
 * UC-08 Register sale, UC-09 Void sale, UC-10 Update VAT rate and UC-16 View sales history.
 * (RF04, RF07)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class SalesMenu {

    private final InputReader input;
    private final SalesManager salesManager;
    private final CustomerManager customerManager;
    private final AccessManager accessManager;
    private final StockMonitor stockMonitor;
    private final ConsoleHelper console;
    private final CustomerMenu customerMenu;

    public SalesMenu(AppContext context, ConsoleHelper console, CustomerMenu customerMenu) {
        this.input = context.getInput();
        this.salesManager = context.getSalesManager();
        this.customerManager = context.getCustomerManager();
        this.accessManager = context.getAccessManager();
        this.stockMonitor = context.getStockMonitor();
        this.console = console;
        this.customerMenu = customerMenu;
    }

    // =====================================================================
    // UC-08 Register sale (includes UC-06 and UC-11)
    // =====================================================================
    public void registerSale() {
        Customer customer = chooseCustomerForSale();
        Sale sale = salesManager.createSale(customer);
        System.out.println("Venta para: " + customer.getName() + " | IVA " + TextFormat.percent(sale.getVatRate()));
        boolean finished = false;
        while (!finished) {
            finished = runSaleOption(sale, showSaleMenu(sale));
        }
    }

    private int showSaleMenu(Sale sale) {
        String title = "Nueva venta (" + sale.getDetails().size() + " producto(s), total $"
                + String.format("%.2f", sale.getTotal()) + ")";
        return console.showMenu(title, "Cancelar venta", "Agregar producto", "Quitar producto", "Ver detalle",
                "Confirmar venta");
    }

    /**
     * @return true when the sale is confirmed or cancelled
     */
    private boolean runSaleOption(Sale sale, int option) {
        switch (option) {
            case 1 -> console.runSafely(() -> addProductToSale(sale));
            case 2 -> console.runSafely(() -> removeProductFromSale(sale));
            case 3 -> printSale(sale);
            case 4 -> {
                return confirmSale(sale);
            }
            case 0 -> {
                System.out.println("Venta cancelada.");
                return true;
            }
            default -> console.showInvalidOption();
        }
        return false;
    }

    /**
     * Enter = "Consumidor Final". If the customer does not exist, it can be
     * registered in the middle of the sale (alternative flow 2a of UC-08).
     */
    private Customer chooseCustomerForSale() {
        String number = input.readOptionalText("Identificación del cliente (Enter = Consumidor Final): ");
        if (number.isEmpty()) {
            return customerManager.getFinalConsumer();
        }
        if (!customerManager.isRegistered(number)) {
            return chooseUnregisteredCustomer();
        }
        Customer customer = customerManager.findByIdentification(number);
        if (!customer.isActive()) {
            throw new IllegalArgumentException("El cliente está inactivo.");
        }
        return customer;
    }

    private Customer chooseUnregisteredCustomer() {
        System.out.println("El cliente no está registrado.");
        if (input.readYesNo("¿Desea registrarlo ahora?")) {
            return customerMenu.registerCustomer();
        }
        if (input.readYesNo("¿Vender a Consumidor Final?")) {
            return customerManager.getFinalConsumer();
        }
        throw new IllegalArgumentException("Venta cancelada: no se eligió un cliente.");
    }

    private void addProductToSale(Sale sale) {
        //UC-08 includes UC-06: search the product
        Product product = console.searchAndChooseProduct("Buscar producto (código o nombre, Enter para ver todos): ");
        double quantity = input.readPositiveDouble("Cantidad (" + product.getUnitOfMeasure() + "): ");
        sale.addProduct(product, quantity);
        System.out.println("Agregado: " + product.getName() + " x " + quantity);
    }

    private void removeProductFromSale(Sale sale) {
        sale.removeProduct(console.findProduct());
        System.out.println("Producto quitado.");
    }

    private boolean confirmSale(Sale sale) {
        if (sale.getDetails().isEmpty()) {
            System.out.println("Agregue al menos un producto.");
            return false;
        }
        sale.setPaymentMethod(input.readOption("Forma de pago:", Sale.PAYMENT_METHODS));
        printSale(sale);
        if (!input.readYesNo("¿Confirmar la venta?")) {
            return false;
        }
        try {
            salesManager.registerSale(sale);
        } catch (IllegalArgumentException e) {
            //the sale stays open, so the user can correct it
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        System.out.println("Venta Nº " + sale.getNumber() + " registrada correctamente.");
        warnLowStock(sale);
        return true;
    }

    /**
     * UC-11 includes UC-17: warn if a product reached its minimum stock.
     */
    private void warnLowStock(Sale sale) {
        for (SaleDetail detail : sale.getDetails()) {
            if (stockMonitor.checkProduct(detail.getProduct())) {
                System.out.println("  Aviso: " + detail.getProduct().getName() + " quedó con stock bajo ("
                        + detail.getProduct().getCurrentStock() + ").");
            }
        }
    }

    private void printSale(Sale sale) {
        System.out.println(TextFormat.LINE);
        printSaleHeader(sale);
        printSaleLines(sale);
        printSaleTotals(sale);
        System.out.println(TextFormat.LINE);
    }

    private void printSaleHeader(Sale sale) {
        if (sale.getNumber() != 0) {
            System.out.println("Venta Nº " + sale.getNumber() + "   " + sale.getDateTime().format(TextFormat.DATE_TIME)
                    + "   Estado: " + sale.getStatus());
        }
        System.out.println("Cliente: " + sale.getCustomer().getName() + " (" + sale.getCustomer().getIdentification()
                + ")");
        System.out.println("Vendedor: " + sale.getRegisteredBy().getFullName() + "   Pago: " + sale.getPaymentMethod());
    }

    private void printSaleLines(Sale sale) {
        System.out.printf("%-10s %-26s %10s %10s %10s%n", "Código", "Producto", "Cantidad", "P. unit.", "Subtotal");
        for (SaleDetail detail : sale.getDetails()) {
            System.out.printf("%-10s %-26s %10.2f %10.2f %10.2f%n", detail.getProduct().getCode(),
                    TextFormat.cut(detail.getProduct().getName(), 26), detail.getQuantity(), detail.getUnitPrice(),
                    detail.calculateSubtotal());
        }
    }

    private void printSaleTotals(Sale sale) {
        sale.calculateTotal();
        System.out.printf("%60s %10.2f%n", "Subtotal:", sale.getSubtotal());
        System.out.printf("%60s %10.2f%n", "IVA " + TextFormat.percent(sale.getVatRate()) + ":", sale.getVatAmount());
        System.out.printf("%60s %10.2f%n", "TOTAL:", sale.getTotal());
        if (sale.isVoided()) {
            System.out.println("ANULADA el " + sale.getVoidDateTime().format(TextFormat.DATE_TIME) + " por "
                    + sale.getVoidedBy().getUsername() + ". Motivo: " + sale.getVoidReason());
        }
    }

    // =====================================================================
    // UC-09 Void sale, UC-10 Update VAT rate
    // =====================================================================
    public void voidSale() {
        Sale sale = salesManager.findSaleByNumber(input.readInt("Número de venta: "));
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

    public void updateVatRate() {
        System.out.println("Tasa de IVA actual: " + TextFormat.percent(salesManager.getVatRate()));
        double newRate = input.readNonNegativeDouble("Nueva tasa en porcentaje (por ejemplo 15): ");
        if (input.readYesNo("¿Cambiar el IVA a " + newRate + "%? Solo afecta a las ventas nuevas")) {
            salesManager.updateVatRate(newRate / 100.0);
            System.out.println("Tasa de IVA actualizada a " + TextFormat.percent(salesManager.getVatRate()) + ".");
        }
    }

    // =====================================================================
    // UC-16 View sales history
    // =====================================================================
    public void salesHistory() {
        System.out.println("Filtros (presione Enter para no usar un filtro):");
        SaleFilter filter = new SaleFilter().from(input.readOptionalDate("Desde")).to(input.readOptionalDate("Hasta"));
        String username = input.readOptionalText("Usuario del vendedor (Enter para todos): ");
        if (!username.isEmpty()) {
            filter.bySalesperson(accessManager.findUser(username));
        }
        String identification = input.readOptionalText("Identificación del cliente (Enter para todos): ");
        if (!identification.isEmpty()) {
            filter.byCustomer(customerManager.findByIdentification(identification));
        }
        filter.withStatus(input.readOptionalOption("Estado:", new String[]{Sale.REGISTERED, Sale.VOIDED}));
        showSalesList(salesManager.filterSales(filter));
    }

    private void showSalesList(List<Sale> sales) {
        if (sales.isEmpty()) {
            System.out.println("No hay ventas con esos filtros.");
            return;
        }
        System.out.printf("%-6s %-17s %-26s %-12s %-11s %10s%n", "Nº", "Fecha", "Cliente", "Vendedor", "Estado",
                "Total");
        for (Sale sale : sales) {
            System.out.printf("%-6d %-17s %-26s %-12s %-11s %10.2f%n", sale.getNumber(),
                    sale.getDateTime().format(TextFormat.DATE_TIME), TextFormat.cut(sale.getCustomer().getName(), 26),
                    sale.getRegisteredBy().getUsername(), sale.getStatus(), sale.getTotal());
        }
        int number = input.readInt("Número de venta para ver el detalle (0 para volver): ");
        if (number != 0) {
            printSale(salesManager.findSaleByNumber(number));
        }
    }
}
