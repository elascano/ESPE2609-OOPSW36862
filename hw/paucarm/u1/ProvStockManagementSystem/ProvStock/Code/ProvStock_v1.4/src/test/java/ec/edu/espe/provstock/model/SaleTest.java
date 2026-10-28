package ec.edu.espe.provstock.model;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class SaleTest {

    //creates a product with 100 meters in stock and a sale price of 0.80
    private Product createProduct() {
        Product product = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        product.updateStock(100);
        return product;
    }

    //creates a new sale for a valid customer with VAT 15%
    private Sale createSale(double vatRate) {
        Customer customer = new Customer(1, Customer.CEDULA, "1710034065", "Matias Paucar", "0991234567",
                "matias@espe.edu.ec", "Sangolqui");
        Role role = new Role(1, Role.SALESPERSON, Arrays.asList(Role.VIEW_PRODUCTS, Role.REGISTER_SALE));
        User user = new User(1, "mpaucar", "Matias Paucar", role, "Espe2026");
        return new Sale(customer, user, vatRate);
    }

    /**
     * Test of calculateSubtotal method, of class Sale.
     */
    @Test
    public void testCalculateSubtotalGood1() {
        Sale instance = createSale(0.15);
        instance.addProduct(createProduct(), 10);
        double expResult = 8.0;
        double result = instance.calculateSubtotal();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of calculateVat method, of class Sale.
     */
    @Test
    public void testCalculateVatGood1() {
        Sale instance = createSale(0.15);
        instance.addProduct(createProduct(), 10);
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        double expResult = 2.2;
        double result = instance.calculateVat();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of calculateTotal method, of class Sale.
     */
    @Test
    public void testCalculateTotalGood1() {
        Sale instance = createSale(0.15);
        instance.addProduct(createProduct(), 10);
        double expResult = 9.20;
        double result = instance.calculateTotal();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of calculateTotal method, of class Sale.
     */
    @Test
    public void testCalculateTotalStrong1() {
        Sale instance = createSale(0.0);
        instance.addProduct(createProduct(), 10);
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        double expResult = 9.0;
        double result = instance.calculateTotal();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of addProduct method, of class Sale.
     */
    @Test
    public void testAddProductStrong1() {
        Sale instance = createSale(0.15);
        Product product = createProduct();
        instance.addProduct(product, 10);
        instance.addProduct(product, 5);
        double expResult = 15.0;
        double result = instance.getDetails().get(0).getQuantity();
        assertEquals(expResult, result, 0.001);
        assertEquals(1, instance.getDetails().size());
    }

    /**
     * Test of addProduct method, of class Sale.
     */
    @Test
    public void testAddProductBad1() {
        Sale instance = createSale(0.15);
        Product product = createProduct();
        assertThrows(IllegalArgumentException.class, () -> instance.addProduct(product, 101));
    }

    /**
     * Test of addProduct method, of class Sale.
     */
    @Test
    public void testAddProductBad2() {
        Sale instance = createSale(0.15);
        Product product = createProduct();
        assertThrows(IllegalArgumentException.class, () -> instance.addProduct(product, 0));
    }

    /**
     * Test of addProduct method, of class Sale.
     */
    @Test
    public void testAddProductBad3() {
        Sale instance = createSale(0.15);
        Product product = createProduct();
        product.deactivate();
        assertThrows(IllegalArgumentException.class, () -> instance.addProduct(product, 5));
    }

    /**
     * Test of removeProduct method, of class Sale.
     */
    @Test
    public void testRemoveProductGood1() {
        Sale instance = createSale(0.15);
        Product product = createProduct();
        instance.addProduct(product, 10);
        instance.removeProduct(product);
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        double expResult = 1.0;
        double result = instance.getTotal();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of validate method, of class Sale.
     */
    @Test
    public void testValidateBad1() {
        Sale instance = createSale(0.15);
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of voidSale method, of class Sale.
     */
    @Test
    public void testVoidSaleGood1() {
        Sale instance = createSale(0.15);
        instance.addProduct(createProduct(), 10);
        instance.setNumber(1);
        Role ownerRole = new Role(3, Role.OWNER, Arrays.asList(Role.VOID_SALE));
        User owner = new User(2, "owner", "Propietario", ownerRole, "Owner2026");
        instance.voidSale("Error en la cantidad", owner);
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = false;
        boolean result = instance.isVoided();
        assertEquals(expResult, result);
    }

    /**
     * Test of voidSale method, of class Sale.
     */
    @Test
    public void testVoidSaleBad1() {
        Sale instance = createSale(0.15);
        instance.addProduct(createProduct(), 10);
        Role ownerRole = new Role(3, Role.OWNER, Arrays.asList(Role.VOID_SALE));
        User owner = new User(2, "owner", "Propietario", ownerRole, "Owner2026");
        assertThrows(IllegalStateException.class, () -> instance.voidSale("Error en la cantidad", owner));
    }
    
}
