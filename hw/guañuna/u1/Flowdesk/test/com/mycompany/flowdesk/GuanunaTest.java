package com.mycompany.flowdesk;

import enums.MovementType;
import enums.PaymentMethod;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import models.CashMovement;
import models.CashRegister;
import models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.Date;


public class GuanunaTest {

    @BeforeEach
    public void setUp() {
        Customer.setAll(new ArrayList<>());
        CashMovement.setAll(new ArrayList<>());
    }

    private String capture(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(buffer));
            action.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString();
    }

    private CashMovement movement(String id, MovementType type, double amount, PaymentMethod method) {
        return new CashMovement(id, type, amount, new Date(26, 10, 2026), "Office", method);
    }

    @Test public void testRegisterOneCustomer() {
        Customer customer = new Customer("C1", "Ana", "0991", "171", "Quito");
        customer.register();
        int expResult = 1;
        int result = Customer.getAll().size();
        assertEquals(expResult, result);
    }

    @Test public void testRegisterMultipleCustomers() {
        new Customer("C1", "Ana", "0991", "171", "Quito").register();
        new Customer("C2", "Luis", "0992", "172", "Sangolqui").register();
        int expResult = 2;
        int result = Customer.getAll().size();
        assertEquals(expResult, result);
    }

    @Test public void testSearchCustomerById() {
        Customer customer = new Customer("C1", "Ana", "0991", "171", "Quito");
        customer.register();
        Customer expResult = customer;
        Customer result = Customer.search("C1");
        assertSame(expResult, result);
    }

    @Test public void testSearchCustomerByNationalId() {
        Customer customer = new Customer("C1", "Ana", "0991", "171", "Quito");
        customer.register();
        Customer expResult = customer;
        Customer result = Customer.search("171");
        assertSame(expResult, result);
    }

    @Test public void testSearchCustomerIdIgnoringCase() {
        Customer customer = new Customer("CUS-01", "Ana", "0991", "171", "Quito");
        customer.register();
        Customer expResult = customer;
        Customer result = Customer.search("cus-01");
        assertSame(expResult, result);
    }

    @Test public void testSearchUnknownCustomer() {
        new Customer("C1", "Ana", "0991", "171", "Quito").register();
        Customer expResult = null;
        Customer result = Customer.search("C99");
        assertEquals(expResult, result);
    }

    @Test public void testSearchCustomerWithEmptyRegistry() {
        Customer expResult = null;
        Customer result = Customer.search("C1");
        assertEquals(expResult, result);
    }

    @Test public void testCustomerNameField() {
        Customer customer = new Customer("C1", "Ana Perez", "0991", "171", "Quito");
        String expResult = "Ana Perez";
        String result = customer.getName();
        assertEquals(expResult, result);
    }

    @Test public void testCustomerContactFields() {
        Customer customer = new Customer("C1", "Ana", "099123", "171", "Quito");
        String expResult = "099123|Quito";
        String result = customer.getPhone() + "|" + customer.getAddress();
        assertEquals(expResult, result);
    }

    @Test public void testCustomerConsoleRejectsDuplicateId() {
        new Customer("C1", "Ana", "0991", "171", "Quito").register();
        Scanner scanner = new Scanner("1\nC1\n0\n");
        String result = capture(() -> Customer.showManagementMenu(scanner));
        assertTrue(result.contains("Ya existe un cliente con ese ID"));
        assertEquals(1, Customer.getAll().size());
    }

    @Test public void testIncomeMovementIncreasesIncome() {
        CashRegister cash = new CashRegister();
        movement("M1", MovementType.INCOME, 25.0, PaymentMethod.CASH).registerMovement(cash);
        double expResult = 25.0;
        double result = cash.calculateIncome();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testExpenseMovementIncreasesExpenses() {
        CashRegister cash = new CashRegister();
        movement("M2", MovementType.EXPENSE, 12.0, PaymentMethod.CASH).registerMovement(cash);
        double expResult = 12.0;
        double result = cash.calculateExpenses();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testIncomeMovementDoesNotIncreaseExpenses() {
        CashRegister cash = new CashRegister();
        movement("M3", MovementType.INCOME, 40.0, PaymentMethod.TRANSFER).registerMovement(cash);
        double expResult = 0.0;
        double result = cash.calculateExpenses();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testExpenseMovementDoesNotIncreaseIncome() {
        CashRegister cash = new CashRegister();
        movement("M4", MovementType.EXPENSE, 6.0, PaymentMethod.TRANSFER).registerMovement(cash);
        double expResult = 0.0;
        double result = cash.calculateIncome();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testTwoIncomeMovementsAreAccumulated() {
        CashRegister cash = new CashRegister();
        movement("M5", MovementType.INCOME, 20.0, PaymentMethod.CASH).registerMovement(cash);
        movement("M6", MovementType.INCOME, 30.0, PaymentMethod.TRANSFER).registerMovement(cash);
        double expResult = 50.0;
        double result = cash.calculateIncome();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testTwoExpenseMovementsAreAccumulated() {
        CashRegister cash = new CashRegister();
        movement("M7", MovementType.EXPENSE, 4.0, PaymentMethod.CASH).registerMovement(cash);
        movement("M8", MovementType.EXPENSE, 9.0, PaymentMethod.TRANSFER).registerMovement(cash);
        double expResult = 13.0;
        double result = cash.calculateExpenses();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testMixedMovementsUpdateBalance() {
        CashRegister cash = new CashRegister();
        movement("M9", MovementType.INCOME, 80.0, PaymentMethod.CASH).registerMovement(cash);
        movement("M10", MovementType.EXPENSE, 25.0, PaymentMethod.CASH).registerMovement(cash);
        double expResult = 55.0;
        double result = cash.calculateBalance();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testCashMovementConsoleIncome() {
        CashRegister cash = new CashRegister();
        Scanner scanner = new Scanner("M11\n1\n15.5\nSales\n1\n");
        capture(() -> CashMovement.registerFromConsole(scanner, cash));
        double expResult = 15.5;
        double result = cash.calculateIncome();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testCashMovementConsoleExpense() {
        CashRegister cash = new CashRegister();
        Scanner scanner = new Scanner("M12\n2\n7.5\nSupplies\n2\n");
        capture(() -> CashMovement.registerFromConsole(scanner, cash));
        double expResult = 7.5;
        double result = cash.calculateExpenses();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testCashMovementConsolePrintsConfirmation() {
        CashRegister cash = new CashRegister();
        Scanner scanner = new Scanner("M13\n1\n8\nDesign\n1\n");
        String result = capture(() -> CashMovement.registerFromConsole(scanner, cash));
        assertTrue(result.contains("Movimiento registrado exitosamente"));
    }

    @Test public void testEmptyCashRegisterBalance() {
        CashRegister cash = new CashRegister();
        double expResult = 0.0;
        double result = cash.calculateBalance();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testCashRegisterStartingValues() {
        CashRegister cash = new CashRegister(100, 30);
        double expResult = 70.0;
        double result = cash.calculateBalance();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testAddIncomeDirectly() {
        CashRegister cash = new CashRegister();
        cash.addIncome(75);
        double expResult = 75.0;
        double result = cash.calculateIncome();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testAddExpenseDirectly() {
        CashRegister cash = new CashRegister();
        cash.addExpense(12);
        double expResult = 12.0;
        double result = cash.calculateExpenses();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testMultipleDirectIncomeAdds() {
        CashRegister cash = new CashRegister();
        cash.addIncome(10);
        cash.addIncome(20);
        cash.addIncome(30);
        double expResult = 60.0;
        double result = cash.calculateIncome();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testMultipleDirectExpenseAdds() {
        CashRegister cash = new CashRegister();
        cash.addExpense(4);
        cash.addExpense(6);
        double expResult = 10.0;
        double result = cash.calculateExpenses();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testNegativeCashRegisterBalance() {
        CashRegister cash = new CashRegister(10, 35);
        double expResult = -25.0;
        double result = cash.calculateBalance();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testCashRegisterSetIncome() {
        CashRegister cash = new CashRegister();
        cash.setIncome(90);
        double expResult = 90.0;
        double result = cash.getIncome();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testCashRegisterSetExpenses() {
        CashRegister cash = new CashRegister();
        cash.setExpenses(15);
        double expResult = 15.0;
        double result = cash.getExpenses();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testCashRegisterPrintedBalance() {
        CashRegister cash = new CashRegister(120, 45);
        String result = capture(cash::printStatus);
        assertTrue(result.contains("Balance Actual:   $75.0"));
    }
}
