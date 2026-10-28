package ec.edu.espe.bodymassindex.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Dennis Criollo, Infinite loops, @eESPE
 */
public class BodyMassIndexTest {

    

    @Test
    public void testCalculateBmiWithErrors() {
        System.out.println("calculateBmi - with errors");
        BodyMassIndex instance = new BodyMassIndex();
        float expResult = 22.86F;                 
        float result = instance.calculateBmi(70.0F, 1.75F);
        assertEquals(expResult, result, 0.01F);  
    }

    

    @Test
    public void testCalculateBmiNormalWeight() {
        System.out.println("calculateBmi - good 1");
        BodyMassIndex instance = new BodyMassIndex();
        float result = instance.calculateBmi(70.0F, 1.75F);
        assertEquals(22.86F, result, 0.01F);
        assertEquals("Normal weight", instance.classify(result));
    }

    @Test
    public void testCalculateBmiOverweight() {
        System.out.println("calculateBmi - good 2");
        BodyMassIndex instance = new BodyMassIndex();
        float result = instance.calculateBmi(85.0F, 1.80F);
        assertEquals(26.23F, result, 0.01F);
        assertEquals("Overweight", instance.classify(result));
    }

    @Test
    public void testCalculateBmiUnderweight() {
        System.out.println("calculateBmi - good 3");
        BodyMassIndex instance = new BodyMassIndex();
        float result = instance.calculateBmi(48.0F, 1.70F);
        assertEquals(16.61F, result, 0.01F);
        assertEquals("Underweight", instance.classify(result));
    }

    

    @Test
    public void testBoundaryUnderweightToNormal() {
        System.out.println("classify - strong 1 (18.5)");
        BodyMassIndex instance = new BodyMassIndex();
        float result = instance.calculateBmi(74.0F, 2.0F);   
        assertEquals(18.5F, result, 0.0F);
        assertEquals("Normal weight", instance.classify(result));
    }

    @Test
    public void testBoundaryNormalToOverweight() {
        System.out.println("classify - strong 2 (25.0)");
        BodyMassIndex instance = new BodyMassIndex();
        float result = instance.calculateBmi(100.0F, 2.0F);  
        assertEquals(25.0F, result, 0.0F);
        assertEquals("Overweight", instance.classify(result));
    }

    @Test
    public void testBoundaryOverweightToObesity() {
        System.out.println("classify - strong 3 (30.0)");
        BodyMassIndex instance = new BodyMassIndex();
        float result = instance.calculateBmi(120.0F, 2.0F);  
        assertEquals(30.0F, result, 0.0F);
        assertEquals("Obesity", instance.classify(result));
    }

    

    @Test
    public void testWeightZero() {
        System.out.println("calculateBmi - bad 1 (weight = 0)");
        BodyMassIndex instance = new BodyMassIndex();
        assertThrows(IllegalArgumentException.class, () -> instance.calculateBmi(0.0F, 1.70F));
    }

    @Test
    public void testHeightZero() {
        System.out.println("calculateBmi - bad 2 (height = 0)");
        BodyMassIndex instance = new BodyMassIndex();
        assertThrows(IllegalArgumentException.class, () -> instance.calculateBmi(70.0F, 0.0F));
    }

    @Test
    public void testNegativeWeight() {
        System.out.println("calculateBmi - bad 3 (negative weight)");
        BodyMassIndex instance = new BodyMassIndex();
        assertThrows(IllegalArgumentException.class, () -> instance.calculateBmi(-70.0F, 1.75F));
    }
}
