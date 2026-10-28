package ec.edu.espe.unistock.service;

import ec.edu.espe.unistock.structure.SimpleList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class StationeryServiceTest {

    private static final String BRANDS_FILE = "brands.json";
    private static final String TYPES_FILE = "types.json";
    private StationeryService stationeryService;

    @BeforeEach
    void setUp() {
        deleteFiles();
        stationeryService = new StationeryService();
    }

    @AfterEach
    void tearDown() {
        deleteFiles();
    }

    private void deleteFiles() {
        File bFile = new File(BRANDS_FILE);
        if (bFile.exists()) {
            bFile.delete();
        }
        File tFile = new File(TYPES_FILE);
        if (tFile.exists()) {
            tFile.delete();
        }
    }

    @Test
    void testConstructorInitializesEmptyListsWhenFilesMissing() {
        assertNotNull(stationeryService.getRegisteredBrands());
        assertNotNull(stationeryService.getRegisteredTypes());
        assertTrue(stationeryService.getRegisteredBrands().isEmpty());
        assertTrue(stationeryService.getRegisteredTypes().isEmpty());
    }

    @Test
    void testConstructorLoadsExistingBrandsFromFile() {
        stationeryService.registerBrand("Norma");
        StationeryService newService = new StationeryService();
        assertEquals(1, newService.getRegisteredBrands().size());
        assertTrue(newService.existsBrand("Norma"));
    }

    @Test
    void testConstructorLoadsExistingTypesFromFile() {
        stationeryService.registerType("Cuaderno");
        StationeryService newService = new StationeryService();
        assertEquals(1, newService.getRegisteredTypes().size());
        assertTrue(newService.existsType("Cuaderno"));
    }

    @Test
    void testConstructorHandlesBrandAndTypePersistenceTogether() {
        stationeryService.registerBrand("FaberCastell");
        stationeryService.registerType("Lapiz");
        StationeryService newService = new StationeryService();
        assertTrue(newService.existsBrand("FaberCastell"));
        assertTrue(newService.existsType("Lapiz"));
    }

    @Test
    void testConstructorIndependentFileLoading() {
        stationeryService.registerBrand("Pelikan");
        StationeryService newService = new StationeryService();
        assertTrue(newService.getRegisteredTypes().isEmpty());
        assertFalse(newService.getRegisteredBrands().isEmpty());
    }

    @Test
    void testConstructorMultipleBrandsLoaded() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerBrand("Pilot");
        StationeryService newService = new StationeryService();
        assertEquals(2, newService.getRegisteredBrands().size());
    }

    @Test
    void testConstructorMultipleTypesLoaded() {
        stationeryService.registerType("Cuaderno");
        stationeryService.registerType("Esfero");
        StationeryService newService = new StationeryService();
        assertEquals(2, newService.getRegisteredTypes().size());
    }

    @Test
    void testConstructorCreatesServiceInstance() {
        assertNotNull(stationeryService);
    }

    @Test
    void testConstructorListsAreNotSameInstance() {
        assertNotSame(stationeryService.getRegisteredBrands(), stationeryService.getRegisteredTypes());
    }

    @Test
    void testConstructorPreservesDataIntegrity() {
        stationeryService.registerBrand("Artesco");
        stationeryService.registerType("Regla");
        StationeryService service2 = new StationeryService();
        assertEquals(1, service2.getRegisteredBrands().size());
        assertEquals(1, service2.getRegisteredTypes().size());
    }

    @Test
    void testRegisterBrandValidString() {
        stationeryService.registerBrand("Norma");
        assertTrue(stationeryService.existsBrand("Norma"));
    }

    @Test
    void testRegisterBrandNullIgnored() {
        stationeryService.registerBrand(null);
        assertTrue(stationeryService.getRegisteredBrands().isEmpty());
    }

    @Test
    void testRegisterBrandEmptyIgnored() {
        stationeryService.registerBrand("");
        assertTrue(stationeryService.getRegisteredBrands().isEmpty());
    }

    @Test
    void testRegisterBrandBlankIgnored() {
        stationeryService.registerBrand("   ");
        assertTrue(stationeryService.getRegisteredBrands().isEmpty());
    }

    @Test
    void testRegisterBrandDuplicateIgnored() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerBrand("Norma");
        assertEquals(1, stationeryService.getRegisteredBrands().size());
    }

    @Test
    void testRegisterBrandCaseInsensitiveDuplicateIgnored() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerBrand("NORMA");
        assertEquals(1, stationeryService.getRegisteredBrands().size());
    }

    @Test
    void testRegisterBrandTrimsInput() {
        stationeryService.registerBrand("  FaberCastell  ");
        assertTrue(stationeryService.existsBrand("FaberCastell"));
        assertEquals("FaberCastell", stationeryService.getRegisteredBrands().next());
    }

    @Test
    void testRegisterBrandPersistsToFile() {
        stationeryService.registerBrand("Pelikan");
        File file = new File(BRANDS_FILE);
        assertTrue(file.exists() && file.length() > 0);
    }

    @Test
    void testRegisterBrandMultipleBrands() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerBrand("Pilot");
        stationeryService.registerBrand("BIC");
        assertEquals(3, stationeryService.getRegisteredBrands().size());
    }

    @Test
    void testRegisterBrandSpecialCharacters() {
        stationeryService.registerBrand("Paper-Mate");
        assertTrue(stationeryService.existsBrand("Paper-Mate"));
    }

    @Test
    void testExistsBrandReturnsTrueWhenPresent() {
        stationeryService.registerBrand("Norma");
        assertTrue(stationeryService.existsBrand("Norma"));
    }

    @Test
    void testExistsBrandReturnsFalseWhenAbsent() {
        assertFalse(stationeryService.existsBrand("Norma"));
    }

    @Test
    void testExistsBrandNullReturnsFalse() {
        assertFalse(stationeryService.existsBrand(null));
    }

    @Test
    void testExistsBrandCaseInsensitiveLower() {
        stationeryService.registerBrand("FaberCastell");
        assertTrue(stationeryService.existsBrand("fabercastell"));
    }

    @Test
    void testExistsBrandCaseInsensitiveUpper() {
        stationeryService.registerBrand("FaberCastell");
        assertTrue(stationeryService.existsBrand("FABERCASTELL"));
    }

    @Test
    void testExistsBrandTrimsInput() {
        stationeryService.registerBrand("Pelikan");
        assertTrue(stationeryService.existsBrand("  Pelikan  "));
    }

    @Test
    void testExistsBrandEmptyStringReturnsFalse() {
        stationeryService.registerBrand("Pilot");
        assertFalse(stationeryService.existsBrand(""));
    }

    @Test
    void testExistsBrandBlankStringReturnsFalse() {
        stationeryService.registerBrand("Pilot");
        assertFalse(stationeryService.existsBrand("   "));
    }

    @Test
    void testExistsBrandMultipleBrandsSearch() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerBrand("Pilot");
        assertTrue(stationeryService.existsBrand("Pilot"));
    }

    @Test
    void testExistsBrandConsistentCall() {
        stationeryService.registerBrand("BIC");
        assertTrue(stationeryService.existsBrand("BIC"));
        assertTrue(stationeryService.existsBrand("BIC"));
    }

    @Test
    void testRegisterTypeValidString() {
        stationeryService.registerType("Cuaderno");
        assertTrue(stationeryService.existsType("Cuaderno"));
    }

    @Test
    void testRegisterTypeNullIgnored() {
        stationeryService.registerType(null);
        assertTrue(stationeryService.getRegisteredTypes().isEmpty());
    }

    @Test
    void testRegisterTypeEmptyIgnored() {
        stationeryService.registerType("");
        assertTrue(stationeryService.getRegisteredTypes().isEmpty());
    }

    @Test
    void testRegisterTypeBlankIgnored() {
        stationeryService.registerType("   ");
        assertTrue(stationeryService.getRegisteredTypes().isEmpty());
    }

    @Test
    void testRegisterTypeDuplicateIgnored() {
        stationeryService.registerType("Cuaderno");
        stationeryService.registerType("Cuaderno");
        assertEquals(1, stationeryService.getRegisteredTypes().size());
    }

    @Test
    void testRegisterTypeCaseInsensitiveDuplicateIgnored() {
        stationeryService.registerType("Cuaderno");
        stationeryService.registerType("CUADERNO");
        assertEquals(1, stationeryService.getRegisteredTypes().size());
    }

    @Test
    void testRegisterTypeTrimsInput() {
        stationeryService.registerType("  Esfero  ");
        assertTrue(stationeryService.existsType("Esfero"));
        assertEquals("Esfero", stationeryService.getRegisteredTypes().next());
    }

    @Test
    void testRegisterTypePersistsToFile() {
        stationeryService.registerType("Borrador");
        File file = new File(TYPES_FILE);
        assertTrue(file.exists() && file.length() > 0);
    }

    @Test
    void testRegisterTypeMultipleTypes() {
        stationeryService.registerType("Cuaderno");
        stationeryService.registerType("Lapiz");
        stationeryService.registerType("Regla");
        assertEquals(3, stationeryService.getRegisteredTypes().size());
    }

    @Test
    void testRegisterTypeWithSpecialCharacters() {
        stationeryService.registerType("Marcador-Agua");
        assertTrue(stationeryService.existsType("Marcador-Agua"));
    }

    @Test
    void testExistsTypeReturnsTrueWhenPresent() {
        stationeryService.registerType("Cuaderno");
        assertTrue(stationeryService.existsType("Cuaderno"));
    }

    @Test
    void testExistsTypeReturnsFalseWhenAbsent() {
        assertFalse(stationeryService.existsType("Cuaderno"));
    }

    @Test
    void testExistsTypeNullReturnsFalse() {
        assertFalse(stationeryService.existsType(null));
    }

    @Test
    void testExistsTypeCaseInsensitiveLower() {
        stationeryService.registerType("Esfero");
        assertTrue(stationeryService.existsType("esfero"));
    }

    @Test
    void testExistsTypeCaseInsensitiveUpper() {
        stationeryService.registerType("Esfero");
        assertTrue(stationeryService.existsType("ESFERO"));
    }

    @Test
    void testExistsTypeTrimsInput() {
        stationeryService.registerType("Lapiz");
        assertTrue(stationeryService.existsType("  Lapiz  "));
    }

    @Test
    void testExistsTypeEmptyStringReturnsFalse() {
        stationeryService.registerType("Borrador");
        assertFalse(stationeryService.existsType(""));
    }

    @Test
    void testExistsTypeBlankStringReturnsFalse() {
        stationeryService.registerType("Borrador");
        assertFalse(stationeryService.existsType("   "));
    }

    @Test
    void testExistsTypeMultipleTypesSearch() {
        stationeryService.registerType("Cuaderno");
        stationeryService.registerType("Regla");
        assertTrue(stationeryService.existsType("Regla"));
    }

    @Test
    void testExistsTypeConsistentCall() {
        stationeryService.registerType("Carpeta");
        assertTrue(stationeryService.existsType("Carpeta"));
        assertTrue(stationeryService.existsType("Carpeta"));
    }

    @Test
    void testGetRegisteredBrandsNotNull() {
        assertNotNull(stationeryService.getRegisteredBrands());
    }

    @Test
    void testGetRegisteredBrandsInitiallyEmpty() {
        assertTrue(stationeryService.getRegisteredBrands().isEmpty());
    }

    @Test
    void testGetRegisteredBrandsReflectsRegisteredBrands() {
        stationeryService.registerBrand("Norma");
        assertEquals(1, stationeryService.getRegisteredBrands().size());
    }

    @Test
    void testGetRegisteredBrandsSameReference() {
        assertSame(stationeryService.getRegisteredBrands(), stationeryService.getRegisteredBrands());
    }

    @Test
    void testGetRegisteredBrandsAllowsIteration() {
        stationeryService.registerBrand("Norma");
        SimpleList<String> brands = stationeryService.getRegisteredBrands();
        brands.resetIteration();
        assertTrue(brands.hasNext());
        assertEquals("Norma", brands.next());
    }

    @Test
    void testGetRegisteredBrandsConsistentCall() {
        assertEquals(stationeryService.getRegisteredBrands(), stationeryService.getRegisteredBrands());
    }

    @Test
    void testGetRegisteredBrandsMultipleBrandsCount() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerBrand("Pilot");
        stationeryService.registerBrand("BIC");
        assertEquals(3, stationeryService.getRegisteredBrands().size());
    }

    @Test
    void testGetRegisteredBrandsOrderPreserved() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerBrand("Pilot");
        SimpleList<String> brands = stationeryService.getRegisteredBrands();
        brands.resetIteration();
        assertEquals("Norma", brands.next());
        assertEquals("Pilot", brands.next());
    }

    @Test
    void testGetRegisteredBrandsReturnsSimpleList() {
        assertTrue(stationeryService.getRegisteredBrands() instanceof SimpleList);
    }

    @Test
    void testGetRegisteredBrandsDoesNotIncludeTypes() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        assertEquals(1, stationeryService.getRegisteredBrands().size());
    }

    @Test
    void testGetRegisteredTypesNotNull() {
        assertNotNull(stationeryService.getRegisteredTypes());
    }

    @Test
    void testGetRegisteredTypesInitiallyEmpty() {
        assertTrue(stationeryService.getRegisteredTypes().isEmpty());
    }

    @Test
    void testGetRegisteredTypesReflectsRegisteredTypes() {
        stationeryService.registerType("Cuaderno");
        assertEquals(1, stationeryService.getRegisteredTypes().size());
    }

    @Test
    void testGetRegisteredTypesSameReference() {
        assertSame(stationeryService.getRegisteredTypes(), stationeryService.getRegisteredTypes());
    }

    @Test
    void testGetRegisteredTypesAllowsIteration() {
        stationeryService.registerType("Cuaderno");
        SimpleList<String> types = stationeryService.getRegisteredTypes();
        types.resetIteration();
        assertTrue(types.hasNext());
        assertEquals("Cuaderno", types.next());
    }

    @Test
    void testGetRegisteredTypesConsistentCall() {
        assertEquals(stationeryService.getRegisteredTypes(), stationeryService.getRegisteredTypes());
    }

    @Test
    void testGetRegisteredTypesMultipleTypesCount() {
        stationeryService.registerType("Cuaderno");
        stationeryService.registerType("Lapiz");
        stationeryService.registerType("Regla");
        assertEquals(3, stationeryService.getRegisteredTypes().size());
    }

    @Test
    void testGetRegisteredTypesOrderPreserved() {
        stationeryService.registerType("Cuaderno");
        stationeryService.registerType("Lapiz");
        SimpleList<String> types = stationeryService.getRegisteredTypes();
        types.resetIteration();
        assertEquals("Cuaderno", types.next());
        assertEquals("Lapiz", types.next());
    }

    @Test
    void testGetRegisteredTypesReturnsSimpleList() {
        assertTrue(stationeryService.getRegisteredTypes() instanceof SimpleList);
    }

    @Test
    void testGetRegisteredTypesDoesNotIncludeBrands() {
        stationeryService.registerType("Cuaderno");
        stationeryService.registerBrand("Norma");
        assertEquals(1, stationeryService.getRegisteredTypes().size());
    }
}