package ec.edu.espe.basicoperations.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Josselyn Ulcuango, CodeLex, @ESPE
 */
public class FinalVelocityTest {
    
    public FinalVelocityTest() {
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
     * Test of calculateFinalVelocity method, of class FinalVelocity.
     */
    @Test
    public void testCalculateFinalVelocity() {
        FinalVelocity instance = new FinalVelocity(2.0, 3.0, 4.0);
        double expResult = 14.0;
        double result = instance.calculateFinalVelocity();
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of getInitialVelocity method, of class FinalVelocity.
     */
    @Test
    public void testGetInitialVelocity() {
        FinalVelocity instance = new FinalVelocity(5.0, 2.0, 3.0);
        double expResult = 5.0;
        double result = instance.getInitialVelocity();
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of setInitialVelocity method, of class FinalVelocity.
     */
    @Test
    public void testSetInitialVelocity() {
        FinalVelocity instance = new FinalVelocity(0.0, 0.0, 0.0);
        instance.setInitialVelocity(10.0);
        double expResult = 10.0;
        assertEquals(expResult, instance.getInitialVelocity(), 0.0001);
    }

    /**
     * Test of getAcceleration method, of class FinalVelocity.
     */
    @Test
    public void testGetAcceleration() {
        FinalVelocity instance = new FinalVelocity(2.0, 4.0, 3.0);
        double expResult = 4.0;
        double result = instance.getAcceleration();
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of setAcceleration method, of class FinalVelocity.
     */
    @Test
    public void testSetAcceleration() {
        FinalVelocity instance = new FinalVelocity(1.0, 1.0, 1.0);
        instance.setAcceleration(8.0);
        double expResult = 8.0;
        assertEquals(expResult, instance.getAcceleration(), 0.0001);
    }

    /**
     * Test of getTime method, of class FinalVelocity.
     */
    @Test
    public void testGetTime() {
        FinalVelocity instance = new FinalVelocity(1.0, 1.0, 5.0);
        double expResult = 5.0;
        double result = instance.getTime();
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of setTime method, of class FinalVelocity.
     */
    @Test
    public void testSetTime() {
        FinalVelocity instance = new FinalVelocity(1.0, 1.0, 1.0);
        instance.setTime(6.0);
        double expResult = 6.0;
        assertEquals(expResult, instance.getTime(), 0.0001);
    }

    /**
     * Test of calculateFinalVelocityFromRest method, of class FinalVelocity.
     */
    @Test
    public void testCalculateFinalVelocityFromRest() {
        FinalVelocity instance = new FinalVelocity(0.0, 9.8, 2.0);
        double expResult = 19.6;
        double result = instance.calculateFinalVelocity();
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of negative time exception in FinalVelocity.
     */
    @Test
    public void testNegativeTimeException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FinalVelocity(5.0, 2.0, -3.0);
        });
    }

    /**
     * Test of calculateFinalVelocity with large values, of class FinalVelocity.
     */
    @Test
    public void testCalculateFinalVelocityLargeValues() {
        FinalVelocity instance = new FinalVelocity(100.0, 50.0, 10.0);
        double expResult = 999.0; // Falla intencionalmente para dejar el 10% de fallo
        double result = instance.calculateFinalVelocity();
        assertEquals(expResult, result, 0.0001);
    }
}