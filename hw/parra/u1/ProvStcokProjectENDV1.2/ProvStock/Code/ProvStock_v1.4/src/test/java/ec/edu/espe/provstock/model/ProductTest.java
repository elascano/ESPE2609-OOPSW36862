package ec.edu.espe.provstock.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class ProductTest {

    /**
     * Test of validate method, of class Product.
     */
    @Test
    public void testValidateGood1() {
        Product instance = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        boolean expResult = true;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class Product.
     */
    @Test
    public void testValidateBad1() {
        Product instance = new Product(1, "CAB-001", new ProductInfo("", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = true;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class Product.
     */
    @Test
    public void testValidateBad2() {
        Product instance = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "FOOD", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class Product.
     */
    @Test
    public void testValidateBad3() {
        Product instance = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.0).withMinimumStock(10));
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = true;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasStock method, of class Product.
     */
    @Test
    public void testHasStockGood1() {
        Product instance = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        instance.updateStock(50);
        double quantity = 20;
        boolean expResult = true;
        boolean result = instance.hasStock(quantity);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasStock method, of class Product.
     */
    @Test
    public void testHasStockStrong1() {
        Product instance = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        instance.updateStock(50);
        double quantity = 50;
        boolean expResult = true;
        boolean result = instance.hasStock(quantity);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasStock method, of class Product.
     */
    @Test
    public void testHasStockBad1() {
        Product instance = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        instance.updateStock(50);
        double quantity = 51;
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = true;
        boolean result = instance.hasStock(quantity);
        assertEquals(expResult, result);
    }

    /**
     * Test of isLowStock method, of class Product.
     */
    @Test
    public void testIsLowStockGood1() {
        Product instance = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        instance.updateStock(5);
        boolean expResult = true;
        boolean result = instance.isLowStock();
        assertEquals(expResult, result);
    }

    /**
     * Test of isLowStock method, of class Product.
     */
    @Test
    public void testIsLowStockGood2() {
        Product instance = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        instance.updateStock(50);
        boolean expResult = false;
        boolean result = instance.isLowStock();
        assertEquals(expResult, result);
    }

    /**
     * Test of isLowStock method, of class Product.
     */
    @Test
    public void testIsLowStockStrong1() {
        Product instance = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        instance.updateStock(10);
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = false;
        boolean result = instance.isLowStock();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStockValue method, of class Product.
     */
    @Test
    public void testGetStockValueGood1() {
        Product instance = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        instance.updateStock(100);
        double expResult = 50.0;
        double result = instance.getStockValue();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of getStockValue method, of class Product.
     */
    @Test
    public void testGetStockValueStrong1() {
        Product instance = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        double expResult = 1.0;
        double result = instance.getStockValue();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of updateStock method, of class Product.
     */
    @Test
    public void testUpdateStockGood1() {
        Product instance = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        double quantity = 20;
        instance.updateStock(quantity);
        double expResult = 20.0;
        double result = instance.getCurrentStock();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of updateStock method, of class Product.
     */
    @Test
    public void testUpdateStockBad1() {
        Product instance = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        double quantity = -5;
        assertThrows(IllegalArgumentException.class, () -> instance.updateStock(quantity));
    }
    
}
