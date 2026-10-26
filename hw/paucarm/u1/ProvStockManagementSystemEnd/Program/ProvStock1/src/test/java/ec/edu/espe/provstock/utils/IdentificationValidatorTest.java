package ec.edu.espe.provstock.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class IdentificationValidatorTest {

    public IdentificationValidatorTest() {
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
     * Test of isValidCedula method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidCedulaGood1() {
        System.out.println("isValidCedula - Good 1");
        String value = "1710034065";
        boolean expResult = true;
        boolean result = IdentificationValidator.isValidCedula(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidCedula method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidCedulaGood2() {
        System.out.println("isValidCedula - Good 2");
        String value = "0912345675";
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = false;
        boolean result = IdentificationValidator.isValidCedula(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidCedula method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidCedulaGood3() {
        System.out.println("isValidCedula - Good 3");
        String value = "0501234561";
        boolean expResult = true;
        boolean result = IdentificationValidator.isValidCedula(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidCedula method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidCedulaStrong1() {
        System.out.println("isValidCedula - Strong 1");
        String value = "3000000004";
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = false;
        boolean result = IdentificationValidator.isValidCedula(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidCedula method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidCedulaStrong2() {
        System.out.println("isValidCedula - Strong 2");
        String value = "2400000002";
        boolean expResult = true;
        boolean result = IdentificationValidator.isValidCedula(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidCedula method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidCedulaBad1() {
        System.out.println("isValidCedula - Bad 1");
        String value = "1710034064";
        boolean expResult = false;
        boolean result = IdentificationValidator.isValidCedula(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidCedula method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidCedulaBad2() {
        System.out.println("isValidCedula - Bad 2");
        String value = "171003406";
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = true;
        boolean result = IdentificationValidator.isValidCedula(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidCedula method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidCedulaBad3() {
        System.out.println("isValidCedula - Bad 3");
        String value = "2510034065";
        boolean expResult = false;
        boolean result = IdentificationValidator.isValidCedula(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidCedula method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidCedulaBad4() {
        System.out.println("isValidCedula - Bad 4");
        String value = null;
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = true;
        boolean result = IdentificationValidator.isValidCedula(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidRuc method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidRucGood1() {
        System.out.println("isValidRuc - Good 1");
        String value = "1710034065001";
        boolean expResult = true;
        boolean result = IdentificationValidator.isValidRuc(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidRuc method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidRucGood2() {
        System.out.println("isValidRuc - Good 2");
        String value = "1790011674001";
        boolean expResult = true;
        boolean result = IdentificationValidator.isValidRuc(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidRuc method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidRucStrong1() {
        System.out.println("isValidRuc - Strong 1");
        String value = "1760001550001";
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = false;
        boolean result = IdentificationValidator.isValidRuc(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidRuc method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidRucBad1() {
        System.out.println("isValidRuc - Bad 1");
        String value = "1710034065000";
        boolean expResult = false;
        boolean result = IdentificationValidator.isValidRuc(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidRuc method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidRucBad2() {
        System.out.println("isValidRuc - Bad 2");
        String value = "1790011674000";
        boolean expResult = false;
        boolean result = IdentificationValidator.isValidRuc(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidRuc method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidRucBad3() {
        System.out.println("isValidRuc - Bad 3");
        String value = "1770034065001";
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = true;
        boolean result = IdentificationValidator.isValidRuc(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidPassport method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidPassportGood1() {
        System.out.println("isValidPassport - Good 1");
        String value = "AB123456";
        boolean expResult = true;
        boolean result = IdentificationValidator.isValidPassport(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidPassport method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidPassportStrong1() {
        System.out.println("isValidPassport - Strong 1");
        String value = "A1234";
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = false;
        boolean result = IdentificationValidator.isValidPassport(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidPassport method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidPassportBad1() {
        System.out.println("isValidPassport - Bad 1");
        String value = "AB-12";
        boolean expResult = false;
        boolean result = IdentificationValidator.isValidPassport(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidEmail method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidEmailGood1() {
        System.out.println("isValidEmail - Good 1");
        String value = "matias@espe.edu.ec";
        boolean expResult = true;
        boolean result = IdentificationValidator.isValidEmail(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidEmail method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidEmailStrong1() {
        System.out.println("isValidEmail - Strong 1");
        String value = "";
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = false;
        boolean result = IdentificationValidator.isValidEmail(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidEmail method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidEmailBad1() {
        System.out.println("isValidEmail - Bad 1");
        String value = "matias.espe.edu.ec";
        boolean expResult = false;
        boolean result = IdentificationValidator.isValidEmail(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidPhone method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidPhoneGood1() {
        System.out.println("isValidPhone - Good 1");
        String value = "0991234567";
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = false;
        boolean result = IdentificationValidator.isValidPhone(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidPhone method, of class IdentificationValidator.
     */
    @Test
    public void testIsValidPhoneBad1() {
        System.out.println("isValidPhone - Bad 1");
        String value = "09912abc";
        boolean expResult = false;
        boolean result = IdentificationValidator.isValidPhone(value);
        assertEquals(expResult, result);
    }
    
}
