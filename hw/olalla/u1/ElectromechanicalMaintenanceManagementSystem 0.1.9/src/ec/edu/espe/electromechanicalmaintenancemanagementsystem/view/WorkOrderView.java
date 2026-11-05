package ec.edu.espe.electromechanicalmaintenancemanagementsystem.view;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.*;
import ec.edu.espe.electromechanicalsystem.controller.WorkOrderController;
import java.util.List;

/** Console screens for work orders, tasks, parts, safety regulations and closing. */
public class WorkOrderView {
    private final WorkOrderController controller;
    private final EquipmentView equipmentView;
    private final TechnicianView technicianView;
    private final ConsoleInput input;

    public WorkOrderView(WorkOrderController controller, EquipmentView equipmentView,
            TechnicianView technicianView, ConsoleInput input) {
        this.controller = controller;
        this.equipmentView = equipmentView;
        this.technicianView = technicianView;
        this.input = input;
    }

    public void create() {
        Equipment equipment = equipmentView.select();
        if (equipment == null) {
            return;
        }
        String description = input.readText("Work description: ");
        String priority = input.readChoice("Priority (low/medium/high): ", "low", "medium", "high");
        System.out.println(controller.create(equipment, description, priority));
    }

    public void assignTechnician() {
        WorkOrder order = selectOrder();
        if (order == null) {
            return;
        }
        if (!controller.isOpen(order) || order.getTechnician() != null) {
            System.out.println("That work order is closed or already has a technician.");
            return;
        }
        Technician technician = technicianView.select();
        if (technician == null) {
            return;
        }
        if (!technician.getAvailabilityStatus()) {
            System.out.println("That technician is not available.");
            return;
        }
        controller.assignTechnician(order, technician);
        System.out.println("Technician assigned: " + technician.getName());
    }

    public void addTask() {
        WorkOrder order = selectOrder();
        if (order == null) {
            return;
        }
        if (!controller.isOpen(order)) {
            System.out.println("That work order is closed.");
            return;
        }
        String description = input.readText("Task description: ");
        String type = input.readText("Task type: ");
        float hours = input.readNonNegativeFloat("Estimated duration in hours (0 or greater): ");
        System.out.println(controller.addTask(order, description, type, hours));
    }

    public void registerPartAndRegulation() {
        MaintenanceTask task = selectPendingTask(selectOpenOrderWithTasks());
        if (task == null) {
            return;
        }
        if (input.readBoolean("Add a part (yes/no): ")) {
            String name = input.readText("Part name: ");
            int stock = input.readNonNegativeInt("Stock quantity (0 or greater): ");
            float cost = input.readNonNegativeFloat("Unit cost (0 or greater): ");
            String supplier = input.readText("Supplier: ");
            System.out.println(controller.addPart(task, name, stock, cost, supplier));
        }
        String title = input.readText("Safety regulation title: ");
        String description = input.readText("Safety description: ");
        String risk = input.readChoice("Risk level (low/medium/high/critical): ", "low", "medium", "high", "critical");
        System.out.println(controller.addRegulation(task, title, description, risk));
    }

    public void executeTask() {
        MaintenanceTask task = selectPendingTask(selectOpenOrderWithTasks());
        if (task == null) {
            return;
        }
        if (task.getRegulations().isEmpty()) {
            System.out.println("This task has no safety verification yet (use option 8 first).");
            return;
        }
        controller.executeTask(task);
        System.out.println("Task completed: " + task.getIsCompleted());
    }

    public void closeOrder() {
        WorkOrder order = selectOpenOrderWithTasks();
        if (order == null) {
            return;
        }
        Report report = controller.closeOrder(order);
        if (report == null) {
            System.out.println("Complete all tasks before closing the order.");
            return;
        }
        System.out.println(report);
        System.out.println(last(order.getAlerts()));
    }

    private WorkOrder selectOrder() {
        if (controller.findAll().isEmpty()) {
            System.out.println("No work orders yet. Create a work order first.");
            return null;
        }
        ConsoleOutput.show(controller.findAll());
        WorkOrder order = controller.findById(input.readPositiveInt("Work order ID: "));
        if (order == null) {
            System.out.println("Work order not found.");
        }
        return order;
    }

    private WorkOrder selectOpenOrderWithTasks() {
        WorkOrder order = selectOrder();
        if (order == null) {
            return null;
        }
        if (!controller.isOpen(order) || order.getTasks().isEmpty()) {
            System.out.println("No open work order with tasks found.");
            return null;
        }
        return order;
    }

    private MaintenanceTask selectPendingTask(WorkOrder order) {
        if (order == null) {
            return null;
        }
        ConsoleOutput.show(order.getTasks());
        MaintenanceTask task = controller.findTask(order, input.readPositiveInt("Task ID: "));
        if (task == null || task.getIsCompleted()) {
            System.out.println("Task not found or already completed.");
            return null;
        }
        return task;
    }

    private static <T> T last(List<T> items) {
        return items.get(items.size() - 1);
    }
}
