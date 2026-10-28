package ec.edu.espe.systemliquorstore.model;

import com.google.gson.annotations.SerializedName;
import ec.edu.espe.systemliquorstore.util.Validation;

public class CashExpense {
    @SerializedName("descripcion")
    private String description;
    @SerializedName("monto")
    private double amount;

    public CashExpense(String description, double amount) {
        if (!Validation.isText(description) || !Validation.isMoney(amount) || amount <= 0) {
            throw new IllegalArgumentException("El gasto requiere una descripcion y un monto mayor que cero con hasta dos decimales.");
        }
        this.description = description.trim();
        this.amount = amount;
    }

    public boolean isValid() {
        return Validation.isText(description) && Validation.isMoney(amount) && amount > 0;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}
