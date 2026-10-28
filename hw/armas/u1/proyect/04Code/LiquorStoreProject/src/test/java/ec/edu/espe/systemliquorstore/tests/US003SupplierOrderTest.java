package ec.edu.espe.systemliquorstore.tests;

import ec.edu.espe.systemliquorstore.model.Product;
import ec.edu.espe.systemliquorstore.model.PurchaseOrder;
import ec.edu.espe.systemliquorstore.model.StockMovement;
import ec.edu.espe.systemliquorstore.model.Supplier;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Enrique Ayala, Logical Lords, @ESPE
 */

public class US003SupplierOrderTest {
    @Test public void testSupplier1() {
        System.out.println("createSupplier");
        Supplier instance = new Supplier(1, "Distribuidora Licores", "0999999999");
        assertEquals(1, instance.getSupplierId());
        assertTrue(instance.isValid());
    }

    @Test public void testSupplier2() {
        System.out.println("createSupplierEmptyName");
        boolean expResult = true;
        boolean result = false;
        try {
            new Supplier(2, "", "0999999999");
        } catch (IllegalArgumentException ex) {
            result = true;
        }
        assertEquals(expResult, result);
    }

    @Test public void testOrder3() {
        System.out.println("createOrder");
        Supplier supplier = new Supplier(1, "Distribuidora", "0999999999");
        PurchaseOrder order = supplier.createPurchaseOrder(1, "2026-10-25");
        assertEquals("PENDING", order.getStatus());
    }

    @Test public void testOrder4() {
        System.out.println("addProductToOrder");
        Supplier supplier = new Supplier(1, "Distribuidora", "0999999999");
        PurchaseOrder order = supplier.createPurchaseOrder(1, "2026-10-25");
        Product product = new Product("P001", "Ron", 10.0, 15.0, 5, 2);
        order.addProduct(product, 10);
        assertEquals(10, order.getItems().get(0).getQuantity());
    }

    @Test public void testOrder5() {
        System.out.println("receiveOrder");
        Supplier supplier = new Supplier(1, "Distribuidora", "0999999999");
        PurchaseOrder order = supplier.createPurchaseOrder(1, "2026-10-25");
        Product product = new Product("P001", "Ron", 10.0, 15.0, 5, 2);
        order.addProduct(product, 10);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        ArrayList<StockMovement> movements = new ArrayList<>();
        order.receive(products, movements, "2026-10-26");
        assertEquals("RECEIVED", order.getStatus());
        assertEquals(15, product.getStock());
    }

    @Test public void testOrder6() {
        System.out.println("receiveOrderDateFail");
        Supplier supplier = new Supplier(1, "Distribuidora", "0999999999");
        PurchaseOrder order = supplier.createPurchaseOrder(1, "2026-10-25");
        Product product = new Product("P001", "Ron", 10.0, 15.0, 5, 2);
        order.addProduct(product, 10);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        ArrayList<StockMovement> movements = new ArrayList<>();
        boolean expResult = true;
        boolean result = false;
        try {
            order.receive(products, movements, "2026-10-24");
        } catch (IllegalArgumentException ex) {
            result = true;
        }
        assertEquals(expResult, result);
    }

    @Test public void testOrder7() {
        System.out.println("receiveOrderTwice");
        Supplier supplier = new Supplier(1, "Distribuidora", "0999999999");
        PurchaseOrder order = supplier.createPurchaseOrder(1, "2026-10-25");
        Product product = new Product("P001", "Ron", 10.0, 15.0, 5, 2);
        order.addProduct(product, 10);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        ArrayList<StockMovement> movements = new ArrayList<>();
        order.receive(products, movements, "2026-10-26");
        boolean expResult = true;
        boolean result = false;
        try {
            order.receive(products, movements, "2026-10-27");
        } catch (IllegalStateException ex) {
            result = true;
        }
        assertEquals(expResult, result);
    }

    @Test public void testOrder8() {
        System.out.println("orderIsValid");
        assertThrows(IllegalArgumentException.class,
                () -> new PurchaseOrder(-1, -1, "fecha-mala"));
    }

    @Test public void testOrder9() {
        System.out.println("addProductToReceivedOrder");
        Supplier supplier = new Supplier(1, "Distribuidora", "0999999999");
        PurchaseOrder order = supplier.createPurchaseOrder(1, "2026-10-25");
        Product product = new Product("P001", "Ron", 10.0, 15.0, 5, 2);
        order.addProduct(product, 10);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        ArrayList<StockMovement> movements = new ArrayList<>();
        order.receive(products, movements, "2026-10-26");
        boolean expResult = true;
        boolean result = false;
        try {
            order.addProduct(product, 5);
        } catch (IllegalStateException ex) {
            result = true;
        }
        assertEquals(expResult, result);
    }

    @Test public void testOrder10() {
        System.out.println("supplierUpdate");
        Supplier supplier = new Supplier(1, "Distribuidora", "0999999999");
        supplier.setName("Nueva Distribuidora");
        assertEquals("Nueva Distribuidora", supplier.getName());
    }
}
