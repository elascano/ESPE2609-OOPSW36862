package enums;

public enum OrderStatus {
    WAITING, APPROVED, IN_PROGRESS, COMPLETED, DELIVERED;

    @Override
    public String toString() {
        return switch (this) {
            case WAITING -> "En espera";
            case APPROVED -> "Aprobada";
            case IN_PROGRESS -> "En proceso";
            case COMPLETED -> "Completada";
            case DELIVERED -> "Entregada";
        };
    }
}
