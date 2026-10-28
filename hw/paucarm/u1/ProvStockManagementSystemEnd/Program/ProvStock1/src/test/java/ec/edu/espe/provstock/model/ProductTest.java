package ec.edu.espe.provstock.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class ProductTest {

    public ProductTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of validate method, of class Product.
     */
    @Test
    public void testValidateGood1() {
        System.out.println("validate - Good 1");
        Product instance = new Product(1, "CAB-001", "Cable 12 AWG", "Cable de cobre", "CABLES", "Incable", "METER", "A1", 0.50, 0.80, 10);
        boolean expResult = true;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class Product.
     */
    @Test
    public void testValidateBad1() {
        System.out.println("validate - Bad 1");
        Product instance = new Product(1, "CAB-001", "", "Cable de cobre", "CABLES", "Incable", "METER", "A1", 0.50, 0.80, 10);
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
        System.out.println("validate - Bad 2");
        Product instance = new Product(1, "CAB-001", "Cable 12 AWG", "Cable de cobre", "FOOD", "Incable", "METER", "A1", 0.50, 0.80, 10);
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class Product.
     */
    @Test
    public void testValidateBad3() {
        System.out.println("validate - Bad 3");
        Product instance = new Product(1, "CAB-001", "Cable 12 AWG", "Cable de cobre", "CABLES", "Incable", "METER", "A1", 0.50, 0.0, 10);
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
        System.out.println("hasStock - Good 1");
        Product instance = new Product(1, "CAB-001", "Cable 12 AWG", "Cable de cobre", "CABLES", "Incable", "METER", "A1", 0.50, 0.80, 10);
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
        System.out.println("hasStock - Strong 1");
        Product instance = new Product(1, "CAB-001", "Cable 12 AWG", "Cable de cobre", "CABLES", "Incable", "METER", "A1", 0.50, 0.80, 10);
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
        System.out.println("hasStock - Bad 1");
        Product instance = new Product(1, "CAB-001", "Cable 12 AWG", "Cable de cobre", "CABLES", "Incable", "METER", "A1", 0.50, 0.80, 10);
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
        System.out.println("isLowStock - Good 1");
        Product instance = new Product(1, "CAB-001", "Cable 12 AWG", "Cable de cobre", "CABLES", "Incable", "METER", "A1", 0.50, 0.80, 10);
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
        System.out.println("isLowStock - Good 2");
        Product instance = new Product(1, "CAB-001", "Cable 12 AWG", "Cable de cobre", "CABLES", "Incable", "METER", "A1", 0.50, 0.80, 10);
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
        System.out.println("isLowStock - Strong 1");
        Product instance = new Product(1, "CAB-001", "Cable 12 AWG", "Cable de cobre", "CABLES", "Incable", "METER", "A1", 0.50, 0.80, 10);
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
        System.out.println("getStockValue - Good 1");
        Product instance = new Product(1, "CAB-001", "Cable 12 AWG", "Cable de cobre", "CABLES", "Incable", "METER", "A1", 0.50, 0.80, 10);
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
        System.out.println("getStockValue - Strong 1");
        Product instance = new Product(1, "CAB-001", "Cable 12 AWG", "Cable de cobre", "CABLES", "Incable", "METER", "A1", 0.50, 0.80, 10);
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
        System.out.println("updateStock - Good 1");
        Product instance = new Product(1, "CAB-001", "Cable 12 AWG", "Cable de cobre", "CABLES", "Incable", "METER", "A1", 0.50, 0.80, 10);
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
        System.out.println("updateStock - Bad 1");
        Product instance = new Product(1, "CAB-001", "Cable 12 AWG", "Cable de cobre", "CABLES", "Incable", "METER", "A1", 0.50, 0.80, 10);
        double quantity = -5;
        assertThrows(IllegalArgumentException.class, () -> instance.updateStock(quantity));
    }
    
}
