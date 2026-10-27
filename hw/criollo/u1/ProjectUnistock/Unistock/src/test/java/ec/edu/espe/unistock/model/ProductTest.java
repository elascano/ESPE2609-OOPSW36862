package ec.edu.espe.unistock.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    private Product testProduct;

    private Product createConcreteProduct(int id, String name, BigDecimal price, int stock, ProductCategory category) {
        return new Product(id, name, price, stock, category) {
            @Override
            public String getDetails() {
                return "Detalles De Prueba";
            }
        };
    }

    @BeforeEach
    void setUp() {
        testProduct = createConcreteProduct(1, "Cuaderno Norma", new BigDecimal("2.50"), 10, ProductCategory.STATIONERY);
    }

    @Test
    void testConstructorValidParameters() {
        Product p = createConcreteProduct(101, "Lapiz FaberCastell", new BigDecimal("0.50"), 20, ProductCategory.STATIONERY);
        assertNotNull(p);
    }

    @Test
    void testConstructorNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> createConcreteProduct(1, null, new BigDecimal("1.00"), 5, ProductCategory.STATIONERY));
    }

    @Test
    void testConstructorBlankNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> createConcreteProduct(1, "   ", new BigDecimal("1.00"), 5, ProductCategory.STATIONERY));
    }

    @Test
    void testConstructorNullPriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> createConcreteProduct(1, "Cuaderno", null, 5, ProductCategory.STATIONERY));
    }

    @Test
    void testConstructorNegativePriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> createConcreteProduct(1, "Cuaderno", new BigDecimal("-0.01"), 5, ProductCategory.STATIONERY));
    }

    @Test
    void testConstructorNegativeStockThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> createConcreteProduct(1, "Cuaderno", new BigDecimal("1.00"), -1, ProductCategory.STATIONERY));
    }

    @Test
    void testConstructorZeroPriceAllowed() {
        Product p = createConcreteProduct(1, "Muestra Muestra", BigDecimal.ZERO, 5, ProductCategory.STATIONERY);
        assertEquals(BigDecimal.ZERO, p.getPrice());
    }

    @Test
    void testConstructorZeroStockAllowed() {
        Product p = createConcreteProduct(1, "Cuaderno Agotado", new BigDecimal("1.00"), 0, ProductCategory.STATIONERY);
        assertEquals(0, p.getStock());
    }

    @Test
    void testConstructorTrimsName() {
        Product p = createConcreteProduct(1, "  Buzo Deportivo  ", new BigDecimal("15.00"), 5, ProductCategory.UNIFORM);
        assertEquals("Buzo Deportivo", p.getName());
    }

    @Test
    void testConstructorAssignsCategoryCorrectly() {
        Product p = createConcreteProduct(1, "Camisa Formal", new BigDecimal("12.00"), 5, ProductCategory.UNIFORM);
        assertEquals(ProductCategory.UNIFORM, p.getCategory());
    }

    @Test
    void testHasStockTrueWhenStockIsGreater() {
        assertTrue(testProduct.hasStock(5));
    }

    @Test
    void testHasStockTrueWhenStockIsEqual() {
        assertTrue(testProduct.hasStock(10));
    }

    @Test
    void testHasStockFalseWhenStockIsLess() {
        assertFalse(testProduct.hasStock(11));
    }

    @Test
    void testHasStockTrueForZeroQuantity() {
        assertTrue(testProduct.hasStock(0));
    }

    @Test
    void testHasStockFalseForLargeQuantity() {
        assertFalse(testProduct.hasStock(1000));
    }

    @Test
    void testHasStockWhenCurrentStockIsZero() {
        Product p = createConcreteProduct(2, "Borrador Pelikan", new BigDecimal("0.25"), 0, ProductCategory.STATIONERY);
        assertFalse(p.hasStock(1));
    }

    @Test
    void testHasStockTrueForZeroQuantityWhenStockIsZero() {
        Product p = createConcreteProduct(2, "Borrador Pelikan", new BigDecimal("0.25"), 0, ProductCategory.STATIONERY);
        assertTrue(p.hasStock(0));
    }

    @Test
    void testHasStockNegativeQuantityAlwaysTrue() {
        assertTrue(testProduct.hasStock(-5));
    }

    @Test
    void testHasStockOneUnit() {
        assertTrue(testProduct.hasStock(1));
    }

    @Test
    void testHasStockExactBoundary() {
        assertTrue(testProduct.hasStock(testProduct.getStock()));
    }

    @Test
    void testDecreaseStockValidQuantity() {
        testProduct.decreaseStock(3);
        assertEquals(7, testProduct.getStock());
    }

    @Test
    void testDecreaseStockExactStock() {
        testProduct.decreaseStock(10);
        assertEquals(0, testProduct.getStock());
    }

    @Test
    void testDecreaseStockInsufficientStockThrowsException() {
        assertThrows(IllegalStateException.class, () -> testProduct.decreaseStock(11));
    }

    @Test
    void testDecreaseStockZeroQuantityDoesNotChangeStock() {
        testProduct.decreaseStock(0);
        assertEquals(10, testProduct.getStock());
    }

    @Test
    void testDecreaseStockMultipleTimes() {
        testProduct.decreaseStock(2);
        testProduct.decreaseStock(3);
        assertEquals(5, testProduct.getStock());
    }

    @Test
    void testDecreaseStockWhenStockIsZeroThrowsException() {
        Product p = createConcreteProduct(2, "Regla Artesco", new BigDecimal("0.80"), 0, ProductCategory.STATIONERY);
        assertThrows(IllegalStateException.class, () -> p.decreaseStock(1));
    }

    @Test
    void testDecreaseStockOneUnit() {
        testProduct.decreaseStock(1);
        assertEquals(9, testProduct.getStock());
    }

    @Test
    void testDecreaseStockExceptionMessageContainsProductName() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> testProduct.decreaseStock(100));
        assertTrue(exception.getMessage().contains("Cuaderno Norma"));
    }

    @Test
    void testDecreaseStockToZeroThenFail() {
        testProduct.decreaseStock(10);
        assertThrows(IllegalStateException.class, () -> testProduct.decreaseStock(1));
    }

    @Test
    void testDecreaseStockSequentialValidCalls() {
        testProduct.decreaseStock(1);
        testProduct.decreaseStock(1);
        testProduct.decreaseStock(1);
        assertEquals(7, testProduct.getStock());
    }

    @Test
    void testIncreaseStockValidQuantity() {
        testProduct.increaseStock(5);
        assertEquals(15, testProduct.getStock());
    }

    @Test
    void testIncreaseStockZeroQuantityThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> testProduct.increaseStock(0));
    }

    @Test
    void testIncreaseStockNegativeQuantityThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> testProduct.increaseStock(-5));
    }

    @Test
    void testIncreaseStockFromZeroStock() {
        Product p = createConcreteProduct(2, "Pluma Pilot", new BigDecimal("1.20"), 0, ProductCategory.STATIONERY);
        p.increaseStock(10);
        assertEquals(10, p.getStock());
    }

    @Test
    void testIncreaseStockMultipleTimes() {
        testProduct.increaseStock(2);
        testProduct.increaseStock(3);
        assertEquals(15, testProduct.getStock());
    }

    @Test
    void testIncreaseStockLargeQuantity() {
        testProduct.increaseStock(500);
        assertEquals(510, testProduct.getStock());
    }

    @Test
    void testIncreaseStockOneUnit() {
        testProduct.increaseStock(1);
        assertEquals(11, testProduct.getStock());
    }

    @Test
    void testIncreaseStockThenDecreaseStock() {
        testProduct.increaseStock(10);
        testProduct.decreaseStock(5);
        assertEquals(15, testProduct.getStock());
    }

    @Test
    void testIncreaseStockExceptionMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> testProduct.increaseStock(0));
        assertTrue(exception.getMessage().contains("mayor a cero"));
    }

    @Test
    void testIncreaseStockMaintainsProductProperties() {
        testProduct.increaseStock(5);
        assertEquals("Cuaderno Norma", testProduct.getName());
        assertEquals(new BigDecimal("2.50"), testProduct.getPrice());
    }

    @Test
    void testGetIdPositiveNumber() {
        assertEquals(1, testProduct.getId());
    }

    @Test
    void testGetIdZero() {
        Product p = createConcreteProduct(0, "Marcador BIC", new BigDecimal("0.75"), 5, ProductCategory.STATIONERY);
        assertEquals(0, p.getId());
    }

    @Test
    void testGetIdNegativeNumber() {
        Product p = createConcreteProduct(-5, "Marcador BIC", new BigDecimal("0.75"), 5, ProductCategory.STATIONERY);
        assertEquals(-5, p.getId());
    }

    @Test
    void testGetIdLargeValue() {
        Product p = createConcreteProduct(99999, "Pinturas Crayola", new BigDecimal("3.50"), 5, ProductCategory.STATIONERY);
        assertEquals(99999, p.getId());
    }

    @Test
    void testGetIdImmutable() {
        int id = testProduct.getId();
        testProduct.increaseStock(5);
        assertEquals(id, testProduct.getId());
    }

    @Test
    void testGetIdUniquePerProduct() {
        Product p2 = createConcreteProduct(2, "Cuaderno Norma", new BigDecimal("2.50"), 10, ProductCategory.STATIONERY);
        assertNotEquals(testProduct.getId(), p2.getId());
    }

    @Test
    void testGetIdConsistentCall() {
        assertEquals(testProduct.getId(), testProduct.getId());
    }

    @Test
    void testGetIdAfterStockModification() {
        testProduct.decreaseStock(2);
        assertEquals(1, testProduct.getId());
    }

    @Test
    void testGetIdStationeryCategory() {
        Product p = createConcreteProduct(45, "Cartulina Artesco", new BigDecimal("0.15"), 100, ProductCategory.STATIONERY);
        assertEquals(45, p.getId());
    }

    @Test
    void testGetIdUniformCategory() {
        Product p = createConcreteProduct(88, "Pantalon Formal", new BigDecimal("18.00"), 12, ProductCategory.UNIFORM);
        assertEquals(88, p.getId());
    }

    @Test
    void testGetNameReturnsCorrectName() {
        assertEquals("Cuaderno Norma", testProduct.getName());
    }

    @Test
    void testGetNameTrimmedOnCreation() {
        Product p = createConcreteProduct(2, "   Tijeras PaperMate   ", new BigDecimal("1.50"), 5, ProductCategory.STATIONERY);
        assertEquals("Tijeras PaperMate", p.getName());
    }

    @Test
    void testGetNameImmutable() {
        testProduct.increaseStock(2);
        assertEquals("Cuaderno Norma", testProduct.getName());
    }

    @Test
    void testGetNameSingleWord() {
        Product p = createConcreteProduct(2, "Compas", new BigDecimal("2.00"), 5, ProductCategory.STATIONERY);
        assertEquals("Compas", p.getName());
    }

    @Test
    void testGetNameMultipleWords() {
        Product p = createConcreteProduct(2, "Juego Geometrico Staedtler", new BigDecimal("4.00"), 5, ProductCategory.STATIONERY);
        assertEquals("Juego Geometrico Staedtler", p.getName());
    }

    @Test
    void testGetNameConsistentCall() {
        assertEquals(testProduct.getName(), testProduct.getName());
    }

    @Test
    void testGetNameWithNumbers() {
        Product p = createConcreteProduct(2, "Cuaderno 100 Hojas", new BigDecimal("1.80"), 5, ProductCategory.STATIONERY);
        assertEquals("Cuaderno 100 Hojas", p.getName());
    }

    @Test
    void testGetNameUniformItem() {
        Product p = createConcreteProduct(2, "Camisa Talla 32", new BigDecimal("10.00"), 5, ProductCategory.UNIFORM);
        assertEquals("Camisa Talla 32", p.getName());
    }

    @Test
    void testGetNameNotNull() {
        assertNotNull(testProduct.getName());
    }

    @Test
    void testGetNameNotEmpty() {
        assertFalse(testProduct.getName().isEmpty());
    }

    @Test
    void testGetPriceReturnsCorrectPrice() {
        assertEquals(new BigDecimal("2.50"), testProduct.getPrice());
    }

    @Test
    void testGetPriceZero() {
        Product p = createConcreteProduct(2, "Gratis", BigDecimal.ZERO, 5, ProductCategory.STATIONERY);
        assertEquals(BigDecimal.ZERO, p.getPrice());
    }

    @Test
    void testGetPriceDecimalValue() {
        Product p = createConcreteProduct(2, "Lapiz", new BigDecimal("0.35"), 5, ProductCategory.STATIONERY);
        assertEquals(new BigDecimal("0.35"), p.getPrice());
    }

    @Test
    void testGetPriceHighValue() {
        Product p = createConcreteProduct(2, "Mo chila Cintas", new BigDecimal("45.99"), 5, ProductCategory.STATIONERY);
        assertEquals(new BigDecimal("45.99"), p.getPrice());
    }

    @Test
    void testGetPriceImmutable() {
        testProduct.increaseStock(10);
        assertEquals(new BigDecimal("2.50"), testProduct.getPrice());
    }

    @Test
    void testGetPriceConsistentCall() {
        assertEquals(testProduct.getPrice(), testProduct.getPrice());
    }

    @Test
    void testGetPriceNotNull() {
        assertNotNull(testProduct.getPrice());
    }

    @Test
    void testGetPricePrecision() {
        Product p = createConcreteProduct(2, "Goma", new BigDecimal("0.123"), 5, ProductCategory.STATIONERY);
        assertEquals(new BigDecimal("0.123"), p.getPrice());
    }

    @Test
    void testGetPriceUniformCategory() {
        Product p = createConcreteProduct(2, "Buzo", new BigDecimal("25.00"), 5, ProductCategory.UNIFORM);
        assertEquals(new BigDecimal("25.00"), p.getPrice());
    }

    @Test
    void testGetPriceSignumNonNegative() {
        assertTrue(testProduct.getPrice().signum() >= 0);
    }

    @Test
    void testGetStockInitialValue() {
        assertEquals(10, testProduct.getStock());
    }

    @Test
    void testGetStockAfterIncrease() {
        testProduct.increaseStock(5);
        assertEquals(15, testProduct.getStock());
    }

    @Test
    void testGetStockAfterDecrease() {
        testProduct.decreaseStock(4);
        assertEquals(6, testProduct.getStock());
    }

    @Test
    void testGetStockZero() {
        Product p = createConcreteProduct(2, "Agotado", new BigDecimal("1.00"), 0, ProductCategory.STATIONERY);
        assertEquals(0, p.getStock());
    }

    @Test
    void testGetStockNeverNegative() {
        assertTrue(testProduct.getStock() >= 0);
    }

    @Test
    void testGetStockConsistentCall() {
        assertEquals(testProduct.getStock(), testProduct.getStock());
    }

    @Test
    void testGetStockLargeAmount() {
        Product p = createConcreteProduct(2, "Grapas", new BigDecimal("1.00"), 5000, ProductCategory.STATIONERY);
        assertEquals(5000, p.getStock());
    }

    @Test
    void testGetStockAfterMultipleModifications() {
        testProduct.increaseStock(10);
        testProduct.decreaseStock(5);
        testProduct.increaseStock(2);
        assertEquals(17, testProduct.getStock());
    }

    @Test
    void testGetStockReflectsCurrentState() {
        int initial = testProduct.getStock();
        testProduct.increaseStock(1);
        assertNotEquals(initial, testProduct.getStock());
    }

    @Test
    void testGetStockOne() {
        Product p = createConcreteProduct(2, "Ultimo", new BigDecimal("1.00"), 1, ProductCategory.STATIONERY);
        assertEquals(1, p.getStock());
    }

    @Test
    void testGetCategoryStationery() {
        assertEquals(ProductCategory.STATIONERY, testProduct.getCategory());
    }

    @Test
    void testGetCategoryUniform() {
        Product p = createConcreteProduct(2, "Falda", new BigDecimal("15.00"), 5, ProductCategory.UNIFORM);
        assertEquals(ProductCategory.UNIFORM, p.getCategory());
    }

    @Test
    void testGetCategoryNotNull() {
        assertNotNull(testProduct.getCategory());
    }

    @Test
    void testGetCategoryImmutable() {
        testProduct.increaseStock(10);
        assertEquals(ProductCategory.STATIONERY, testProduct.getCategory());
    }

    @Test
    void testGetCategoryConsistentCall() {
        assertEquals(testProduct.getCategory(), testProduct.getCategory());
    }

    @Test
    void testGetCategoryEnumValue() {
        Product p = createConcreteProduct(2, "SacaPuntas", new BigDecimal("0.20"), 5, ProductCategory.STATIONERY);
        assertSame(ProductCategory.STATIONERY, p.getCategory());
    }

    @Test
    void testGetCategoryComparison() {
        Product p1 = createConcreteProduct(1, "Prod1", new BigDecimal("1.00"), 5, ProductCategory.STATIONERY);
        Product p2 = createConcreteProduct(2, "Prod2", new BigDecimal("1.00"), 5, ProductCategory.UNIFORM);
        assertNotEquals(p1.getCategory(), p2.getCategory());
    }

    @Test
    void testGetCategoryStationeryLabel() {
        assertEquals(ProductCategory.STATIONERY, testProduct.getCategory());
    }

    @Test
    void testGetCategoryAfterStockDecrease() {
        testProduct.decreaseStock(1);
        assertEquals(ProductCategory.STATIONERY, testProduct.getCategory());
    }

    @Test
    void testGetCategoryMultipleProducts() {
        Product p1 = createConcreteProduct(1, "A", new BigDecimal("1.00"), 1, ProductCategory.STATIONERY);
        Product p2 = createConcreteProduct(2, "B", new BigDecimal("1.00"), 1, ProductCategory.STATIONERY);
        assertEquals(p1.getCategory(), p2.getCategory());
    }

    @Test
    void testEqualsSameObjectReturnsTrue() {
        assertTrue(testProduct.equals(testProduct));
    }

    @Test
    void testEqualsSameIdReturnsTrue() {
        Product p2 = createConcreteProduct(1, "Cuaderno Distinto", new BigDecimal("3.00"), 20, ProductCategory.STATIONERY);
        assertTrue(testProduct.equals(p2));
    }

    @Test
    void testEqualsDifferentIdReturnsFalse() {
        Product p2 = createConcreteProduct(2, "Cuaderno Norma", new BigDecimal("2.50"), 10, ProductCategory.STATIONERY);
        assertFalse(testProduct.equals(p2));
    }

    @Test
    void testEqualsNullReturnsFalse() {
        assertFalse(testProduct.equals(null));
    }

    @Test
    void testEqualsDifferentClassReturnsFalse() {
        assertFalse(testProduct.equals("Cuaderno Norma"));
    }

    @Test
    void testEqualsSymmetric() {
        Product p2 = createConcreteProduct(1, "Otro", new BigDecimal("1.00"), 5, ProductCategory.UNIFORM);
        assertTrue(testProduct.equals(p2) && p2.equals(testProduct));
    }

    @Test
    void testEqualsTransitive() {
        Product p1 = createConcreteProduct(1, "A", new BigDecimal("1.00"), 5, ProductCategory.STATIONERY);
        Product p2 = createConcreteProduct(1, "B", new BigDecimal("2.00"), 5, ProductCategory.UNIFORM);
        Product p3 = createConcreteProduct(1, "C", new BigDecimal("3.00"), 5, ProductCategory.STATIONERY);
        assertTrue(p1.equals(p2) && p2.equals(p3) && p1.equals(p3));
    }

    @Test
    void testEqualsConsistent() {
        Product p2 = createConcreteProduct(1, "A", new BigDecimal("1.00"), 5, ProductCategory.STATIONERY);
        assertTrue(testProduct.equals(p2));
        assertTrue(testProduct.equals(p2));
    }

    @Test
    void testEqualsDifferentCategorySameIdReturnsTrue() {
        Product p2 = createConcreteProduct(1, "Camisa", new BigDecimal("10.00"), 5, ProductCategory.UNIFORM);
        assertTrue(testProduct.equals(p2));
    }

    @Test
    void testEqualsZeroIdsReturnsTrue() {
        Product p1 = createConcreteProduct(0, "P1", new BigDecimal("1.00"), 1, ProductCategory.STATIONERY);
        Product p2 = createConcreteProduct(0, "P2", new BigDecimal("2.00"), 2, ProductCategory.UNIFORM);
        assertTrue(p1.equals(p2));
    }

    @Test
    void testHashCodeSameForEqualObjects() {
        Product p2 = createConcreteProduct(1, "Otro Nombre", new BigDecimal("5.00"), 2, ProductCategory.UNIFORM);
        assertEquals(testProduct.hashCode(), p2.hashCode());
    }

    @Test
    void testHashCodeConsistent() {
        int initialHashCode = testProduct.hashCode();
        assertEquals(initialHashCode, testProduct.hashCode());
    }

    @Test
    void testHashCodeDifferentForDifferentIds() {
        Product p2 = createConcreteProduct(2, "Cuaderno Norma", new BigDecimal("2.50"), 10, ProductCategory.STATIONERY);
        assertNotEquals(testProduct.hashCode(), p2.hashCode());
    }

    @Test
    void testHashCodeEqualsIntegerHashCode() {
        assertEquals(Integer.hashCode(1), testProduct.hashCode());
    }

    @Test
    void testHashCodeZeroId() {
        Product p = createConcreteProduct(0, "Test", new BigDecimal("1.00"), 1, ProductCategory.STATIONERY);
        assertEquals(Integer.hashCode(0), p.hashCode());
    }

    @Test
    void testHashCodeNegativeId() {
        Product p = createConcreteProduct(-10, "Test", new BigDecimal("1.00"), 1, ProductCategory.STATIONERY);
        assertEquals(Integer.hashCode(-10), p.hashCode());
    }

    @Test
    void testHashCodeUnchangedAfterStockChange() {
        int hashBefore = testProduct.hashCode();
        testProduct.increaseStock(10);
        assertEquals(hashBefore, testProduct.hashCode());
    }

    @Test
    void testHashCodeMultipleInvocations() {
        for (int i = 0; i < 5; i++) {
            assertEquals(Integer.hashCode(1), testProduct.hashCode());
        }
    }

    @Test
    void testHashCodeLargeId() {
        Product p = createConcreteProduct(88888, "Test", new BigDecimal("1.00"), 1, ProductCategory.STATIONERY);
        assertEquals(Integer.hashCode(88888), p.hashCode());
    }

    @Test
    void testHashCodeDifferentCategoriesSameId() {
        Product p2 = createConcreteProduct(1, "Falda", new BigDecimal("12.00"), 3, ProductCategory.UNIFORM);
        assertEquals(testProduct.hashCode(), p2.hashCode());
    }

    @Test
    void testToStringContainsId() {
        assertTrue(testProduct.toString().contains("[1]"));
    }

    @Test
    void testToStringContainsName() {
        assertTrue(testProduct.toString().contains("Cuaderno Norma"));
    }

    @Test
    void testToStringContainsStock() {
        assertTrue(testProduct.toString().contains("Stock: 10"));
    }

    @Test
    void testToStringContainsPrice() {
        assertTrue(testProduct.toString().contains("2.50"));
    }

    @Test
    void testToStringContainsDetails() {
        assertTrue(testProduct.toString().contains("Detalles De Prueba"));
    }

    @Test
    void testToStringNotNull() {
        assertNotNull(testProduct.toString());
    }

    @Test
    void testToStringNotEmpty() {
        assertFalse(testProduct.toString().isEmpty());
    }

    @Test
    void testToStringUpdatesWhenStockChanges() {
        testProduct.increaseStock(5);
        assertTrue(testProduct.toString().contains("Stock: 15"));
    }

    @Test
    void testToStringUniformProduct() {
        Product p = createConcreteProduct(5, "Camisa", new BigDecimal("10.00"), 4, ProductCategory.UNIFORM);
        assertTrue(p.toString().contains("Camisa"));
    }

    @Test
    void testToStringFormatConsistency() {
        String str1 = testProduct.toString();
        String str2 = testProduct.toString();
        assertEquals(str1, str2);
    }
}