/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espe.legalsync.model;

import java.time.LocalDate;
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
public class CaseFileTest {

    private Client client;
    private Defendant defendant;
    private Lawyer lawyer;

    public CaseFileTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        client = new Client(1, "Maria Lopez", "1712345678", "0991234567", "mlopez@mail.com", "Quito");
        defendant = new Defendant(1, "Jorge Salazar", "1723456789", "0987654321", "Sangolqui");
        lawyer = new Lawyer(2, "Carlos Mena", "cmena", "lawyer123", Role.LAWYER, "Litigation Lawyer", "LIC-0102");
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of register method, of class CaseFile.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        CaseFile instance = new CaseFile(1, "17230-2026-00101", "Breach of contract", client, defendant, lawyer);
        boolean expResult = true;
        boolean result = instance.register();
        assertEquals(expResult, result);
    }

    /**
     * Test of register method without an assigned lawyer, of class CaseFile.
     */
    @Test
    public void testRegisterWithoutLawyer() {
        System.out.println("register without lawyer");
        CaseFile instance = new CaseFile(1, "17230-2026-00101", "Breach of contract", client, defendant, null);
        boolean expResult = false;
        boolean result = instance.register();
        assertEquals(expResult, result);
    }

    /**
     * Test of register method with a client with a wrong id number, of class CaseFile.
     */
    @Test
    public void testRegisterInvalidClient() {
        System.out.println("register invalid client");
        Client badClient = new Client(2, "Pedro Andrade", "123", "", "", "");
        CaseFile instance = new CaseFile(1, "17230-2026-00101", "Breach of contract", badClient, defendant, lawyer);
        boolean expResult = false;
        boolean result = instance.register();
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class CaseFile.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        CaseFile instance = new CaseFile(1, "17230-2026-00101", "Breach of contract", client, defendant, lawyer);
        instance.setStatus(CaseStatus.CLOSED);
        boolean expResult = false;
        boolean result = instance.update();
        assertEquals(expResult, result);
    }

    /**
     * Test of update method with an open case, of class CaseFile.
     */
    @Test
    public void testUpdateOpenCase() {
        System.out.println("update open case");
        CaseFile instance = new CaseFile(1, "17230-2026-00101", "Breach of contract", client, defendant, lawyer);
        boolean expResult = true;
        boolean result = instance.update();
        assertEquals(expResult, result);
    }

    /**
     * Test of update method with a suspended case, of class CaseFile.
     */
    @Test
    public void testUpdateSuspendedCase() {
        System.out.println("update suspended case");
        CaseFile instance = new CaseFile(1, "17230-2026-00101", "Breach of contract", client, defendant, lawyer);
        instance.setStatus(CaseStatus.SUSPENDED);
        boolean expResult = false;
        boolean result = instance.update();
        assertEquals(expResult, result);
    }

    /**
     * Test of validateMandatoryFields method, of class CaseFile.
     */
    @Test
    public void testValidateMandatoryFields() {
        System.out.println("validateMandatoryFields");
        CaseFile instance = new CaseFile(1, "", "Breach of contract", client, defendant, null);
        boolean expResult = false;
        boolean result = instance.validateMandatoryFields();
        assertEquals(expResult, result);
    }

    /**
     * Test of validateMandatoryFields method with a future opening date, of class CaseFile.
     */
    @Test
    public void testValidateMandatoryFieldsFutureDate() {
        System.out.println("validateMandatoryFields future date");
        CaseFile instance = new CaseFile(1, "17230-2026-00101", "Breach of contract", client, defendant, lawyer);
        instance.setOpeningDate(LocalDate.now().plusDays(1));
        boolean expResult = false;
        boolean result = instance.validateMandatoryFields();
        assertEquals(expResult, result);
    }

    /**
     * Test of addDocument method, of class CaseFile.
     */
    @Test
    public void testAddDocument() {
        System.out.println("addDocument");
        Document document = new Document(1, "Contract", "", "C:\\cases\\contract.pdf");
        CaseFile instance = new CaseFile(1, "17230-2026-00101", "Breach of contract", client, defendant, lawyer);
        instance.addDocument(document);
        assertEquals(1, instance.getDocuments().size());
        assertEquals("PDF", document.getType());
    }

    /**
     * Test of addDocument method with a not allowed extension, of class CaseFile.
     */
    @Test
    public void testAddDocumentInvalidExtension() {
        System.out.println("addDocument invalid extension");
        Document document = new Document(2, "Photo", "", "C:\\cases\\photo.jpg");
        CaseFile instance = new CaseFile(1, "17230-2026-00101", "Breach of contract", client, defendant, lawyer);
        instance.addDocument(document);
        assertEquals(0, instance.getDocuments().size());
    }

    /**
     * Test of addDocument method with a null document, of class CaseFile.
     */
    @Test
    public void testAddDocumentNull() {
        System.out.println("addDocument null");
        CaseFile instance = new CaseFile(1, "17230-2026-00101", "Breach of contract", client, defendant, lawyer);
        instance.addDocument(null);
        assertEquals(0, instance.getDocuments().size());
    }

    /**
     * Test of addEvidence method, of class CaseFile.
     */
    @Test
    public void testAddEvidence() {
        System.out.println("addEvidence");
        Evidence evidence = new Evidence(1, "Photo of the property", "Photo", "C:\\cases\\photo.jpg",
                LocalDate.now().plusDays(3));
        CaseFile instance = new CaseFile(1, "17230-2026-00101", "Breach of contract", client, defendant, lawyer);
        instance.addEvidence(evidence);
        assertEquals(0, instance.getEvidences().size());
    }

    /**
     * Test of addEvidence method with a valid evidence, of class CaseFile.
     */
    @Test
    public void testAddEvidenceValid() {
        System.out.println("addEvidence valid");
        Evidence evidence = new Evidence(2, "Signed receipt", "Document", "C:\\cases\\receipt.pdf",
                LocalDate.now().minusDays(2));
        CaseFile instance = new CaseFile(1, "17230-2026-00101", "Breach of contract", client, defendant, lawyer);
        instance.addEvidence(evidence);
        assertEquals(1, instance.getEvidences().size());
    }

    /**
     * Test of addDeadline method, of class CaseFile.
     */
    @Test
    public void testAddDeadline() {
        System.out.println("addDeadline");
        Deadline deadline = new Deadline(1, 0, "Preliminary hearing", "Hearing", LocalDate.now().plusDays(10), 5);
        CaseFile instance = new CaseFile(7, "17230-2026-00101", "Breach of contract", client, defendant, lawyer);
        instance.addDeadline(deadline);
        assertEquals(1, instance.getDeadlines().size());
        assertEquals(7, deadline.getCaseId());
    }

    /**
     * Test of addDeadline method without a target date, of class CaseFile.
     */
    @Test
    public void testAddDeadlineWithoutDate() {
        System.out.println("addDeadline without date");
        Deadline deadline = new Deadline(2, 0, "Appeal", "Appeal", null, 3);
        CaseFile instance = new CaseFile(1, "17230-2026-00101", "Breach of contract", client, defendant, lawyer);
        instance.addDeadline(deadline);
        assertEquals(0, instance.getDeadlines().size());
    }

    /**
     * Test of getDetails method, of class CaseFile.
     */
    @Test
    public void testGetDetails() {
        System.out.println("getDetails");
        CaseFile instance = new CaseFile(1, "17230-2026-00101", "Breach of contract", client, defendant, null);
        String result = instance.getDetails();
        assertTrue(result.contains("17230-2026-00101"));
    }

    /**
     * Test of the default status of a new case, of class CaseFile.
     */
    @Test
    public void testDefaultStatus() {
        System.out.println("default status");
        CaseFile instance = new CaseFile(1, "17230-2026-00101", "Breach of contract", client, defendant, lawyer);
        CaseStatus expResult = CaseStatus.OPEN;
        CaseStatus result = instance.getStatus();
        assertEquals(expResult, result);
    }

}
