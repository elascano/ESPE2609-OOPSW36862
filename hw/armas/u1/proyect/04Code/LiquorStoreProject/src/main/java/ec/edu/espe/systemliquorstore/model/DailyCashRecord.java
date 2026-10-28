package ec.edu.espe.systemliquorstore.model;

import com.google.gson.annotations.SerializedName;
import ec.edu.espe.systemliquorstore.util.Validation;
import java.util.ArrayList;

public class DailyCashRecord {
    @SerializedName("fecha")
    private String date;
    @SerializedName("efectivoInicial")
    private double openingCash;
    @SerializedName("efectivoContado")
    private double countedCash;
    @SerializedName("conteoRegistrado")
    private boolean cashCountRecorded;
    @SerializedName("gastos")
    private ArrayList<CashExpense> expenses;

    public DailyCashRecord(String date, double openingCash) {
        if (!Validation.isValidDate(date) || !Validation.isMoney(openingCash) || openingCash < 0) {
            throw new IllegalArgumentException("La caja requiere una fecha valida y un saldo inicial no negativo con hasta dos decimales.");
        }
        this.date = date;
        this.openingCash = openingCash;
        countedCash = 0;
        cashCountRecorded = false;
        expenses = new ArrayList<>();
    }

    public CashExpense addExpense(String description, double amount) {
        CashExpense expense = new CashExpense(description, amount);
        if (!Validation.isFinite(getTotalExpenses() + expense.getAmount())) {
            throw new IllegalArgumentException("El total de gastos es demasiado grande.");
        }
        expenses.add(expense);
        return expense;
    }

    public double getTotalExpenses() {
        double total = 0;
        for (CashExpense expense : expenses) {
            if (expense == null || !expense.isValid()) {
                throw new IllegalStateException("Existe un gasto de caja no valido.");
            }
            total += expense.getAmount();
            if (!Validation.isFinite(total)) {
                throw new IllegalStateException("El total de gastos es demasiado grande.");
            }
        }
        return Validation.roundMoney(total);
    }

    public double calculateExpectedCash(ArrayList<Sale> sales, ArrayList<CustomerPayment> payments) {
        if (sales == null || payments == null) {
            throw new IllegalArgumentException("Las listas de ventas y pagos son necesarias.");
        }
        double receipts = openingCash;
        for (Sale sale : sales) {
            if (sale == null) {
                throw new IllegalArgumentException("La lista de ventas contiene un registro no valido.");
            }
            if (sale.isCompleted() && "CASH".equals(sale.getPaymentType())
                    && date.equals(sale.getDate())) {
                if (!sale.isValid()) {
                    throw new IllegalArgumentException("Existe una venta de contado no valida.");
                }
                receipts += sale.getTotalAmount();
                if (!Validation.isFinite(receipts)) {
                    throw new IllegalArgumentException("Los ingresos de caja son demasiado grandes.");
                }
            }
        }
        for (CustomerPayment payment : payments) {
            if (payment == null || !payment.isValid()) {
                throw new IllegalArgumentException("La lista de pagos contiene un registro no valido.");
            }
            if (date.equals(payment.getDate())) {
                receipts += payment.getAmount();
                if (!Validation.isFinite(receipts)) {
                    throw new IllegalArgumentException("Los ingresos de caja son demasiado grandes.");
                }
            }
        }
        return Validation.roundMoney(receipts - getTotalExpenses());
    }

    public double calculateDifference(ArrayList<Sale> sales, ArrayList<CustomerPayment> payments) {
        if (!cashCountRecorded) {
            throw new IllegalStateException("Primero debe registrar el dinero contado fisicamente.");
        }
        return Validation.roundMoney(countedCash - calculateExpectedCash(sales, payments));
    }

    public void recordCount(double amount) {
        if (!Validation.isMoney(amount) || amount < 0) {
            throw new IllegalArgumentException("El dinero contado debe ser no negativo y tener hasta dos decimales.");
        }
        countedCash = amount;
        cashCountRecorded = true;
    }

    public boolean isValid() {
        if (!Validation.isValidDate(date) || !Validation.isMoney(openingCash)
                || openingCash < 0 || !Validation.isMoney(countedCash) || countedCash < 0
                || expenses == null || (!cashCountRecorded && countedCash != 0)) {
            return false;
        }
        double total = 0;
        for (CashExpense expense : expenses) {
            if (expense == null || !expense.isValid()) {
                return false;
            }
            total += expense.getAmount();
            if (!Validation.isFinite(total)) {
                return false;
            }
        }
        return true;
    }

    public String getDate() {
        return date;
    }

    public double getOpeningCash() {
        return openingCash;
    }

    public double getCountedCash() {
        return countedCash;
    }

    public boolean isCashCountRecorded() {
        return cashCountRecorded;
    }

    public ArrayList<CashExpense> getExpenses() {
        return new ArrayList<>(expenses);
    }
}
