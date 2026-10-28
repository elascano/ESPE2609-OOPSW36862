package com.mycompany.flowdesk;

import enums.OrderStatus;
import enums.PaymentMethod;
import enums.ReportType;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.CashRegister;
import models.Customer;
import models.OrderDetail;
import models.Payment;
import models.WorkOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import reports.Dashboard;
import reports.Report;
import utils.Date;


public class JaramilloTest {

    @BeforeEach
    public void setUp() {
        WorkOrder.setAll(new ArrayList<>());
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

    private WorkOrder order(String id, OrderStatus status, double price) {
        WorkOrder result = new WorkOrder(id, new Date(26, 10, 2026), status);
        result.getDetails().add(new OrderDetail("D-" + id, "Banner", 1, "Vinyl", price));
        result.calculateTotals();
        return result;
    }

    private void assignCustomer(WorkOrder order) {
        try {
            Field field = WorkOrder.class.getDeclaredField("customer");
            field.setAccessible(true);
            field.set(order, new Customer("C1", "Ana", "099", "171", "Quito"));
        } catch (ReflectiveOperationException ex) {
            throw new AssertionError(ex);
        }
    }

    private String dashboardOutput(WorkOrder... orders) {
        Dashboard dashboard = new Dashboard();
        List<WorkOrder> list = new ArrayList<>();
        for (WorkOrder order : orders) {
            assignCustomer(order);
            list.add(order);
        }
        dashboard.setOrders(list);
        return capture(dashboard::showDashboard);
    }

    private String reportOutput(String option, CashRegister cash) {
        return capture(() -> Report.generateFromConsole(new Scanner(option + "\n"), cash));
    }

    @Test public void testDashboardStartsWithoutOrders() {
        Dashboard dashboard = new Dashboard();
        List<WorkOrder> expResult = null;
        List<WorkOrder> result = dashboard.getOrders();
        assertEquals(expResult, result);
    }

    @Test public void testDashboardAcceptsOrderList() {
        Dashboard dashboard = new Dashboard();
        List<WorkOrder> expResult = new ArrayList<>();
        dashboard.setOrders(expResult);
        List<WorkOrder> result = dashboard.getOrders();
        assertSame(expResult, result);
    }

    @Test public void testDashboardRejectsEmptyDisplayGracefully() {
        Dashboard dashboard = new Dashboard();
        dashboard.setOrders(new ArrayList<>());
        String result = capture(dashboard::showDashboard);
        assertTrue(result.contains("lista de ordenes esta vacia"));
    }

    @Test public void testDashboardCountsOneWaitingOrder() {
        String result = dashboardOutput(order("W1", OrderStatus.WAITING, 10));
        assertTrue(result.contains("En espera: 1 orden(es)"));
    }

    @Test public void testDashboardCountsTwoApprovedOrders() {
        String result = dashboardOutput(order("A1", OrderStatus.APPROVED, 10), order("A2", OrderStatus.APPROVED, 20));
        assertTrue(result.contains("Aprobada: 2 orden(es)"));
    }

    @Test public void testDashboardCountsInProgressOrder() {
        String result = dashboardOutput(order("P1", OrderStatus.IN_PROGRESS, 15));
        assertTrue(result.contains("En proceso: 1 orden(es)"));
    }

    @Test public void testDashboardCountsCompletedOrder() {
        String result = dashboardOutput(order("C1", OrderStatus.COMPLETED, 30));
        assertTrue(result.contains("Completada: 1 orden(es)"));
    }

    @Test public void testDashboardCountsDeliveredOrder() {
        String result = dashboardOutput(order("D1", OrderStatus.DELIVERED, 30));
        assertTrue(result.contains("Entregada: 1 orden(es)"));
    }

    @Test public void testDashboardDisplaysTotalOrderCount() {
        String result = dashboardOutput(order("W1", OrderStatus.WAITING, 10), order("D1", OrderStatus.DELIVERED, 20));
        assertTrue(result.contains("Total de ordenes en el sistema: 2"));
    }

    @Test public void testDashboardListsPendingOrderId() {
        String result = dashboardOutput(order("WO-888", OrderStatus.WAITING, 10));
        assertTrue(result.contains("[WO-888] Cliente: Ana | Estado: En espera"));
    }

    @Test public void testDailyReportSelection() {
        String result = reportOutput("1", new CashRegister());
        assertTrue(result.contains("REPORTE Diario DE FLOWDESK"));
    }

    @Test public void testWeeklyReportSelection() {
        String result = reportOutput("2", new CashRegister());
        assertTrue(result.contains("REPORTE Semanal DE FLOWDESK"));
    }

    @Test public void testMonthlyReportSelection() {
        String result = reportOutput("3", new CashRegister());
        assertTrue(result.contains("REPORTE Mensual DE FLOWDESK"));
    }

    @Test public void testReportRejectsUnknownOption() {
        String result = reportOutput("9\n0", new CashRegister());
        assertTrue(result.contains("Opcion no valida"));
    }

    @Test public void testReportEmptyOrders() {
        String result = reportOutput("1", new CashRegister());
        assertTrue(result.contains("Total de ordenes: 0"));
    }

    @Test public void testReportCountsCompletedAndDeliveredOrders() {
        order("C1", OrderStatus.COMPLETED, 10).create();
        order("D1", OrderStatus.DELIVERED, 20).create();
        String result = reportOutput("2", new CashRegister());
        assertTrue(result.contains("Ordenes terminadas: 2"));
    }

    @Test public void testReportCountsPendingOrders() {
        order("W1", OrderStatus.WAITING, 10).create();
        order("A1", OrderStatus.APPROVED, 20).create();
        String result = reportOutput("2", new CashRegister());
        assertTrue(result.contains("Ordenes pendientes: 2"));
    }

    @Test public void testReportCalculatesOutstandingBalance() {
        order("W1", OrderStatus.WAITING, 25).create();
        order("W2", OrderStatus.WAITING, 15).create();
        String result = reportOutput("1", new CashRegister());
        assertTrue(result.contains("Dinero por cobrar (Saldos): $40.0"));
    }

    @Test public void testReportDisplaysCashIncome() {
        String result = reportOutput("1", new CashRegister(90, 20));
        assertTrue(result.contains("Ingresos totales: $90.0"));
    }

    @Test public void testReportDisplaysNetBalance() {
        String result = reportOutput("3", new CashRegister(90, 20));
        assertTrue(result.contains("Balance neto:     $70.0"));
    }

    @Test public void testPaymentAmountGetter() {
        Payment payment = new Payment("P1", 12.5, new Date(26, 10, 2026), PaymentMethod.CASH);
        double expResult = 12.5;
        double result = payment.getAmount();
        assertEquals(expResult, result, 0.001);
    }

    @Test public void testPaymentIdGetter() {
        Payment payment = new Payment("PAY-42", 10, new Date(26, 10, 2026), PaymentMethod.CASH);
        String expResult = "PAY-42";
        String result = payment.getPaymentId();
        assertEquals(expResult, result);
    }

    @Test public void testPaymentMethodGetter() {
        Payment payment = new Payment("P1", 10, new Date(26, 10, 2026), PaymentMethod.TRANSFER);
        PaymentMethod expResult = PaymentMethod.TRANSFER;
        PaymentMethod result = payment.getPaymentMethod();
        assertEquals(expResult, result);
    }

    @Test public void testValidCashPaymentAmount() {
        WorkOrder order = order("W1", OrderStatus.APPROVED, 50);
        Payment payment = new Payment("P1", 20, new Date(26, 10, 2026), PaymentMethod.CASH);
        payment.setWorkOrder(order);
        boolean expResult = true;
        boolean result = payment.validateAmount();
        assertEquals(expResult, result);
    }

    @Test public void testPaymentAmountZeroIsInvalid() {
        WorkOrder order = order("W1", OrderStatus.APPROVED, 50);
        Payment payment = new Payment("P1", 0, new Date(26, 10, 2026), PaymentMethod.CASH);
        payment.setWorkOrder(order);
        boolean expResult = false;
        boolean result = payment.validateAmount();
        assertEquals(expResult, result);
    }

    @Test public void testNegativePaymentAmountIsInvalid() {
        WorkOrder order = order("W1", OrderStatus.APPROVED, 50);
        Payment payment = new Payment("P1", -5, new Date(26, 10, 2026), PaymentMethod.CASH);
        payment.setWorkOrder(order);
        boolean expResult = false;
        boolean result = payment.validateAmount();
        assertEquals(expResult, result);
    }

    @Test public void testPaymentExceedingBalanceIsInvalid() {
        WorkOrder order = order("W1", OrderStatus.APPROVED, 50);
        Payment payment = new Payment("P1", 51, new Date(26, 10, 2026), PaymentMethod.CASH);
        payment.setWorkOrder(order);
        boolean expResult = false;
        boolean result = payment.validateAmount();
        assertEquals(expResult, result);
    }

    @Test public void testPaymentWithoutWorkOrderIsInvalid() {
        Payment payment = new Payment("P1", 10, new Date(26, 10, 2026), PaymentMethod.CASH);
        boolean expResult = false;
        boolean result = payment.validateAmount();
        assertEquals(expResult, result);
    }

    @Test public void testRegisterPaymentWithoutOrderThrows() {
        Payment payment = new Payment("P1", 10, new Date(26, 10, 2026), PaymentMethod.CASH);
        assertThrows(exceptions.InvalidPaymentException.class, payment::registerPayment);
    }

    @Test public void testPaymentDetailsIncludeIdAndMethod() {
        Payment payment = new Payment("PAY-77", 10, new Date(26, 10, 2026), PaymentMethod.TRANSFER);
        String result = payment.getPaymentDetails();
        assertAll(() -> assertTrue(result.contains("PAY-77")),
                  () -> assertTrue(result.contains("Transferencia")));
    }
}
