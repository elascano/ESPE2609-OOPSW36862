package models;

import enums.MovementType;
import enums.PaymentMethod;
import utils.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.ConsoleInput;
import utils.Validation;
import exceptions.ValidationException;

public class CashMovement {
    private String movementId;
    private MovementType type;
    private double amount;
    private Date date;
    private String reason;
    private PaymentMethod paymentMethod;

    private static List<CashMovement> movements = new ArrayList<>();

    public CashMovement() { }

    public CashMovement(String movementId, MovementType type, double amount, Date date, String reason, PaymentMethod paymentMethod) {
        this.movementId = movementId;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.reason = reason;
        this.paymentMethod = paymentMethod;
        validate();
    }

    public void registerMovement(CashRegister cashRegister) {
        validateRegistration(cashRegister);
        if (this.type == MovementType.INCOME) {
            cashRegister.addIncome(this.amount);
        } else {
            cashRegister.addExpense(this.amount);
        }
        cashRegister.addMovement(this);
        movements.add(this);
    }

    void validateRegistration(CashRegister cashRegister) {
        validate();
        Validation.requireValue(cashRegister, "caja");
        for (CashMovement existing : movements) {
            if (Validation.sameIdentifier(existing.movementId, movementId)) {
                throw new ValidationException("Ya existe un movimiento con ese ID.");
            }
        }
        double accumulatedAmount = type == MovementType.INCOME
                ? cashRegister.calculateIncome() + amount
                : cashRegister.calculateExpenses() + amount;
        Validation.requireNonNegativeAmount(accumulatedAmount, "monto acumulado de caja");
    }

    private void validate() {
        Validation.requireText(movementId, "identificador del movimiento");
        Validation.requireText(reason, "motivo");
        Validation.requirePositiveAmount(amount, "monto");
        Validation.requireValue(type, "tipo de movimiento");
        Validation.requireValue(date, "fecha");
        Validation.requireValue(paymentMethod, "metodo de pago");
    }

    public static List<CashMovement> getAll() { return movements; }
    public static void setAll(List<CashMovement> newItems) { movements = newItems == null ? new ArrayList<>() : newItems; }

    public static void registerFromConsole(Scanner scanner, CashRegister cashRegister) {
        System.out.println("\n--- REGISTRAR MOVIMIENTO DE CAJA ---");

        String id = ConsoleInput.readRequiredText(scanner, "ID del Movimiento: ");

        int typeOption = ConsoleInput.readOption(scanner, "Tipo (1. INGRESO / 2. EGRESO): ", 1, 2);
        MovementType type = (typeOption == 1) ? MovementType.INCOME : MovementType.EXPENSE;

        double amount = ConsoleInput.readPositiveAmount(scanner, "Monto: $");
        String reason = ConsoleInput.readRequiredText(scanner, "Razon/Descripcion: ");

        int methodOption = ConsoleInput.readOption(scanner, "Metodo de pago (1. EFECTIVO / 2. TRANSFERENCIA): ", 1, 2);
        PaymentMethod method = (methodOption == 1) ? PaymentMethod.CASH : PaymentMethod.TRANSFER;

        Date currentDate = Date.now();

        CashMovement newMovement = new CashMovement(id, type, amount, currentDate, reason, method);
        newMovement.registerMovement(cashRegister);

        System.out.println("¡Movimiento registrado exitosamente!");
    }

    public static void listFromConsole() {
        System.out.println("\n--- HISTORIAL DE MOVIMIENTOS ---");
        if (movements.isEmpty()) {
            System.out.println("No hay movimientos registrados.");
        } else {
            for (CashMovement m : movements) {
                String sign = (m.type == MovementType.INCOME) ? "+" : "-";
                System.out.println("- [" + m.movementId + "] " + m.date.toString() +
                                   " | " + m.type + " | " + sign + "$" + m.amount +
                                   " | " + m.reason + " (" + m.paymentMethod + ")");
            }
        }
    }
    
    public String getMovementId() { return movementId; }
    public double getAmount() { return amount; }
    public MovementType getType() { return type; }
    public Date getDate() { return date; }
    public String getReason() { return reason; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
}