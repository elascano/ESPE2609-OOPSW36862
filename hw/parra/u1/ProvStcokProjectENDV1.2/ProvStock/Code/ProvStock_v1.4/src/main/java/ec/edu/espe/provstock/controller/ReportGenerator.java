package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.InventoryReport;
import ec.edu.espe.provstock.model.PurchaseReport;
import ec.edu.espe.provstock.model.SaleFilter;
import ec.edu.espe.provstock.model.SalesReport;
import ec.edu.espe.provstock.model.SlowMovingProductsReport;
import ec.edu.espe.provstock.persistence.FileManager;
import java.time.LocalDate;

/**
 * Creates the four reports (UC-19, UC-20). (RF09)
 * Before every report the data is read again from the JSON database (FileManager),
 * so the report always shows what is saved; then the report is exported to CSV.
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class ReportGenerator {

    //directed associations to other control classes (note N8 of the UML Design Specification)
    private final SalesManager salesManager;
    private final PurchasingManager purchasingManager;
    private final ProductCatalog productCatalog;
    private final FileManager fileManager;

    public ReportGenerator(SalesManager salesManager, PurchasingManager purchasingManager,
            ProductCatalog productCatalog, FileManager fileManager) {
        this.salesManager = salesManager;
        this.purchasingManager = purchasingManager;
        this.productCatalog = productCatalog;
        this.fileManager = fileManager;
    }

    public SalesReport generateSalesReport(LocalDate start, LocalDate end) {
        checkPeriod(start, end);
        readDatabase();
        SalesReport report = new SalesReport(start, end, salesManager.filterSales(new SaleFilter().from(start).to(end)));
        report.generate();
        return report;
    }

    public InventoryReport generateInventoryReport() {
        readDatabase();
        InventoryReport report = new InventoryReport(productCatalog.getActiveProducts());
        report.generate();
        return report;
    }

    public PurchaseReport generatePurchaseReport(LocalDate start, LocalDate end) {
        checkPeriod(start, end);
        readDatabase();
        PurchaseReport report = new PurchaseReport(start, end, purchasingManager.getPurchases(start, end));
        report.generate();
        return report;
    }

    public SlowMovingProductsReport generateSlowMovingReport(LocalDate start, LocalDate end) {
        checkPeriod(start, end);
        readDatabase();
        SlowMovingProductsReport report = new SlowMovingProductsReport(start, end,
                productCatalog.getActiveProducts(), salesManager.filterSales(new SaleFilter().from(start).to(end)));
        report.generate();
        return report;
    }

    /**
     * Reads the JSON files again, so the report uses the data saved in the database.
     */
    private void readDatabase() {
        if (fileManager != null && fileManager.hasSavedData()) {
            fileManager.loadAll();
        }
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
