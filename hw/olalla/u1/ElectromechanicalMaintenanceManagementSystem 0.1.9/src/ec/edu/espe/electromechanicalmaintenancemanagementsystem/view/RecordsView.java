package ec.edu.espe.electromechanicalmaintenancemanagementsystem.view;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.WorkOrder;
import ec.edu.espe.electromechanicalsystem.controller.CsvExporter;
import ec.edu.espe.electromechanicalsystem.controller.SystemState;
import java.io.IOException;
import java.nio.file.Path;

/** Console screens that show every stored record and export them to CSV. */
public class RecordsView {
    private final SystemState state;

    public RecordsView(SystemState state) {
        this.state = state;
    }

    public void showAll() {
        ConsoleOutput.section("CLIENTS", state.clients);
        ConsoleOutput.section("EQUIPMENT", state.equipments);
        ConsoleOutput.section("QR CODES", state.qrCodes);
        ConsoleOutput.section("TECHNICIANS", state.technicians);
        ConsoleOutput.section("MAINTENANCE SCHEDULES", state.schedules);
        ConsoleOutput.section("WORK ORDERS", state.orders);
        for (WorkOrder order : state.orders) {
            ConsoleOutput.section("Tasks for work order " + order.getWorkOrderId(), order.getTasks());
        }
        ConsoleOutput.section("PARTS", state.parts);
        ConsoleOutput.section("SAFETY REGULATIONS", state.regulations);
        ConsoleOutput.section("MAINTENANCE HISTORIES", state.histories);
        ConsoleOutput.section("ALERTS", state.alerts);
        ConsoleOutput.section("REPORTS", state.reports);
    }

    public void exportCsv() {
        Path folder = Path.of("").toAbsolutePath();
        try {
            int count = CsvExporter.exportAll(state, folder);
            System.out.println("CSV files generated successfully.");
            System.out.println("Folder: " + folder);
            System.out.println("Records exported: " + count);
        } catch (IOException | RuntimeException exception) {
            System.out.println("Could not generate CSV files: " + exception.getMessage());
        }
    }
}
