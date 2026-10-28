package reports;

import enums.OrderStatus;
import enums.ReportType;
import models.CashRegister;
import models.WorkOrder;
import utils.Date;
import java.util.Scanner;
import utils.ConsoleInput;

public class Report {
    private String reportId;
    private Date startDate;
    private Date endDate;
    private ReportType reportType;

    public Report(String reportId, Date startDate, Date endDate, ReportType reportType) {
        this.reportId = reportId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reportType = reportType;
    }

    public static void generateFromConsole(Scanner scanner, CashRegister cashRegister) {
        System.out.println("\n--- GENERAR REPORTE ---");
        System.out.println("Seleccione el tipo de reporte:");
        System.out.println("1. Diario");
        System.out.println("2. Semanal");
        System.out.println("3. Mensual");
        System.out.println("0. Volver");
        int option = ConsoleInput.readOption(scanner, "Opcion: ", 0, 3);
        if (option == 0) {
            return;
        }
        ReportType type = null;

        switch (option) {
            case 1 -> type = ReportType.DAILY;
            case 2 -> type = ReportType.WEEKLY;
            case 3 -> type = ReportType.MONTHLY;
            default -> {
                System.out.println("Opcion no valida.");
                return;
            }
        }

        System.out.println("\n=============================================");
        System.out.println("        REPORTE " + type + " DE FLOWDESK      ");
        System.out.println("=============================================");
        System.out.println("Fecha de emision: 24/10/2026 (Simulada)");
        System.out.println("---------------------------------------------");

        int orderCount = WorkOrder.getAll().size();
        int completedCount = 0;
        int pendingCount = 0;
        double outstandingBalance = 0;

        for (WorkOrder wo : WorkOrder.getAll()) {
            if (wo.getStatus() == OrderStatus.COMPLETED || wo.getStatus() == OrderStatus.DELIVERED) {
                completedCount++;
            } else {
                pendingCount++;
            }
            outstandingBalance += wo.getBalance();
        }

        System.out.println("[*] ESTADO DE ORDENES:");
        System.out.println("    - Total de ordenes: " + orderCount);
        System.out.println("    - Ordenes terminadas: " + completedCount);
        System.out.println("    - Ordenes pendientes: " + pendingCount);
        System.out.println("    - Dinero por cobrar (Saldos): $" + outstandingBalance);

        System.out.println("\n[*] RESUMEN FINANCIERO (CAJA):");
        System.out.println("    - Ingresos totales: $" + cashRegister.calculateIncome());
        System.out.println("    - Egresos totales:  $" + cashRegister.calculateExpenses());
        System.out.println("    - Balance neto:     $" + cashRegister.calculateBalance());

        System.out.println("=============================================");
        System.out.println("             FIN DEL REPORTE                 ");
        System.out.println("=============================================\n");
    }

    public String getReportId() { return reportId; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
    public ReportType getReportType() { return reportType; }
}
