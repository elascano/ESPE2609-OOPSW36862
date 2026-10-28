package ec.edu.espe.unistock.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class UniformTest {

    private Uniform testUniform;

    @BeforeEach
    void setUp() {
        testUniform = new Uniform(101, "Camisa Formal", new BigDecimal("12.50"), 25, "M", "Colegio San Gabriel");
    }

    @Test
    void testConstructorValidParameters() {
        Uniform uniform = new Uniform(102, "Pantalon Vestir", new BigDecimal("18.00"), 10, "32", "Colegio Benalcazar");
        assertNotNull(uniform);
    }

    @Test
    void testConstructorInheritedProductAttributes() {
        assertEquals(101, testUniform.getId());
        assertEquals("Camisa Formal", testUniform.getName());
        assertEquals(new BigDecimal("12.50"), testUniform.getPrice());
        assertEquals(25, testUniform.getStock());
        assertEquals(ProductCategory.UNIFORM, testUniform.getCategory());
    }

    @Test
    void testConstructorNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Uniform(1, null, new BigDecimal("10.00"), 5, "S", "Mejia"));
    }

    @Test
    void testConstructorBlankNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Uniform(1, "   ", new BigDecimal("10.00"), 5, "S", "Mejia"));
    }

    @Test
    void testConstructorNegativePriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Uniform(1, "Buzo", new BigDecimal("-1.00"), 5, "S", "Mejia"));
    }

    @Test
    void testConstructorNegativeStockThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Uniform(1, "Buzo", new BigDecimal("10.00"), -1, "S", "Mejia"));
    }

    @Test
    void testConstructorAllowsNullSizeAndSchool() {
        Uniform uniform = new Uniform(103, "Falda", new BigDecimal("15.00"), 8, null, null);
        assertNull(uniform.getSize());
        assertNull(uniform.getSchool());
    }

    @Test
    void testConstructorZeroPriceAllowed() {
        Uniform uniform = new Uniform(104, "Donacion", BigDecimal.ZERO, 5, "L", "Mejia");
        assertEquals(BigDecimal.ZERO, uniform.getPrice());
    }

    @Test
    void testConstructorZeroStockAllowed() {
        Uniform uniform = new Uniform(105, "Agotado", new BigDecimal("10.00"), 0, "S", "Mejia");
        assertEquals(0, uniform.getStock());
    }

    @Test
    void testConstructorCategoryIsAlwaysUniform() {
        assertEquals(ProductCategory.UNIFORM, testUniform.getCategory());
    }

    @Test
    void testGetSizeStandardLetter() {
        assertEquals("M", testUniform.getSize());
    }

    @Test
    void testGetSizeNumeric() {
        Uniform uniform = new Uniform(2, "Pantalon", new BigDecimal("15.00"), 5, "34", "San Gabriel");
        assertEquals("34", uniform.getSize());
    }

    @Test
    void testGetSizeNull() {
        Uniform uniform = new Uniform(3, "Camisa", new BigDecimal("10.00"), 5, null, "Benalcazar");
        assertNull(uniform.getSize());
    }

    @Test
    void testGetSizeEmpty() {
        Uniform uniform = new Uniform(4, "Camisa", new BigDecimal("10.00"), 5, "", "Benalcazar");
        assertEquals("", uniform.getSize());
    }

    @Test
    void testGetSizeImmutable() {
        String size = testUniform.getSize();
        assertEquals("M", size);
    }

    @Test
    void testGetSizeConsistentCall() {
        assertEquals(testUniform.getSize(), testUniform.getSize());
    }

    @Test
    void testGetSizeWithSpaces() {
        Uniform uniform = new Uniform(5, "Camisa", new BigDecimal("10.00"), 5, " L ", "San Gabriel");
        assertEquals(" L ", uniform.getSize());
    }

    @Test
    void testGetSizeSmall() {
        Uniform uniform = new Uniform(6, "Buzo", new BigDecimal("20.00"), 5, "S", "Epitacio");
        assertEquals("S", uniform.getSize());
    }

    @Test
    void testGetSizeExtraLarge() {
        Uniform uniform = new Uniform(7, "Chaqueta", new BigDecimal("25.00"), 5, "XL", "Epitacio");
        assertEquals("XL", uniform.getSize());
    }

    @Test
    void testGetSizeChild() {
        Uniform uniform = new Uniform(8, "Camiseta", new BigDecimal("8.00"), 5, "Talla 12", "Epitacio");
        assertEquals("Talla 12", uniform.getSize());
    }

    @Test
    void testGetSchoolValidString() {
        assertEquals("Colegio San Gabriel", testUniform.getSchool());
    }

    @Test
    void testGetSchoolNull() {
        Uniform uniform = new Uniform(1, "Camisa", new BigDecimal("10.00"), 5, "M", null);
        assertNull(uniform.getSchool());
    }

    @Test
    void testGetSchoolEmpty() {
        Uniform uniform = new Uniform(1, "Camisa", new BigDecimal("10.00"), 5, "M", "");
        assertEquals("", uniform.getSchool());
    }

    @Test
    void testGetSchoolImmutable() {
        String school = testUniform.getSchool();
        assertEquals("Colegio San Gabriel", school);
    }

    @Test
    void testGetSchoolConsistentCall() {
        assertEquals(testUniform.getSchool(), testUniform.getSchool());
    }

    @Test
    void testGetSchoolMejia() {
        Uniform uniform = new Uniform(2, "Buzo", new BigDecimal("15.00"), 5, "M", "Instituto Nacional Mejia");
        assertEquals("Instituto Nacional Mejia", uniform.getSchool());
    }

    @Test
    void testGetSchoolBenalcazar() {
        Uniform uniform = new Uniform(3, "Chaqueta", new BigDecimal("22.00"), 5, "L", "Colegio Benalcazar");
        assertEquals("Colegio Benalcazar", uniform.getSchool());
    }

    @Test
    void testGetSchoolNotNull() {
        assertNotNull(testUniform.getSchool());
    }

    @Test
    void testGetSchoolWithSpecialChars() {
        Uniform uniform = new Uniform(4, "Falda", new BigDecimal("12.00"), 5, "S", "U.E. 24 de Mayo");
        assertEquals("U.E. 24 de Mayo", uniform.getSchool());
    }

    @Test
    void testGetSchoolMultipleWords() {
        Uniform uniform = new Uniform(5, "Polo", new BigDecimal("9.00"), 5, "M", "Unidad Educativa Femenina");
        assertEquals("Unidad Educativa Femenina", uniform.getSchool());
    }

    @Test
    void testGetDetailsFormatExactMatch() {
        assertEquals("Talla: M | Colegio: Colegio San Gabriel", testUniform.getDetails());
    }

    @Test
    void testGetDetailsContainsSize() {
        assertTrue(testUniform.getDetails().contains("M"));
    }

    @Test
    void testGetDetailsContainsSchool() {
        assertTrue(testUniform.getDetails().contains("Colegio San Gabriel"));
    }

    @Test
    void testGetDetailsWithNullValues() {
        Uniform uniform = new Uniform(1, "Camisa", new BigDecimal("10.00"), 5, null, null);
        assertEquals("Talla: null | Colegio: null", uniform.getDetails());
    }

    @Test
    void testGetDetailsNotNull() {
        assertNotNull(testUniform.getDetails());
    }

    @Test
    void testGetDetailsNotEmpty() {
        assertFalse(testUniform.getDetails().isEmpty());
    }

    @Test
    void testGetDetailsConsistency() {
        String details1 = testUniform.getDetails();
        String details2 = testUniform.getDetails();
        assertEquals(details1, details2);
    }

    @Test
    void testGetDetailsContainsSeparators() {
        assertTrue(testUniform.getDetails().contains("Talla:") && testUniform.getDetails().contains("| Colegio:"));
    }

    @Test
    void testGetDetailsCustomUniform() {
        Uniform uniform = new Uniform(2, "Buzo", new BigDecimal("15.00"), 5, "XL", "Mejia");
        assertEquals("Talla: XL | Colegio: Mejia", uniform.getDetails());
    }

    @Test
    void testGetDetailsUnchangedAfterStockChange() {
        testUniform.decreaseStock(5);
        assertEquals("Talla: M | Colegio: Colegio San Gabriel", testUniform.getDetails());
    }
}