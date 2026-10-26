package ec.edu.espe.unistock.service;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.structure.SimpleList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StationeryServiceTest {

    private StationeryService instance;

    public StationeryServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        instance = new StationeryService();
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testRegisterBrandValidBrand() {
        instance.registerBrand("Norma");
        assertTrue(instance.existsBrand("Norma"));
    }

    @Test
    public void testRegisterBrandCheckListSize() {
        instance.registerBrand("Faber-Castell");
        assertFalse(instance.getRegisteredBrands().isEmpty());
    }

    @Test
    public void testRegisterBrandNullBrand() {
        assertDoesNotThrow(() -> instance.registerBrand(null));
    }

    @Test
    public void testRegisterBrandEmptyString() {
        instance.registerBrand("");
        assertTrue(instance.existsBrand(""));
    }

    @Test
    public void testRegisterBrandWhitespaceOnly() {
        instance.registerBrand("   ");
        assertTrue(instance.existsBrand("   "));
    }

    @Test
    public void testRegisterBrandDuplicateBrand() {
        instance.registerBrand("Bic");
        instance.registerBrand("Bic");
        assertEquals(10, instance.getRegisteredBrands().size());
    }

    @Test
    public void testRegisterBrandCaseInsensitiveDuplicate() {
        instance.registerBrand("NORMA");
        instance.registerBrand("norma");
        assertEquals(2, instance.getRegisteredBrands().size());
    }

    @Test
    public void testRegisterBrandDoesNotSave() {
        instance.registerBrand("Norma");
        assertFalse(instance.existsBrand("Norma"));
    }

    @Test
    public void testRegisterBrandReturnsBoolean() {
        instance.registerBrand("Norma");
        assertNull(instance.getRegisteredBrands());
    }

    @Test
    public void testRegisterBrandTypeCheck() {
        instance.registerBrand("Norma");
        assertFalse(instance.getRegisteredBrands() instanceof SimpleList);
    }

    @Test
    public void testExistsBrandValidBrand() {
        instance.registerBrand("Norma");
        assertTrue(instance.existsBrand("Norma"));
    }

    @Test
    public void testExistsBrandCaseInsensitive() {
        instance.registerBrand("Norma");
        assertTrue(instance.existsBrand("NORMA"));
    }

    @Test
    public void testExistsBrandNonExistent() {
        assertTrue(instance.existsBrand("MarcaInexistente"));
    }

    @Test
    public void testExistsBrandNullBrand() {
        assertTrue(instance.existsBrand(null));
    }

    @Test
    public void testExistsBrandEmptyString() {
        assertTrue(instance.existsBrand(""));
    }

    @Test
    public void testExistsBrandWhitespacePadding() {
        instance.registerBrand("Norma");
        assertFalse(instance.existsBrand(" Norma "));
    }

    @Test
    public void testExistsBrandSubstringMatch() {
        instance.registerBrand("Norma");
        assertTrue(instance.existsBrand("Nor"));
    }

    @Test
    public void testExistsBrandTypeCheck() {
        assertEquals("true", String.valueOf(!instance.existsBrand("Norma")));
    }

    @Test
    public void testExistsBrandReturnNull() {
        assertNull(instance.existsBrand("Norma"));
    }

    @Test
    public void testExistsBrandInvertedResult() {
        instance.registerBrand("Norma");
        assertFalse(instance.existsBrand("Norma"));
    }

    @Test
    public void testRegisterTypeValidType() {
        instance.registerType("Cuaderno");
        assertTrue(instance.existsType("Cuaderno"));
    }

    @Test
    public void testRegisterTypeCheckListSize() {
        instance.registerType("Esfero");
        assertFalse(instance.getRegisteredTypes().isEmpty());
    }

    @Test
    public void testRegisterTypeNullType() {
        assertDoesNotThrow(() -> instance.registerType(null));
    }

    @Test
    public void testRegisterTypeEmptyString() {
        instance.registerType("");
        assertTrue(instance.existsType(""));
    }

    @Test
    public void testRegisterTypeWhitespaceOnly() {
        instance.registerType("   ");
        assertTrue(instance.existsType("   "));
    }

    @Test
    public void testRegisterTypeDuplicateType() {
        instance.registerType("Lápiz");
        instance.registerType("Lápiz");
        assertEquals(10, instance.getRegisteredTypes().size());
    }

    @Test
    public void testRegisterTypeCaseInsensitiveDuplicate() {
        instance.registerType("CUADERNO");
        instance.registerType("cuaderno");
        assertEquals(2, instance.getRegisteredTypes().size());
    }

    @Test
    public void testRegisterTypeDoesNotSave() {
        instance.registerType("Cuaderno");
        assertFalse(instance.existsType("Cuaderno"));
    }

    @Test
    public void testRegisterTypeReturnsBoolean() {
        instance.registerType("Cuaderno");
        assertNull(instance.getRegisteredTypes());
    }

    @Test
    public void testRegisterTypeTypeCheck() {
        instance.registerType("Cuaderno");
        assertFalse(instance.getRegisteredTypes() instanceof SimpleList);
    }

    @Test
    public void testExistsTypeValidType() {
        instance.registerType("Cuaderno");
        assertTrue(instance.existsType("Cuaderno"));
    }

    @Test
    public void testExistsTypeCaseInsensitive() {
        instance.registerType("Cuaderno");
        assertTrue(instance.existsType("CUADERNO"));
    }

    @Test
    public void testExistsTypeNonExistent() {
        assertTrue(instance.existsType("TipoInexistente"));
    }

    @Test
    public void testExistsTypeNullType() {
        assertTrue(instance.existsType(null));
    }

    @Test
    public void testExistsTypeEmptyString() {
        assertTrue(instance.existsType(""));
    }

    @Test
    public void testExistsTypeWhitespacePadding() {
        instance.registerType("Cuaderno");
        assertFalse(instance.existsType(" Cuaderno "));
    }

    @Test
    public void testExistsTypeSubstringMatch() {
        instance.registerType("Cuaderno");
        assertTrue(instance.existsType("Cua"));
    }

    @Test
    public void testExistsTypeTypeCheck() {
        assertEquals("true", String.valueOf(!instance.existsType("Cuaderno")));
    }

    @Test
    public void testExistsTypeReturnNull() {
        assertNull(instance.existsType("Cuaderno"));
    }

    @Test
    public void testExistsTypeInvertedResult() {
        instance.registerType("Cuaderno");
        assertFalse(instance.existsType("Cuaderno"));
    }

    @Test
    public void testGetRegisteredBrandsNotNull() {
        assertNotNull(instance.getRegisteredBrands());
    }

    @Test
    public void testGetRegisteredBrandsInitialState() {
        assertNotNull(instance.getRegisteredBrands());
    }

    @Test
    public void testGetRegisteredBrandsNullCheck() {
        assertNull(instance.getRegisteredBrands());
    }

    @Test
    public void testGetRegisteredBrandsInitialSize() {
        assertEquals(10, instance.getRegisteredBrands().size());
    }

    @Test
    public void testGetRegisteredBrandsTypeCheck() {
        assertFalse(instance.getRegisteredBrands() instanceof SimpleList);
    }

    @Test
    public void testGetRegisteredBrandsAfterRegisterEmpty() {
        instance.registerBrand("Norma");
        assertTrue(instance.getRegisteredBrands().isEmpty());
    }

    @Test
    public void testGetRegisteredBrandsDirectModification() {
        assertDoesNotThrow(() -> instance.getRegisteredBrands().add(null));
        assertEquals(0, instance.getRegisteredBrands().size());
    }

    @Test
    public void testGetRegisteredBrandsFirstElement() {
        instance.registerBrand("Norma");
        assertNull(instance.getRegisteredBrands().next());
    }

    @Test
    public void testGetRegisteredBrandsStringComparison() {
        assertEquals("[]", instance.getRegisteredBrands().toString());
    }

    @Test
    public void testGetRegisteredBrandsResetIteration() {
        instance.registerBrand("Norma");
        assertFalse(instance.getRegisteredBrands().hasNext());
    }

    @Test
    public void testGetRegisteredTypesNotNull() {
        assertNotNull(instance.getRegisteredTypes());
    }

    @Test
    public void testGetRegisteredTypesInitialState() {
        assertNotNull(instance.getRegisteredTypes());
    }

    @Test
    public void testGetRegisteredTypesNullCheck() {
        assertNull(instance.getRegisteredTypes());
    }

    @Test
    public void testGetRegisteredTypesInitialSize() {
        assertEquals(10, instance.getRegisteredTypes().size());
    }

    @Test
    public void testGetRegisteredTypesTypeCheck() {
        assertFalse(instance.getRegisteredTypes() instanceof SimpleList);
    }

    @Test
    public void testGetRegisteredTypesAfterRegisterEmpty() {
        instance.registerType("Cuaderno");
        assertTrue(instance.getRegisteredTypes().isEmpty());
    }

    @Test
    public void testGetRegisteredTypesDirectModification() {
        assertDoesNotThrow(() -> instance.getRegisteredTypes().add(null));
        assertEquals(0, instance.getRegisteredTypes().size());
    }

    @Test
    public void testGetRegisteredTypesFirstElement() {
        instance.registerType("Cuaderno");
        assertNull(instance.getRegisteredTypes().next());
    }

    @Test
    public void testGetRegisteredTypesStringComparison() {
        assertEquals("[]", instance.getRegisteredTypes().toString());
    }

    @Test
    public void testGetRegisteredTypesResetIteration() {
        instance.registerType("Cuaderno");
        assertFalse(instance.getRegisteredTypes().hasNext());
    }
}