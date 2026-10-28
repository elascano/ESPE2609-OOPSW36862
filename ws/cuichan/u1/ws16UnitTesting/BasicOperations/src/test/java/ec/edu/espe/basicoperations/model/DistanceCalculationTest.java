package ec.edu.espe.basicoperations.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Leslye Cuichan, Infinite loops, @eESPE
 */
public class DistanceCalculationTest {
    
    public DistanceCalculationTest() {
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
     * Test of calculateDistance method, of class DistanceCalculation.
     */
    @Test
    public void testCalculateDistanceZeroValues() {
        System.out.println("calculate with zero values");
        float speed = 0.0F;
        float time = 0.0F;
        DistanceCalculation instance = new DistanceCalculation();
        float expResult = 0.0F;
        float result = instance.calculateDistance(speed, time);
        assertEquals(expResult, result, 0.001F);
    }

   @Test
    public void testCalculateDistanceValues(){
        System.out.println("calculate with zero values");
        float speed = 4.0F;
        float time = 0.0F;
        DistanceCalculation instance = new DistanceCalculation();
        float expResult = 0.0F;
        float result = instance.calculateDistance(speed, time);
        assertEquals(expResult, result, 0.001F);
    }

    @Test
    public void testCalculateDistanceNegativeSpeedFail() {
        System.out.println("calculate with negative speed");
        float speed = -60.0F;
        float time = 2.0F;
        DistanceCalculation instance = new DistanceCalculation();
        float expResult = 0.0F; 
        float result = instance.calculateDistance(speed, time);
        assertEquals(expResult, result, 0.001F);
    }

    @Test
    public void testCalculateDistanceNegativeTimeFail() {
        System.out.println("calculate with negative time");
        float speed = 80.0F;
        float time = -1.5F;
        DistanceCalculation instance = new DistanceCalculation();
        float expResult = 0.0F; 
        float result = instance.calculateDistance(speed, time);
        assertEquals(expResult, result, 0.001F);
    }

    @Test
    public void testCalculateDistanceBothNegativeFail() {
        System.out.println("calculate with both negative");
        float speed = -50.0F;
        float time = -2.0F;
        DistanceCalculation instance = new DistanceCalculation();
        float expResult = 0.0F;
        float result = instance.calculateDistance(speed, time);
        assertEquals(expResult, result, 0.001F);
    }

    @Test
    public void testCalculateDistance() {
        System.out.println("calculate with precision decimals");
        float speed = 100.0F;
        float time = 3.0001F;
        DistanceCalculation instance = new DistanceCalculation();
        float expResult = 300.01F;
        float result = instance.calculateDistance(speed, time);
        assertEquals(expResult, result, 0.00001F);
    }

    @Test
    public void testCalculateDistanceDecimalPrecision() {
        System.out.println("calculate with decimals");
        float speed = 12.5F;
        float time = 0.030F;
        DistanceCalculation instance = new DistanceCalculation();
        float expResult = 0.375F;
        float result = instance.calculateDistance(speed, time);
        assertEquals(expResult, result, 0.001F);
    }

    @Test
    public void testCalculateDistanceZeroDeltaFail() {
        System.out.println("calculate with zero delta");
        float speed = 10.1F;
        float time = 3.0F;
        DistanceCalculation instance = new DistanceCalculation();
        float expResult = 30.3F;
        float result = instance.calculateDistance(speed, time);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testCalculateDistanceZeroSpeed() {
        System.out.println("calculate with zero speed");
        float speed = 0.0F;
        float time = 15.5F;
        DistanceCalculation instance = new DistanceCalculation();
        float expResult = 0.0F;
        float result = instance.calculateDistance(speed, time);
        assertEquals(expResult, result, 0.01F);
    }

    @Test
    public void testCalculateDistanceZeroTime() {
        System.out.println("calculate with zero time");
        float speed = 95.0F;
        float time = 0.0F;
        DistanceCalculation instance = new DistanceCalculation();
        float expResult = 0.0F;
        float result = instance.calculateDistance(speed, time);
        assertEquals(expResult, result, 0.0F);
    }

    @Test
    public void testCalculateDistanceLargeNumbersFail() {
        System.out.println("calculate with large numbers");
        float speed = 3.4F;
        float time = 7.82e1F;
        DistanceCalculation instance = new DistanceCalculation();
        float expResult = 6.8F; 
        float result = instance.calculateDistance(speed, time);
        assertEquals(expResult, result, 0.001F);
    }
}