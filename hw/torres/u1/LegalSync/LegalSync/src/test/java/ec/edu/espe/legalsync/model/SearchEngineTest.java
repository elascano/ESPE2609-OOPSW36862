/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espe.legalsync.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author CodeLex, @ESPE
 */
public class SearchEngineTest {

    private List<CaseFile> caseFiles;

    public SearchEngineTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        Lawyer lawyer = new Lawyer(2, "Carlos Mena", "cmena", "lawyer123", Role.LAWYER, "Lawyer", "LIC-0102");
        Client maria = new Client(1, "Maria Fernanda Lopez", "1712345678", "", "", "");
        Client pedro = new Client(2, "Pedro Andrade", "1709876543", "", "", "");
        Defendant defendant = new Defendant(1, "Jorge Salazar", "", "", "");
        caseFiles = new ArrayList<>();
        caseFiles.add(new CaseFile(1, "17230-2026-00101", "Lease contract", maria, defendant, lawyer));
        caseFiles.add(new CaseFile(2, "17204-2026-00230", "Wrongful dismissal", pedro, defendant, lawyer));
        caseFiles.add(new CaseFile(3, "17230-2026-00312", "Child support", maria, defendant, lawyer));
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of searchByClientName method, of class SearchEngine.
     */
    @Test
    public void testSearchByClientName() {
        System.out.println("searchByClientName");
        String clientName = "LOPEZ";
        SearchEngine instance = new SearchEngine();
        instance.loadData(caseFiles);
        int expResult = 2;
        List<CaseFile> result = instance.searchByClientName(clientName);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of searchByClientName method with an empty name, of class SearchEngine.
     */
    @Test
    public void testSearchByClientNameEmpty() {
        System.out.println("searchByClientName empty");
        String clientName = "";
        SearchEngine instance = new SearchEngine();
        instance.loadData(caseFiles);
        int expResult = 0;
        List<CaseFile> result = instance.searchByClientName(clientName);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of searchByClientName method with a null name, of class SearchEngine.
     */
    @Test
    public void testSearchByClientNameNull() {
        System.out.println("searchByClientName null");
        SearchEngine instance = new SearchEngine();
        instance.loadData(caseFiles);
        List<CaseFile> result = instance.searchByClientName(null);
        assertTrue(result.isEmpty());
    }

    /**
     * Test of searchByClientName method with a name that does not exist, of class SearchEngine.
     */
    @Test
    public void testSearchByClientNameNoMatch() {
        System.out.println("searchByClientName no match");
        SearchEngine instance = new SearchEngine();
        instance.loadData(caseFiles);
        int expResult = 0;
        List<CaseFile> result = instance.searchByClientName("Gomez");
        assertEquals(expResult, result.size());
    }

    /**
     * Test of searchByClientName method with spaces around the name, of class SearchEngine.
     */
    @Test
    public void testSearchByClientNameWithSpaces() {
        System.out.println("searchByClientName with spaces");
        SearchEngine instance = new SearchEngine();
        instance.loadData(caseFiles);
        List<CaseFile> result = instance.searchByClientName("   Pedro   ");
        assertEquals(1, result.size());
        assertEquals("17204-2026-00230", result.get(0).getDocketNumber());
    }

    /**
     * Test of searchByClientName method with part of the first name, of class SearchEngine.
     */
    @Test
    public void testSearchByClientNamePartial() {
        System.out.println("searchByClientName partial");
        SearchEngine instance = new SearchEngine();
        instance.loadData(caseFiles);
        int expResult = 2;
        List<CaseFile> result = instance.searchByClientName("mar");
        assertEquals(expResult, result.size());
    }

    /**
     * Test of searchByClientName method without loaded data, of class SearchEngine.
     */
    @Test
    public void testSearchByClientNameWithoutData() {
        System.out.println("searchByClientName without data");
        SearchEngine instance = new SearchEngine();
        List<CaseFile> result = instance.searchByClientName("Lopez");
        assertEquals(0, result.size());
    }

    /**
     * Test of getLastQuery method after a search, of class SearchEngine.
     */
    @Test
    public void testGetLastQuery() {
        System.out.println("getLastQuery");
        SearchEngine instance = new SearchEngine();
        instance.loadData(caseFiles);
        instance.searchByClientName("  Andrade ");
        String expResult = "Andrade";
        String result = instance.getLastQuery();
        assertEquals(expResult, result);
    }

    /**
     * Test of getResults method, of class SearchEngine.
     */
    @Test
    public void testGetResults() {
        System.out.println("getResults");
        SearchEngine instance = new SearchEngine();
        instance.loadData(caseFiles);
        instance.searchByClientName("Lopez");
        List<CaseFile> result = instance.getResults();
        assertEquals(2, result.size());
    }

    /**
     * Test of loadData method, of class SearchEngine.
     */
    @Test
    public void testLoadData() {
        System.out.println("loadData");
        SearchEngine instance = new SearchEngine();
        instance.loadData(caseFiles);
        int expResult = 3;
        int result = instance.getCaseFiles().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of findByDocketNumber method, of class SearchEngine.
     */
    @Test
    public void testFindByDocketNumber() {
        System.out.println("findByDocketNumber");
        SearchEngine instance = new SearchEngine();
        instance.loadData(caseFiles);
        CaseFile result = instance.findByDocketNumber("17204-2026-00230");
        assertEquals("Pedro Andrade", result.getClient().getName());
    }

    /**
     * Test of findByDocketNumber method with a docket number that does not exist, of class SearchEngine.
     */
    @Test
    public void testFindByDocketNumberNotFound() {
        System.out.println("findByDocketNumber not found");
        SearchEngine instance = new SearchEngine();
        instance.loadData(caseFiles);
        CaseFile result = instance.findByDocketNumber("99999-2026-00001");
        assertNull(result);
    }

    /**
     * Test of findByDocketNumber method with spaces around the docket number, of class SearchEngine.
     */
    @Test
    public void testFindByDocketNumberWithSpaces() {
        System.out.println("findByDocketNumber with spaces");
        SearchEngine instance = new SearchEngine();
        instance.loadData(caseFiles);
        CaseFile result = instance.findByDocketNumber(" 17230-2026-00312 ");
        assertNotNull(result);
    }

}
