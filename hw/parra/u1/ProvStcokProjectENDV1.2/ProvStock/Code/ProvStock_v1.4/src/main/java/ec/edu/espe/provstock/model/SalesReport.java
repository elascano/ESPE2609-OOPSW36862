package ec.edu.espe.provstock.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Sales report of a period (FR-09.1). (RF09)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class SalesReport {

    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime generatedAt;
    private List<Sale> sales;

    public SalesReport(LocalDate startDate, LocalDate endDate, List<Sale> sales) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.sales = new ArrayList<>(sales);
    }

    /**
     * Keeps only the sales of the period and saves the date of generation.
     */
    public void generate() {
        List<Sale> salesOfPeriod = new ArrayList<>();
        for (Sale sale : sales) {
            LocalDate date = sale.getDateTime().toLocalDate();
            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                salesOfPeriod.add(sale);
            }
        }
        sales = salesOfPeriod;
        generatedAt = LocalDateTime.now();
    }

    /**
     * Voided sales are not counted.
     */
    public int getSalesCount() {
        int count = 0;
        for (Sale sale : sales) {
            if (!sale.isVoided()) {
                count++;
            }
        }
        return count;
    }

    public double getTotalSold() {
        double total = 0;
        for (Sale sale : sales) {
            if (!sale.isVoided()) {
                total = total + sale.getTotal();
            }
        }
        return round(total);
    }

    public double getVatCollected() {
        double vat = 0;
        for (Sale sale : sales) {
            if (!sale.isVoided()) {
                vat = vat + sale.getVatAmount();
            }
        }
        return round(vat);
    }

    public double getTotalBySalesperson(User user) {
        double total = 0;
        for (Sale sale : sales) {
            if (!sale.isVoided() && sale.getRegisteredBy() == user) {
                total = total + sale.getTotal();
            }
        }
        return round(total);
    }

    public List<Product> getBestSellingProducts(int limit) {
        Map<Product, Double> quantities = getQuantitiesSold();
        List<Product> products = new ArrayList<>(quantities.keySet());
        products.sort((a, b) -> Double.compare(quantities.get(b), quantities.get(a)));
        if (products.size() > limit) {
            products = new ArrayList<>(products.subList(0, limit));
        }
        return products;
    }

    private Map<Product, Double> getQuantitiesSold() {
        Map<Product, Double> quantities = new LinkedHashMap<>();
        for (Sale sale : sales) {
            if (sale.isVoided()) {
                continue;
            }
            for (SaleDetail detail : sale.getDetails()) {
                quantities.merge(detail.getProduct(), detail.getQuantity(), Double::sum);
            }
        }
        return quantities;
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("REPORTE DE VENTAS\n");
        text.append("Periodo: ").append(startDate).append(" a ").append(endDate).append("\n");
        text.append("Generado: ").append(generatedAt == null ? "-" : generatedAt.format(DATE_TIME)).append("\n");
        appendSales(text);
        appendTotalsBySalesperson(text);
        appendBestSellingProducts(text);
        return text.toString();
    }

    private void appendSales(StringBuilder text) {
        text.append("-".repeat(86)).append("\n");
        text.append(String.format("%-6s %-17s %-22s %-14s %-10s %10s%n",
                "Num", "Fecha", "Cliente", "Vendedor", "Estado", "Total"));
        for (Sale sale : sales) {
            text.append(String.format("%-6d %-17s %-22s %-14s %-10s %10.2f%n",
                    sale.getNumber(), sale.getDateTime().format(DATE_TIME), cut(sale.getCustomer().getName(), 22),
                    cut(sale.getRegisteredBy().getUsername(), 14), sale.getStatus(), sale.getTotal()));
        }
        text.append("-".repeat(86)).append("\n");
        text.append(String.format("Ventas registradas: %d%n", getSalesCount()));
        text.append(String.format("Total vendido:      $%.2f%n", getTotalSold()));
        text.append(String.format("IVA recaudado:      $%.2f%n", getVatCollected()));
    }

    private void appendTotalsBySalesperson(StringBuilder text) {
        text.append("\nTotal por vendedor:\n");
        List<User> salespeople = new ArrayList<>();
        for (Sale sale : sales) {
            if (!salespeople.contains(sale.getRegisteredBy())) {
                salespeople.add(sale.getRegisteredBy());
            }
        }
        for (User user : salespeople) {
            text.append(String.format("  %-30s $%.2f%n", user.getFullName(), getTotalBySalesperson(user)));
        }
    }

    private void appendBestSellingProducts(StringBuilder text) {
        text.append("\nProductos mas vendidos:\n");
        Map<Product, Double> quantities = getQuantitiesSold();
        int position = 1;
        for (Product product : getBestSellingProducts(5)) {
            text.append(String.format("  %d. %-12s %-35s %.2f %s%n", position, product.getCode(),
                    cut(product.getName(), 35), quantities.get(product), product.getUnitOfMeasure()));
            position++;
        }
    }

    private static String cut(String text, int length) {
        return text.length() <= length ? text : text.substring(0, length - 1) + ".";
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
     * @return the sales
     */
    public List<Sale> getSales() {
        return sales;
    }
}
