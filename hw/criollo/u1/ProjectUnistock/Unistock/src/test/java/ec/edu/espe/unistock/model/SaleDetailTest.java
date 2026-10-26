package ec.edu.espe.unistock.model;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SaleDetailTest {

    private Product product;
    private SaleDetail instance;

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
        product = new ProductImpl(1, "Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, ProductCategory.STATIONERY);
        instance = new SaleDetail(product, 4);
    }

    @AfterEach
    public void tearDown() {
        product = null;
        instance = null;
    }

    @Test
    public void testSubtotalValidQuantity() {
        assertEquals(new BigDecimal("10.00"), instance.subtotal());
    }

    @Test
    public void testSubtotalSingleQuantity() {
        SaleDetail detail = new SaleDetail(product, 1);
        assertEquals(new BigDecimal("2.50"), detail.subtotal());
    }

    @Test
    public void testSubtotalZeroValue() {
        assertEquals(BigDecimal.ZERO, instance.subtotal());
    }

    @Test
    public void testSubtotalIncorrectCalculation() {
        assertEquals(new BigDecimal("12.00"), instance.subtotal());
    }

    @Test
    public void testSubtotalNegativeValue() {
        assertEquals(new BigDecimal("-10.00"), instance.subtotal());
    }

    @Test
    public void testSubtotalScaleMismatch() {
        assertEquals(new BigDecimal("10"), instance.subtotal());
    }

    @Test
    public void testSubtotalNullCheck() {
        assertNull(instance.subtotal());
    }

    @Test
    public void testSubtotalGreaterThanLimit() {
        assertTrue(instance.subtotal().compareTo(new BigDecimal("100.00")) > 0);
    }

    @Test
    public void testSubtotalExactDouble() {
        assertEquals(10.00, instance.subtotal());
    }

    @Test
    public void testSubtotalOneDollar() {
        assertEquals(BigDecimal.ONE, instance.subtotal());
    }

    @Test
    public void testGetProductValidProduct() {
        assertEquals(product, instance.getProduct());
    }

    @Test
    public void testGetProductNotNull() {
        assertNotNull(instance.getProduct());
    }

    @Test
    public void testGetProductDifferentInstance() {
        Product anotherProduct = new ProductImpl(2, "Mochila", new BigDecimal("15.00"), 5, ProductCategory.STATIONERY);
        assertEquals(anotherProduct, instance.getProduct());
    }

    @Test
    public void testGetProductNull() {
        assertNull(instance.getProduct());
    }

    @Test
    public void testGetProductNameMatch() {
        assertEquals("Mochila Escolar", instance.getProduct().getName());
    }

    @Test
    public void testGetProductIdMatch() {
        assertEquals(999, instance.getProduct().getId());
    }

    @Test
    public void testGetProductCategoryMatch() {
        assertEquals(ProductCategory.UNIFORM, instance.getProduct().getCategory());
    }

    @Test
    public void testGetProductPriceMatch() {
        assertEquals(new BigDecimal("99.99"), instance.getProduct().getPrice());
    }

    @Test
    public void testGetProductTypeCheck() {
        assertFalse(instance.getProduct() instanceof Product);
    }

    @Test
    public void testGetProductStringComparison() {
        assertEquals("Cuaderno 100 Hojas", instance.getProduct());
    }

    @Test
    public void testGetQuantityValidQuantity() {
        assertEquals(4, instance.getQuantity());
    }

    @Test
    public void testGetQuantityPositiveCheck() {
        assertTrue(instance.getQuantity() > 0);
    }

    @Test
    public void testGetQuantityIncorrectValue() {
        assertEquals(10, instance.getQuantity());
    }

    @Test
    public void testGetQuantityZero() {
        assertEquals(0, instance.getQuantity());
    }

    @Test
    public void testGetQuantityNegative() {
        assertEquals(-4, instance.getQuantity());
    }

    @Test
    public void testGetQuantityMaxInt() {
        assertEquals(Integer.MAX_VALUE, instance.getQuantity());
    }

    @Test
    public void testGetQuantityOddCheck() {
        assertEquals(1, instance.getQuantity() % 2);
    }

    @Test
    public void testGetQuantityGreaterThanHundred() {
        assertTrue(instance.getQuantity() > 100);
    }

    @Test
    public void testGetQuantityNullCheck() {
        assertNull(instance.getQuantity());
    }

    @Test
    public void testGetQuantityDoubleCheck() {
        assertEquals(4.0, instance.getQuantity());
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