package ec.edu.espe.electromechanicalmaintenancemanagementsystem.view;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Equipment;
import ec.edu.espe.electromechanicalsystem.controller.MaintenanceController;
import java.time.LocalDate;
import java.util.List;

/** Console screens for maintenance schedules, histories and upcoming alerts. */
public class MaintenanceView {
    private final MaintenanceController controller;
    private final EquipmentView equipmentView;
    private final ConsoleInput input;

    public MaintenanceView(MaintenanceController controller, EquipmentView equipmentView, ConsoleInput input) {
        this.controller = controller;
        this.equipmentView = equipmentView;
        this.input = input;
    }

    public void schedule() {
        Equipment equipment = equipmentView.select();
        if (equipment == null) {
            return;
        }
        String frequency = input.readText("Frequency: ");
        String date = input.readDate("Scheduled date (YYYY-MM-DD, today or later): ");
        boolean preventive = input.readBoolean("Preventive maintenance (yes/no): ");
        System.out.println(controller.schedule(equipment, frequency, date, preventive));
    }

    public void recordHistory() {
        Equipment equipment = equipmentView.select();
        if (equipment == null) {
            return;
        }
        String type = input.readText("Maintenance type: ");
        String description = input.readText("Description: ");
        System.out.println(controller.recordHistory(equipment, type, description));
    }

    public void showUpcoming() {
        List<String> notices = controller.upcomingNotices(LocalDate.now());
        if (notices.isEmpty()) {
            System.out.println("No upcoming or overdue maintenance in the next 30 days.");
        }
        notices.forEach(System.out::println);
    }
}
