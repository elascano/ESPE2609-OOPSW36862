package ec.edu.espe.unistock.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class SaleDetailTest {

    private Product testProduct;
    private SaleDetail testSaleDetail;

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
        testProduct = createConcreteProduct(1, "Cuaderno Norma", new BigDecimal("2.50"), 50);
        testSaleDetail = new SaleDetail(testProduct, 4);
    }

    @Test
    void testConstructorValidParameters() {
        Product p = createConcreteProduct(2, "Lapiz FaberCastell", new BigDecimal("0.50"), 20);
        SaleDetail detail = new SaleDetail(p, 5);
        assertNotNull(detail);
    }

    @Test
    void testConstructorZeroQuantityThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new SaleDetail(testProduct, 0));
    }

    @Test
    void testConstructorNegativeQuantityThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new SaleDetail(testProduct, -1));
    }

    @Test
    void testConstructorAllowsNullProduct() {
        SaleDetail detail = new SaleDetail(null, 2);
        assertNull(detail.getProduct());
    }

    @Test
    void testConstructorQuantityOneAllowed() {
        SaleDetail detail = new SaleDetail(testProduct, 1);
        assertEquals(1, detail.getQuantity());
    }

    @Test
    void testConstructorLargeQuantityAllowed() {
        SaleDetail detail = new SaleDetail(testProduct, 1000);
        assertEquals(1000, detail.getQuantity());
    }

    @Test
    void testConstructorAssignsProductCorrectly() {
        assertEquals(testProduct, testSaleDetail.getProduct());
    }

    @Test
    void testConstructorAssignsQuantityCorrectly() {
        assertEquals(4, testSaleDetail.getQuantity());
    }

    @Test
    void testConstructorExceptionMessageContainsZeroMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SaleDetail(testProduct, 0));
        assertTrue(exception.getMessage().contains("mayor a cero"));
    }

    @Test
    void testConstructorUniformProduct() {
        Product uniform = new Uniform(10, "Camisa Formal", new BigDecimal("12.00"), 10, "M", "San Gabriel");
        SaleDetail detail = new SaleDetail(uniform, 2);
        assertEquals(2, detail.getQuantity());
    }

    @Test
    void testSubtotalStandardCalculation() {
        assertEquals(new BigDecimal("10.00"), testSaleDetail.subtotal());
    }

    @Test
    void testSubtotalQuantityOne() {
        SaleDetail detail = new SaleDetail(testProduct, 1);
        assertEquals(new BigDecimal("2.50"), detail.subtotal());
    }

    @Test
    void testSubtotalZeroProductPrice() {
        Product freeProduct = createConcreteProduct(2, "Muestra Pelikan", BigDecimal.ZERO, 10);
        SaleDetail detail = new SaleDetail(freeProduct, 5);
        assertEquals(new BigDecimal("0.00"), detail.subtotal());
    }

    @Test
    void testSubtotalDecimalPrice() {
        Product p = createConcreteProduct(3, "Borrador Artesco", new BigDecimal("0.35"), 100);
        SaleDetail detail = new SaleDetail(p, 3);
        assertEquals(new BigDecimal("1.05"), detail.subtotal());
    }

    @Test
    void testSubtotalLargeQuantity() {
        Product p = createConcreteProduct(4, "Esfero BIC", new BigDecimal("0.25"), 1000);
        SaleDetail detail = new SaleDetail(p, 200);
        assertEquals(new BigDecimal("50.00"), detail.subtotal());
    }

    @Test
    void testSubtotalConsistentCall() {
        assertEquals(testSaleDetail.subtotal(), testSaleDetail.subtotal());
    }

    @Test
    void testSubtotalNotNull() {
        assertNotNull(testSaleDetail.subtotal());
    }

    @Test
    void testSubtotalHighPriceProduct() {
        Product p = createConcreteProduct(5, "Uniforme Completo", new BigDecimal("45.99"), 5);
        SaleDetail detail = new SaleDetail(p, 2);
        assertEquals(new BigDecimal("91.98"), detail.subtotal());
    }

    @Test
    void testSubtotalThrowsNullPointerExceptionIfProductIsNull() {
        SaleDetail detail = new SaleDetail(null, 5);
        assertThrows(NullPointerException.class, () -> detail.subtotal());
    }

    @Test
    void testSubtotalUniformItem() {
        Product uniform = new Uniform(11, "Buzo Deportivo", new BigDecimal("20.00"), 5, "L", "Mejia");
        SaleDetail detail = new SaleDetail(uniform, 3);
        assertEquals(new BigDecimal("60.00"), detail.subtotal());
    }

    @Test
    void testGetProductReturnsCorrectProduct() {
        assertEquals(testProduct, testSaleDetail.getProduct());
    }

    @Test
    void testGetProductNullIfNullInjected() {
        SaleDetail detail = new SaleDetail(null, 1);
        assertNull(detail.getProduct());
    }

    @Test
    void testGetProductSameReference() {
        assertSame(testProduct, testSaleDetail.getProduct());
    }

    @Test
    void testGetProductImmutable() {
        Product p = testSaleDetail.getProduct();
        assertEquals("Cuaderno Norma", p.getName());
    }

    @Test
    void testGetProductConsistentCall() {
        assertEquals(testSaleDetail.getProduct(), testSaleDetail.getProduct());
    }

    @Test
    void testGetProductStationeryType() {
        Stationery s = new Stationery(20, "Compas Staedtler", new BigDecimal("3.50"), 10, "Staedtler", "Dibujo");
        SaleDetail detail = new SaleDetail(s, 1);
        assertEquals("Compas Staedtler", detail.getProduct().getName());
    }

    @Test
    void testGetProductUniformType() {
        Uniform u = new Uniform(30, "Pantalón Benalcazar", new BigDecimal("15.00"), 10, "30", "Benalcazar");
        SaleDetail detail = new SaleDetail(u, 2);
        assertEquals("Pantalón Benalcazar", detail.getProduct().getName());
    }

    @Test
    void testGetProductNotNull() {
        assertNotNull(testSaleDetail.getProduct());
    }

    @Test
    void testGetProductIdMatch() {
        assertEquals(1, testSaleDetail.getProduct().getId());
    }

    @Test
    void testGetProductPriceMatch() {
        assertEquals(new BigDecimal("2.50"), testSaleDetail.getProduct().getPrice());
    }

    @Test
    void testGetQuantityReturnsCorrectValue() {
        assertEquals(4, testSaleDetail.getQuantity());
    }

    @Test
    void testGetQuantityOne() {
        SaleDetail detail = new SaleDetail(testProduct, 1);
        assertEquals(1, detail.getQuantity());
    }

    @Test
    void testGetQuantityPositiveValue() {
        assertTrue(testSaleDetail.getQuantity() > 0);
    }

    @Test
    void testGetQuantityImmutable() {
        int q = testSaleDetail.getQuantity();
        assertEquals(4, q);
    }

    @Test
    void testGetQuantityConsistentCall() {
        assertEquals(testSaleDetail.getQuantity(), testSaleDetail.getQuantity());
    }

    @Test
    void testGetQuantityLargeNumber() {
        SaleDetail detail = new SaleDetail(testProduct, 500);
        assertEquals(500, detail.getQuantity());
    }

    @Test
    void testGetQuantitySmallNumber() {
        SaleDetail detail = new SaleDetail(testProduct, 2);
        assertEquals(2, detail.getQuantity());
    }

    @Test
    void testGetQuantityNotZero() {
        assertNotEquals(0, testSaleDetail.getQuantity());
    }

    @Test
    void testGetQuantityNotNegative() {
        assertTrue(testSaleDetail.getQuantity() >= 1);
    }

    @Test
    void testGetQuantityStationeryItem() {
        Product p = createConcreteProduct(2, "Caja Colores PaperMate", new BigDecimal("3.00"), 10);
        SaleDetail detail = new SaleDetail(p, 10);
        assertEquals(10, detail.getQuantity());
    }
}