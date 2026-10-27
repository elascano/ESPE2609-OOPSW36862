package ec.edu.espe.basicoperations.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VolumeTorusTest {


    private VolumeTorus torus;

    @BeforeEach
    public void setUp() {
        torus = new VolumeTorus();
    }


    @Test
    public void testVolumeR3r1() {
        assertEquals(59.2176f, torus.computeVolume(3, 1), 0.01f);
    }

    @Test
    public void testVolumeEqualRadii() {
        assertEquals(19.7392f, torus.computeVolume(1, 1), 0.01f);
    }

    @Test
    public void testVolumeR2r1() {
        assertEquals(39.4784f, torus.computeVolume(2, 1), 0.01f);
    }

    @Test
    public void testVolumeR5r2() {
        assertEquals(394.7842f, torus.computeVolume(5, 2), 0.01f);
    }

    @Test
    public void testVolumeZeroMajorRadius() {
        assertEquals(0f, torus.computeVolume(0, 1), 0.01f);
    }

    @Test
    public void testVolumeR2r5() {
        assertEquals(986.9604f, torus.computeVolume(2, 5), 0.01f);
    }

    @Test
    public void testVolumeDecimalMinorRadius() {
        assertEquals(19.7392f, torus.computeVolume(4, 0.5f), 0.01f);
    }


    @Test
    public void testFailRoundedExpectedValue() {
        assertEquals(60f, torus.computeVolume(3, 1), 0.01f);
    }

    @Test
    public void testFailUsesAreaInsteadOfVolume() {
        assertEquals(78.9568f, torus.computeVolume(2, 1), 0.01f);
    }

    @Test
    public void testFailExpectsExceptionForNegativeRadius() {
        assertThrows(IllegalArgumentException.class, () -> torus.computeVolume(-3, 1));
    }
}