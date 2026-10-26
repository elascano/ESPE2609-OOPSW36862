package ec.edu.espe.basicoperations.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HorizonCalculatorTest {
    
    public HorizonCalculatorTest() {
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

    @Test
    public void testZeroHeight() {
        HorizonCalculator instance = new HorizonCalculator();
        double result = instance.calculateDistance(0.0);
        assertEquals(0.0, result, 0.001);
    }

    @Test
    public void testStandardEyeLevel() {
        HorizonCalculator instance = new HorizonCalculator();
        double result = instance.calculateDistance(1.8);
        assertEquals(4.789, result, 0.01);
    }

    @Test
    public void testChildEyeLevel() {
        HorizonCalculator instance = new HorizonCalculator();
        double result = instance.calculateDistance(1.0);
        assertEquals(3.57, result, 0.01);
    }

    @Test
    public void testTallPersonEyeLevel() {
        HorizonCalculator instance = new HorizonCalculator();
        double result = instance.calculateDistance(2.0);
        assertEquals(5.048, result, 0.01);
    }

    @Test
    public void testBuildingHeight() {
        HorizonCalculator instance = new HorizonCalculator();
        double result = instance.calculateDistance(100.0);
        assertEquals(35.7, result, 0.01);
    }

    @Test
    public void testMountainHeight() {
        HorizonCalculator instance = new HorizonCalculator();
        double result = instance.calculateDistance(1000.0);
        assertEquals(112.89, result, 0.1);
    }

    @Test
    public void testEverestHeight() {
        HorizonCalculator instance = new HorizonCalculator();
        double result = instance.calculateDistance(8848.0);
        assertEquals(335.81, result, 0.5);
    }

    @Test
    public void testSmallDecimalHeight() {
        HorizonCalculator instance = new HorizonCalculator();
        double result = instance.calculateDistance(0.25);
        assertEquals(1.785, result, 0.01);
    }

    @Test
    public void testNegativeHeightThrowsException() {
        HorizonCalculator instance = new HorizonCalculator();
        assertThrows(IllegalArgumentException.class, () -> {
            instance.calculateDistance(-10.0);
        });
    }

    @Test
    public void testSmallNegativeHeightThrowsException() {
        HorizonCalculator instance = new HorizonCalculator();
        assertThrows(IllegalArgumentException.class, () -> {
            instance.calculateDistance(-0.0001);
        });
    }
}