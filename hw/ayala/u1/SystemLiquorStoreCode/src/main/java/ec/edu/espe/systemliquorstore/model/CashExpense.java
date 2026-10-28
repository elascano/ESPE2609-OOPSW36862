package ec.edu.espe.systemliquorstore.model;

import ec.edu.espe.systemliquorstore.util.Validation;

public class CashExpense {
    private String description;
    private double amount;
    private static final Validation validation = new Validation();

    public CashExpense(String description, double amount) {
        if (!validation.isText(description) || !validation.isMoney(amount) || amount <= 0) {
            throw new IllegalArgumentException("El gasto requiere una descripcion y un monto mayor que cero con hasta dos decimales.");
        }
        this.description = description.trim();
        this.amount = amount;
    }

    public boolean isValid() {
        return validation.isText(description) && validation.isMoney(amount) && amount > 0;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}
