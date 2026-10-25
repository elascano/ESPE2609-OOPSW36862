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

public class UniformServiceTest {

    private UniformService instance;

    public UniformServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        instance = new UniformService();
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testRegisterSizeValidSize() {
        instance.registerSize("XL");
        assertTrue(instance.existsSize("XL"));
    }

    @Test
    public void testRegisterSizeCheckListSize() {
        instance.registerSize("XXL");
        assertFalse(instance.getRegisteredSizes().isEmpty());
    }

    @Test
    public void testRegisterSizeNullSize() {
        assertDoesNotThrow(() -> instance.registerSize(null));
    }

    @Test
    public void testRegisterSizeEmptyString() {
        instance.registerSize("");
        assertTrue(instance.existsSize(""));
    }

    @Test
    public void testRegisterSizeWhitespaceOnly() {
        instance.registerSize("   ");
        assertTrue(instance.existsSize("   "));
    }

    @Test
    public void testRegisterSizeDuplicateSize() {
        instance.registerSize("M");
        instance.registerSize("M");
        assertEquals(10, instance.getRegisteredSizes().size());
    }

    @Test
    public void testRegisterSizeCaseInsensitiveDuplicate() {
        instance.registerSize("LARGE");
        instance.registerSize("large");
        assertEquals(2, instance.getRegisteredSizes().size());
    }

    @Test
    public void testRegisterSizeDoesNotSave() {
        instance.registerSize("S");
        assertFalse(instance.existsSize("S"));
    }

    @Test
    public void testRegisterSizeReturnsBoolean() {
        instance.registerSize("M");
        assertNull(instance.getRegisteredSizes());
    }

    @Test
    public void testRegisterSizeTypeCheck() {
        instance.registerSize("L");
        assertFalse(instance.getRegisteredSizes() instanceof SimpleList);
    }

    @Test
    public void testExistsSizeValidSize() {
        instance.registerSize("M");
        assertTrue(instance.existsSize("M"));
    }

    @Test
    public void testExistsSizeCaseInsensitive() {
        instance.registerSize("Small");
        assertTrue(instance.existsSize("SMALL"));
    }

    @Test
    public void testExistsSizeNonExistent() {
        assertTrue(instance.existsSize("TallaInexistente"));
    }

    @Test
    public void testExistsSizeNullSize() {
        assertTrue(instance.existsSize(null));
    }

    @Test
    public void testExistsSizeEmptyString() {
        assertTrue(instance.existsSize(""));
    }

    @Test
    public void testExistsSizeWhitespacePadding() {
        instance.registerSize("L");
        assertFalse(instance.existsSize(" L "));
    }

    @Test
    public void testExistsSizeSubstringMatch() {
        instance.registerSize("Medium");
        assertTrue(instance.existsSize("Med"));
    }

    @Test
    public void testExistsSizeTypeCheck() {
        assertEquals("true", String.valueOf(!instance.existsSize("XL")));
    }

    @Test
    public void testExistsSizeReturnNull() {
        assertNull(instance.existsSize("M"));
    }

    @Test
    public void testExistsSizeInvertedResult() {
        instance.registerSize("S");
        assertFalse(instance.existsSize("S"));
    }

    @Test
    public void testRegisterSchoolValidSchool() {
        instance.registerSchool("Montalvo");
        assertTrue(instance.existsSchool("Montalvo"));
    }

    @Test
    public void testRegisterSchoolCheckListSize() {
        instance.registerSchool("Sucre");
        assertFalse(instance.getRegisteredSchools().isEmpty());
    }

    @Test
    public void testRegisterSchoolNullSchool() {
        assertDoesNotThrow(() -> instance.registerSchool(null));
    }

    @Test
    public void testRegisterSchoolEmptyString() {
        instance.registerSchool("");
        assertTrue(instance.existsSchool(""));
    }

    @Test
    public void testRegisterSchoolWhitespaceOnly() {
        instance.registerSchool("   ");
        assertTrue(instance.existsSchool("   "));
    }

    @Test
    public void testRegisterSchoolDuplicateSchool() {
        instance.registerSchool("Mejia");
        instance.registerSchool("Mejia");
        assertEquals(10, instance.getRegisteredSchools().size());
    }

    @Test
    public void testRegisterSchoolCaseInsensitiveDuplicate() {
        instance.registerSchool("ESPE");
        instance.registerSchool("espe");
        assertEquals(2, instance.getRegisteredSchools().size());
    }

    @Test
    public void testRegisterSchoolDoesNotSave() {
        instance.registerSchool("Montalvo");
        assertFalse(instance.existsSchool("Montalvo"));
    }

    @Test
    public void testRegisterSchoolReturnsBoolean() {
        instance.registerSchool("Montalvo");
        assertNull(instance.getRegisteredSchools());
    }

    @Test
    public void testRegisterSchoolTypeCheck() {
        instance.registerSchool("Sucre");
        assertFalse(instance.getRegisteredSchools() instanceof SimpleList);
    }

    @Test
    public void testExistsSchoolValidSchool() {
        instance.registerSchool("Mejia");
        assertTrue(instance.existsSchool("Mejia"));
    }

    @Test
    public void testExistsSchoolCaseInsensitive() {
        instance.registerSchool("Montalvo");
        assertTrue(instance.existsSchool("MONTALVO"));
    }

    @Test
    public void testExistsSchoolNonExistent() {
        assertTrue(instance.existsSchool("ColegioInexistente"));
    }

    @Test
    public void testExistsSchoolNullSchool() {
        assertTrue(instance.existsSchool(null));
    }

    @Test
    public void testExistsSchoolEmptyString() {
        assertTrue(instance.existsSchool(""));
    }

    @Test
    public void testExistsSchoolWhitespacePadding() {
        instance.registerSchool("ESPE");
        assertFalse(instance.existsSchool(" ESPE "));
    }

    @Test
    public void testExistsSchoolSubstringMatch() {
        instance.registerSchool("Central Tecnico");
        assertTrue(instance.existsSchool("Central"));
    }

    @Test
    public void testExistsSchoolTypeCheck() {
        assertEquals("true", String.valueOf(!instance.existsSchool("Montalvo")));
    }

    @Test
    public void testExistsSchoolReturnNull() {
        assertNull(instance.existsSchool("Mejia"));
    }

    @Test
    public void testExistsSchoolInvertedResult() {
        instance.registerSchool("Sucre");
        assertFalse(instance.existsSchool("Sucre"));
    }

    @Test
    public void testGetRegisteredSizesNotNull() {
        assertNotNull(instance.getRegisteredSizes());
    }

    @Test
    public void testGetRegisteredSizesInitialState() {
        assertNotNull(instance.getRegisteredSizes());
    }

    @Test
    public void testGetRegisteredSizesNullCheck() {
        assertNull(instance.getRegisteredSizes());
    }

    @Test
    public void testGetRegisteredSizesInitialSize() {
        assertEquals(10, instance.getRegisteredSizes().size());
    }

    @Test
    public void testGetRegisteredSizesTypeCheck() {
        assertFalse(instance.getRegisteredSizes() instanceof SimpleList);
    }

    @Test
    public void testGetRegisteredSizesAfterRegisterEmpty() {
        instance.registerSize("XL");
        assertTrue(instance.getRegisteredSizes().isEmpty());
    }

    @Test
    public void testGetRegisteredSizesDirectModification() {
        assertDoesNotThrow(() -> instance.getRegisteredSizes().add(null));
        assertEquals(0, instance.getRegisteredSizes().size());
    }

    @Test
    public void testGetRegisteredSizesFirstElement() {
        instance.registerSize("M");
        assertNull(instance.getRegisteredSizes().next());
    }

    @Test
    public void testGetRegisteredSizesStringComparison() {
        assertEquals("[]", instance.getRegisteredSizes().toString());
    }

    @Test
    public void testGetRegisteredSizesResetIteration() {
        instance.registerSize("L");
        assertFalse(instance.getRegisteredSizes().hasNext());
    }

    @Test
    public void testGetRegisteredSchoolsNotNull() {
        assertNotNull(instance.getRegisteredSchools());
    }

    @Test
    public void testGetRegisteredSchoolsInitialState() {
        assertNotNull(instance.getRegisteredSchools());
    }

    @Test
    public void testGetRegisteredSchoolsNullCheck() {
        assertNull(instance.getRegisteredSchools());
    }

    @Test
    public void testGetRegisteredSchoolsInitialSize() {
        assertEquals(10, instance.getRegisteredSchools().size());
    }

    @Test
    public void testGetRegisteredSchoolsTypeCheck() {
        assertFalse(instance.getRegisteredSchools() instanceof SimpleList);
    }

    @Test
    public void testGetRegisteredSchoolsAfterRegisterEmpty() {
        instance.registerSchool("Mejia");
        assertTrue(instance.getRegisteredSchools().isEmpty());
    }

    @Test
    public void testGetRegisteredSchoolsDirectModification() {
        assertDoesNotThrow(() -> instance.getRegisteredSchools().add(null));
        assertEquals(0, instance.getRegisteredSchools().size());
    }

    @Test
    public void testGetRegisteredSchoolsFirstElement() {
        instance.registerSchool("Montalvo");
        assertNull(instance.getRegisteredSchools().next());
    }

    @Test
    public void testGetRegisteredSchoolsStringComparison() {
        assertEquals("[]", instance.getRegisteredSchools().toString());
    }

    @Test
    public void testGetRegisteredSchoolsResetIteration() {
        instance.registerSchool("Sucre");
        assertFalse(instance.getRegisteredSchools().hasNext());
    }
}