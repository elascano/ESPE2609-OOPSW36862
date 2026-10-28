package ec.edu.espe.systemliquorstore.tests;

import ec.edu.espe.systemliquorstore.exception.BusinessRuleException;
import ec.edu.espe.systemliquorstore.model.*;
import ec.edu.espe.systemliquorstore.service.StoreService;
import org.junit.Test;
import static org.junit.Assert.*;

public class StoreServiceTest {
    @Test
    public void productCrud() {
        StoreData data = new StoreData();
        StoreService service = new StoreService(data);
        service.addProduct(new Product("P1", "Vino", 5, 8, 10, 2));

        service.updateProduct("P1", "Vino tinto", 6, 9, 3);

        assertEquals("Vino tinto", service.findProduct("p1").getName());
        assertEquals(9, service.getProducts().get(0).getSellingPrice(), 0.001);
        service.deleteProduct("P1");
        assertTrue(service.getProducts().isEmpty());
    }

    @Test
    public void customerAndSupplierCrud() {
        StoreService service = new StoreService(new StoreData());
        service.addCustomer(new Customer(1, "Ana"));
        service.addSupplier(new Supplier(1, "Licores", "0991234567"));

        service.updateCustomer(1, "Ana Pérez");
        service.updateSupplier(1, "Distribuidora", "0981234567");

        assertEquals("Ana Pérez", service.findCustomer(1).getName());
        assertEquals("0981234567", service.findSupplier(1).getContact());
        service.deleteCustomer(1);
        service.deleteSupplier(1);
        assertTrue(service.getCustomers().isEmpty());
        assertTrue(service.getSuppliers().isEmpty());
    }

    @Test
    public void duplicateCodeDoesNotAddProduct() {
        StoreService service = new StoreService(new StoreData());
        service.addProduct(new Product("P1", "Vino", 5, 8, 10, 2));

        assertThrows(BusinessRuleException.class,
                () -> service.addProduct(new Product(" p1 ", "Otro vino", 5, 8, 10, 2)));
        assertEquals(1, service.getProducts().size());
    }

    @Test
    public void productAndSupplierWithOrdersCannotBeDeleted() {
        StoreData data = new StoreData();
        StoreService service = new StoreService(data);
        Product product = new Product("P1", "Vino", 5, 8, 10, 2);
        Supplier supplier = new Supplier(1, "Distribuidora", "0991234567");
        service.addProduct(product);
        service.addSupplier(supplier);
        PurchaseOrder order = supplier.createPurchaseOrder(1, "2026-10-25");
        order.addProduct(product, 2);
        data.getOrders().add(order);

        assertThrows(BusinessRuleException.class, () -> service.deleteProduct("P1"));
        assertThrows(BusinessRuleException.class, () -> service.deleteSupplier(1));
        assertEquals(1, service.getProducts().size());
        assertEquals(1, service.getSuppliers().size());
    }
}
