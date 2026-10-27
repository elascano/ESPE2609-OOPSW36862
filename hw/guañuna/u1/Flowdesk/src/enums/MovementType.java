package enums;

public enum MovementType {
    INCOME, EXPENSE;

    @Override
    public String toString() {
        return switch (this) {
            case INCOME -> "Ingreso";
            case EXPENSE -> "Egreso";
        };
    }
}
