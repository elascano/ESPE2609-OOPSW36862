package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

public class WorkOrder {
    private int workOrderId;
    private String description;
    private String priority;
    private String status;
    private String creationDate;

    private Technician technician;
    private List<MaintenanceTask> tasks = new ArrayList<>();
    private List<Alert> alerts = new ArrayList<>();
    private List<Report> reports = new ArrayList<>();

    public WorkOrder(int workOrderId, String description, String priority, String status, String creationDate) {
        this.workOrderId = workOrderId;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.creationDate = creationDate;
    }

    public int getWorkOrderId() {
        return workOrderId;
    }
    public void setWorkOrderId(int workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Technician getTechnician() {
        return technician;
    }

    public List<MaintenanceTask> getTasks() {
        return new ArrayList<>(tasks);
    }

    public List<Alert> getAlerts() {
        return new ArrayList<>(alerts);
    }

    public List<Report> getReports() {
        return new ArrayList<>(reports);
    }

    public void assignTechnician(Technician technician) {
        this.technician = technician;
    }

    public MaintenanceTask createTask() {
        MaintenanceTask task = new MaintenanceTask(tasks.size() + 1, "General inspection", "Preventive", 1.0f, false);
        tasks.add(task);
        return task;
    }

    public Alert createAlert() {
        Alert alert = new Alert(alerts.size() + 1, "Inspection required", "Medium", false);
        alerts.add(alert);
        return alert;
    }

    public Report createReport() {
        Report report = new Report(reports.size() + 1, "Work order", java.time.LocalDate.now().toString(), description + " - " + status);
        reports.add(report);
        return report;
    }

    public void closeOrder() {
        for (MaintenanceTask task : tasks) {
            if (!task.getIsCompleted()) return;
        }
        status = "Closed";
        if (technician != null) technician.setAvailabilityStatus(true);
    }

    @Override
    public String toString() {
        return "WorkOrder{" + "workOrderId=" + workOrderId + ", " + "description=" + description + ", " + "priority=" + priority + ", " + "status=" + status + ", " + "creationDate=" + creationDate + "}";
    }
}
