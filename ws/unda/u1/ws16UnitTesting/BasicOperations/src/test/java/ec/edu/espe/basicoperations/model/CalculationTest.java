package ec.edu.espe.basicoperations.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mateo Unda, CodeLex, @ESPE
 */
public class CalculationTest {
    
    public CalculationTest() {
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
     * Test of time method, of class Calculation.
     */
    @Test
    public void testTime() {
        System.out.println("time");
        float distance = 100.0F;
        float speed = 50.0F;
        Calculation instance = new Calculation();
        float expResult = 2.0F;
        float result = instance.time(distance, speed);
        assertEquals(expResult, result, 0);
    }
    public void testTime1() {
        System.out.println("time");
        float distance = 200.0F;
        float speed = 100.0F;
        Calculation instance = new Calculation();
        float expResult = 2.0F;
        float result = instance.time(distance, speed);
        assertEquals(expResult, result, 0);
    }
    public void testTime2() {
        System.out.println("time");
        float distance = 0.0F;
        float speed = 10.0F;
        Calculation instance = new Calculation();
        float expResult = 0.0F;
        float result = instance.time(distance, speed);
        assertEquals(expResult, result, 0);
    }
    public void testTime3() {
        System.out.println("time");
        float distance = 100.0F;
        float speed = 0.0F;
        Calculation instance = new Calculation();
        float expResult = 0.0F;
        float result = instance.time(distance, speed);
        assertEquals(expResult, result, 0);
    }
    public void testTime4() {
        System.out.println("time");
        float distance = -100.0F;
        float speed = 50.0F;
        Calculation instance = new Calculation();
        float expResult = -2.0F;
        float result = instance.time(distance, speed);
        assertEquals(expResult, result, 0);
    }
    public void testTime5() {
        System.out.println("time");
        float distance = 100.0F;
        float speed = -50.0F;
        Calculation instance = new Calculation();
        float expResult = -2.0F;
        float result = instance.time(distance, speed);
        assertEquals(expResult, result, 0);
    }
    public void testTime6() {
        System.out.println("time");
        float distance = -100.0F;
        float speed = -50.0F;
        Calculation instance = new Calculation();
        float expResult = 0.0F;
        float result = instance.time(distance, speed);
        assertEquals(expResult, result, 0);
    }
    public void testTim7() {
        System.out.println("time");
        float distance = 0.0001F;
        float speed = 0.0001F;
        Calculation instance = new Calculation();
        float expResult = 1.0F;
        float result = instance.time(distance, speed);
        assertEquals(expResult, result, 0);
    }
    public void testTime8() {
        System.out.println("time");
        float distance = 100.0F;
        float speed = 0.00000001F;
        Calculation instance = new Calculation();
        float expResult = 10000000000.0F;
        float result = instance.time(distance, speed);
        assertEquals(expResult, result, 0);
    }
    public void testTime9() {
        System.out.println("time");
        float distance = 0.0F;
        float speed = 0.0F;
        Calculation instance = new Calculation();
        float expResult = 0.0F;
        float result = instance.time(distance, speed);
        assertEquals(expResult, result, 0);
    }
    public void testTime10() {
        System.out.println("time");
        float distance = 0.0F;
        float speed = 0.0F;
        Calculation instance = new Calculation();
        float expResult = 0.0F;
        float result = instance.time(distance, speed);
        assertEquals(expResult, result, 0);
    }
}
