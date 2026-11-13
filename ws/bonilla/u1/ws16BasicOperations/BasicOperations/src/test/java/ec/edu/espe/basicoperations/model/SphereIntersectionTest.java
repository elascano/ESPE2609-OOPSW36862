package ec.edu.espe.basicoperations.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Angel Bonilla, @ESPE
 */
public class SphereIntersectionTest {

    private static final float DELTA = 0.01F;


    @Test
    public void testComputeVolumeNormalCase() {
        SphereIntersection instance = new SphereIntersection();
        assertEquals(12.5664F, instance.computeVolume(3F, 2F, 3F), DELTA);
    }

    @Test
    public void testComputeVolumeEqualSpheres() {
        SphereIntersection instance = new SphereIntersection();
        assertEquals(35.3429F, instance.computeVolume(3F, 3F, 3F), DELTA);
    }

    @Test
    public void testComputeVolumeUnitValues() {
        SphereIntersection instance = new SphereIntersection();
        assertEquals(1.3090F, instance.computeVolume(1F, 1F, 1F), DELTA);
    }


    @Test
    public void testComputeVolumeDecimals() {
        SphereIntersection instance = new SphereIntersection();
        assertEquals(8.9012F, instance.computeVolume(2.5F, 1.5F, 2.0F), DELTA);
    }


    @Test
    public void testComputeVolumeLargeValues() {
        SphereIntersection instance = new SphereIntersection();
        assertEquals(1308.9969F, instance.computeVolume(10F, 10F, 10F), DELTA);
    }


    @Test
    public void testComputeVolumeSmallValues() {
        SphereIntersection instance = new SphereIntersection();
        assertEquals(0.1636F, instance.computeVolume(0.5F, 0.5F, 0.5F), DELTA);
    }


    @Test
    public void testComputeVolumeZeroRadius() {
        SphereIntersection instance = new SphereIntersection();
        assertEquals(0.0F, instance.computeVolume(0F, 5F, 2F), DELTA);
    }


    @Test
    public void testComputeVolumeAsymmetricSpheres() {
        SphereIntersection instance = new SphereIntersection();
        assertEquals(25.9181F, instance.computeVolume(4F, 2F, 3F), DELTA);
    }


    @Test
    public void testComputeVolumeIsCommutative() {
        SphereIntersection instance = new SphereIntersection();
        assertEquals(instance.computeVolume(4F, 2F, 3F), instance.computeVolume(2F, 4F, 3F), DELTA);
    }

 
    @Test
    public void testComputeVolumeNegativeValue() {
        SphereIntersection instance = new SphereIntersection();
        assertThrows(IllegalArgumentException.class, () -> {
            instance.computeVolume(-3F, 2F, 3F);
        });
    }
}