package ec.edu.espe.unistock.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class StationeryTest {

    private Stationery testStationery;

    @BeforeEach
    void setUp() {
        testStationery = new Stationery(201, "Cuaderno Universitario", new BigDecimal("2.50"), 50, "Norma", "Cuaderno");
    }

    @Test
    void testConstructorValidParameters() {
        Stationery stationery = new Stationery(202, "Lapiz de Color", new BigDecimal("4.80"), 30, "FaberCastell", "Colores");
        assertNotNull(stationery);
    }

    @Test
    void testConstructorInheritedProductAttributes() {
        assertEquals(201, testStationery.getId());
        assertEquals("Cuaderno Universitario", testStationery.getName());
        assertEquals(new BigDecimal("2.50"), testStationery.getPrice());
        assertEquals(50, testStationery.getStock());
        assertEquals(ProductCategory.STATIONERY, testStationery.getCategory());
    }

    @Test
    void testConstructorNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Stationery(1, null, new BigDecimal("1.00"), 5, "Norma", "Cuaderno"));
    }

    @Test
    void testConstructorBlankNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Stationery(1, "   ", new BigDecimal("1.00"), 5, "Norma", "Cuaderno"));
    }

    @Test
    void testConstructorNegativePriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Stationery(1, "Borrador", new BigDecimal("-0.50"), 5, "Pelikan", "Goma"));
    }

    @Test
    void testConstructorNegativeStockThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Stationery(1, "Borrador", new BigDecimal("0.50"), -1, "Pelikan", "Goma"));
    }

    @Test
    void testConstructorAllowsNullBrandAndType() {
        Stationery stationery = new Stationery(203, "Regla Metalica", new BigDecimal("1.20"), 15, null, null);
        assertNull(stationery.getBrand());
        assertNull(stationery.getType());
    }

    @Test
    void testConstructorZeroPriceAllowed() {
        Stationery stationery = new Stationery(204, "Muestra Escolar", BigDecimal.ZERO, 5, "BIC", "Esfero");
        assertEquals(BigDecimal.ZERO, stationery.getPrice());
    }

    @Test
    void testConstructorZeroStockAllowed() {
        Stationery stationery = new Stationery(205, "Pluma Agotada", new BigDecimal("0.80"), 0, "Pilot", "Esfero");
        assertEquals(0, stationery.getStock());
    }

    @Test
    void testConstructorCategoryIsAlwaysStationery() {
        assertEquals(ProductCategory.STATIONERY, testStationery.getCategory());
    }

    @Test
    void testGetBrandValidString() {
        assertEquals("Norma", testStationery.getBrand());
    }

    @Test
    void testGetBrandFaberCastell() {
        Stationery stationery = new Stationery(2, "Pluma", new BigDecimal("0.50"), 10, "FaberCastell", "Esfero");
        assertEquals("FaberCastell", stationery.getBrand());
    }

    @Test
    void testGetBrandNull() {
        Stationery stationery = new Stationery(3, "Goma", new BigDecimal("0.30"), 10, null, "Pegamento");
        assertNull(stationery.getBrand());
    }

    @Test
    void testGetBrandEmpty() {
        Stationery stationery = new Stationery(4, "Goma", new BigDecimal("0.30"), 10, "", "Pegamento");
        assertEquals("", stationery.getBrand());
    }

    @Test
    void testGetBrandImmutable() {
        String brand = testStationery.getBrand();
        assertEquals("Norma", brand);
    }

    @Test
    void testGetBrandConsistentCall() {
        assertEquals(testStationery.getBrand(), testStationery.getBrand());
    }

    @Test
    void testGetBrandWithSpaces() {
        Stationery stationery = new Stationery(5, "Tijeras", new BigDecimal("1.50"), 10, " PaperMate ", "Corte");
        assertEquals(" PaperMate ", stationery.getBrand());
    }

    @Test
    void testGetBrandPelikan() {
        Stationery stationery = new Stationery(6, "Acuarelas", new BigDecimal("3.50"), 10, "Pelikan", "Pintura");
        assertEquals("Pelikan", stationery.getBrand());
    }

    @Test
    void testGetBrandArtesco() {
        Stationery stationery = new Stationery(7, "Juego Geometrico", new BigDecimal("2.00"), 10, "Artesco", "Geometria");
        assertEquals("Artesco", stationery.getBrand());
    }

    @Test
    void testGetBrandStaedtler() {
        Stationery stationery = new Stationery(8, "Compas", new BigDecimal("4.00"), 10, "Staedtler", "Dibujo");
        assertEquals("Staedtler", stationery.getBrand());
    }

    @Test
    void testGetTypeValidString() {
        assertEquals("Cuaderno", testStationery.getType());
    }

    @Test
    void testGetTypeEsfero() {
        Stationery stationery = new Stationery(2, "Pluma BIC", new BigDecimal("0.40"), 20, "BIC", "Esfero");
        assertEquals("Esfero", stationery.getType());
    }

    @Test
    void testGetTypeNull() {
        Stationery stationery = new Stationery(3, "Goma", new BigDecimal("0.30"), 10, "Pelikan", null);
        assertNull(stationery.getType());
    }

    @Test
    void testGetTypeEmpty() {
        Stationery stationery = new Stationery(4, "Goma", new BigDecimal("0.30"), 10, "Pelikan", "");
        assertEquals("", stationery.getType());
    }

    @Test
    void testGetTypeImmutable() {
        String type = testStationery.getType();
        assertEquals("Cuaderno", type);
    }

    @Test
    void testGetTypeConsistentCall() {
        assertEquals(testStationery.getType(), testStationery.getType());
    }

    @Test
    void testGetTypeWithSpaces() {
        Stationery stationery = new Stationery(5, "Carpeta", new BigDecimal("0.80"), 10, "Norma", " Archivo ");
        assertEquals(" Archivo ", stationery.getType());
    }

    @Test
    void testGetTypeMarcador() {
        Stationery stationery = new Stationery(6, "Resaltador", new BigDecimal("0.90"), 10, "Pelikan", "Marcador");
        assertEquals("Marcador", stationery.getType());
    }

    @Test
    void testGetTypePintura() {
        Stationery stationery = new Stationery(7, "Temperas", new BigDecimal("2.80"), 10, "Crayola", "Pintura");
        assertEquals("Pintura", stationery.getType());
    }

    @Test
    void testGetTypeCalculadora() {
        Stationery stationery = new Stationery(8, "Calculadora", new BigDecimal("15.00"), 10, "Casio", "Electronico");
        assertEquals("Electronico", stationery.getType());
    }

    @Test
    void testGetDetailsFormatExactMatch() {
        assertEquals("Marca: Norma | Tipo: Cuaderno", testStationery.getDetails());
    }

    @Test
    void testGetDetailsContainsBrand() {
        assertTrue(testStationery.getDetails().contains("Norma"));
    }

    @Test
    void testGetDetailsContainsType() {
        assertTrue(testStationery.getDetails().contains("Cuaderno"));
    }

    @Test
    void testGetDetailsWithNullValues() {
        Stationery stationery = new Stationery(1, "Regla", new BigDecimal("0.50"), 5, null, null);
        assertEquals("Marca: null | Tipo: null", stationery.getDetails());
    }

    @Test
    void testGetDetailsNotNull() {
        assertNotNull(testStationery.getDetails());
    }

    @Test
    void testGetDetailsNotEmpty() {
        assertFalse(testStationery.getDetails().isEmpty());
    }

    @Test
    void testGetDetailsConsistency() {
        String details1 = testStationery.getDetails();
        String details2 = testStationery.getDetails();
        assertEquals(details1, details2);
    }

    @Test
    void testGetDetailsContainsSeparators() {
        assertTrue(testStationery.getDetails().contains("Marca:") && testStationery.getDetails().contains("| Tipo:"));
    }

    @Test
    void testGetDetailsCustomStationery() {
        Stationery stationery = new Stationery(2, "Color", new BigDecimal("5.00"), 10, "FaberCastell", "Colores");
        assertEquals("Marca: FaberCastell | Tipo: Colores", stationery.getDetails());
    }

    @Test
    void testGetDetailsUnchangedAfterStockChange() {
        testStationery.decreaseStock(10);
        assertEquals("Marca: Norma | Tipo: Cuaderno", testStationery.getDetails());
    }
}