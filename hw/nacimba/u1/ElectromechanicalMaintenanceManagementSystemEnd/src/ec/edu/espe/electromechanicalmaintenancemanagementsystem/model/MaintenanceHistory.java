package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

public class MaintenanceHistory {
    private int historyId;
    private String maintenanceDate;
    private String maintenanceType;
    private String description;

    public MaintenanceHistory(int historyId, String maintenanceDate, String maintenanceType, String description) {
        this.historyId = historyId;
        this.maintenanceDate = maintenanceDate;
        this.maintenanceType = maintenanceType;
        this.description = description;
    }

    public int getHistoryId() {
        return historyId;
    }
    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public String getMaintenanceDate() {
        return maintenanceDate;
    }
    public void setMaintenanceDate(String maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }
    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MaintenanceHistory{" + "historyId=" + historyId + ", " + "maintenanceDate=" + maintenanceDate + ", " + "maintenanceType=" + maintenanceType + ", " + "description=" + description + "}";
    }
}
