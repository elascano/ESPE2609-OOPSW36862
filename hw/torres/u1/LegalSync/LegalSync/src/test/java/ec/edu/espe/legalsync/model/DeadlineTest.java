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
public class DeadlineTest {

    public DeadlineTest() {
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
     * Test of calculateRemainingDays method, of class Deadline.
     */
    @Test
    public void testCalculateRemainingDays() {
        System.out.println("calculateRemainingDays");
        Deadline instance = new Deadline(1, 1, "Preliminary hearing", "Hearing", LocalDate.now().plusDays(5), 3);
        int expResult = 5;
        int result = instance.calculateRemainingDays();
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateRemainingDays method with the default values, of class Deadline.
     */
    @Test
    public void testCalculateRemainingDaysWithoutDate() {
        System.out.println("calculateRemainingDays without target date");
        Deadline instance = new Deadline(0, 0, "", "", null, 0);
        int expResult = 0;
        int result = instance.calculateRemainingDays();
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateRemainingDays method with a past date, of class Deadline.
     */
    @Test
    public void testCalculateRemainingDaysPastDate() {
        System.out.println("calculateRemainingDays past date");
        Deadline instance = new Deadline(1, 1, "Preliminary hearing", "Hearing", LocalDate.now().minusDays(3), 3);
        int expResult = -3;
        int result = instance.calculateRemainingDays();
        assertEquals(expResult, result);
    }

    /**
     * Test of isApproaching method, of class Deadline.
     */
    @Test
    public void testIsApproaching() {
        System.out.println("isApproaching");
        Deadline instance = new Deadline(1, 1, "Preliminary hearing", "Hearing", LocalDate.now().plusDays(2), 3);
        boolean expResult = true;
        boolean result = instance.isApproaching();
        assertEquals(expResult, result);
    }

    /**
     * Test of isApproaching method on the exact alert limit, of class Deadline.
     */
    @Test
    public void testIsApproachingExactLimit() {
        System.out.println("isApproaching exact limit");
        Deadline instance = new Deadline(1, 1, "Preliminary hearing", "Hearing", LocalDate.now().plusDays(3), 3);
        boolean expResult = true;
        boolean result = instance.isApproaching();
        assertEquals(expResult, result);
    }

    /**
     * Test of isApproaching method with a far date, of class Deadline.
     */
    @Test
    public void testIsApproachingFarDate() {
        System.out.println("isApproaching far date");
        Deadline instance = new Deadline(1, 1, "Preliminary hearing", "Hearing", LocalDate.now().plusDays(30), 3);
        boolean expResult = false;
        boolean result = instance.isApproaching();
        assertEquals(expResult, result);
    }

    /**
     * Test of isApproaching method with an overdue deadline, of class Deadline.
     */
    @Test
    public void testIsApproachingOverdue() {
        System.out.println("isApproaching overdue");
        Deadline instance = new Deadline(1, 1, "Preliminary hearing", "Hearing", LocalDate.now().minusDays(2), 3);
        boolean expResult = false;
        boolean result = instance.isApproaching();
        assertEquals(expResult, result);
    }

    /**
     * Test of needsAlert method with an overdue deadline, of class Deadline.
     */
    @Test
    public void testNeedsAlertOverdue() {
        System.out.println("needsAlert overdue");
        Deadline instance = new Deadline(1, 1, "Preliminary hearing", "Hearing", LocalDate.now().minusDays(2), 3);
        boolean expResult = true;
        boolean result = instance.needsAlert();
        assertEquals(expResult, result);
    }

    /**
     * Test of needsAlert method with a completed deadline, of class Deadline.
     */
    @Test
    public void testNeedsAlertCompleted() {
        System.out.println("needsAlert completed");
        Deadline instance = new Deadline(1, 1, "Preliminary hearing", "Hearing", LocalDate.now().plusDays(1), 3);
        instance.markCompleted();
        boolean expResult = false;
        boolean result = instance.needsAlert();
        assertEquals(expResult, result);
    }

    /**
     * Test of generateAlertMessage method, of class Deadline.
     */
    @Test
    public void testGenerateAlertMessage() {
        System.out.println("generateAlertMessage");
        Deadline instance = new Deadline(1, 1, "Appeal submission", "Appeal", LocalDate.now().minusDays(1), 3);
        String result = instance.generateAlertMessage();
        assertTrue(result.startsWith("OVERDUE"));
    }

    /**
     * Test of generateAlertMessage method for a deadline due today, of class Deadline.
     */
    @Test
    public void testGenerateAlertMessageToday() {
        System.out.println("generateAlertMessage today");
        Deadline instance = new Deadline(1, 1, "Preliminary hearing", "Hearing", LocalDate.now(), 3);
        String result = instance.generateAlertMessage();
        assertTrue(result.startsWith("TODAY"));
    }

    /**
     * Test of generateAlertMessage method for an upcoming deadline, of class Deadline.
     */
    @Test
    public void testGenerateAlertMessageUpcoming() {
        System.out.println("generateAlertMessage upcoming");
        Deadline instance = new Deadline(1, 1, "Preliminary hearing", "Hearing", LocalDate.now().plusDays(4), 3);
        String result = instance.generateAlertMessage();
        assertTrue(result.startsWith("UPCOMING"));
        assertTrue(result.contains("4 day(s) remaining"));
    }

    /**
     * Test of generateAlertMessage method for a completed deadline, of class Deadline.
     */
    @Test
    public void testGenerateAlertMessageCompleted() {
        System.out.println("generateAlertMessage completed");
        Deadline instance = new Deadline(1, 1, "Preliminary hearing", "Hearing", LocalDate.now().plusDays(4), 3);
        instance.markCompleted();
        String result = instance.generateAlertMessage();
        assertTrue(result.startsWith("COMPLETED"));
    }

    /**
     * Test of markCompleted method, of class Deadline.
     */
    @Test
    public void testMarkCompleted() {
        System.out.println("markCompleted");
        Deadline instance = new Deadline(1, 1, "Preliminary hearing", "Hearing", LocalDate.now().plusDays(1), 3);
        instance.markCompleted();
        assertTrue(instance.isCompleted());
        assertFalse(instance.isApproaching());
    }

}
