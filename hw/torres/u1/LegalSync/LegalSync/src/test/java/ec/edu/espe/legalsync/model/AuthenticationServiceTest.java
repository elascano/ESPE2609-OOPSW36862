/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espe.legalsync.model;

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
public class AuthenticationServiceTest {

    public AuthenticationServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of authenticate method, of class AuthenticationService.
     */
    @Test
    public void testAuthenticate() {
        System.out.println("authenticate");
        AuthenticationService instance = new AuthenticationService("data/credentials.txt", 3);
        instance.loadCredentials();
        User result = instance.authenticate("atorres", "partner123");
        assertNotNull(result);
        assertEquals(Role.PARTNER, result.getRole());
    }

    /**
     * Test of maskPassword method, of class AuthenticationService.
     */
    @Test
    public void testMaskPassword() {
        System.out.println("maskPassword");
        String input = "partner123";
        AuthenticationService instance = new AuthenticationService("data/credentials.txt", 3);
        String expResult = "**********";
        String result = instance.maskPassword(input);
        assertEquals(expResult, result);
    }

    /**
     * Test of maskPassword method with a null password, of class AuthenticationService.
     */
    @Test
    public void testMaskPasswordNull() {
        System.out.println("maskPassword null");
        String input = null;
        AuthenticationService instance = new AuthenticationService("data/credentials.txt", 3);
        String expResult = "";
        String result = instance.maskPassword(input);
        assertEquals(expResult, result);
    }

    /**
     * Test of maskPassword method with an empty password, of class AuthenticationService.
     */
    @Test
    public void testMaskPasswordEmpty() {
        System.out.println("maskPassword empty");
        AuthenticationService instance = new AuthenticationService("data/credentials.txt", 3);
        String expResult = "";
        String result = instance.maskPassword("");
        assertEquals(expResult, result);
    }

    /**
     * Test of authenticate method with a wrong password, of class AuthenticationService.
     */
    @Test
    public void testAuthenticateWrongPassword() {
        System.out.println("authenticate wrong password");
        AuthenticationService instance = new AuthenticationService("data/credentials.txt", 3);
        instance.loadCredentials();
        User result = instance.authenticate("atorres", "wrongPassword");
        assertNull(result);
    }

    /**
     * Test of authenticate method with a legal assistant, of class AuthenticationService.
     */
    @Test
    public void testAuthenticateLegalAssistant() {
        System.out.println("authenticate legal assistant");
        AuthenticationService instance = new AuthenticationService("data/credentials.txt", 3);
        instance.loadCredentials();
        User result = instance.authenticate("lparedes", "assist123");
        assertNotNull(result);
        assertEquals(Role.LEGAL_ASSISTANT, result.getRole());
    }

    /**
     * Test of authenticate method without loading the credentials, of class AuthenticationService.
     */
    @Test
    public void testAuthenticateWithoutLoadingCredentials() {
        System.out.println("authenticate without loading credentials");
        AuthenticationService instance = new AuthenticationService("data/credentials.txt", 3);
        User result = instance.authenticate("atorres", "partner123");
        assertNull(result);
    }

    /**
     * Test of authenticate method with spaces around the username, of class AuthenticationService.
     */
    @Test
    public void testAuthenticateUsernameWithSpaces() {
        System.out.println("authenticate username with spaces");
        AuthenticationService instance = new AuthenticationService("data/credentials.txt", 3);
        instance.loadCredentials();
        User result = instance.authenticate(" atorres ", "partner123");
        assertNotNull(result);
    }

    /**
     * Test of loadCredentials method, of class AuthenticationService.
     */
    @Test
    public void testLoadCredentials() {
        System.out.println("loadCredentials");
        AuthenticationService instance = new AuthenticationService("data/credentials.txt", 3);
        boolean expResult = true;
        boolean result = instance.loadCredentials();
        assertEquals(expResult, result);
        assertEquals(3, instance.getLawyers().size());
        assertEquals(1, instance.getAssistants().size());
    }

    /**
     * Test of findLawyer method, of class AuthenticationService.
     */
    @Test
    public void testFindLawyer() {
        System.out.println("findLawyer");
        AuthenticationService instance = new AuthenticationService("data/credentials.txt", 3);
        instance.loadCredentials();
        Lawyer result = instance.findLawyer("cmena");
        assertNotNull(result);
        assertEquals("Carlos Mena", result.getName());
    }

    /**
     * Test of findUser method with a legal assistant, of class AuthenticationService.
     */
    @Test
    public void testFindUserAssistant() {
        System.out.println("findUser assistant");
        AuthenticationService instance = new AuthenticationService("data/credentials.txt", 3);
        instance.loadCredentials();
        User result = instance.findUser("lparedes");
        assertNotNull(result);
        assertEquals("Luis Paredes", result.getName());
    }

    /**
     * Test of findUser method with a user that does not exist, of class AuthenticationService.
     */
    @Test
    public void testFindUserNotExisting() {
        System.out.println("findUser not existing");
        AuthenticationService instance = new AuthenticationService("data/credentials.txt", 3);
        instance.loadCredentials();
        User result = instance.findUser("jperez");
        assertNull(result);
    }

    /**
     * Test of nextUserId method, of class AuthenticationService.
     */
    @Test
    public void testNextUserId() {
        System.out.println("nextUserId");
        AuthenticationService instance = new AuthenticationService("data/credentials.txt", 3);
        instance.loadCredentials();
        int expResult = 5;
        int result = instance.nextUserId();
        assertEquals(expResult, result);
    }

    /**
     * Test of loadCredentials method, checks the supervisor of the assistant, of class AuthenticationService.
     */
    @Test
    public void testLoadCredentialsAssistantSupervisor() {
        System.out.println("loadCredentials assistant supervisor");
        AuthenticationService instance = new AuthenticationService("data/credentials.txt", 3);
        instance.loadCredentials();
        LegalAssistant assistant = instance.findAssistant("lparedes");
        assertNotNull(assistant.getSupervisor());
        assertEquals("cmena", assistant.getSupervisor().getUsername());
    }

}
