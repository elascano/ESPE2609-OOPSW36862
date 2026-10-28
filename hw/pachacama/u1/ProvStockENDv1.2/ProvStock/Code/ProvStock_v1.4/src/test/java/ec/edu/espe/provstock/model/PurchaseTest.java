package ec.edu.espe.provstock.model;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class PurchaseTest {

    //creates an active product without stock
    private Product createProduct(String code, double purchaseCost, double salePrice) {
        return new Product(1, code, new ProductInfo("Producto " + code, "CABLES", "METER")
                .withDescription("").withBrand("Incable").withLocation("A1")
                .withPrices(purchaseCost, salePrice).withMinimumStock(10));
    }

    //creates a new purchase for a valid supplier
    private Purchase createPurchase(String invoiceNumber) {
        Supplier supplier = new Supplier(1, "1790011674001", "Electro Andes S.A.", "Carlos Parra", "022345678",
                "ventas@electroandes.com", "Quito");
        Role role = new Role(2, Role.WAREHOUSE_CLERK, Arrays.asList(Role.REGISTER_PURCHASE));
        User user = new User(1, "cparra", "Carlos Parra", role, "Bodega2026");
        return new Purchase(supplier, user, invoiceNumber);
    }

    /**
     * Test of calculateTotal method, of class Purchase.
     */
    @Test
    public void testCalculateTotalGood1() {
        Purchase instance = createPurchase("001-001-000123");
        instance.addProduct(createProduct("CAB-001", 0.45, 0.80), 100, 0.45);
        instance.addProduct(createProduct("INT-001", 2.00, 3.50), 10, 2.00);
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        double expResult = 66.0;
        double result = instance.calculateTotal();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of addProduct method, of class Purchase.
     */
    @Test
    public void testAddProductStrong1() {
        Purchase instance = createPurchase("001-001-000123");
        Product product = createProduct("CAB-001", 0.45, 0.80);
        instance.addProduct(product, 100, 0.45);
        instance.addProduct(product, 50, 0.50);
        double expResult = 75.0;
        double result = instance.getTotal();
        assertEquals(expResult, result, 0.001);
        assertEquals(1, instance.getDetails().size());
    }

    /**
     * Test of addProduct method, of class Purchase.
     */
    @Test
    public void testAddProductBad1() {
        Purchase instance = createPurchase("001-001-000123");
        Product product = createProduct("CAB-001", 0.45, 0.80);
        assertThrows(IllegalArgumentException.class, () -> instance.addProduct(product, 100, -0.45));
    }

    /**
     * Test of validate method, of class Purchase.
     */
    @Test
    public void testValidateGood1() {
        Purchase instance = createPurchase("001-001-000123");
        instance.addProduct(createProduct("CAB-001", 0.45, 0.80), 100, 0.45);
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of addProduct method, of class Purchase.
     * BR-02: a unit cost of 0 is rejected.
     */
    @Test
    public void testAddProductBad2() {
        Purchase instance = createPurchase("001-001-000123");
        Product product = createProduct("CAB-001", 0.45, 0.80);
        assertThrows(IllegalArgumentException.class, () -> instance.addProduct(product, 100, 0));
    }

    /**
     * Test of validate method, of class Purchase.
     * UC-15: the supplier invoice number is optional.
     */
    @Test
    public void testValidateGood2() {
        Purchase instance = createPurchase("");
        instance.addProduct(createProduct("CAB-001", 0.45, 0.80), 100, 0.45);
        boolean expResult = true;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class Purchase.
     * A purchase without products is not valid.
     */
    @Test
    public void testValidateBad1() {
        Purchase instance = createPurchase("001-001-000123");
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }
    
}
