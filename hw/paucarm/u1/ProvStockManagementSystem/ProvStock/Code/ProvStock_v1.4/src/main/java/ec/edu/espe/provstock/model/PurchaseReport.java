package ec.edu.espe.provstock.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Purchases report by supplier of a period (FR-09.3). (RF09)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class PurchaseReport {

    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime generatedAt;
    private List<Purchase> purchases;

    public PurchaseReport(LocalDate startDate, LocalDate endDate, List<Purchase> purchases) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.purchases = new ArrayList<>(purchases);
    }

    /**
     * Keeps only the purchases of the period and saves the date of generation.
     */
    public void generate() {
        List<Purchase> purchasesOfPeriod = new ArrayList<>();
        for (Purchase purchase : purchases) {
            LocalDate date = purchase.getDateTime().toLocalDate();
            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                purchasesOfPeriod.add(purchase);
            }
        }
        purchases = purchasesOfPeriod;
        generatedAt = LocalDateTime.now();
    }

    public double getTotalCost() {
        double total = 0;
        for (Purchase purchase : purchases) {
            total = total + purchase.getTotal();
        }
        return Math.round(total * 100.0) / 100.0;
    }

    public double getTotalBySupplier(Supplier supplier) {
        double total = 0;
        for (Purchase purchase : purchases) {
            if (purchase.getSupplier() == supplier) {
                total = total + purchase.getTotal();
            }
        }
        return Math.round(total * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("REPORTE DE COMPRAS POR PROVEEDOR\n");
        text.append("Periodo: ").append(startDate).append(" a ").append(endDate).append("\n");
        text.append("Generado: ").append(generatedAt == null ? "-" : generatedAt.format(DATE_TIME)).append("\n");
        for (Supplier supplier : getSuppliers()) {
            appendSupplier(text, supplier);
        }
        text.append("-".repeat(80)).append("\n");
        text.append(String.format("Compras: %d%n", purchases.size()));
        text.append(String.format("Costo total: $%.2f%n", getTotalCost()));
        return text.toString();
    }

    private List<Supplier> getSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        for (Purchase purchase : purchases) {
            if (!suppliers.contains(purchase.getSupplier())) {
                suppliers.add(purchase.getSupplier());
            }
        }
        return suppliers;
    }

    private void appendSupplier(StringBuilder text, Supplier supplier) {
        text.append("-".repeat(80)).append("\n");
        text.append(supplier.getName()).append(" (RUC ").append(supplier.getRuc()).append(")\n");
        for (Purchase purchase : purchases) {
            if (purchase.getSupplier() == supplier) {
                String invoice = purchase.getSupplierInvoiceNumber();
                text.append(String.format("  Compra %-5d %-17s Factura %-20s $%10.2f%n",
                        purchase.getNumber(), purchase.getDateTime().format(DATE_TIME),
                        invoice.isEmpty() ? "-" : invoice, purchase.getTotal()));
            }
        }
        text.append(String.format("  Total del proveedor: $%.2f%n", getTotalBySupplier(supplier)));
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
     * @return the purchases
     */
    public List<Purchase> getPurchases() {
        return purchases;
    }
}
