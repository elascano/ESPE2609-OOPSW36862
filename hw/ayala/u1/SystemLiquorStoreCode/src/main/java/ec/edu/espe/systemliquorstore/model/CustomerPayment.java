package ec.edu.espe.systemliquorstore.model;

import ec.edu.espe.systemliquorstore.util.Validation;

public class CustomerPayment {
    private int paymentId;
    private int customerId;
    private String date;
    private double amount;
    private static final Validation validation = new Validation();

    public CustomerPayment(int paymentId, int customerId, String date, double amount) {
        if (paymentId <= 0 || customerId <= 0 || !validation.isValidDate(date)
                || !validation.isMoney(amount) || amount <= 0) {
            throw new IllegalArgumentException("El pago requiere identificadores positivos, fecha valida y un monto mayor que cero con hasta dos decimales.");
        }
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.date = date;
        this.amount = amount;
    }

    public boolean isValid() {
        return paymentId > 0 && customerId > 0 && validation.isValidDate(date)
                && validation.isMoney(amount) && amount > 0;
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
