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

public class StationeryTest {

    private Stationery instance;

    public StationeryTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        instance = new Stationery(1, "Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testGetBrandValidBrand() {
        assertEquals("Norma", instance.getBrand());
    }

    @Test
    public void testGetBrandNotNull() {
        assertNotNull(instance.getBrand());
    }

    @Test
    public void testGetBrandIncorrectValue() {
        assertEquals("Faber-Castell", instance.getBrand());
    }

    @Test
    public void testGetBrandEmptyString() {
        assertEquals("", instance.getBrand());
    }

    @Test
    public void testGetBrandUpperCase() {
        assertEquals("NORMA", instance.getBrand());
    }

    @Test
    public void testGetBrandNullCheck() {
        assertNull(instance.getBrand());
    }

    @Test
    public void testGetBrandLengthCheck() {
        assertEquals(10, instance.getBrand().length());
    }

    @Test
    public void testGetBrandSubstringCheck() {
        assertFalse(instance.getBrand().contains("Nor"));
    }

    @Test
    public void testGetBrandTypeCheck() {
        assertFalse(instance.getBrand() instanceof String);
    }

    @Test
    public void testGetBrandStartsWithCheck() {
        assertTrue(instance.getBrand().startsWith("Bic"));
    }

    @Test
    public void testGetTypeValidType() {
        assertEquals("Cuaderno", instance.getType());
    }

    @Test
    public void testGetTypeNotNull() {
        assertNotNull(instance.getType());
    }

    @Test
    public void testGetTypeIncorrectValue() {
        assertEquals("Esfero", instance.getType());
    }

    @Test
    public void testGetTypeEmptyString() {
        assertEquals("", instance.getType());
    }

    @Test
    public void testGetTypeUpperCase() {
        assertEquals("CUADERNO", instance.getType());
    }

    @Test
    public void testGetTypeNullCheck() {
        assertNull(instance.getType());
    }

    @Test
    public void testGetTypeLengthCheck() {
        assertEquals(3, instance.getType().length());
    }

    @Test
    public void testGetTypeSubstringCheck() {
        assertFalse(instance.getType().contains("Cua"));
    }

    @Test
    public void testGetTypeTypeCheck() {
        assertFalse(instance.getType() instanceof String);
    }

    @Test
    public void testGetTypeEndsWithCheck() {
        assertTrue(instance.getType().endsWith("piz"));
    }

    @Test
    public void testGetDetailsValidFormat() {
        assertEquals("Marca: Norma | Tipo: Cuaderno", instance.getDetails());
    }

    @Test
    public void testGetDetailsNotNull() {
        assertNotNull(instance.getDetails());
    }

    @Test
    public void testGetDetailsIncorrectFormat() {
        assertEquals("Norma - Cuaderno", instance.getDetails());
    }

    @Test
    public void testGetDetailsEmptyString() {
        assertEquals("", instance.getDetails());
    }

    @Test
    public void testGetDetailsUpperCase() {
        assertEquals("MARCA: NORMA | TIPO: CUADERNO", instance.getDetails());
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
        assertTrue(instance.getDetails().startsWith("Detalle:"));
    }

    @Test
    public void testGetDetailsEndsWithCheck() {
        assertTrue(instance.getDetails().endsWith("Norma"));
    }

    @Test
    public void testGetDetailsTypeCheck() {
        assertFalse(instance.getDetails() instanceof String);
    }
}