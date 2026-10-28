package ec.edu.espe.unistock.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCategoryTest {

    @Test
    void testGetLabelUniform() {
        assertEquals("Uniforme", ProductCategory.UNIFORM.getLabel());
    }

    @Test
    void testGetLabelStationery() {
        assertEquals("Papelería", ProductCategory.STATIONERY.getLabel());
    }

    @Test
    void testGetLabelNotNullUniform() {
        assertNotNull(ProductCategory.UNIFORM.getLabel());
    }

    @Test
    void testGetLabelNotNullStationery() {
        assertNotNull(ProductCategory.STATIONERY.getLabel());
    }

    @Test
    void testGetLabelNotEmptyUniform() {
        assertFalse(ProductCategory.UNIFORM.getLabel().isEmpty());
    }

    @Test
    void testGetLabelNotEmptyStationery() {
        assertFalse(ProductCategory.STATIONERY.getLabel().isEmpty());
    }

    @Test
    void testGetLabelConsistencyUniform() {
        String label1 = ProductCategory.UNIFORM.getLabel();
        String label2 = ProductCategory.UNIFORM.getLabel();
        assertEquals(label1, label2);
    }

    @Test
    void testGetLabelConsistencyStationery() {
        String label1 = ProductCategory.STATIONERY.getLabel();
        String label2 = ProductCategory.STATIONERY.getLabel();
        assertEquals(label1, label2);
    }

    @Test
    void testGetLabelDifferentBetweenCategories() {
        assertNotEquals(ProductCategory.UNIFORM.getLabel(), ProductCategory.STATIONERY.getLabel());
    }

    @Test
    void testGetLabelReturnsExactString() {
        assertSame("Uniforme", ProductCategory.UNIFORM.getLabel());
        assertSame("Papelería", ProductCategory.STATIONERY.getLabel());
    }

    @Test
    void testValuesLength() {
        assertEquals(2, ProductCategory.values().length);
    }

    @Test
    void testValuesContainsUniform() {
        ProductCategory[] categories = ProductCategory.values();
        assertEquals(ProductCategory.UNIFORM, categories[0]);
    }

    @Test
    void testValuesContainsStationery() {
        ProductCategory[] categories = ProductCategory.values();
        assertEquals(ProductCategory.STATIONERY, categories[1]);
    }

    @Test
    void testValuesArrayNotNull() {
        assertNotNull(ProductCategory.values());
    }

    @Test
    void testValuesReturnsNewArrayInstance() {
        ProductCategory[] array1 = ProductCategory.values();
        ProductCategory[] array2 = ProductCategory.values();
        assertNotSame(array1, array2);
    }

    @Test
    void testValuesOrderIsPreserved() {
        ProductCategory[] categories = ProductCategory.values();
        assertSame(ProductCategory.UNIFORM, categories[0]);
        assertSame(ProductCategory.STATIONERY, categories[1]);
    }

    @Test
    void testValuesElementsAreNotNull() {
        for (ProductCategory category : ProductCategory.values()) {
            assertNotNull(category);
        }
    }

    @Test
    void testValuesCanBeIterated() {
        int count = 0;
        for (ProductCategory category : ProductCategory.values()) {
            assertNotNull(category.getLabel());
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    void testValuesContainsAllEnumConstants() {
        ProductCategory[] categories = ProductCategory.values();
        assertTrue(categories[0] == ProductCategory.UNIFORM || categories[1] == ProductCategory.UNIFORM);
        assertTrue(categories[0] == ProductCategory.STATIONERY || categories[1] == ProductCategory.STATIONERY);
    }

    @Test
    void testValuesMatchesEnumCount() {
        assertEquals(ProductCategory.class.getEnumConstants().length, ProductCategory.values().length);
    }

    @Test
    void testValueOfUniform() {
        assertEquals(ProductCategory.UNIFORM, ProductCategory.valueOf("UNIFORM"));
    }

    @Test
    void testValueOfStationery() {
        assertEquals(ProductCategory.STATIONERY, ProductCategory.valueOf("STATIONERY"));
    }

    @Test
    void testValueOfInvalidNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ProductCategory.valueOf("INVALID"));
    }

    @Test
    void testValueOfNullThrowsException() {
        assertThrows(NullPointerException.class, () -> ProductCategory.valueOf(null));
    }

    @Test
    void testValueOfLowerCaseThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ProductCategory.valueOf("uniform"));
    }

    @Test
    void testValueOfWithSpacesThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ProductCategory.valueOf(" UNIFORM "));
    }

    @Test
    void testValueOfReturnsSameInstance() {
        assertSame(ProductCategory.UNIFORM, ProductCategory.valueOf("UNIFORM"));
    }

    @Test
    void testValueOfLabelNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ProductCategory.valueOf("Papelería"));
    }

    @Test
    void testValueOfConsistency() {
        ProductCategory cat1 = ProductCategory.valueOf("STATIONERY");
        ProductCategory cat2 = ProductCategory.valueOf("STATIONERY");
        assertEquals(cat1, cat2);
    }

    @Test
    void testValueOfMatchingNameAndToString() {
        ProductCategory category = ProductCategory.valueOf("UNIFORM");
        assertEquals(category.name(), "UNIFORM");
    }
}