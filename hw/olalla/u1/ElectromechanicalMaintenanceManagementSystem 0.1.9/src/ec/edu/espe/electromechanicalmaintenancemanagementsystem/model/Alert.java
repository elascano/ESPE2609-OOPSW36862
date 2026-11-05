package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

public class Alert implements java.io.Serializable {
    private int alertId;
    private String message;
    private String severity;
    private boolean isResolved;

    public Alert(int alertId, String message, String severity, boolean isResolved) {
        this.alertId = alertId;
        this.message = message;
        this.severity = severity;
        this.isResolved = isResolved;
    }

    public int getAlertId() {
        return alertId;
    }
    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getSeverity() {
        return severity;
    }
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public boolean getIsResolved() {
        return isResolved;
    }
    public void setIsResolved(boolean isResolved) {
        this.isResolved = isResolved;
    }

    @Override
    public String toString() {
        return "Alert{" + "alertId=" + alertId + ", " + "message=" + message + ", " + "severity=" + severity + ", " + "isResolved=" + isResolved + "}";
    }
}
