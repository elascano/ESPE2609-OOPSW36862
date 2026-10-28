package ec.edu.espe.provstock.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class PurchaseDetailTest {

    /**
     * Test of calculateSubtotal method, of class PurchaseDetail.
     */
    @Test
    public void testCalculateSubtotalGood1() {
        Product product = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        PurchaseDetail instance = new PurchaseDetail(product, 100, 0.45);
        double expResult = 45.0;
        double result = instance.calculateSubtotal();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of validate method, of class PurchaseDetail.
     */
    @Test
    public void testValidateGood1() {
        Product product = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        PurchaseDetail instance = new PurchaseDetail(product, 100, 0.45);
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class PurchaseDetail.
     */
    @Test
    public void testValidateBad1() {
        Product product = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        product.deactivate();
        PurchaseDetail instance = new PurchaseDetail(product, 100, 0.45);
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class PurchaseDetail.
     * BR-02: a unit cost of 0 is not valid.
     */
    @Test
    public void testValidateBad2() {
        Product product = new Product(1, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("Cable de cobre").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        PurchaseDetail instance = new PurchaseDetail(product, 100, 0);
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }
    
}
