package ec.edu.espe.basicoperations.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Isaac Acurio, @ESPE
 */
public class EllipseTest {

    private static final float DELTA = 0.01F;

    // 1. Normal case: a=3, b=2 -> 18.8496   
    @Test
    public void testComputeAreaNormalCase() {
        Ellipse instance = new Ellipse();
        assertEquals(18.8496F, instance.computeArea(3F, 2F), DELTA);
    }

    // 2. Circle (a = b): a=5, b=5 -> 78.5398
    @Test
    public void testComputeAreaCircle() {
        Ellipse instance = new Ellipse();
        assertEquals(78.5398F, instance.computeArea(5F, 5F), DELTA);
    }

    // 3. Unit values: a=1, b=1 -> 3.1416    
    @Test
    public void testComputeAreaUnitValues() {
        Ellipse instance = new Ellipse();
        assertEquals(3.1416F, instance.computeArea(1F, 1F), DELTA);
    }

    // 4. Decimals: a=2.5, b=1.5 -> 11.7810
    @Test
    public void testComputeAreaDecimals() {
        Ellipse instance = new Ellipse();
        assertEquals(11.7810F, instance.computeArea(2.5F, 1.5F), DELTA);
    }

    // 5. Large values: a=100, b=50 -> 15707.9633    
    @Test
    public void testComputeAreaLargeValues() {
        Ellipse instance = new Ellipse();
        assertEquals(15707.9633F, instance.computeArea(100F, 50F), 0.1F);
    }

    // 6. Small values: a=0.5, b=0.5 -> 0.7854
    @Test
    public void testComputeAreaSmallValues() {
        Ellipse instance = new Ellipse();
        assertEquals(0.7854F, instance.computeArea(0.5F, 0.5F), DELTA);
    }

    // 7. One semi-axis is zero: a=0, b=5 -> 0
    @Test
    public void testComputeAreaZeroSemiAxis() {
        Ellipse instance = new Ellipse();
        assertEquals(0F, instance.computeArea(0F, 5F), DELTA);
    }

    // 8. Commutativity: area(a,b) == area(b,a) 
    @Test
    public void testComputeAreaIsCommutative() {
        Ellipse instance = new Ellipse();
        assertEquals(instance.computeArea(4F, 7F), instance.computeArea(7F, 4F), DELTA);
    }

    // 9. Elongated ellipse: a=10, b=1 -> 31.4159
    @Test
    public void testComputeAreaElongatedEllipse() {
        Ellipse instance = new Ellipse();
        assertEquals(31.4159F, instance.computeArea(10F, 1F), DELTA);
    }

    // 10. Negative value: must throw an exception
    @Test
    public void testComputeAreaNegativeValue() {
        Ellipse instance = new Ellipse();
        assertThrows(IllegalArgumentException.class, () -> instance.computeArea(-3F, 2F));
    }
}