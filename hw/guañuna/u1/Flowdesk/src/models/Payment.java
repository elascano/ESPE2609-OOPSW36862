package models;

import enums.MovementType;
import enums.PaymentMethod;
import utils.Date;
import java.util.Scanner;
import utils.ConsoleInput;
import utils.Validation;
import exceptions.InvalidPaymentException;

public class Payment {

    private String paymentId;
    private double amount;
    private Date date;
    private PaymentMethod paymentMethod;
    private String orderId;
    private CashMovement cashMovement;
    private transient boolean registered;

    public Payment() { }

    public Payment(String paymentId, double amount, Date date, PaymentMethod paymentMethod) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    public void setWorkOrder(WorkOrder workOrder) {
        requireEditable();
        if (workOrder != null) {
            this.orderId = workOrder.getOrderId();
        } else {
            this.orderId = null;
        }
    }

    public WorkOrder getWorkOrder() {
        if (orderId == null) return null;
        for (WorkOrder wo : WorkOrder.getAll()) {
            if (Validation.sameIdentifier(wo.getOrderId(), orderId)) {
                return wo;
            }
        }
        return null;
    }

    public void registerPayment() {
        WorkOrder workOrder = getWorkOrder();
        if (workOrder == null) {
            throw new InvalidPaymentException("Se requiere una orden de trabajo válida.");
        }
        workOrder.addPayment(this);
    }

    public void registerPayment(CashRegister cashRegister) {
        WorkOrder workOrder = getWorkOrder();
        if (workOrder == null) {
            throw new InvalidPaymentException("Se requiere una orden de trabajo válida.");
        }
        workOrder.calculateTotals();
        validateFor(workOrder);
        
        CashMovement movement = new CashMovement("MOV-" + paymentId, MovementType.INCOME,
                amount, this.date, "Pago Orden " + workOrder.getOrderId(), paymentMethod);
        
        movement.validateRegistration(cashRegister);
        registerPayment();
        movement.registerMovement(cashRegister);
        cashMovement = movement;
    }

    void validateFor(WorkOrder order) {
        if (isRegistered()) {
            throw new InvalidPaymentException("El pago ya fue registrado.");
        }
        if (getWorkOrder() != order) {
            throw new InvalidPaymentException("El pago pertenece a otra orden.");
        }
        if (paymentId == null || paymentId.isBlank() || date == null || paymentMethod == null) {
            throw new InvalidPaymentException("El pago requiere identificador, fecha y metodo de pago.");
        }
        rejectDuplicateId(order);
        for (WorkOrder existingOrder : WorkOrder.getAll()) {
            if (existingOrder != order) {
                rejectDuplicateId(existingOrder);
            }
        }
        if (!validateAmount()) {
            throw new InvalidPaymentException("El monto debe ser finito, mayor a 0 y no superar el saldo pendiente.");
        }
    }

    private void rejectDuplicateId(WorkOrder order) {
        for (Payment existing : order.getPayments()) {
            if (Validation.sameIdentifier(existing.paymentId, paymentId)) {
                throw new InvalidPaymentException("Ya existe un pago con ese ID.");
            }
        }
    }

    private boolean isRegistered() {
        return registered || (getWorkOrder() != null && getWorkOrder().getPayments().contains(this));
    }

    void markRegistered() {
        registered = true;
    }

    private void requireEditable() {
        if (isRegistered()) {
            throw new InvalidPaymentException("Un pago registrado no se puede modificar.");
        }
    }

    public boolean validateAmount() {
        return getWorkOrder() != null
                && Double.isFinite(amount)
                && amount > 0
                && amount <= getWorkOrder().getBalance();
    }

    // ---> MÉTODO AGREGADO AQUÍ <---
    public String getPaymentDetails() {
        return "Identificador del pago: " + paymentId
                + ", Monto: " + amount
                + ", Metodo: " + paymentMethod;
    }

    public static void registerFromConsole(Scanner scanner, CashRegister cashRegister) {
        System.out.println("\n--- REGISTRAR PAGO ---");

        String orderIdInput = ConsoleInput.readRequiredText(scanner, "Ingrese el numero de orden a pagar (ej. WO-001): ");

        WorkOrder order = null;

        for (WorkOrder wo : WorkOrder.getAll()) {
            if (Validation.sameIdentifier(wo.getOrderId(), orderIdInput)) {
                order = wo;
                break;
            }
        }

        if (order == null) {
            System.out.println("Error: Orden no encontrada.");
            return;
        }

        if (order.getBalance() <= 0) {
            System.out.println("Esta orden ya esta totalmente pagada.");
            return;
        }

        String customerName = (order.getCustomer() != null) ? order.getCustomer().getName() : "Sin cliente";
        System.out.println("Cliente: " + customerName);
        System.out.println("Total de la Orden: $" + order.getTotal());
        System.out.println("Saldo Pendiente: $" + order.getBalance());

        double amount = ConsoleInput.readPositiveAmount(scanner, "\nMonto a pagar ahora: $");

        if (amount > order.getBalance()) {
            System.out.println("Error: El monto excede el saldo pendiente ($" + order.getBalance() + ").");
            return;
        }

        int option = ConsoleInput.readOption(scanner, "Metodo de pago (1. EFECTIVO / 2. TRANSFERENCIA): ", 1, 2);

        PaymentMethod method = switch (option) {
            case 1 -> PaymentMethod.CASH;
            case 2 -> PaymentMethod.TRANSFER;
            default -> null;
        };

        if (method == null) {
            System.out.println("Metodo de pago no valido.");
            return;
        }

        String newPaymentId = "P-" + System.currentTimeMillis();

        Payment newPayment = new Payment(newPaymentId, amount, Date.now(), method);
        newPayment.setWorkOrder(order);

        newPayment.registerPayment(cashRegister);

        System.out.println("\nPago registrado exitosamente!");
        System.out.println("Se ha generado un ingreso automatico en la caja.");
        System.out.println("Nuevo saldo pendiente de la orden: $" + order.getBalance());

        if (order.getBalance() == 0) {
            System.out.println("LA ORDEN HA SIDO TOTALMENTE PAGADA!");
        }
    }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { requireEditable(); this.paymentId = paymentId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { requireEditable(); this.amount = amount; }
    public Date getDate() { return date; }
    public void setDate(Date date) { requireEditable(); this.date = date; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { requireEditable(); this.paymentMethod = paymentMethod; }
    public String getOrderId() { return orderId; }
    public CashMovement getCashMovement() { return cashMovement; }
    public void setCashMovement(CashMovement cashMovement) { this.cashMovement = cashMovement; }
}