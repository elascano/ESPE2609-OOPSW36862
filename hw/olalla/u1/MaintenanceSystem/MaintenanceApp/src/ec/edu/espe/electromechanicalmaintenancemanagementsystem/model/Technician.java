package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Technician {
    private int technicianId;
    private String name;
    private String expertise;
    private boolean availabilityStatus;

    private List<MaintenanceHistory> reviewedHistories = new ArrayList<>();

    public Technician(int technicianId, String name, String expertise, boolean availabilityStatus) {
        this.technicianId = technicianId;
        this.name = name;
        this.expertise = expertise;
        this.availabilityStatus = availabilityStatus;
    }

    public int getTechnicianId() {
        return technicianId;
    }
    public void setTechnicianId(int technicianId) {
        this.technicianId = technicianId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getExpertise() {
        return expertise;
    }
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public boolean getAvailabilityStatus() {
        return availabilityStatus;
    }
    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public List<MaintenanceHistory> getReviewedHistories() {
        return new ArrayList<>(reviewedHistories);
    }

    public void acceptWorkOrder(WorkOrder order) {
        if (order != null && availabilityStatus) {
            order.assignTechnician(this);
            availabilityStatus = false;
        }
    }

    public void reviewHistory(MaintenanceHistory history) {
        if (history != null && !reviewedHistories.contains(history)) reviewedHistories.add(history);
    }

    @Override
    public String toString() {
        return "Technician{" + "technicianId=" + technicianId + ", " + "name=" + name + ", " + "expertise=" + expertise + ", " + "availabilityStatus=" + availabilityStatus + "}";
    }
}
