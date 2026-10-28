package ec.edu.espe.provstock.view;

import ec.edu.espe.provstock.controller.ReportGenerator;
import ec.edu.espe.provstock.persistence.CsvReportExporter;
import ec.edu.espe.provstock.utils.CsvWriter;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

/**
 * UC-19 Generate reports, which includes UC-20 Export report to CSV. (RF09)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class ReportMenu {

    private static final DateTimeFormatter FILE_DATE = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private final InputReader input;
    private final ReportGenerator reportGenerator;
    private final ConsoleHelper console;
    private final CsvReportExporter exporter = new CsvReportExporter();

    public ReportMenu(AppContext context, ConsoleHelper console) {
        this.input = context.getInput();
        this.reportGenerator = context.getReportGenerator();
        this.console = console;
    }

    public void generateReports() {
        while (true) {
            int option = console.showMenu("Generar reportes", "Volver", "Reporte de ventas",
                    "Reporte de inventario", "Reporte de compras por proveedor", "Reporte de productos sin movimiento");
            switch (option) {
                case 1 -> console.runSafely(this::salesReport);
                case 2 -> console.runSafely(this::inventoryReport);
                case 3 -> console.runSafely(this::purchaseReport);
                case 4 -> console.runSafely(this::slowMovingReport);
                case 0 -> {
                    return;
                }
                default -> console.showInvalidOption();
            }
        }
    }

    private void salesReport() {
        var report = reportGenerator.generateSalesReport(input.readDate("Desde"), input.readDate("Hasta"));
        showAndExport(report, "reporte_ventas", path -> exporter.export(report, path));
    }

    private void inventoryReport() {
        var report = reportGenerator.generateInventoryReport();
        showAndExport(report, "reporte_inventario", path -> exporter.export(report, path));
    }

    private void purchaseReport() {
        var report = reportGenerator.generatePurchaseReport(input.readDate("Desde"), input.readDate("Hasta"));
        showAndExport(report, "reporte_compras", path -> exporter.export(report, path));
    }

    private void slowMovingReport() {
        var report = reportGenerator.generateSlowMovingReport(input.readDate("Desde"), input.readDate("Hasta"));
        showAndExport(report, "reporte_sin_movimiento", path -> exporter.export(report, path));
    }

    /**
     * UC-20: the report is shown and saved as a CSV file in the "reports" folder.
     * The file name has the date and time, so old reports are not replaced.
     */
    private void showAndExport(Object report, String baseName, Consumer<String> exporter) {
        System.out.println(report);
        String fileName = CsvWriter.REPORTS_FOLDER + File.separator + baseName + "_"
                + LocalDateTime.now().format(FILE_DATE) + ".csv";
        exporter.accept(fileName);
        System.out.println("Reporte CSV guardado en: " + new File(fileName).getAbsolutePath());
    }
}
