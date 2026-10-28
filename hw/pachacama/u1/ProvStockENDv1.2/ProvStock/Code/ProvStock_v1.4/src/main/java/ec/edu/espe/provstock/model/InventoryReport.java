package ec.edu.espe.provstock.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Inventory report with the value of the stock (FR-09.2). (RF09)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class InventoryReport {

    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private LocalDateTime generatedAt;
    private List<Product> products;

    public InventoryReport(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    /**
     * Keeps only the active products, ordered by code, and saves the date of generation.
     */
    public void generate() {
        List<Product> activeProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.isActive()) {
                activeProducts.add(product);
            }
        }
        activeProducts.sort((a, b) -> a.getCode().compareToIgnoreCase(b.getCode()));
        products = activeProducts;
        generatedAt = LocalDateTime.now();
    }

    public double getTotalStockValue() {
        double total = 0;
        for (Product product : products) {
            total = total + product.getStockValue();
        }
        return Math.round(total * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("REPORTE DE INVENTARIO\n");
        text.append("Generado: ").append(generatedAt == null ? "-" : generatedAt.format(DATE_TIME)).append("\n");
        text.append("-".repeat(88)).append("\n");
        text.append(String.format("%-10s %-28s %-6s %10s %10s %9s %10s%n",
                "Codigo", "Producto", "Unidad", "Stock", "Minimo", "Costo", "Valor"));
        for (Product product : products) {
            String name = product.getName().length() <= 28 ? product.getName()
                    : product.getName().substring(0, 27) + ".";
            text.append(String.format("%-10s %-28s %-6s %10.2f %10.2f %9.2f %10.2f%s%n",
                    product.getCode(), name, product.getUnitOfMeasure(), product.getCurrentStock(),
                    product.getMinimumStock(), product.getPurchaseCost(), product.getStockValue(),
                    product.isLowStock() ? " *" : ""));
        }
        text.append("-".repeat(88)).append("\n");
        text.append(String.format("Productos: %d%n", products.size()));
        text.append(String.format("Valor total del stock: $%.2f%n", getTotalStockValue()));
        text.append("(*) Stock igual o menor al minimo\n");
        return text.toString();
    }

    /**
     * @return the generatedAt
     */
    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    /**
     * @return the products
     */
    public List<Product> getProducts() {
        return products;
    }
}
