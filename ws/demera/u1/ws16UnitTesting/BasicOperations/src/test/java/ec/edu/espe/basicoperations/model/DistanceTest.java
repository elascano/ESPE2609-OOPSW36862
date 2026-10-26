package ec.edu.espe.basicoperations.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Francisco Demera, Byte and Fabric Devs, @ESPE
 */
public class DistanceTest {

    public DistanceTest() {
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
     * Test of computeDistance method, of class Distance.
     */
    @Test
    public void testComputeDistanceComplexDecimal() {
        System.out.println("computeDistance with complex decimals");
        float speed = 123.56F;
        float time = 21.37F;
        Distance instance = new Distance();
        float expResult = 2640.4772F;
        float result = instance.computeDistance(speed, time);
        assertEquals(expResult, result, 0.001F);
    }

    @Test
    public void testComputeDistanceSmallValues() {
        System.out.println("computeDistance with small decimal values");
        float speed = 0.000123F;
        float time = 0.00456F;
        Distance instance = new Distance();
        float expResult = 0.00000056088F;
        float result = instance.computeDistance(speed, time);
        assertEquals(expResult, result, 0.000000001F);
    }

    @Test
    public void testComputeDistanceLargeValues() {
        System.out.println("computeDistance with large values");
        float speed = 98765.43F;
        float time = 123.45F;
        Distance instance = new Distance();
        float expResult = 12192592.0F;
        float result = instance.computeDistance(speed, time);
        assertEquals(expResult, result, 1.0F);
    }

    @Test
    public void testComputeDistanceNegativeTime() {
        System.out.println("computeDistance with negative time");
        float speed = 123.56F;
        float time = -21.0F;
        Distance instance = new Distance();
        float expResult = 0F;
        float result = instance.computeDistance(speed, time);
        assertEquals(expResult, result, 0);

    }

    @Test
    public void testComputeDistanceOverflow() {
        System.out.println("computeDistance with float overflow");
        float speed = Float.MAX_VALUE;
        float time = 1000.0F;
        Distance instance = new Distance();
        float expResult = Float.MAX_VALUE;
        float result = instance.computeDistance(speed, time);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testComputeDistanceIndetermination() {
        System.out.println("computeDistance with NaN input");
        float speed = Float.NaN;
        float time = 123.45F;
        Distance instance = new Distance();
        float expResult = 0.0F;
        float result = instance.computeDistance(speed, time);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testComputeDistanceInfiniteInput() {
        System.out.println("computeDistance with infinite input");
        float speed = Float.POSITIVE_INFINITY;
        float time = 999.99F;
        Distance instance = new Distance();
        float expResult = 999.99F;
        float result = instance.computeDistance(speed, time);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testComputeDistanceNegativeSpeed() {
        System.out.println("computeDistance with negative speed");
        float speed = -987.654F;
        float time = 123.456F;
        Distance instance = new Distance();
        float expResult = 0.0F;
        float result = instance.computeDistance(speed, time);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testComputeDistanceNegativeOverflow() {
        System.out.println("computeDistance with negative overflow");
        float speed = -Float.MAX_VALUE;
        float time = 2.0F;
        Distance instance = new Distance();
        float expResult = 0.0F;
        float result = instance.computeDistance(speed, time);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testComputeDistanceMinimumFloat() {
        System.out.println("computeDistance with minimum positive float");
        float speed = Float.MIN_VALUE;
        float time = 0.5F;
        Distance instance = new Distance();
        float expResult = Float.MIN_VALUE;
        float result = instance.computeDistance(speed, time);
        assertEquals(expResult, result, 0);
    }

}
