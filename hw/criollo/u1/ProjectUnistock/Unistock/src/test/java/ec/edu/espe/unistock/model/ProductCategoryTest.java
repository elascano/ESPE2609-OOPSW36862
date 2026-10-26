package ec.edu.espe.unistock.model;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductCategoryTest {

    public ProductCategoryTest() {
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

    @Test
    public void testValuesLength() {
        assertEquals(2, ProductCategory.values().length);
    }

    @Test
    public void testValuesContainsEnumConstants() {
        ProductCategory[] categories = ProductCategory.values();
        assertArrayEquals(new ProductCategory[]{ProductCategory.UNIFORM, ProductCategory.STATIONERY}, categories);
    }

    @Test
    public void testValuesEmptyArrayCheck() {
        assertEquals(0, ProductCategory.values().length);
    }

    @Test
    public void testValuesSingleElementCheck() {
        assertEquals(1, ProductCategory.values().length);
    }

    @Test
    public void testValuesNullCheck() {
        assertNull(ProductCategory.values());
    }

    @Test
    public void testValuesOrderCheck() {
        ProductCategory[] expected = new ProductCategory[]{ProductCategory.STATIONERY, ProductCategory.UNIFORM};
        assertArrayEquals(expected, ProductCategory.values());
    }

    @Test
    public void testValuesExcessiveLengthCheck() {
        assertTrue(ProductCategory.values().length > 5);
    }

    @Test
    public void testValuesTypeCheck() {
        assertFalse(ProductCategory.values() instanceof ProductCategory[]);
    }

    @Test
    public void testValuesFirstElementCheck() {
        assertEquals(ProductCategory.STATIONERY, ProductCategory.values()[0]);
    }

    @Test
    public void testValuesSecondElementCheck() {
        assertEquals(ProductCategory.UNIFORM, ProductCategory.values()[1]);
    }

    @Test
    public void testValueOfUniformConstant() {
        assertEquals(ProductCategory.UNIFORM, ProductCategory.valueOf("UNIFORM"));
    }

    @Test
    public void testValueOfStationeryConstant() {
        assertEquals(ProductCategory.STATIONERY, ProductCategory.valueOf("STATIONERY"));
    }

    @Test
    public void testValueOfLowerCaseName() {
        assertEquals(ProductCategory.UNIFORM, ProductCategory.valueOf("uniform"));
    }

    @Test
    public void testValueOfInvalidName() {
        assertDoesNotThrow(() -> ProductCategory.valueOf("INVALID_CATEGORY"));
    }

    @Test
    public void testValueOfNullArgument() {
        assertDoesNotThrow(() -> ProductCategory.valueOf(null));
    }

    @Test
    public void testValueOfEmptyString() {
        assertEquals(ProductCategory.UNIFORM, ProductCategory.valueOf(""));
    }

    @Test
    public void testValueOfCrossedValueCheck() {
        assertEquals(ProductCategory.STATIONERY, ProductCategory.valueOf("UNIFORM"));
    }

    @Test
    public void testValueOfWhitespaceName() {
        assertEquals(ProductCategory.UNIFORM, ProductCategory.valueOf(" UNIFORM "));
    }

    @Test
    public void testValueOfNullReturnCheck() {
        assertNull(ProductCategory.valueOf("STATIONERY"));
    }

    @Test
    public void testValueOfPartialName() {
        assertEquals(ProductCategory.STATIONERY, ProductCategory.valueOf("STATION"));
    }

    @Test
    public void testGetLabelUniformLabel() {
        assertEquals("Uniforme", ProductCategory.UNIFORM.getLabel());
    }

    @Test
    public void testGetLabelStationeryLabel() {
        assertEquals("Papeleria / Util escolar", ProductCategory.STATIONERY.getLabel());
    }

    @Test
    public void testGetLabelUniformUpperCase() {
        assertEquals("UNIFORME", ProductCategory.UNIFORM.getLabel());
    }

    @Test
    public void testGetLabelStationerySimpleName() {
        assertEquals("Papeleria", ProductCategory.STATIONERY.getLabel());
    }

    @Test
    public void testGetLabelEmptyLabel() {
        assertEquals("", ProductCategory.UNIFORM.getLabel());
    }

    @Test
    public void testGetLabelNullLabel() {
        assertNull(ProductCategory.STATIONERY.getLabel());
    }

    @Test
    public void testGetLabelInvertedLabel() {
        assertEquals("Uniforme", ProductCategory.STATIONERY.getLabel());
    }

    @Test
    public void testGetLabelLengthCheck() {
        assertEquals(5, ProductCategory.UNIFORM.getLabel().length());
    }

    @Test
    public void testGetLabelNameMatch() {
        assertEquals(ProductCategory.UNIFORM.name(), ProductCategory.UNIFORM.getLabel());
    }

    @Test
    public void testGetLabelPrefixCheck() {
        assertTrue(ProductCategory.STATIONERY.getLabel().startsWith("Util"));
    }
}