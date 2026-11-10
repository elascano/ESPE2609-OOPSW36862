package ec.edu.espe.electromechanicalsystem.controller;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Business rules for work orders, their tasks, parts, safety regulations and closing. */
public class WorkOrderController {
    private static final String CLOSED = "Closed";
    private static final String EQUIPMENT_TAG = "Equipment ID: ";
    private static final Pattern EQUIPMENT_PATTERN = Pattern.compile(EQUIPMENT_TAG + "(\\d+)");

    private final SystemState state;
    private final EquipmentController equipmentController;
    private final MaintenanceController maintenanceController;

    public WorkOrderController(SystemState state, EquipmentController equipmentController,
            MaintenanceController maintenanceController) {
        this.state = state;
        this.equipmentController = equipmentController;
        this.maintenanceController = maintenanceController;
    }

    public List<WorkOrder> findAll() {
        return state.orders;
    }

    public WorkOrder findById(int id) {
        for (WorkOrder order : state.orders) {
            if (order.getWorkOrderId() == id) {
                return order;
            }
        }
        return null;
    }

    public MaintenanceTask findTask(WorkOrder order, int taskId) {
        for (MaintenanceTask task : order.getTasks()) {
            if (task.getTaskId() == taskId) {
                return task;
            }
        }
        return null;
    }

    public boolean isOpen(WorkOrder order) {
        return !CLOSED.equals(order.getStatus());
    }

    public WorkOrder create(Equipment equipment, String description, String priority) {
        WorkOrder order = equipment.requestMaintenanceOrder();
        order.setWorkOrderId(IdGenerator.nextId(state.orders, WorkOrder::getWorkOrderId));
        order.setDescription(EQUIPMENT_TAG + equipment.getEquipmentId() + " | " + description);
        order.setPriority(priority);
        state.orders.add(order);
        equipment.updateStatus("Maintenance required");
        return order;
    }

    public void assignTechnician(WorkOrder order, Technician technician) {
        technician.acceptWorkOrder(order);
        order.setStatus("In progress");
    }

    public MaintenanceTask addTask(WorkOrder order, String description, String type, float estimatedHours) {
        MaintenanceTask task = order.createTask();
        task.setTaskDescription(description);
        task.setTaskType(type);
        task.setEstimatedDuration(estimatedHours);
        return task;
    }

    public Part addPart(MaintenanceTask task, String name, int stock, float unitCost, String supplier) {
        Part part = new Part(IdGenerator.nextId(state.parts, Part::getPartId), name, stock, unitCost, supplier);
        state.parts.add(part);
        task.recordPart(part);
        return part;
    }

    public SafetyRegulation addRegulation(MaintenanceTask task, String title, String description, String riskLevel) {
        int id = IdGenerator.nextId(state.regulations, SafetyRegulation::getRegulationId);
        SafetyRegulation regulation = new SafetyRegulation(id, title, description, riskLevel);
        state.regulations.add(regulation);
        task.verifySafety(regulation);
        return regulation;
    }

    public void executeTask(MaintenanceTask task) {
        task.executeTask();
    }

    /**
     * Closes the order when all its tasks are completed, records the maintenance history,
     * and generates the report and the alert.
     * @return the generated report, or null if the order still has pending tasks
     */
    public Report closeOrder(WorkOrder order) {
        order.closeOrder();
        if (isOpen(order)) {
            return null;
        }
        recordHistoryFor(order);
        Report report = order.createReport();
        report.setReportId(IdGenerator.nextId(state.reports, Report::getReportId));
        state.reports.add(report);

        Alert alert = order.createAlert();
        alert.setMessage("Work order completed");
        alert.setIsResolved(true);
        maintenanceController.registerAlert(alert);
        return report;
    }

    public List<WorkOrder> ordersFor(Equipment equipment) {
        List<WorkOrder> orders = new ArrayList<>();
        for (WorkOrder order : state.orders) {
            if (equipmentIdOf(order) == equipment.getEquipmentId()) {
                orders.add(order);
            }
        }
        return orders;
    }

    private void recordHistoryFor(WorkOrder order) {
        Equipment equipment = equipmentController.findById(equipmentIdOf(order));
        if (equipment == null) {
            return;
        }
        StringBuilder details = new StringBuilder(order.getDescription());
        for (MaintenanceTask task : order.getTasks()) {
            details.append(" | Task: ").append(task.getTaskDescription());
            for (Part part : task.getParts()) {
                details.append(" | Part: ").append(part.getName()).append(" x ").append(part.getStockQuantity());
            }
        }
        maintenanceController.recordHistory(equipment, "Corrective/Completed work order", details.toString());
    }

    private static int equipmentIdOf(WorkOrder order) {
        Matcher matcher = EQUIPMENT_PATTERN.matcher(order.getDescription());
        return matcher.find() ? Integer.parseInt(matcher.group(1)) : -1;
    }
}
