package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Equipment {
    private int equipmentId;
    private String name;
    private String serialNumber;
    private String location;
    private String status;

    private QrCode qrCode;
    private List<MaintenanceHistory> maintenanceHistories = new ArrayList<>();
    private List<MaintenanceSchedule> maintenanceSchedules = new ArrayList<>();
    private List<WorkOrder> workOrders = new ArrayList<>();

    public Equipment(int equipmentId, String name, String serialNumber, String location, String status) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.serialNumber = serialNumber;
        this.location = location;
        this.status = status;
    }

    public int getEquipmentId() {
        return equipmentId;
    }
    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public QrCode getQrCode() {
        return qrCode;
    }

    public List<MaintenanceHistory> getMaintenanceHistories() {
        return new ArrayList<>(maintenanceHistories);
    }

    public List<MaintenanceSchedule> getMaintenanceSchedules() {
        return new ArrayList<>(maintenanceSchedules);
    }

    public List<WorkOrder> getWorkOrders() {
        return new ArrayList<>(workOrders);
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public QrCode createQrCode() {
        if (qrCode == null) {
            qrCode = new QrCode(equipmentId, serialNumber, "https://maintenance.local/equipment/" + equipmentId);
        }
        return qrCode;
    }

    public WorkOrder requestMaintenanceOrder() {
        WorkOrder order = new WorkOrder(workOrders.size() + 1, "Maintenance for " + name, "Medium", "Pending", java.time.LocalDate.now().toString());
        workOrders.add(order);
        return order;
    }

    public void assignSchedule(MaintenanceSchedule schedule) {
        if (schedule != null && !maintenanceSchedules.contains(schedule)) maintenanceSchedules.add(schedule);
    }

    public void attachHistory(MaintenanceHistory history) {
        if (history != null) maintenanceHistories.add(history);
    }

    @Override
    public String toString() {
        return "Equipment{" + "equipmentId=" + equipmentId + ", " + "name=" + name + ", " + "serialNumber=" + serialNumber + ", " + "location=" + location + ", " + "status=" + status + "}";
    }
}
