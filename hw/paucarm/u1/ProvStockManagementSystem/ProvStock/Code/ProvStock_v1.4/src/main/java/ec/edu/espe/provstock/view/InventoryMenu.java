package ec.edu.espe.provstock.view;

import ec.edu.espe.provstock.controller.AccessManager;
import ec.edu.espe.provstock.controller.Inventory;
import ec.edu.espe.provstock.controller.ProductCatalog;
import ec.edu.espe.provstock.controller.StockMonitor;
import ec.edu.espe.provstock.model.Product;
import ec.edu.espe.provstock.model.StockMovement;
import java.util.List;

/**
 * UC-12 View inventory, UC-13 Adjust stock and UC-18 Receive stock alerts. (RF05, RF08)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class InventoryMenu {

    private final InputReader input;
    private final Inventory inventory;
    private final ProductCatalog productCatalog;
    private final StockMonitor stockMonitor;
    private final AccessManager accessManager;
    private final ConsoleHelper console;

    public InventoryMenu(AppContext context, ConsoleHelper console) {
        this.input = context.getInput();
        this.inventory = context.getInventory();
        this.productCatalog = context.getProductCatalog();
        this.stockMonitor = context.getStockMonitor();
        this.accessManager = context.getAccessManager();
        this.console = console;
    }

    public void viewInventory() {
        //UC-12 2a: the list can be filtered by name or category
        String text = input.readOptionalText("Filtrar por código o nombre (Enter para todos): ");
        String category = input.readOptionalOption("Categoría:", Product.CATEGORIES);
        if (text.isEmpty() && (category == null || category.isBlank())) {
            console.printProducts(inventory.getStockOverview());
        } else {
            console.printProducts(productCatalog.search(text, category));
        }
        String code = input.readOptionalText("Código para ver sus movimientos (Enter para volver): ");
        if (code.isEmpty()) {
            return;
        }
        printMovements(productCatalog.findByCode(code));
    }

    private void printMovements(Product product) {
        List<StockMovement> movements = inventory.getMovementsByProduct(product);
        System.out.println("Movimientos de " + product.getName() + ":");
        System.out.printf("%-4s %-17s %-12s %10s %-12s %s%n", "Id", "Fecha", "Tipo", "Cantidad", "Usuario",
                "Referencia");
        for (StockMovement movement : movements) {
            System.out.printf("%-4d %-17s %-12s %+10.2f %-12s %s%n", movement.getId(),
                    movement.getDateTime().format(TextFormat.DATE_TIME), movement.getType(), movement.getQuantity(),
                    movement.getUser().getUsername(), reference(movement));
        }
        System.out.println("Stock actual: " + product.getCurrentStock() + " " + product.getUnitOfMeasure());
    }

    /**
     * The sale or purchase that caused the movement, or the reason of an adjustment.
     */
    private String reference(StockMovement movement) {
        if (movement.getSale() != null) {
            return "Venta Nº " + movement.getSale().getNumber();
        }
        if (movement.getPurchase() != null) {
            return "Compra Nº " + movement.getPurchase().getNumber();
        }
        return movement.getReason();
    }

    public void adjustStock() {
        Product product = console.findProduct();
        System.out.println("Stock actual de " + product.getName() + ": " + product.getCurrentStock());
        double quantity = input.readDouble("Cantidad a ajustar (positiva suma, negativa resta): ");
        String reason = input.readText("Motivo del ajuste: ");
        inventory.adjustStock(product, quantity, reason, accessManager.getCurrentUser());
        System.out.println("Stock ajustado. Nuevo stock: " + product.getCurrentStock());
        if (stockMonitor.checkProduct(product)) {
            System.out.println("Aviso: el producto quedó con stock bajo.");
        }
    }

    public void stockAlerts() {
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
            System.out.printf("%-10s %-30s %10.2f %10.2f %-6s  %-10s%n", product.getCode(),
                    TextFormat.cut(product.getName(), 30), product.getCurrentStock(), product.getMinimumStock(),
                    product.getUnitOfMeasure(), TextFormat.location(product));
        }
    }
}
