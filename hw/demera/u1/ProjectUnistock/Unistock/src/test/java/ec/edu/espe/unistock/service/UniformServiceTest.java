package ec.edu.espe.unistock.service;

import ec.edu.espe.unistock.structure.SimpleList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class UniformServiceTest {

    private static final String SIZES_FILE = "sizes.json";
    private static final String SCHOOLS_FILE = "schools.json";
    private UniformService uniformService;

    @BeforeEach
    void setUp() {
        deleteFiles();
        uniformService = new UniformService();
    }

    @AfterEach
    void tearDown() {
        deleteFiles();
    }

    private void deleteFiles() {
        File szFile = new File(SIZES_FILE);
        if (szFile.exists()) {
            szFile.delete();
        }
        File scFile = new File(SCHOOLS_FILE);
        if (scFile.exists()) {
            scFile.delete();
        }
    }

    @Test
    void testConstructorInitializesEmptyListsWhenFilesMissing() {
        assertNotNull(uniformService.getRegisteredSizes());
        assertNotNull(uniformService.getRegisteredSchools());
        assertTrue(uniformService.getRegisteredSizes().isEmpty());
        assertTrue(uniformService.getRegisteredSchools().isEmpty());
    }

    @Test
    void testConstructorLoadsExistingSizesFromFile() {
        uniformService.registerSize("M");
        UniformService newService = new UniformService();
        assertEquals(1, newService.getRegisteredSizes().size());
        assertTrue(newService.existsSize("M"));
    }

    @Test
    void testConstructorLoadsExistingSchoolsFromFile() {
        uniformService.registerSchool("Colegio San Gabriel");
        UniformService newService = new UniformService();
        assertEquals(1, newService.getRegisteredSchools().size());
        assertTrue(newService.existsSchool("Colegio San Gabriel"));
    }

    @Test
    void testConstructorHandlesSizeAndSchoolPersistenceTogether() {
        uniformService.registerSize("L");
        uniformService.registerSchool("Colegio Benalcazar");
        UniformService newService = new UniformService();
        assertTrue(newService.existsSize("L"));
        assertTrue(newService.existsSchool("Colegio Benalcazar"));
    }

    @Test
    void testConstructorIndependentFileLoading() {
        uniformService.registerSize("S");
        UniformService newService = new UniformService();
        assertTrue(newService.getRegisteredSchools().isEmpty());
        assertFalse(newService.getRegisteredSizes().isEmpty());
    }

    @Test
    void testConstructorMultipleSizesLoaded() {
        uniformService.registerSize("S");
        uniformService.registerSize("M");
        UniformService newService = new UniformService();
        assertEquals(2, newService.getRegisteredSizes().size());
    }

    @Test
    void testConstructorMultipleSchoolsLoaded() {
        uniformService.registerSchool("Colegio San Gabriel");
        uniformService.registerSchool("Instituto Nacional Mejia");
        UniformService newService = new UniformService();
        assertEquals(2, newService.getRegisteredSchools().size());
    }

    @Test
    void testConstructorCreatesServiceInstance() {
        assertNotNull(uniformService);
    }

    @Test
    void testConstructorListsAreNotSameInstance() {
        assertNotSame(uniformService.getRegisteredSizes(), uniformService.getRegisteredSchools());
    }

    @Test
    void testConstructorPreservesDataIntegrity() {
        uniformService.registerSize("XL");
        uniformService.registerSchool("U.E. 24 de Mayo");
        UniformService service2 = new UniformService();
        assertEquals(1, service2.getRegisteredSizes().size());
        assertEquals(1, service2.getRegisteredSchools().size());
    }

    @Test
    void testRegisterSizeValidString() {
        uniformService.registerSize("M");
        assertTrue(uniformService.existsSize("M"));
    }

    @Test
    void testRegisterSizeConvertsToUpperCase() {
        uniformService.registerSize("s");
        assertTrue(uniformService.existsSize("S"));
        assertEquals("S", uniformService.getRegisteredSizes().next());
    }

    @Test
    void testRegisterSizeNullIgnored() {
        uniformService.registerSize(null);
        assertTrue(uniformService.getRegisteredSizes().isEmpty());
    }

    @Test
    void testRegisterSizeEmptyIgnored() {
        uniformService.registerSize("");
        assertTrue(uniformService.getRegisteredSizes().isEmpty());
    }

    @Test
    void testRegisterSizeBlankIgnored() {
        uniformService.registerSize("   ");
        assertTrue(uniformService.getRegisteredSizes().isEmpty());
    }

    @Test
    void testRegisterSizeDuplicateIgnored() {
        uniformService.registerSize("M");
        uniformService.registerSize("M");
        assertEquals(1, uniformService.getRegisteredSizes().size());
    }

    @Test
    void testRegisterSizeCaseInsensitiveDuplicateIgnored() {
        uniformService.registerSize("m");
        uniformService.registerSize("M");
        assertEquals(1, uniformService.getRegisteredSizes().size());
    }

    @Test
    void testRegisterSizeTrimsInput() {
        uniformService.registerSize("  L  ");
        assertTrue(uniformService.existsSize("L"));
        assertEquals("L", uniformService.getRegisteredSizes().next());
    }

    @Test
    void testRegisterSizePersistsToFile() {
        uniformService.registerSize("32");
        File file = new File(SIZES_FILE);
        assertTrue(file.exists() && file.length() > 0);
    }

    @Test
    void testRegisterSizeMultipleSizes() {
        uniformService.registerSize("S");
        uniformService.registerSize("M");
        uniformService.registerSize("L");
        assertEquals(3, uniformService.getRegisteredSizes().size());
    }

    @Test
    void testExistsSizeReturnsTrueWhenPresent() {
        uniformService.registerSize("30");
        assertTrue(uniformService.existsSize("30"));
    }

    @Test
    void testExistsSizeReturnsFalseWhenAbsent() {
        assertFalse(uniformService.existsSize("30"));
    }

    @Test
    void testExistsSizeNullReturnsFalse() {
        assertFalse(uniformService.existsSize(null));
    }

    @Test
    void testExistsSizeCaseInsensitiveLower() {
        uniformService.registerSize("XL");
        assertTrue(uniformService.existsSize("xl"));
    }

    @Test
    void testExistsSizeCaseInsensitiveUpper() {
        uniformService.registerSize("xl");
        assertTrue(uniformService.existsSize("XL"));
    }

    @Test
    void testExistsSizeTrimsInput() {
        uniformService.registerSize("M");
        assertTrue(uniformService.existsSize("  M  "));
    }

    @Test
    void testExistsSizeEmptyStringReturnsFalse() {
        uniformService.registerSize("M");
        assertFalse(uniformService.existsSize(""));
    }

    @Test
    void testExistsSizeBlankStringReturnsFalse() {
        uniformService.registerSize("M");
        assertFalse(uniformService.existsSize("   "));
    }

    @Test
    void testExistsSizeMultipleSizesSearch() {
        uniformService.registerSize("30");
        uniformService.registerSize("32");
        assertTrue(uniformService.existsSize("32"));
    }

    @Test
    void testExistsSizeConsistentCall() {
        uniformService.registerSize("S");
        assertTrue(uniformService.existsSize("S"));
        assertTrue(uniformService.existsSize("S"));
    }

    @Test
    void testRegisterSchoolValidString() {
        uniformService.registerSchool("Colegio San Gabriel");
        assertTrue(uniformService.existsSchool("Colegio San Gabriel"));
    }

    @Test
    void testRegisterSchoolNullIgnored() {
        uniformService.registerSchool(null);
        assertTrue(uniformService.getRegisteredSchools().isEmpty());
    }

    @Test
    void testRegisterSchoolEmptyIgnored() {
        uniformService.registerSchool("");
        assertTrue(uniformService.getRegisteredSchools().isEmpty());
    }

    @Test
    void testRegisterSchoolBlankIgnored() {
        uniformService.registerSchool("   ");
        assertTrue(uniformService.getRegisteredSchools().isEmpty());
    }

    @Test
    void testRegisterSchoolDuplicateIgnored() {
        uniformService.registerSchool("Colegio San Gabriel");
        uniformService.registerSchool("Colegio San Gabriel");
        assertEquals(1, uniformService.getRegisteredSchools().size());
    }

    @Test
    void testRegisterSchoolCaseInsensitiveDuplicateIgnored() {
        uniformService.registerSchool("Colegio San Gabriel");
        uniformService.registerSchool("COLEGIO SAN GABRIEL");
        assertEquals(1, uniformService.getRegisteredSchools().size());
    }

    @Test
    void testRegisterSchoolTrimsInput() {
        uniformService.registerSchool("  Colegio Benalcazar  ");
        assertTrue(uniformService.existsSchool("Colegio Benalcazar"));
        assertEquals("Colegio Benalcazar", uniformService.getRegisteredSchools().next());
    }

    @Test
    void testRegisterSchoolPersistsToFile() {
        uniformService.registerSchool("Instituto Nacional Mejia");
        File file = new File(SCHOOLS_FILE);
        assertTrue(file.exists() && file.length() > 0);
    }

    @Test
    void testRegisterSchoolMultipleSchools() {
        uniformService.registerSchool("San Gabriel");
        uniformService.registerSchool("Benalcazar");
        uniformService.registerSchool("Mejia");
        assertEquals(3, uniformService.getRegisteredSchools().size());
    }

    @Test
    void testRegisterSchoolSpecialCharacters() {
        uniformService.registerSchool("U.E. 24 de Mayo");
        assertTrue(uniformService.existsSchool("U.E. 24 de Mayo"));
    }

    @Test
    void testExistsSchoolReturnsTrueWhenPresent() {
        uniformService.registerSchool("Colegio San Gabriel");
        assertTrue(uniformService.existsSchool("Colegio San Gabriel"));
    }

    @Test
    void testExistsSchoolReturnsFalseWhenAbsent() {
        assertFalse(uniformService.existsSchool("Colegio San Gabriel"));
    }

    @Test
    void testExistsSchoolNullReturnsFalse() {
        assertFalse(uniformService.existsSchool(null));
    }

    @Test
    void testExistsSchoolCaseInsensitiveLower() {
        uniformService.registerSchool("Colegio Benalcazar");
        assertTrue(uniformService.existsSchool("colegio benalcazar"));
    }

    @Test
    void testExistsSchoolCaseInsensitiveUpper() {
        uniformService.registerSchool("Colegio Benalcazar");
        assertTrue(uniformService.existsSchool("COLEGIO BENALCAZAR"));
    }

    @Test
    void testExistsSchoolTrimsInput() {
        uniformService.registerSchool("Instituto Nacional Mejia");
        assertTrue(uniformService.existsSchool("  Instituto Nacional Mejia  "));
    }

    @Test
    void testExistsSchoolEmptyStringReturnsFalse() {
        uniformService.registerSchool("Mejia");
        assertFalse(uniformService.existsSchool(""));
    }

    @Test
    void testExistsSchoolBlankStringReturnsFalse() {
        uniformService.registerSchool("Mejia");
        assertFalse(uniformService.existsSchool("   "));
    }

    @Test
    void testExistsSchoolMultipleSchoolsSearch() {
        uniformService.registerSchool("San Gabriel");
        uniformService.registerSchool("Mejia");
        assertTrue(uniformService.existsSchool("Mejia"));
    }

    @Test
    void testExistsSchoolConsistentCall() {
        uniformService.registerSchool("Benalcazar");
        assertTrue(uniformService.existsSchool("Benalcazar"));
        assertTrue(uniformService.existsSchool("Benalcazar"));
    }

    @Test
    void testGetRegisteredSizesNotNull() {
        assertNotNull(uniformService.getRegisteredSizes());
    }

    @Test
    void testGetRegisteredSizesInitiallyEmpty() {
        assertTrue(uniformService.getRegisteredSizes().isEmpty());
    }

    @Test
    void testGetRegisteredSizesReflectsRegisteredSizes() {
        uniformService.registerSize("M");
        assertEquals(1, uniformService.getRegisteredSizes().size());
    }

    @Test
    void testGetRegisteredSizesSameReference() {
        assertSame(uniformService.getRegisteredSizes(), uniformService.getRegisteredSizes());
    }

    @Test
    void testGetRegisteredSizesAllowsIteration() {
        uniformService.registerSize("M");
        SimpleList<String> sizes = uniformService.getRegisteredSizes();
        sizes.resetIteration();
        assertTrue(sizes.hasNext());
        assertEquals("M", sizes.next());
    }

    @Test
    void testGetRegisteredSizesConsistentCall() {
        assertEquals(uniformService.getRegisteredSizes(), uniformService.getRegisteredSizes());
    }

    @Test
    void testGetRegisteredSizesMultipleSizesCount() {
        uniformService.registerSize("S");
        uniformService.registerSize("M");
        uniformService.registerSize("L");
        assertEquals(3, uniformService.getRegisteredSizes().size());
    }

    @Test
    void testGetRegisteredSizesOrderPreserved() {
        uniformService.registerSize("S");
        uniformService.registerSize("M");
        SimpleList<String> sizes = uniformService.getRegisteredSizes();
        sizes.resetIteration();
        assertEquals("S", sizes.next());
        assertEquals("M", sizes.next());
    }

    @Test
    void testGetRegisteredSizesReturnsSimpleList() {
        assertTrue(uniformService.getRegisteredSizes() instanceof SimpleList);
    }

    @Test
    void testGetRegisteredSizesDoesNotIncludeSchools() {
        uniformService.registerSize("M");
        uniformService.registerSchool("San Gabriel");
        assertEquals(1, uniformService.getRegisteredSizes().size());
    }

    @Test
    void testGetRegisteredSchoolsNotNull() {
        assertNotNull(uniformService.getRegisteredSchools());
    }

    @Test
    void testGetRegisteredSchoolsInitiallyEmpty() {
        assertTrue(uniformService.getRegisteredSchools().isEmpty());
    }

    @Test
    void testGetRegisteredSchoolsReflectsRegisteredSchools() {
        uniformService.registerSchool("San Gabriel");
        assertEquals(1, uniformService.getRegisteredSchools().size());
    }

    @Test
    void testGetRegisteredSchoolsSameReference() {
        assertSame(uniformService.getRegisteredSchools(), uniformService.getRegisteredSchools());
    }

    @Test
    void testGetRegisteredSchoolsAllowsIteration() {
        uniformService.registerSchool("San Gabriel");
        SimpleList<String> schools = uniformService.getRegisteredSchools();
        schools.resetIteration();
        assertTrue(schools.hasNext());
        assertEquals("San Gabriel", schools.next());
    }

    @Test
    void testGetRegisteredSchoolsConsistentCall() {
        assertEquals(uniformService.getRegisteredSchools(), uniformService.getRegisteredSchools());
    }

    @Test
    void testGetRegisteredSchoolsMultipleSchoolsCount() {
        uniformService.registerSchool("San Gabriel");
        uniformService.registerSchool("Benalcazar");
        uniformService.registerSchool("Mejia");
        assertEquals(3, uniformService.getRegisteredSchools().size());
    }

    @Test
    void testGetRegisteredSchoolsOrderPreserved() {
        uniformService.registerSchool("San Gabriel");
        uniformService.registerSchool("Mejia");
        SimpleList<String> schools = uniformService.getRegisteredSchools();
        schools.resetIteration();
        assertEquals("San Gabriel", schools.next());
        assertEquals("Mejia", schools.next());
    }

    @Test
    void testGetRegisteredSchoolsReturnsSimpleList() {
        assertTrue(uniformService.getRegisteredSchools() instanceof SimpleList);
    }

    @Test
    void testGetRegisteredSchoolsDoesNotIncludeSizes() {
        uniformService.registerSchool("San Gabriel");
        uniformService.registerSize("M");
        assertEquals(1, uniformService.getRegisteredSchools().size());
    }
}