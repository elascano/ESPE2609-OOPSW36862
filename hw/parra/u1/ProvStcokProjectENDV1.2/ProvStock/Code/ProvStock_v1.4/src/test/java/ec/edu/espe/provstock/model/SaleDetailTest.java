package ec.edu.espe.provstock.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class SaleDetailTest {

    /**
     * Test of calculateSubtotal method, of class SaleDetail.
     */
    @Test
    public void testCalculateSubtotalGood1() {
        Product product = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
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
        Product product = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
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
        Product product = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        SaleDetail instance = new SaleDetail(product, 0, 2.50);
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }
    
}
