package enums;

public enum PaymentMethod {
    CASH, TRANSFER;

    @Override
    public String toString() {
        return switch (this) {
            case CASH -> "Efectivo";
            case TRANSFER -> "Transferencia";
        };
    }
}
