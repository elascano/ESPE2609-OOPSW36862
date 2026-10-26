package ec.edu.espe.unistock.service;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.model.Product;
import ec.edu.espe.unistock.model.ProductCategory;
import ec.edu.espe.unistock.structure.SimpleList;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StockAlertServiceTest {

    private StockAlertService instance;
    private SimpleList<Product> products;

    public StockAlertServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        instance = new StockAlertService();
        products = new SimpleList<>();
        products.add(new ProductImpl(1, "Cuaderno", new BigDecimal("2.50"), 3, ProductCategory.STATIONERY));
        products.add(new ProductImpl(2, "Camisa Escolar", new BigDecimal("15.00"), 12, ProductCategory.UNIFORM));
    }

    @AfterEach
    public void tearDown() {
        instance = null;
        products = null;
    }

    @Test
    public void testGetLowStockProductsValidList() {
        SimpleList<Product> result = instance.getLowStockProducts(products);
        assertNotNull(result);
    }

    @Test
    public void testGetLowStockProductsSizeCheck() {
        SimpleList<Product> result = instance.getLowStockProducts(products);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetLowStockProductsEmptyList() {
        SimpleList<Product> emptyList = new SimpleList<>();
        SimpleList<Product> result = instance.getLowStockProducts(emptyList);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetLowStockProductsNullList() {
        assertDoesNotThrow(() -> instance.getLowStockProducts(null));
    }

    @Test
    public void testGetLowStockProductsAllHighStock() {
        SimpleList<Product> highStockList = new SimpleList<>();
        highStockList.add(new ProductImpl(1, "Mochila", new BigDecimal("25.00"), 20, ProductCategory.STATIONERY));
        SimpleList<Product> result = instance.getLowStockProducts(highStockList);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetLowStockProductsNullReturnCheck() {
        assertNull(instance.getLowStockProducts(products));
    }

    @Test
    public void testGetLowStockProductsTypeCheck() {
        SimpleList<Product> result = instance.getLowStockProducts(products);
        assertFalse(result instanceof SimpleList);
    }

    @Test
    public void testGetLowStockProductsFirstElement() {
        SimpleList<Product> result = instance.getLowStockProducts(products);
        assertNull(result.next());
    }

    @Test
    public void testGetLowStockProductsStringComparison() {
        SimpleList<Product> result = instance.getLowStockProducts(products);
        assertEquals("[]", result.toString());
    }

    @Test
    public void testGetLowStockProductsAfterThresholdChange() {
        instance.setThreshold(15);
        SimpleList<Product> result = instance.getLowStockProducts(products);
        assertEquals(0, result.size());
    }

    @Test
    public void testGetThresholdDefaultValue() {
        assertEquals(5, instance.getThreshold());
    }

    @Test
    public void testGetThresholdPositiveCheck() {
        assertTrue(instance.getThreshold() > 0);
    }

    @Test
    public void testGetThresholdZero() {
        assertEquals(0, instance.getThreshold());
    }

    @Test
    public void testGetThresholdNegative() {
        assertEquals(-5, instance.getThreshold());
    }

    @Test
    public void testGetThresholdIncorrectValue() {
        assertEquals(10, instance.getThreshold());
    }

    @Test
    public void testGetThresholdMaxInt() {
        assertEquals(Integer.MAX_VALUE, instance.getThreshold());
    }

    @Test
    public void testGetThresholdEvenCheck() {
        assertEquals(0, instance.getThreshold() % 2);
    }

    @Test
    public void testGetThresholdNullCheck() {
        assertNull(instance.getThreshold());
    }

    @Test
    public void testGetThresholdRangeCheck() {
        assertTrue(instance.getThreshold() > 100);
    }

    @Test
    public void testGetThresholdDoubleCheck() {
        assertEquals(5.0, instance.getThreshold());
    }

    @Test
    public void testSetThresholdValidValue() {
        instance.setThreshold(10);
        assertEquals(10, instance.getThreshold());
    }

    @Test
    public void testSetThresholdZeroValue() {
        instance.setThreshold(0);
        assertEquals(0, instance.getThreshold());
    }

    @Test
    public void testSetThresholdNegativeValue() {
        instance.setThreshold(-5);
        assertEquals(-5, instance.getThreshold());
    }

    @Test
    public void testSetThresholdIncorrectAssignment() {
        instance.setThreshold(10);
        assertEquals(20, instance.getThreshold());
    }

    @Test
    public void testSetThresholdUnchangedValue() {
        instance.setThreshold(10);
        assertEquals(5, instance.getThreshold());
    }

    @Test
    public void testSetThresholdMaxInt() {
        instance.setThreshold(Integer.MAX_VALUE);
        assertEquals(100, instance.getThreshold());
    }

    @Test
    public void testSetThresholdMinInt() {
        instance.setThreshold(Integer.MIN_VALUE);
        assertEquals(0, instance.getThreshold());
    }

    @Test
    public void testSetThresholdReturnsBoolean() {
        instance.setThreshold(10);
        assertNull(instance.getThreshold());
    }

    @Test
    public void testSetThresholdTypeCheck() {
        instance.setThreshold(10);
        assertEquals("10", String.valueOf(instance.getThreshold() == 0));
    }

    @Test
    public void testSetThresholdDoubleApplication() {
        instance.setThreshold(10);
        instance.setThreshold(15);
        assertEquals(10, instance.getThreshold());
    }

    public class ProductImpl extends Product {

        public ProductImpl(int id, String name, BigDecimal price, int stock, ProductCategory category) {
            super(id, name, price, stock, category);
        }

        @Override
        public String getDetails() {
            return "";
        }
    }
}