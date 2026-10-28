package com.mycompany.flowdesk;

import enums.OrderStatus;
import enums.PaymentMethod;
import exceptions.InvalidPaymentException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import models.CashMovement;
import models.CashRegister;
import models.OrderDetail;
import models.Payment;
import models.WorkOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ConsoleInput;
import utils.Date;
import static org.junit.jupiter.api.Assertions.*;

class PaymentConsoleTest {
    private WorkOrder order;
    private CashRegister cashRegister;

    @BeforeEach
    void prepareOrder() {
        WorkOrder.setAll(new ArrayList<>());
        CashMovement.setAll(new ArrayList<>());
        order = new WorkOrder("WO-001", new Date(27, 10, 2026), OrderStatus.WAITING);
        order.getDetails().add(new OrderDetail("D1", "Volante", 2, "A4", 50));
        order.create();
        cashRegister = new CashRegister();
    }

    @AfterEach
    void clearData() {
        WorkOrder.setAll(new ArrayList<>());
        CashMovement.setAll(new ArrayList<>());
    }

    private String capture(Runnable action) {
        PrintStream previousOutput = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            action.run();
        } finally {
            System.setOut(previousOutput);
        }
        return output.toString(StandardCharsets.UTF_8);
    }

    private String enterPayment(String input) {
        return capture(() -> ConsoleInput.runAction(
                () -> Payment.registerFromConsole(new Scanner(input), cashRegister)));
    }

    private void assertUnchanged() {
        assertAll(
                () -> assertEquals(100, order.getBalance()),
                () -> assertEquals(0, order.calculateTotalPaid()),
                () -> assertTrue(order.getPayments().isEmpty()),
                () -> assertEquals(0, cashRegister.calculateIncome()),
                () -> assertTrue(CashMovement.getAll().isEmpty()));
    }

    @Test
    void partialCashPaymentUpdatesOrderAndCashTogether() {
        String output = enterPayment("WO-001\n40\n1\n");
        assertEquals(60, order.getBalance());
        assertEquals(40, order.calculateTotalPaid());
        assertEquals(40, cashRegister.calculateIncome());
        assertEquals(1, CashMovement.getAll().size());
        assertEquals(PaymentMethod.CASH, order.getPayments().get(0).getPaymentMethod());
        assertSame(CashMovement.getAll().get(0), order.getPayments().get(0).getCashMovement());
        assertTrue(output.contains("Pago registrado exitosamente"));
    }

    @Test
    void fullTransferPaymentClearsBalance() {
        String output = enterPayment("WO-001\n100\n2\n");
        assertEquals(0, order.getBalance());
        assertEquals(100, cashRegister.calculateIncome());
        assertEquals(PaymentMethod.TRANSFER, order.getPayments().get(0).getPaymentMethod());
        assertTrue(output.contains("LA ORDEN HA SIDO TOTALMENTE PAGADA"));
    }

    @Test
    void invalidAmountsAndPaymentMethodsRetryBeforeOneValidPayment() {
        String output = enterPayment("WO-001\ntexto\nNaN\nInfinity\n0\n-2\n40\ntexto\n0\n3\n2\n");
        assertEquals(60, order.getBalance());
        assertEquals(40, cashRegister.calculateIncome());
        assertEquals(1, order.getPayments().size());
        assertEquals(1, CashMovement.getAll().size());
        assertTrue(output.contains("Ingrese un monto valido"));
        assertTrue(output.contains("Opcion no valida"));
    }

    @Test
    void overpaymentIsRejectedAndNextAttemptCanSucceed() {
        String rejected = enterPayment("WO-001\n101\n");
        assertUnchanged();
        assertTrue(rejected.contains("excede el saldo pendiente"));
        assertFalse(rejected.contains("Pago registrado exitosamente"));
        enterPayment("WO-001\n100\n1\n");
        assertEquals(0, order.getBalance());
        assertEquals(100, cashRegister.calculateIncome());
    }

    @Test
    void unknownOrderDoesNotChangeCash() {
        assertTrue(enterPayment("WO-999\n").contains("Orden no encontrada"));
        assertUnchanged();
    }

    @Test
    void cancellingPaymentMethodLeavesOrderAndCashUnchanged() {
        String output = enterPayment("WO-001\n40\n:cancelar\n");
        assertTrue(output.contains("Operacion cancelada"));
        assertUnchanged();
    }

    @Test
    void alreadyPaidOrderRejectsAnotherPaymentBeforeAskingForAmount() {
        // CORREGIDO
        Payment p = new Payment("P1", 100, new Date(27, 10, 2026), PaymentMethod.CASH);
        p.setWorkOrder(order);
        p.registerPayment(cashRegister);

        String output = enterPayment("WO-001\n");
        assertTrue(output.contains("ya esta totalmente pagada"));
        assertFalse(output.contains("Monto a pagar ahora"));
        assertEquals(1, order.getPayments().size());
        assertEquals(100, cashRegister.calculateIncome());
    }

    @Test
    void successiveInstallmentsAndFinalPaymentPreserveTotalsAndRejectDuplicate() {
        // CORREGIDO
        Payment first = new Payment("P1", 25, new Date(27, 10, 2026), PaymentMethod.CASH);
        first.setWorkOrder(order);
        first.registerPayment(cashRegister);

        Payment second = new Payment("P2", 35, new Date(27, 10, 2026), PaymentMethod.TRANSFER);
        second.setWorkOrder(order);
        second.registerPayment(cashRegister);

        assertEquals(40, order.getBalance());

        Payment third = new Payment("P3", 40, new Date(27, 10, 2026), PaymentMethod.CASH);
        third.setWorkOrder(order);
        third.registerPayment(cashRegister);

        assertThrowsExactly(InvalidPaymentException.class, () -> first.registerPayment(cashRegister));
        assertEquals(0, order.getBalance());
        assertEquals(100, order.calculateTotalPaid());
        assertEquals(100, cashRegister.calculateIncome());
        assertEquals(3, CashMovement.getAll().size());
    }
}