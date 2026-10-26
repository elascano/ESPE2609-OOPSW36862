package ec.edu.espe.basicoperations.model;

/**
 *
 * @author Daniel Sangoquiza, SaborCode, @ESPE
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OperationTest {

    @Test
    public void testAddNormal() {
        Operation instance = new Operation();
        assertEquals(5.0F, instance.add(2.0F, 3.0F), 0.0001);
    }

    @Test
    public void testAddNegatives() {
        Operation instance = new Operation();
        assertEquals(-5.0F, instance.add(-2.0F, -3.0F), 0.0001);
    }

    @Test
    public void testSubtractResultNegative() {
        Operation instance = new Operation();
        assertEquals(-5.0F, instance.subtract(10.0F, 15.0F), 0.0001);
    }

    @Test
    public void testMultiplyByZero() {
        Operation instance = new Operation();
        assertEquals(0.0F, instance.multiply(125.5F, 0.0F), 0.0001);
    }

    @Test
    public void testDivideNormal() {
        Operation instance = new Operation();
        assertEquals(2.5F, instance.divide(5.0F, 2.0F), 0.0001);
    }

    @Test
    public void testDivideByZeroInfinity() {
        Operation instance = new Operation();
        float result = instance.divide(10.0F, 0.0F);
        assertEquals(Float.POSITIVE_INFINITY, result, 0.0);
    }

    @Test
    public void testAddIntentionalFail() {
        Operation instance = new Operation();
        float expResult = 15.0F; 
        float result = instance.add(5.0F, 5.0F);
        assertEquals(expResult, result, 0.0001);
    }

    @Test
    public void testSubtractIntentionalFail() {
        Operation instance = new Operation();
        float expResult = 2.0F; 
        float result = instance.subtract(3.0F, 5.0F);
        assertEquals(expResult, result, 0.0001);
    }

    @Test
    public void testDivideZeroByZeroNaN() {
        Operation instance = new Operation();
        float expResult = 0.0F;
        float result = instance.divide(0.0F, 0.0F);
        assertEquals(expResult, result, 0.0001);
    }

    @Test
    public void testMultiplyPrecisionFail() {
        Operation instance = new Operation();
        float expResult = 10.0F; 
        float result = instance.multiply(3.0F, 3.5F);
        assertEquals(expResult, result, 0.0001);
    }
}