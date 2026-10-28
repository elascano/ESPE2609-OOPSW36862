package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.Product;
import ec.edu.espe.provstock.model.ProductInfo;
import ec.edu.espe.provstock.model.Sale;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class SalesManagerTest {

    private ProductCatalog catalog;
    private AccessManager accessManager;
    private SalesManager instance;
    private Product product;

    //creates the managers, logs in as owner and registers one product with 100 units
    private void createSalesManager() {
        catalog = new ProductCatalog();
        accessManager = new AccessManager();
        accessManager.login("owner", "Owner2026");
        Inventory inventory = new Inventory(new StockMonitor(catalog), catalog);
        instance = new SalesManager(inventory, new CustomerManager(), accessManager);
        product = new Product(0, "CAB-001", new ProductInfo("Cable 12 AWG", "CABLES", "METER")
                .withDescription("").withBrand("Incable").withLocation("A1")
                .withPrices(0.50, 0.80).withMinimumStock(10));
        catalog.registerProduct(product);
        product.updateStock(100);
    }

    /**
     * Test of registerSale method, of class SalesManager.
     */
    @Test
    public void testRegisterSaleGood1() {
        createSalesManager();
        Sale sale = instance.createSale(null);
        sale.addProduct(product, 10);
        instance.registerSale(sale);
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        double expResult = 91.0;
        double result = product.getCurrentStock();
        assertEquals(expResult, result, 0.001);
        assertEquals(1, sale.getNumber());
    }

    /**
     * Test of updateVatRate method, of class SalesManager.
     */
    @Test
    public void testUpdateVatRateBad1() {
        createSalesManager();
        double newRate = 1.5;
        assertThrows(IllegalArgumentException.class, () -> instance.updateVatRate(newRate));
    }
    
}
