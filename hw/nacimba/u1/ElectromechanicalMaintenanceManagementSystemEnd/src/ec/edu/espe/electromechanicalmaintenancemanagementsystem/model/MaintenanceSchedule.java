package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceSchedule {
    private int scheduleId;
    private String frequency;
    private String scheduleDate;
    private boolean isPreventive;

    private List<WorkOrder> workOrders = new ArrayList<>();

    public MaintenanceSchedule(int scheduleId, String frequency, String scheduleDate, boolean isPreventive) {
        this.scheduleId = scheduleId;
        this.frequency = frequency;
        this.scheduleDate = scheduleDate;
        this.isPreventive = isPreventive;
    }

    public int getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getFrequency() {
        return frequency;
    }
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }
    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public boolean getIsPreventive() {
        return isPreventive;
    }
    public void setIsPreventive(boolean isPreventive) {
        this.isPreventive = isPreventive;
    }

    public List<WorkOrder> getWorkOrders() {
        return new ArrayList<>(workOrders);
    }

    public WorkOrder createWorkOrder() {
        WorkOrder order = new WorkOrder(workOrders.size() + 1, "Scheduled " + frequency + " maintenance", "Medium", "Pending", scheduleDate);
        workOrders.add(order);
        return order;
    }

    public void updateScheduleDate(String newDate) {
        this.scheduleDate = newDate;
    }

    @Override
    public String toString() {
        return "MaintenanceSchedule{" + "scheduleId=" + scheduleId + ", " + "frequency=" + frequency + ", " + "scheduleDate=" + scheduleDate + ", " + "isPreventive=" + isPreventive + "}";
    }
}
