package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

public class Report {
    private int reportId;
    private String reportType;
    private String generatedDate;
    private String dataContent;

    public Report(int reportId, String reportType, String generatedDate, String dataContent) {
        this.reportId = reportId;
        this.reportType = reportType;
        this.generatedDate = generatedDate;
        this.dataContent = dataContent;
    }

    public int getReportId() {
        return reportId;
    }
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getReportType() {
        return reportType;
    }
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getGeneratedDate() {
        return generatedDate;
    }
    public void setGeneratedDate(String generatedDate) {
        this.generatedDate = generatedDate;
    }

    public String getDataContent() {
        return dataContent;
    }
    public void setDataContent(String dataContent) {
        this.dataContent = dataContent;
    }

    @Override
    public String toString() {
        return "Report{" + "reportId=" + reportId + ", " + "reportType=" + reportType + ", " + "generatedDate=" + generatedDate + ", " + "dataContent=" + dataContent + "}";
    }
}
