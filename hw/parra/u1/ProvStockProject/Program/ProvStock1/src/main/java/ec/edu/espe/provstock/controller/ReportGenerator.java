package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.InventoryReport;
import ec.edu.espe.provstock.model.PurchaseReport;
import ec.edu.espe.provstock.model.SalesReport;
import ec.edu.espe.provstock.model.SlowMovingProductsReport;
import java.time.LocalDate;

/**
 * Creates the four reports (UC-19, UC-20). (RF09)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class ReportGenerator {

    //dependencies «use»
    private final SalesManager salesManager;
    private final PurchasingManager purchasingManager;
    private final ProductCatalog productCatalog;

    public ReportGenerator(SalesManager salesManager, PurchasingManager purchasingManager,
            ProductCatalog productCatalog) {
        this.salesManager = salesManager;
        this.purchasingManager = purchasingManager;
        this.productCatalog = productCatalog;
    }

    public SalesReport generateSalesReport(LocalDate start, LocalDate end) {
        checkPeriod(start, end);
        SalesReport report = new SalesReport(start, end, salesManager.filterSales(start, end, null, null, null));
        report.generate();
        return report;
    }

    public InventoryReport generateInventoryReport() {
        InventoryReport report = new InventoryReport(productCatalog.getActiveProducts());
        report.generate();
        return report;
    }

    public PurchaseReport generatePurchaseReport(LocalDate start, LocalDate end) {
        checkPeriod(start, end);
        PurchaseReport report = new PurchaseReport(start, end, purchasingManager.getPurchases(start, end));
        report.generate();
        return report;
    }

    public SlowMovingProductsReport generateSlowMovingReport(LocalDate start, LocalDate end) {
        checkPeriod(start, end);
        SlowMovingProductsReport report = new SlowMovingProductsReport(start, end,
                productCatalog.getActiveProducts(), salesManager.filterSales(start, end, null, null, null));
        report.generate();
        return report;
    }

    private void checkPeriod(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Debe ingresar la fecha de inicio y la fecha de fin");
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }
    }
}
