package com.mycompany.flowdesk;

import com.google.gson.Gson;
import enums.MovementType;
import enums.OrderStatus;
import enums.PaymentMethod;
import enums.Role;
import exceptions.InvalidPaymentException;
import exceptions.PersistenceException;
import exceptions.ValidationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ConsoleInput;
import utils.Date;
import static org.junit.jupiter.api.Assertions.*;

class BusinessValidationTest {
    private final Date date = new Date(26, 10, 2026);

    @BeforeEach
    void resetData() {
        Customer.setAll(new ArrayList<>());
        Category.setAll(new ArrayList<>());
        Product.setAll(new ArrayList<>());
        Service.setAll(new ArrayList<>());
        WorkOrder.setAll(new ArrayList<>());
        User.setAll(new ArrayList<>());
        CashMovement.setAll(new ArrayList<>());
    }

    private Category category() {
        return new Category("CAT1", "Impresion", "Catalogo");
    }

    private WorkOrder order(String id) {
        WorkOrder order = new WorkOrder(id, date, OrderStatus.WAITING);
        order.getDetails().add(new OrderDetail("D1", "Volante", 2, "A4", 50));
        order.calculateTotals();
        return order;
    }

    // CORREGIDO
    private Payment payment(String id, double amount, WorkOrder order) {
        Payment p = new Payment(id, amount, date, PaymentMethod.CASH);
        p.setWorkOrder(order);
        return p;
    }

    private String capture(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(output));
            action.run();
        } finally {
            System.setOut(original);
        }
        return output.toString();
    }

    @Test
    void requiredFieldsAreCheckedOutsideConsole() {
        assertThrows(ValidationException.class, () -> new Customer("C1", " ", "099", "171", "Quito"));
        assertThrows(ValidationException.class, () -> new Category(null, "Impresion", "Catalogo"));
        assertThrows(ValidationException.class, () -> new Product("P1", "Volante", "A4", 5, null));
        assertThrows(ValidationException.class, () -> new Service("S1", "Diseno", "", 5, category()));
        assertThrows(ValidationException.class, () -> new User("U1", "ana", "", Role.EMPLOYEE));
    }

    @Test
    void duplicateCustomerIdAndNationalIdDoNotChangeRegistry() {
        new Customer("C1", "Ana", "099", "171", "Quito").register();
        assertThrows(ValidationException.class,
                () -> new Customer(" c1 ", "Luis", "099", "172", "Quito").register());
        assertThrows(ValidationException.class,
                () -> new Customer("C2", "Luis", "099", " 171 ", "Quito").register());
        assertEquals(1, Customer.getAll().size());
    }

    @Test
    void catalogIdsMustBeUniqueIgnoringCaseAndOuterWhitespace() {
        Category category = category();
        category.create();
        new Product("P1", "Volante", "A4", 0, category).register();
        new Service("S1", "Diseno", "Logo", 0, category).register();
        assertThrows(ValidationException.class, () -> new Category(" cat1 ", "Otra", "Catalogo").create());
        assertThrows(ValidationException.class, () -> new Product("p1", "Otro", "A4", 1, category).register());
        assertThrows(ValidationException.class, () -> new Service("s1", "Otro", "Logo", 1, category).register());
        assertEquals(1, Product.getAll().size());
        assertEquals(1, Service.getAll().size());
    }

    @Test
    void invalidPricesAreRejectedAndZeroIsAllowed() {
        for (double amount : new ArrayList<>(java.util.List.of(-1.0, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY))) {
            assertThrows(ValidationException.class, () -> new Product("P1", "Volante", "A4", amount, category()));
            assertThrows(ValidationException.class, () -> new Service("S1", "Diseno", "Logo", amount, category()));
            assertThrows(ValidationException.class, () -> new OrderDetail("D1", "Volante", 1, "A4", amount));
        }
        assertEquals(0, new OrderDetail("D1", "Volante", 1, "A4", 0).calculateSubtotal());
    }

    @Test
    void zeroAndNegativeQuantitiesAreRejected() {
        assertThrows(ValidationException.class, () -> new OrderDetail("D1", "Volante", 0, "A4", 1));
        assertThrows(ValidationException.class, () -> new OrderDetail("D1", "Volante", -1, "A4", 1));
    }

    @Test
    void deserializedInvalidObjectsAreValidatedBeforeRegistration() {
        Product product = new Gson().fromJson("{\"productId\":\"P1\",\"price\":-1}", Product.class);
        assertThrows(ValidationException.class, product::register);
        assertTrue(Product.getAll().isEmpty());
        assertThrows(ValidationException.class, new Customer()::register);
    }

    @Test
    void emptyOrderCannotBeRegistered() {
        WorkOrder order = new WorkOrder("WO-1", date, OrderStatus.WAITING);
        assertThrows(ValidationException.class, order::create);
        assertTrue(WorkOrder.getAll().isEmpty());
    }

    @Test
    void duplicateOrderAndDetailIdsAreRejected() {
        order("WO-1").create();
        assertThrows(ValidationException.class, order(" wo-1 ")::create);
        WorkOrder repeatedDetails = order("WO-2");
        repeatedDetails.getDetails().add(new OrderDetail("d1", "Volante", 1, "A4", 5));
        assertThrows(ValidationException.class, repeatedDetails::create);
        assertEquals(1, WorkOrder.getAll().size());
    }

    @Test
    void overflowingOrderTotalIsRejectedBeforeRegistration() {
        WorkOrder order = new WorkOrder("WO-1", date, OrderStatus.WAITING);
        order.getDetails().add(new OrderDetail("D1", "Volante", 2, "A4", Double.MAX_VALUE));
        assertThrows(ValidationException.class, order::create);
        assertTrue(WorkOrder.getAll().isEmpty());
    }

    @Test
    void invalidPaymentsLeaveOrderUnchanged() {
        WorkOrder order = order("WO-1");
        for (double amount : new ArrayList<>(java.util.List.of(0.0, -1.0, 101.0, Double.NaN, Double.POSITIVE_INFINITY))) {
            assertThrows(InvalidPaymentException.class, () -> payment("P1", amount, order).registerPayment());
        }
        assertEquals(100, order.getBalance());
        assertTrue(order.getPayments().isEmpty());
    }

    @Test
    void missingPaymentFieldsAndWrongOrderAreRejected() {
        WorkOrder order = order("WO-1");
        assertThrows(InvalidPaymentException.class, () -> payment(" ", 10, order).registerPayment());
        // CORREGIDO
        assertThrows(InvalidPaymentException.class, () -> {
            Payment p = new Payment("P1", 10, date, null);
            p.setWorkOrder(order);
            p.registerPayment();
        });
        assertThrows(InvalidPaymentException.class, () -> order.addPayment(payment("P1", 10, order("WO-2"))));
        assertThrows(InvalidPaymentException.class, () -> order.addPayment(null));
        assertEquals(100, order.getBalance());
    }

    @Test
    void samePaymentObjectCannotBeRegisteredTwiceOrMoved() {
        WorkOrder order = order("WO-1");
        Payment payment = payment("P1", 10, order);
        payment.registerPayment();
        assertThrows(InvalidPaymentException.class, payment::registerPayment);
        assertThrows(InvalidPaymentException.class, () -> payment.setWorkOrder(order("WO-2")));
        assertThrows(InvalidPaymentException.class, () -> payment.setAmount(20));
        assertEquals(90, order.getBalance());
        assertEquals(1, order.getPayments().size());
    }

    @Test
    void duplicatePaymentIdIsRejectedWithinAndAcrossRegisteredOrders() {
        WorkOrder first = order("WO-1");
        WorkOrder second = order("WO-2");
        first.create();
        second.create();
        payment("P1", 10, first).registerPayment();
        assertThrows(InvalidPaymentException.class, () -> payment(" p1 ", 10, first).registerPayment());
        assertThrows(InvalidPaymentException.class, () -> payment("p1", 10, second).registerPayment());
        assertEquals(90, first.getBalance());
        assertEquals(100, second.getBalance());
    }

    @Test
    void paymentAddedDirectlyIsAlsoProtectedAgainstReregistration() {
        WorkOrder order = order("WO-1");
        Payment payment = payment("P1", 10, order);
        order.addPayment(payment);
        assertThrows(InvalidPaymentException.class, payment::registerPayment);
        assertThrows(InvalidPaymentException.class, () -> payment.setPaymentId("P2"));
    }

    @Test
    void paymentAndCashAreUpdatedOnceTogether() {
        WorkOrder order = order("WO-1");
        CashRegister cashRegister = new CashRegister();
        Payment payment = payment("P1", 10, order);
        payment.registerPayment(cashRegister);
        assertThrows(InvalidPaymentException.class, () -> payment.registerPayment(cashRegister));
        assertEquals(90, order.getBalance());
        assertEquals(10, cashRegister.calculateIncome());
        assertEquals(1, CashMovement.getAll().size());
    }

    @Test
    void rejectedMovementDoesNotPartiallyRegisterPayment() {
        WorkOrder order = order("WO-1");
        CashRegister cashRegister = new CashRegister();
        new CashMovement("MOV-P1", MovementType.INCOME, 5, date, "Venta", PaymentMethod.CASH)
                .registerMovement(cashRegister);
        assertThrows(ValidationException.class, () -> payment("P1", 10, order).registerPayment(cashRegister));
        assertEquals(100, order.getBalance());
        assertTrue(order.getPayments().isEmpty());
        assertEquals(5, cashRegister.calculateIncome());
    }

    @Test
    void duplicateMovementDoesNotIncreaseCashTwice() {
        CashRegister cashRegister = new CashRegister();
        CashMovement movement = new CashMovement("M1", MovementType.INCOME, 10, date, "Venta", PaymentMethod.CASH);
        movement.registerMovement(cashRegister);
        assertThrows(ValidationException.class, () -> movement.registerMovement(cashRegister));
        assertThrows(ValidationException.class, () -> new CashMovement("m1", MovementType.INCOME,
                20, date, "Venta", PaymentMethod.CASH).registerMovement(cashRegister));
        assertEquals(10, cashRegister.calculateIncome());
        assertEquals(1, CashMovement.getAll().size());
    }

    @Test
    void invalidCashAmountsDoNotChangeCashRegister() {
        CashRegister cashRegister = new CashRegister();
        assertThrows(ValidationException.class, () -> cashRegister.addIncome(-1));
        assertThrows(ValidationException.class, () -> cashRegister.addExpense(Double.NaN));
        assertThrows(ValidationException.class, () -> cashRegister.setIncome(Double.POSITIVE_INFINITY));
        assertEquals(0, cashRegister.calculateBalance());
    }

    @Test
    void duplicateUserNameIdOrRoleIsRejected() {
        new User("U1", "ana", "secret", Role.EMPLOYEE).register();
        assertThrows(ValidationException.class, () -> new User("u1", "luis", "secret", Role.CASHIER).register());
        assertThrows(ValidationException.class, () -> new User("U2", "ANA", "secret", Role.CASHIER).register());
        assertThrows(ValidationException.class, () -> new User("U3", "luis", "secret", Role.EMPLOYEE).register());
        assertEquals(1, User.getAll().size());
    }

    @Test
    void changingRegisteredUserCannotIntroduceDuplicates() {
        User employee = new User("U1", "ana", "secret", Role.EMPLOYEE);
        employee.register();
        new User("U2", "luis", "secret", Role.CASHIER).register();
        assertThrows(ValidationException.class, () -> employee.setUserId("u2"));
        assertThrows(ValidationException.class, () -> employee.setUsername("luis"));
        assertThrows(ValidationException.class, () -> employee.setRole(Role.CASHIER));
        assertEquals("U1", employee.getUserId());
        assertEquals("ana", employee.getUsername());
        assertEquals(Role.EMPLOYEE, employee.getRole());
    }

    @Test
    void customerMenuContinuesAfterBusinessValidationFailure() {
        new Customer("C1", "Ana", "099", "171", "Quito").register();
        String output = capture(() -> Customer.showManagementMenu(
                new Scanner("1\nC2\nLuis\n099\n171\nQuito\n3\n0\n")));
        assertTrue(output.contains("Ya existe un cliente con esa cedula."));
        assertTrue(output.contains("LISTA DE CLIENTES"));
        assertEquals(1, Customer.getAll().size());
    }

    @Test
    void consoleReportsEmptyOrderWithoutSavingIt() {
        new Customer("C1", "Ana", "099", "171", "Quito").register();
        User employee = new User("U1", "ana", "secret", Role.EMPLOYEE);
        String output = capture(() -> ConsoleInput.runAction(
                () -> WorkOrder.registerFromConsole(new Scanner("C1\n0\n"), employee)));
        assertTrue(output.contains("La orden debe tener al menos un detalle."));
        assertFalse(output.contains("creada exitosamente"));
        assertTrue(WorkOrder.getAll().isEmpty());
    }

    @Test
    void consoleCatchesPaymentExceptionWithoutShowingStackTrace() {
        String output = capture(() -> ConsoleInput.runAction(
                () -> payment("P1", -1, order("WO-1")).registerPayment()));
        assertTrue(output.startsWith("Error: "));
        assertFalse(output.contains("Exception"));
    }

    @Test
    void consoleHidesTechnicalCauseOfPersistenceFailure() {
        String output = capture(() -> ConsoleInput.runAction(() -> {
            throw new PersistenceException("No se pudieron guardar los datos.", new IOException("Disk unavailable"));
        }));
        assertTrue(output.contains("Error: No se pudieron guardar los datos."));
        assertFalse(output.contains("Disk unavailable"));
        assertFalse(output.contains("Exception"));
    }

    @Test
    void persistenceExceptionPreservesTechnicalCause() {
        IOException cause = new IOException("Disk unavailable");
        PersistenceException exception = new PersistenceException("No se pudieron guardar los datos.", cause);
        assertSame(cause, exception.getCause());
        assertEquals("No se pudieron guardar los datos.", exception.getMessage());
    }
}