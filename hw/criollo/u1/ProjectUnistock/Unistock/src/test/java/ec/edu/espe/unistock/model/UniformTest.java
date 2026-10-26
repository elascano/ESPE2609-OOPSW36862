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

public class UniformTest {

    private Uniform instance;

    public UniformTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        instance = new Uniform(1, "Camisa Escolar", new BigDecimal("15.50"), 20, "M", "Colegio Espe");
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testGetSizeValidSize() {
        assertEquals("M", instance.getSize());
    }

    @Test
    public void testGetSizeNotNull() {
        assertNotNull(instance.getSize());
    }

    @Test
    public void testGetSizeIncorrectValue() {
        assertEquals("L", instance.getSize());
    }

    @Test
    public void testGetSizeEmptyString() {
        assertEquals("", instance.getSize());
    }

    @Test
    public void testGetSizeLowerCase() {
        assertEquals("m", instance.getSize());
    }

    @Test
    public void testGetSizeNullCheck() {
        assertNull(instance.getSize());
    }

    @Test
    public void testGetSizeLengthCheck() {
        assertEquals(5, instance.getSize().length());
    }

    @Test
    public void testGetSizeSubstringCheck() {
        assertFalse(instance.getSize().contains("M"));
    }

    @Test
    public void testGetSizeTypeCheck() {
        assertFalse(instance.getSize() instanceof String);
    }

    @Test
    public void testGetSizeStartsWithCheck() {
        assertTrue(instance.getSize().startsWith("S"));
    }

    @Test
    public void testGetSchoolValidSchool() {
        assertEquals("Colegio Espe", instance.getSchool());
    }

    @Test
    public void testGetSchoolNotNull() {
        assertNotNull(instance.getSchool());
    }

    @Test
    public void testGetSchoolIncorrectValue() {
        assertEquals("Colegio Central", instance.getSchool());
    }

    @Test
    public void testGetSchoolEmptyString() {
        assertEquals("", instance.getSchool());
    }

    @Test
    public void testGetSchoolUpperCase() {
        assertEquals("COLEGIO ESPE", instance.getSchool());
    }

    @Test
    public void testGetSchoolNullCheck() {
        assertNull(instance.getSchool());
    }

    @Test
    public void testGetSchoolLengthCheck() {
        assertEquals(3, instance.getSchool().length());
    }

    @Test
    public void testGetSchoolSubstringCheck() {
        assertFalse(instance.getSchool().contains("Espe"));
    }

    @Test
    public void testGetSchoolTypeCheck() {
        assertFalse(instance.getSchool() instanceof String);
    }

    @Test
    public void testGetSchoolEndsWithCheck() {
        assertTrue(instance.getSchool().endsWith("Nacional"));
    }

    @Test
    public void testGetDetailsValidFormat() {
        assertEquals("Talla: M | Colegio: Colegio Espe", instance.getDetails());
    }

    @Test
    public void testGetDetailsNotNull() {
        assertNotNull(instance.getDetails());
    }

    @Test
    public void testGetDetailsIncorrectFormat() {
        assertEquals("M - Colegio Espe", instance.getDetails());
    }

    @Test
    public void testGetDetailsEmptyString() {
        assertEquals("", instance.getDetails());
    }

    @Test
    public void testGetDetailsUpperCase() {
        assertEquals("TALLA: M | COLEGIO: COLEGIO ESPE", instance.getDetails());
    }

    @Test
    public void testGetDetailsNullCheck() {
        assertNull(instance.getDetails());
    }

    @Test
    public void testGetDetailsLengthCheck() {
        assertEquals(10, instance.getDetails().length());
    }

    @Test
    public void testGetDetailsStartsWithCheck() {
        assertTrue(instance.getDetails().startsWith("Colegio:"));
    }

    @Test
    public void testGetDetailsEndsWithCheck() {
        assertTrue(instance.getDetails().endsWith("Talla: M"));
    }

    @Test
    public void testGetDetailsTypeCheck() {
        assertFalse(instance.getDetails() instanceof String);
    }
}