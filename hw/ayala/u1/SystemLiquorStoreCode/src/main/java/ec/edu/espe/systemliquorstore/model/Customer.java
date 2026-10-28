package ec.edu.espe.systemliquorstore.model;

import ec.edu.espe.systemliquorstore.util.Validation;
import java.util.ArrayList;

public class Customer {
    private int customerId;
    private String name;
    private static final Validation validation = new Validation();

    public Customer(int customerId, String name) {
        if (customerId <= 0 || !validation.isText(name)) {
            throw new IllegalArgumentException("El cliente debe tener un identificador positivo y un nombre.");
        }
        this.customerId = customerId;
        this.name = name.trim();
    }

    public Sale createCreditSale(int id, String date, String seller) {
        Sale sale = new Sale(id, date, seller, "CREDIT");
        sale.assignCustomer(this);
        return sale;
    }

    public double getOutstandingBalance(ArrayList<Sale> sales, ArrayList<CustomerPayment> payments) {
        if (sales == null || payments == null) {
            throw new IllegalArgumentException("Las listas de ventas y pagos son necesarias.");
        }
        double creditSales = 0;
        double paidAmount = 0;
        for (Sale sale : sales) {
            if (sale == null) {
                throw new IllegalArgumentException("La lista de ventas contiene un registro no valido.");
            }
            if (sale.isCompleted() && sale.getCustomerId() == customerId
                    && "CREDIT".equals(sale.getPaymentType())) {
                if (!sale.isValid()) {
                    throw new IllegalArgumentException("Existe una venta a credito no valida.");
                }
                creditSales += sale.getTotalAmount();
                if (!validation.isFinite(creditSales)) {
                    throw new IllegalArgumentException("El total de ventas a credito es demasiado grande.");
                }
            }
        }
        for (CustomerPayment payment : payments) {
            if (payment == null || !payment.isValid()) {
                throw new IllegalArgumentException("La lista de pagos contiene un registro no valido.");
            }
            if (payment.getCustomerId() == customerId) {
                paidAmount += payment.getAmount();
                if (!validation.isFinite(paidAmount)) {
                    throw new IllegalArgumentException("El total de pagos es demasiado grande.");
                }
            }
        }
        double balance = validation.roundMoney(creditSales + paidAmount);
        if (balance < 0) {
            throw new IllegalStateException("Los pagos registrados superan la deuda del cliente.");
        }
        return balance;
    }

    public CustomerPayment createPayment(int id, String date, double amount) {
        return new CustomerPayment(id, customerId, date, amount);
    }

    public void registerPayment(CustomerPayment payment, ArrayList<Sale> sales,
            ArrayList<CustomerPayment> payments) {
        if (payment == null || !payment.isValid() || payment.getCustomerId() != customerId) {
            throw new IllegalArgumentException("El pago debe ser valido y pertenecer al cliente seleccionado.");
        }
        if (payments == null || sales == null) {
            throw new IllegalArgumentException("Las listas de ventas y pagos son necesarias.");
        }
        for (CustomerPayment existing : payments) {
            if (existing == null || !existing.isValid()) {
                throw new IllegalArgumentException("La lista de pagos contiene un registro no valido.");
            }
            if (existing.getPaymentId() == payment.getPaymentId()) {
                throw new IllegalArgumentException("Ya existe un pago con ese identificador.");
            }
        }
        double balance = getOutstandingBalance(sales, payments);
        if (payment.getAmount() > balance) {
            throw new IllegalArgumentException("El pago no puede superar el saldo pendiente de USD "
                    + String.format(java.util.Locale.US, "%.2f", balance) + ".");
        }
        payments.add(payment);
    }

    public boolean isValid() {
        return customerId > 0 && validation.isText(name);
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!validation.isText(name)) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacio.");
        }
        this.name = name.trim();
    }
}
