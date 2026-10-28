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
public class ClientTest {

    public ClientTest() {
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
     * Test of register method, of class Client.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        Client instance = new Client(1, "Maria Lopez", "1712345678", "0991234567", "mlopez@mail.com", "Quito");
        boolean expResult = true;
        boolean result = instance.register();
        assertEquals(expResult, result);
    }

    /**
     * Test of register method with a wrong id number, of class Client.
     */
    @Test
    public void testRegisterWrongIdNumber() {
        System.out.println("register wrong id number");
        Client instance = new Client(1, "Maria Lopez", "17123", "0991234567", "mlopez@mail.com", "Quito");
        boolean expResult = false;
        boolean result = instance.register();
        assertEquals(expResult, result);
    }

    /**
     * Test of register method with an id number written with a dash, of class Client.
     */
    @Test
    public void testRegisterIdNumberWithDash() {
        System.out.println("register id number with dash");
        Client instance = new Client(1, "Maria Lopez", "171234567-8", "0991234567", "mlopez@mail.com", "Quito");
        boolean expResult = true;
        boolean result = instance.register();
        assertEquals(expResult, result);
    }

    /**
     * Test of register method with a RUC of 13 digits, of class Client.
     */
    @Test
    public void testRegisterRuc() {
        System.out.println("register RUC");
        Client instance = new Client(1, "Maria Lopez", "1712345678001", "0991234567", "mlopez@mail.com", "Quito");
        boolean expResult = true;
        boolean result = instance.register();
        assertEquals(expResult, result);
    }

    /**
     * Test of register method with an id number that has letters, of class Client.
     */
    @Test
    public void testRegisterIdNumberWithLetters() {
        System.out.println("register id number with letters");
        Client instance = new Client(1, "Maria Lopez", "17123A5678", "0991234567", "mlopez@mail.com", "Quito");
        boolean expResult = false;
        boolean result = instance.register();
        assertEquals(expResult, result);
    }

    /**
     * Test of register method with an id number with a wrong check digit, of class Client.
     */
    @Test
    public void testRegisterIdNumberWrongCheckDigit() {
        System.out.println("register id number wrong check digit");
        Client instance = new Client(1, "Maria Lopez", "1234567890", "0991234567", "mlopez@mail.com", "Quito");
        boolean expResult = false;
        boolean result = instance.register();
        assertEquals(expResult, result);
    }

    /**
     * Test of register method with an empty name, of class Client.
     */
    @Test
    public void testRegisterEmptyName() {
        System.out.println("register empty name");
        Client instance = new Client(1, "   ", "1712345678", "0991234567", "mlopez@mail.com", "Quito");
        boolean expResult = false;
        boolean result = instance.register();
        assertEquals(expResult, result);
    }

    /**
     * Test of register method with an email without @, of class Client.
     */
    @Test
    public void testRegisterInvalidEmail() {
        System.out.println("register invalid email");
        Client instance = new Client(1, "Maria Lopez", "1712345678", "0991234567", "mlopez.mail.com", "Quito");
        boolean expResult = false;
        boolean result = instance.register();
        assertEquals(expResult, result);
    }

    /**
     * Test of register method with an email without domain after the @, of class Client.
     */
    @Test
    public void testRegisterEmailWithoutDomain() {
        System.out.println("register email without domain");
        Client instance = new Client(1, "Maria Lopez", "1712345678", "0991234567", "maria.lopez@mail", "Quito");
        boolean expResult = false;
        boolean result = instance.register();
        assertEquals(expResult, result);
    }

    /**
     * Test of register method without email, of class Client.
     */
    @Test
    public void testRegisterWithoutEmail() {
        System.out.println("register without email");
        Client instance = new Client(1, "Maria Lopez", "1712345678", "0991234567", "", "Quito");
        boolean expResult = true;
        boolean result = instance.register();
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class Client.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Client instance = new Client(1, "Maria Lopez", "1712345678", "0991234567", "mlopez@mail.com", "Quito");
        instance.setEmail("wrong-email");
        boolean expResult = false;
        boolean result = instance.update();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDetails method, of class Client.
     */
    @Test
    public void testGetDetails() {
        System.out.println("getDetails");
        Client instance = new Client(1, "Maria Lopez", "1712345678", "0991234567", "mlopez@mail.com", "Quito");
        String result = instance.getDetails();
        assertTrue(result.contains("Maria Lopez"));
        assertTrue(result.contains("1712345678"));
    }

    /**
     * Test of toString method, of class Client.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Client instance = new Client(1, "Maria Lopez", "1712345678", "0991234567", "mlopez@mail.com", "Quito");
        String expResult = "Client{clientId=1, name=Maria Lopez, idNumber=1712345678}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
