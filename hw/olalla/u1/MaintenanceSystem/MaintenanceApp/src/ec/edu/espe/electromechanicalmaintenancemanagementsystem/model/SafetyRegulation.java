package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

public class SafetyRegulation {
    private int regulationId;
    private String title;
    private String description;
    private String riskLevel;

    public SafetyRegulation(int regulationId, String title, String description, String riskLevel) {
        this.regulationId = regulationId;
        this.title = title;
        this.description = description;
        this.riskLevel = riskLevel;
    }

    public int getRegulationId() {
        return regulationId;
    }
    public void setRegulationId(int regulationId) {
        this.regulationId = regulationId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getRiskLevel() {
        return riskLevel;
    }
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    @Override
    public String toString() {
        return "SafetyRegulation{" + "regulationId=" + regulationId + ", " + "title=" + title + ", " + "description=" + description + ", " + "riskLevel=" + riskLevel + "}";
    }
}
