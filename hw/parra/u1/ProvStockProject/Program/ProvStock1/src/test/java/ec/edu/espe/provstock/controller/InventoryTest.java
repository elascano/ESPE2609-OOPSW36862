package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.Product;
import ec.edu.espe.provstock.model.Role;
import ec.edu.espe.provstock.model.User;
import java.util.Arrays;

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
public class InventoryTest {

    public InventoryTest() {
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

    private ProductCatalog catalog;
    private Inventory instance;
    private Product product;

    //creates the catalog, the inventory and one registered product
    private void createInventory() {
        catalog = new ProductCatalog();
        instance = new Inventory(new StockMonitor(catalog), catalog);
        product = new Product(0, "CAB-001", "Cable 12 AWG", "", "CABLES", "Incable", "METER", "A1",
                0.50, 0.80, 10);
        catalog.registerProduct(product);
    }

    /**
     * Test of adjustStock method, of class Inventory.
     */
    @Test
    public void testAdjustStockGood1() {
        System.out.println("adjustStock - Good 1");
        createInventory();
        Role role = new Role(2, Role.WAREHOUSE_CLERK, Arrays.asList(Role.ADJUST_STOCK));
        User user = new User(1, "cparra", "Carlos Parra", role, "Bodega2026");
        instance.adjustStock(product, 30, "Conteo fisico", user);
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        double expResult = 31.0;
        double result = product.getCurrentStock();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of adjustStock method, of class Inventory.
     */
    @Test
    public void testAdjustStockBad1() {
        System.out.println("adjustStock - Bad 1");
        createInventory();
        Role role = new Role(1, Role.SALESPERSON, Arrays.asList(Role.VIEW_PRODUCTS, Role.REGISTER_SALE));
        User user = new User(1, "mpaucar", "Matias Paucar", role, "Espe2026");
        assertThrows(SecurityException.class, () -> instance.adjustStock(product, 30, "Conteo fisico", user));
    }
    
}
