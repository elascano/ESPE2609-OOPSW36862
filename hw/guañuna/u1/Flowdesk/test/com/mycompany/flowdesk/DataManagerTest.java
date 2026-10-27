package com.mycompany.flowdesk;

import data.DataManager;
import models.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import utils.Date;
import enums.OrderStatus;
import enums.PaymentMethod;
import enums.MovementType;
import exceptions.PersistenceException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataManagerTest {

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        System.setProperty("flowdesk.dataDirectory", tempDir.toString());
        DataManager.init();
        
        Customer.setAll(new ArrayList<>());
        Category.setAll(new ArrayList<>());
        Product.setAll(new ArrayList<>());
        Service.setAll(new ArrayList<>());
        WorkOrder.setAll(new ArrayList<>());
        CashMovement.setAll(new ArrayList<>());
    }

    @AfterEach
    void tearDown() {
        System.clearProperty("flowdesk.dataDirectory");
    }

    @Test
    void testSaveAndLoadCustomersAndCatalog() {
        Category cat = new Category("C1", "Herramientas", "Fierro");
        Category.setAll(List.of(cat));

        Product p = new Product("P1", "Martillo", "Fierro", 10.0, cat);
        Product.setAll(List.of(p));

        Service s = new Service("S1", "Reparación", "Arreglo", 20.0, cat);
        Service.setAll(List.of(s));

        DataManager.saveAll();

        Customer.setAll(new ArrayList<>());
        Category.setAll(new ArrayList<>());
        Product.setAll(new ArrayList<>());
        Service.setAll(new ArrayList<>());

        DataManager.loadAll();

        assertEquals(1, Product.getAll().size());
        assertEquals("Martillo", Product.getAll().get(0).getName());
        assertEquals(1, Service.getAll().size());
        assertEquals("Reparación", Service.getAll().get(0).getName());
    }

    @Test
    void testSaveAndLoadOrdersWithPayments() {
        Customer c = new Customer("C01", "Juan", "099", "123", "Calle 1");
        Customer.setAll(List.of(c));

        WorkOrder order = new WorkOrder("WO-001", Date.now(), OrderStatus.WAITING);
        order.getDetails().add(new OrderDetail("D1", "Item", 1, "desc", 100.0));
        order.calculateTotals();
        WorkOrder.setAll(List.of(order));

        Payment p = new Payment("P1", 50.0, Date.now(), PaymentMethod.CASH);
        p.setWorkOrder(order);
        order.addPayment(p);

        DataManager.saveAll();
        WorkOrder.setAll(new ArrayList<>());

        DataManager.loadAll();

        assertEquals(1, WorkOrder.getAll().size());
        WorkOrder loadedOrder = WorkOrder.getAll().get(0);
        
        assertEquals(100.0, loadedOrder.getTotal());
        assertEquals(50.0, loadedOrder.getBalance());
        assertEquals(1, loadedOrder.getPayments().size());
        assertEquals(50.0, loadedOrder.calculateTotalPaid());
    }

    @Test
    void testSaveAndLoadCashMovements() {
        CashRegister cash = new CashRegister();
        
        CashMovement m = new CashMovement("M1", MovementType.INCOME, 100.0, Date.now(), "Venta", PaymentMethod.CASH);
        m.registerMovement(cash);

        DataManager.saveList(CashMovement.getAll(), "movements");
        DataManager.saveCashRegister(cash);

        CashMovement.setAll(new ArrayList<>());
        
        CashRegister loadedCash = DataManager.loadCashRegister();
        CashMovement.setAll(DataManager.loadList("movements", new com.google.gson.reflect.TypeToken<ArrayList<CashMovement>>(){}.getType()));

        assertEquals(1, CashMovement.getAll().size());
        assertEquals("M1", CashMovement.getAll().get(0).getMovementId());
        assertEquals(100.0, loadedCash.getIncome());
        assertEquals(1, loadedCash.getMovements().size());
    }

    @Test
    void testFailedLoadPreventsOverwrite() throws Exception {
        Path badFile = tempDir.resolve("customers.json");
        Files.writeString(badFile, "{ esto no es un json valido }");

        assertThrows(PersistenceException.class, () -> {
            DataManager.loadAll();
        });
    }
}