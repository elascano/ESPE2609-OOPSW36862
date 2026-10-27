package ec.edu.espe.unistock.service;

import ec.edu.espe.unistock.model.Product;
import ec.edu.espe.unistock.model.ProductCategory;
import ec.edu.espe.unistock.model.Stationery;
import ec.edu.espe.unistock.model.Uniform;
import ec.edu.espe.unistock.structure.SimpleList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class StockAlertServiceTest {

    private StockAlertService stockAlertService;

    private Product createConcreteProduct(int id, String name, BigDecimal price, int stock) {
        return new Product(id, name, price, stock, ProductCategory.STATIONERY) {
            @Override
            public String getDetails() {
                return "Detalles Prueba";
            }
        };
    }

    @BeforeEach
    void setUp() {
        stockAlertService = new StockAlertService();
    }

    @Test
    void testDefaultConstructorSetsThresholdToFive() {
        assertEquals(5, stockAlertService.getThreshold());
    }

    @Test
    void testDefaultConstructorNotNullInstance() {
        assertNotNull(stockAlertService);
    }

    @Test
    void testDefaultConstructorThresholdIsPositive() {
        assertTrue(stockAlertService.getThreshold() > 0);
    }

    @Test
    void testDefaultConstructorThresholdIsInteger() {
        int threshold = stockAlertService.getThreshold();
        assertEquals(5, threshold);
    }

    @Test
    void testDefaultConstructorConsistentThreshold() {
        assertEquals(stockAlertService.getThreshold(), stockAlertService.getThreshold());
    }

    @Test
    void testDefaultConstructorInitialStateReady() {
        SimpleList<Product> emptyList = new SimpleList<>();
        assertTrue(stockAlertService.getLowStockProducts(emptyList).isEmpty());
    }

    @Test
    void testDefaultConstructorMultipleInstancesHaveSameDefaultThreshold() {
        StockAlertService service1 = new StockAlertService();
        StockAlertService service2 = new StockAlertService();
        assertEquals(service1.getThreshold(), service2.getThreshold());
    }

    @Test
    void testDefaultConstructorCanBeModifiedLater() {
        stockAlertService.setThreshold(10);
        assertEquals(10, stockAlertService.getThreshold());
    }

    @Test
    void testDefaultConstructorFilterBehaviorWithDefaultThreshold() {
        SimpleList<Product> products = new SimpleList<>();
        products.add(createConcreteProduct(1, "Cuaderno Norma", new BigDecimal("2.50"), 5));
        products.add(createConcreteProduct(2, "Lapiz FaberCastell", new BigDecimal("0.50"), 6));
        assertEquals(1, stockAlertService.getLowStockProducts(products).size());
    }

    @Test
    void testDefaultConstructorSameClassType() {
        assertTrue(stockAlertService instanceof StockAlertService);
    }

    @Test
    void testCustomConstructorSetsCustomThreshold() {
        StockAlertService customService = new StockAlertService(10);
        assertEquals(10, customService.getThreshold());
    }

    @Test
    void testCustomConstructorZeroThresholdAllowed() {
        StockAlertService customService = new StockAlertService(0);
        assertEquals(0, customService.getThreshold());
    }

    @Test
    void testCustomConstructorNegativeThresholdAllowed() {
        StockAlertService customService = new StockAlertService(-5);
        assertEquals(-5, customService.getThreshold());
    }

    @Test
    void testCustomConstructorHighThresholdAllowed() {
        StockAlertService customService = new StockAlertService(100);
        assertEquals(100, customService.getThreshold());
    }

    @Test
    void testCustomConstructorNotNullInstance() {
        StockAlertService customService = new StockAlertService(3);
        assertNotNull(customService);
    }

    @Test
    void testCustomConstructorFilterBehavior() {
        StockAlertService customService = new StockAlertService(2);
        SimpleList<Product> products = new SimpleList<>();
        products.add(createConcreteProduct(1, "Cuaderno Norma", new BigDecimal("2.50"), 2));
        products.add(createConcreteProduct(2, "Lapiz FaberCastell", new BigDecimal("0.50"), 3));
        assertEquals(1, customService.getLowStockProducts(products).size());
    }

    @Test
    void testCustomConstructorDifferentFromDefault() {
        StockAlertService customService = new StockAlertService(8);
        assertNotEquals(stockAlertService.getThreshold(), customService.getThreshold());
    }

    @Test
    void testCustomConstructorConsistentThreshold() {
        StockAlertService customService = new StockAlertService(15);
        assertEquals(customService.getThreshold(), customService.getThreshold());
    }

    @Test
    void testCustomConstructorOneThreshold() {
        StockAlertService customService = new StockAlertService(1);
        assertEquals(1, customService.getThreshold());
    }

    @Test
    void testCustomConstructorSameClassType() {
        StockAlertService customService = new StockAlertService(7);
        assertTrue(customService instanceof StockAlertService);
    }

    @Test
    void testGetLowStockProductsReturnsProductsBelowOrEqualThreshold() {
        SimpleList<Product> products = new SimpleList<>();
        products.add(createConcreteProduct(1, "Cuaderno Norma", new BigDecimal("2.50"), 3));
        products.add(createConcreteProduct(2, "Lapiz FaberCastell", new BigDecimal("0.50"), 10));

        SimpleList<Product> lowStock = stockAlertService.getLowStockProducts(products);

        assertEquals(1, lowStock.size());
        lowStock.resetIteration();
        assertEquals("Cuaderno Norma", lowStock.next().getName());
    }

    @Test
    void testGetLowStockProductsIncludesExactThreshold() {
        SimpleList<Product> products = new SimpleList<>();
        products.add(createConcreteProduct(1, "Cuaderno Norma", new BigDecimal("2.50"), 5));

        SimpleList<Product> lowStock = stockAlertService.getLowStockProducts(products);

        assertEquals(1, lowStock.size());
    }

    @Test
    void testGetLowStockProductsExcludesAboveThreshold() {
        SimpleList<Product> products = new SimpleList<>();
        products.add(createConcreteProduct(1, "Cuaderno Norma", new BigDecimal("2.50"), 6));

        SimpleList<Product> lowStock = stockAlertService.getLowStockProducts(products);

        assertTrue(lowStock.isEmpty());
    }

    @Test
    void testGetLowStockProductsNullListReturnsEmptyList() {
        SimpleList<Product> lowStock = stockAlertService.getLowStockProducts(null);

        assertNotNull(lowStock);
        assertTrue(lowStock.isEmpty());
    }

    @Test
    void testGetLowStockProductsEmptyListReturnsEmptyList() {
        SimpleList<Product> lowStock = stockAlertService.getLowStockProducts(new SimpleList<>());

        assertNotNull(lowStock);
        assertTrue(lowStock.isEmpty());
    }

    @Test
    void testGetLowStockProductsZeroStockIncluded() {
        SimpleList<Product> products = new SimpleList<>();
        products.add(createConcreteProduct(1, "Borrador Pelikan", new BigDecimal("0.25"), 0));

        SimpleList<Product> lowStock = stockAlertService.getLowStockProducts(products);

        assertEquals(1, lowStock.size());
    }

    @Test
    void testGetLowStockProductsMixedProductTypes() {
        SimpleList<Product> products = new SimpleList<>();
        products.add(new Stationery(1, "Regla Artesco", new BigDecimal("1.00"), 2, "Artesco", "Regla"));
        products.add(new Uniform(2, "Camisa Formal", new BigDecimal("12.00"), 1, "M", "San Gabriel"));
        products.add(new Stationery(3, "Esfero BIC", new BigDecimal("0.40"), 20, "BIC", "Esfero"));

        SimpleList<Product> lowStock = stockAlertService.getLowStockProducts(products);

        assertEquals(2, lowStock.size());
    }

    @Test
    void testGetLowStockProductsPreservesOrder() {
        Product p1 = createConcreteProduct(1, "Producto A", new BigDecimal("1.00"), 2);
        Product p2 = createConcreteProduct(2, "Producto B", new BigDecimal("1.00"), 1);
        SimpleList<Product> products = new SimpleList<>();
        products.add(p1);
        products.add(p2);

        SimpleList<Product> lowStock = stockAlertService.getLowStockProducts(products);

        lowStock.resetIteration();
        assertEquals(p1, lowStock.next());
        assertEquals(p2, lowStock.next());
    }

    @Test
    void testGetLowStockProductsReturnsNewSimpleListInstance() {
        SimpleList<Product> products = new SimpleList<>();
        products.add(createConcreteProduct(1, "Cuaderno Norma", new BigDecimal("2.50"), 3));

        SimpleList<Product> lowStock = stockAlertService.getLowStockProducts(products);

        assertNotSame(products, lowStock);
    }

    @Test
    void testGetLowStockProductsAllProductsLowStock() {
        SimpleList<Product> products = new SimpleList<>();
        products.add(createConcreteProduct(1, "P1", new BigDecimal("1.00"), 1));
        products.add(createConcreteProduct(2, "P2", new BigDecimal("1.00"), 2));
        products.add(createConcreteProduct(3, "P3", new BigDecimal("1.00"), 3));

        SimpleList<Product> lowStock = stockAlertService.getLowStockProducts(products);

        assertEquals(3, lowStock.size());
    }

    @Test
    void testGetThresholdReturnsDefault() {
        assertEquals(5, stockAlertService.getThreshold());
    }

    @Test
    void testGetThresholdReturnsModifiedValue() {
        stockAlertService.setThreshold(8);
        assertEquals(8, stockAlertService.getThreshold());
    }

    @Test
    void testGetThresholdNotNull() {
        Integer threshold = stockAlertService.getThreshold();
        assertNotNull(threshold);
    }

    @Test
    void testGetThresholdConsistentCall() {
        assertEquals(stockAlertService.getThreshold(), stockAlertService.getThreshold());
    }

    @Test
    void testGetThresholdCustomConstructorValue() {
        StockAlertService customService = new StockAlertService(12);
        assertEquals(12, customService.getThreshold());
    }

    @Test
    void testGetThresholdNegativeValue() {
        StockAlertService customService = new StockAlertService(-1);
        assertEquals(-1, customService.getThreshold());
    }

    @Test
    void testGetThresholdZeroValue() {
        StockAlertService customService = new StockAlertService(0);
        assertEquals(0, customService.getThreshold());
    }

    @Test
    void testGetThresholdImmutableWithoutSetter() {
        int initial = stockAlertService.getThreshold();
        SimpleList<Product> products = new SimpleList<>();
        stockAlertService.getLowStockProducts(products);
        assertEquals(initial, stockAlertService.getThreshold());
    }

    @Test
    void testGetThresholdPrimitiveIntType() {
        int threshold = stockAlertService.getThreshold();
        assertTrue(threshold == 5);
    }

    @Test
    void testGetThresholdAfterMultipleSetters() {
        stockAlertService.setThreshold(3);
        stockAlertService.setThreshold(7);
        assertEquals(7, stockAlertService.getThreshold());
    }

    @Test
    void testSetThresholdValidPositiveValue() {
        stockAlertService.setThreshold(10);
        assertEquals(10, stockAlertService.getThreshold());
    }

    @Test
    void testSetThresholdZeroValue() {
        stockAlertService.setThreshold(0);
        assertEquals(0, stockAlertService.getThreshold());
    }

    @Test
    void testSetThresholdNegativeValue() {
        stockAlertService.setThreshold(-3);
        assertEquals(-3, stockAlertService.getThreshold());
    }

    @Test
    void testSetThresholdChangesFilterBehavior() {
        SimpleList<Product> products = new SimpleList<>();
        products.add(createConcreteProduct(1, "Cuaderno Norma", new BigDecimal("2.50"), 8));

        stockAlertService.setThreshold(10);
        SimpleList<Product> lowStock = stockAlertService.getLowStockProducts(products);

        assertEquals(1, lowStock.size());
    }

    @Test
    void testSetThresholdRestrictsFilterBehavior() {
        SimpleList<Product> products = new SimpleList<>();
        products.add(createConcreteProduct(1, "Cuaderno Norma", new BigDecimal("2.50"), 4));

        stockAlertService.setThreshold(2);
        SimpleList<Product> lowStock = stockAlertService.getLowStockProducts(products);

        assertTrue(lowStock.isEmpty());
    }

    @Test
    void testSetThresholdLargeValue() {
        stockAlertService.setThreshold(1000);
        assertEquals(1000, stockAlertService.getThreshold());
    }

    @Test
    void testSetThresholdSameValue() {
        stockAlertService.setThreshold(5);
        assertEquals(5, stockAlertService.getThreshold());
    }

    @Test
    void testSetThresholdMultipleSequentialCalls() {
        stockAlertService.setThreshold(1);
        stockAlertService.setThreshold(2);
        stockAlertService.setThreshold(3);
        assertEquals(3, stockAlertService.getThreshold());
    }

    @Test
    void testSetThresholdMaintainsInstanceIntegrity() {
        stockAlertService.setThreshold(15);
        assertNotNull(stockAlertService);
    }

    @Test
    void testSetThresholdAffectsSubsequentInvocationsOnly() {
        SimpleList<Product> products = new SimpleList<>();
        products.add(createConcreteProduct(1, "Cuaderno Norma", new BigDecimal("2.50"), 4));

        assertEquals(1, stockAlertService.getLowStockProducts(products).size());
        stockAlertService.setThreshold(2);
        assertTrue(stockAlertService.getLowStockProducts(products).isEmpty());
    }
}