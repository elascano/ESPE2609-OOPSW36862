package enums;

public enum Role {
    CASHIER, EMPLOYEE, MANAGER;

    @Override
    public String toString() {
        return switch (this) {
            case CASHIER -> "Cajero";
            case EMPLOYEE -> "Empleado";
            case MANAGER -> "Gerente";
        };
    }
}
