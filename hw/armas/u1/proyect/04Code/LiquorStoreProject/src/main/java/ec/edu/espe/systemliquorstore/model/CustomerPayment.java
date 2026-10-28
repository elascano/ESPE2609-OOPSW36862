package ec.edu.espe.systemliquorstore.model;

import com.google.gson.annotations.SerializedName;
import ec.edu.espe.systemliquorstore.util.Validation;

public class CustomerPayment {
    @SerializedName("idPago")
    private int paymentId;
    @SerializedName("idCliente")
    private int customerId;
    @SerializedName("fecha")
    private String date;
    @SerializedName("monto")
    private double amount;

    public CustomerPayment(int paymentId, int customerId, String date, double amount) {
        if (paymentId <= 0 || customerId <= 0 || !Validation.isValidDate(date)
                || !Validation.isMoney(amount) || amount <= 0) {
            throw new IllegalArgumentException("El pago requiere identificadores positivos, fecha valida y un monto mayor que cero con hasta dos decimales.");
        }
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.date = date;
        this.amount = amount;
    }

    public boolean isValid() {
        return paymentId > 0 && customerId > 0 && Validation.isValidDate(date)
                && Validation.isMoney(amount) && amount > 0;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }
}
