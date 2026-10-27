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
public class SaleDetailTest {

    public SaleDetailTest() {
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
     * Test of calculateSubtotal method, of class SaleDetail.
     */
    @Test
    public void testCalculateSubtotalGood1() {
        System.out.println("calculateSubtotal - Good 1");
        Product product = new Product(1, "CAB-001", "Cable 12 AWG", "Cable de cobre", "CABLES", "Incable", "METER", "A1", 0.50, 0.80, 10);
        SaleDetail instance = new SaleDetail(product, 3, 2.50);
        double expResult = 7.50;
        double result = instance.calculateSubtotal();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of calculateSubtotal method, of class SaleDetail.
     */
    @Test
    public void testCalculateSubtotalStrong1() {
        System.out.println("calculateSubtotal - Strong 1");
        Product product = new Product(1, "CAB-001", "Cable 12 AWG", "Cable de cobre", "CABLES", "Incable", "METER", "A1", 0.50, 0.80, 10);
        SaleDetail instance = new SaleDetail(product, 0.333, 3.0);
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        double expResult = 2.0;
        double result = instance.calculateSubtotal();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of validate method, of class SaleDetail.
     */
    @Test
    public void testValidateBad1() {
        System.out.println("validate - Bad 1");
        Product product = new Product(1, "CAB-001", "Cable 12 AWG", "Cable de cobre", "CABLES", "Incable", "METER", "A1", 0.50, 0.80, 10);
        SaleDetail instance = new SaleDetail(product, 0, 2.50);
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }
    
}
