package ec.edu.espe.basicoperations.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author David Clavón, Infinite Loops, @ESPE
 */
public class conversionOfDegreesToRadiansTest {
    
    private static final double DELTA = 1e-4;

    public conversionOfDegreesToRadiansTest() {
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
    public void testConversionAngleZero() {
        double angleInDegrees = 0.0;
        conversionOfDegreesToRadians instance = new conversionOfDegreesToRadians();
        double expResult = 0.0;
        double result = instance.conversionAngle(angleInDegrees);
        assertEquals(expResult, result, DELTA);
    }

    @Test
    public void testConversionAngle90Degrees() {
        double angleInDegrees = 90.0;
        conversionOfDegreesToRadians instance = new conversionOfDegreesToRadians();
        double expResult = Math.PI / 2.0;
        double result = instance.conversionAngle(angleInDegrees);
        assertEquals(expResult, result, DELTA);
    }

    @Test
    public void testConversionAngle180Degrees() {
        double angleInDegrees = 180.0;
        conversionOfDegreesToRadians instance = new conversionOfDegreesToRadians();
        double expResult = Math.PI;
        double result = instance.conversionAngle(angleInDegrees);
        assertEquals(expResult, result, DELTA);
    }

    @Test
    public void testConversionAngle270Degrees() {
        double angleInDegrees = 270.0;
        conversionOfDegreesToRadians instance = new conversionOfDegreesToRadians();
        double expResult = (3.0 * Math.PI) / 2.0;
        double result = instance.conversionAngle(angleInDegrees);
        assertEquals(expResult, result, DELTA);
    }

    @Test
    public void testConversionAngleDecimal() {
        double angleInDegrees = 45.5;
        conversionOfDegreesToRadians instance = new conversionOfDegreesToRadians();
        double expResult = (45.5 * Math.PI) / 180.0;
        double result = instance.conversionAngle(angleInDegrees);
        assertEquals(expResult, result, DELTA);
    }

    @Test
    public void testConversionAngleSmallValue() {
        double angleInDegrees = 0.0001;
        conversionOfDegreesToRadians instance = new conversionOfDegreesToRadians();
        double expResult = (0.0001 * Math.PI) / 180.0;
        double result = instance.conversionAngle(angleInDegrees);
        assertEquals(expResult, result, 1e-7);
    }

    @Test
    public void testConversionAngle30Degrees() {
        double angleInDegrees = 30.0;
        conversionOfDegreesToRadians instance = new conversionOfDegreesToRadians();
        double expResult = Math.PI / 6.0;
        double result = instance.conversionAngle(angleInDegrees);
        assertEquals(expResult, result, DELTA);
    }


    @Test
    public void testAnglesGreaterThan360() {
        double angleInDegrees = 765.0; 
        conversionOfDegreesToRadians instance = new conversionOfDegreesToRadians();
        double expResult = Math.PI / 4.0; 
        double result = instance.conversionAngle(angleInDegrees);
        assertEquals(expResult, result, DELTA);
    }

    @Test
    public void testConversionAngleNegative() {
        double angleInDegrees = -90.0;
        conversionOfDegreesToRadians instance = new conversionOfDegreesToRadians();
        double expResult = (3.0 * Math.PI) / 2.0;
        double result = instance.conversionAngle(angleInDegrees);
        assertEquals(expResult, result, DELTA);
    }

    @Test
    public void testConversionAngle360DegreesBoundary() {
        double angleInDegrees = 360.0;
        conversionOfDegreesToRadians instance = new conversionOfDegreesToRadians();
        double expResult = 0.0;
        double result = instance.conversionAngle(angleInDegrees);
        assertEquals(expResult, result, DELTA);
    }
}