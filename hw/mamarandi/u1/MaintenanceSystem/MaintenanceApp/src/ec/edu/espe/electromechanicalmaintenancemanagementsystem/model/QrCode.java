package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

public class QrCode {
    private int qrId;
    private String codeData;
    private String generateUrl;

    public QrCode(int qrId, String codeData, String generateUrl) {
        this.qrId = qrId;
        this.codeData = codeData;
        this.generateUrl = generateUrl;
    }

    public int getQrId() {
        return qrId;
    }
    public void setQrId(int qrId) {
        this.qrId = qrId;
    }

    public String getCodeData() {
        return codeData;
    }
    public void setCodeData(String codeData) {
        this.codeData = codeData;
    }

    public String getGenerateUrl() {
        return generateUrl;
    }
    public void setGenerateUrl(String generateUrl) {
        this.generateUrl = generateUrl;
    }

    @Override
    public String toString() {
        return "QrCode{" + "qrId=" + qrId + ", " + "codeData=" + codeData + ", " + "generateUrl=" + generateUrl + "}";
    }
}
