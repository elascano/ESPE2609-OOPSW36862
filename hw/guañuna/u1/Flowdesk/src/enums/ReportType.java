package enums;

public enum ReportType {
    DAILY, WEEKLY, MONTHLY;

    @Override
    public String toString() {
        return switch (this) {
            case DAILY -> "Diario";
            case WEEKLY -> "Semanal";
            case MONTHLY -> "Mensual";
        };
    }
}
