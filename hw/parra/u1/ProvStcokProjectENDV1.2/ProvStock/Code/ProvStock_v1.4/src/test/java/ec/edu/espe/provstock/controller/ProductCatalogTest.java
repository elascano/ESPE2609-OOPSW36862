package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.Product;
import ec.edu.espe.provstock.model.ProductInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class ProductCatalogTest {

    private Product createProduct(String code, String name, String category) {
        return new Product(0, code, new ProductInfo(name, category, "UNIT")
                .withDescription("").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
    }

    /**
     * Test of registerProduct method, of class ProductCatalog.
     */
    @Test
    public void testRegisterProductGood1() {
        ProductCatalog instance = new ProductCatalog();
        Product product = createProduct("CAB-001", "Cable 12 AWG", "CABLES");
        instance.registerProduct(product);
        boolean expResult = true;
        boolean result = instance.isRegistered("CAB-001");
        assertEquals(expResult, result);
    }

    /**
     * Test of registerProduct method, of class ProductCatalog.
     */
    @Test
    public void testRegisterProductBad1() {
        ProductCatalog instance = new ProductCatalog();
        instance.registerProduct(createProduct("CAB-001", "Cable 12 AWG", "CABLES"));
        Product repeated = createProduct("cab-001", "Cable 14 AWG", "CABLES");
        //this test fails on purpose: the expected exception is wrong (35% failure requirement)
        assertThrows(IllegalStateException.class, () -> instance.registerProduct(repeated));
    }

    /**
     * Test of findByCode method, of class ProductCatalog.
     */
    @Test
    public void testFindByCodeStrong1() {
        ProductCatalog instance = new ProductCatalog();
        Product product = createProduct("CAB-001", "Cable 12 AWG", "CABLES");
        instance.registerProduct(product);
        Product expResult = product;
        Product result = instance.findByCode("  cab-001 ");
        assertEquals(expResult, result);
    }

    /**
     * Test of search method, of class ProductCatalog.
     */
    @Test
    public void testSearchGood1() {
        ProductCatalog instance = new ProductCatalog();
        instance.registerProduct(createProduct("CAB-001", "Cable 12 AWG", "CABLES"));
        instance.registerProduct(createProduct("FOC-001", "Foco LED 9W", "LIGHTING"));
        instance.registerProduct(createProduct("FOC-002", "Foco LED 12W", "LIGHTING"));
        int expResult = 2;
        int result = instance.search("", "LIGHTING").size();
        assertEquals(expResult, result);
    }

    /**
     * Test of deactivateProduct method, of class ProductCatalog.
     */
    @Test
    public void testDeactivateProductGood1() {
        ProductCatalog instance = new ProductCatalog();
        Product product = createProduct("CAB-001", "Cable 12 AWG", "CABLES");
        instance.registerProduct(product);
        instance.deactivateProduct(product);
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        int expResult = 1;
        int result = instance.getActiveProducts().size();
        assertEquals(expResult, result);
    }
    
}
