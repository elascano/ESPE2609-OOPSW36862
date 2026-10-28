package ec.edu.espe.provstock.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Products without sales in a period (FR-09.5). (RF09)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class SlowMovingProductsReport {

    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime generatedAt;
    private List<Product> products;
    private List<Sale> sales;

    public SlowMovingProductsReport(LocalDate startDate, LocalDate endDate, List<Product> products,
            List<Sale> sales) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.products = new ArrayList<>(products);
        this.sales = new ArrayList<>(sales);
    }

    /**
     * Keeps the active products and the sales of the period that were not voided.
     */
    public void generate() {
        List<Product> activeProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.isActive()) {
                activeProducts.add(product);
            }
        }
        List<Sale> salesOfPeriod = new ArrayList<>();
        for (Sale sale : sales) {
            LocalDate date = sale.getDateTime().toLocalDate();
            if (!sale.isVoided() && !date.isBefore(startDate) && !date.isAfter(endDate)) {
                salesOfPeriod.add(sale);
            }
        }
        products = activeProducts;
        sales = salesOfPeriod;
        generatedAt = LocalDateTime.now();
    }

    public List<Product> getProductsWithoutSales() {
        List<Product> productsWithoutSales = new ArrayList<>();
        for (Product product : products) {
            boolean sold = false;
            for (Sale sale : sales) {
                for (SaleDetail detail : sale.getDetails()) {
                    if (detail.getProduct() == product) {
                        sold = true;
                    }
                }
            }
            if (!sold) {
                productsWithoutSales.add(product);
            }
        }
        return productsWithoutSales;
    }

    /**
     * Value of the stock that did not move (money stopped in the warehouse).
     */
    public double getImmobilizedValue() {
        double total = 0;
        for (Product product : getProductsWithoutSales()) {
            total = total + product.getStockValue();
        }
        return Math.round(total * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("REPORTE DE PRODUCTOS SIN MOVIMIENTO\n");
        text.append("Periodo: ").append(startDate).append(" a ").append(endDate).append("\n");
        text.append("Generado: ").append(generatedAt == null ? "-" : generatedAt.format(DATE_TIME)).append("\n");
        text.append("-".repeat(76)).append("\n");
        text.append(String.format("%-10s %-32s %-6s %10s %12s%n", "Codigo", "Producto", "Unidad", "Stock", "Valor"));
        List<Product> productsWithoutSales = getProductsWithoutSales();
        for (Product product : productsWithoutSales) {
            String name = product.getName().length() <= 32 ? product.getName()
                    : product.getName().substring(0, 31) + ".";
            text.append(String.format("%-10s %-32s %-6s %10.2f %12.2f%n", product.getCode(), name,
                    product.getUnitOfMeasure(), product.getCurrentStock(), product.getStockValue()));
        }
        text.append("-".repeat(76)).append("\n");
        text.append(String.format("Productos sin ventas: %d%n", productsWithoutSales.size()));
        text.append(String.format("Valor inmovilizado: $%.2f%n", getImmobilizedValue()));
        return text.toString();
    }

    /**
     * @return the startDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @return the endDate
     */
    public LocalDate getEndDate() {
        return endDate;
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

    /**
     * @return the sales
     */
    public List<Sale> getSales() {
        return sales;
    }
}
