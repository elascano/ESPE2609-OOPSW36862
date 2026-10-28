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
public class UserTest {

    public UserTest() {
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
     * Test of login method, of class User.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String username = "cmena";
        String password = "lawyer123";
        User instance = new User(2, "Carlos Mena", "cmena", "lawyer123", Role.LAWYER);
        boolean expResult = true;
        boolean result = instance.login(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of login method with a wrong password, of class User.
     */
    @Test
    public void testLoginWrongPassword() {
        System.out.println("login wrong password");
        User instance = new User(2, "Carlos Mena", "cmena", "lawyer123", Role.LAWYER);
        boolean expResult = false;
        boolean result = instance.login("cmena", "lawyer321");
        assertEquals(expResult, result);
    }

    /**
     * Test of login method with an inactive user, of class User.
     */
    @Test
    public void testLoginInactiveUser() {
        System.out.println("login inactive user");
        User instance = new User(2, "Carlos Mena", "cmena", "lawyer123", Role.LAWYER);
        instance.setActive(false);
        boolean result = instance.login("cmena", "lawyer123");
        assertFalse(result);
    }

    /**
     * Test of login method with the username in capital letters, of class User.
     */
    @Test
    public void testLoginUppercaseUsername() {
        System.out.println("login uppercase username");
        User instance = new User(2, "Carlos Mena", "cmena", "lawyer123", Role.LAWYER);
        boolean expResult = true;
        boolean result = instance.login("CMENA", "lawyer123");
        assertEquals(expResult, result);
    }

    /**
     * Test of changePassword method, of class User.
     */
    @Test
    public void testChangePassword() {
        System.out.println("changePassword");
        String oldPass = "lawyer123";
        String newPass = "abc";
        User instance = new User(2, "Carlos Mena", "cmena", "lawyer123", Role.LAWYER);
        boolean expResult = false;
        boolean result = instance.changePassword(oldPass, newPass);
        assertEquals(expResult, result);
    }

    /**
     * Test of changePassword method with a valid new password, of class User.
     */
    @Test
    public void testChangePasswordValid() {
        System.out.println("changePassword valid");
        User instance = new User(2, "Carlos Mena", "cmena", "lawyer123", Role.LAWYER);
        boolean result = instance.changePassword("lawyer123", "newLawyer2026");
        assertTrue(result);
        assertEquals("newLawyer2026", instance.getPassword());
    }

    /**
     * Test of changePassword method with a wrong old password, of class User.
     */
    @Test
    public void testChangePasswordWrongOldPassword() {
        System.out.println("changePassword wrong old password");
        User instance = new User(2, "Carlos Mena", "cmena", "lawyer123", Role.LAWYER);
        boolean expResult = false;
        boolean result = instance.changePassword("otherPass", "newLawyer2026");
        assertEquals(expResult, result);
    }

    /**
     * Test of changePassword method with the same password, of class User.
     */
    @Test
    public void testChangePasswordSamePassword() {
        System.out.println("changePassword same password");
        User instance = new User(2, "Carlos Mena", "cmena", "lawyer123", Role.LAWYER);
        boolean expResult = false;
        boolean result = instance.changePassword("lawyer123", "lawyer123");
        assertEquals(expResult, result);
    }

    /**
     * Test of changePassword method with a null new password, of class User.
     */
    @Test
    public void testChangePasswordNull() {
        System.out.println("changePassword null");
        User instance = new User(2, "Carlos Mena", "cmena", "lawyer123", Role.LAWYER);
        boolean expResult = false;
        boolean result = instance.changePassword("lawyer123", null);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasPermission method, of class User.
     */
    @Test
    public void testHasPermission() {
        System.out.println("hasPermission");
        User instance = new User(4, "Luis Paredes", "lparedes", "assist123", Role.LEGAL_ASSISTANT);
        assertTrue(instance.hasPermission("IMPORT_CSV"));
        assertFalse(instance.hasPermission("MANAGE_USERS"));
    }

    /**
     * Test of hasPermission method with a partner, of class User.
     */
    @Test
    public void testHasPermissionPartner() {
        System.out.println("hasPermission partner");
        User instance = new User(1, "Ana Torres", "atorres", "partner123", Role.PARTNER);
        assertTrue(instance.hasPermission("MANAGE_USERS"));
    }

    /**
     * Test of hasPermission method with a lawyer, of class User.
     */
    @Test
    public void testHasPermissionLawyer() {
        System.out.println("hasPermission lawyer");
        User instance = new User(2, "Carlos Mena", "cmena", "lawyer123", Role.LAWYER);
        assertTrue(instance.hasPermission("GENERATE_DOCUMENT"));
        assertFalse(instance.hasPermission("IMPORT_CSV"));
    }

    /**
     * Test of hasPermission method with a junior lawyer, of class User.
     */
    @Test
    public void testHasPermissionJuniorLawyer() {
        System.out.println("hasPermission junior lawyer");
        User instance = new User(3, "Daniela Ruiz", "druiz", "junior123", Role.JUNIOR_LAWYER);
        assertTrue(instance.hasPermission("SEARCH_CASE"));
        assertFalse(instance.hasPermission("REGISTER_EVIDENCE"));
    }

    /**
     * Test of hasPermission method with an inactive user, of class User.
     */
    @Test
    public void testHasPermissionInactiveUser() {
        System.out.println("hasPermission inactive user");
        User instance = new User(1, "Ana Torres", "atorres", "partner123", Role.PARTNER);
        instance.setActive(false);
        boolean expResult = false;
        boolean result = instance.hasPermission("SEARCH_CASE");
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class User.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        User instance = new User(2, "Carlos Mena", "cmena", "lawyer123", Role.LAWYER);
        String result = instance.toString();
        assertTrue(result.contains("username=cmena"));
        assertTrue(result.contains("role=LAWYER"));
    }

}
