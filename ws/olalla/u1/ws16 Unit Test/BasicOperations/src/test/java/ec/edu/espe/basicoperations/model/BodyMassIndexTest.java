
package ec.edu.espe.basicoperations.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BodyMassIndexTest {

    @Test
    public void testCalculateBMIWithNegativeWeightShouldFail() {
        BodyMassIndex instance = new BodyMassIndex();
        assertTrue(instance.calculateBMI(-64.0f, 1.6f) < 0);
    }

    @Test
    public void testCalculateBMIWithInfiniteResult() {
        BodyMassIndex instance = new BodyMassIndex();
        assertThrows(IllegalArgumentException.class, () -> {
            instance.calculateBMI(60.0f, 0.0f);
        });
    }

    @Test
    public void testCalculateBMIWithAnotherValue() {
        BodyMassIndex instance = new BodyMassIndex();
        assertEquals(24.22f, instance.calculateBMI(70.0f, 1.7f), 0.01f);
    }

    @Test
    public void testCalculateBMIWithZeroWeight() {
        BodyMassIndex instance = new BodyMassIndex();
        assertEquals(0.0f, instance.calculateBMI(0.0f, 1.7f), 0.01f);
    }

    @Test
    public void testCalculateBMIWithIntegerValues() {
        BodyMassIndex instance = new BodyMassIndex();
        assertEquals(20.0f, instance.calculateBMI(80.0f, 2.0f), 0.01f);
    }

    @Test
    public void testCalculateBMIWithSmallWeight() {
        BodyMassIndex instance = new BodyMassIndex();
        assertEquals(12.5f, instance.calculateBMI(20.0f, 1.264911f), 0.01f);
    }

    @Test
    public void testCalculateBMIWithLargeWeight() {
        BodyMassIndex instance = new BodyMassIndex();
        assertEquals(40.0f, instance.calculateBMI(100.0f, 1.581139f), 0.01f);
    }

    @Test
    public void testCalculateBMIWithHeightOne() {
        BodyMassIndex instance = new BodyMassIndex();
        assertEquals(60.0f, instance.calculateBMI(60.0f, 1.0f), 0.01f);
    }

    @Test
    public void testCalculateBMIWithDecimalValues() {
        BodyMassIndex instance = new BodyMassIndex();
        assertEquals(22.84f, instance.calculateBMI(65.0f, 1.687f), 0.02f);
    }

    @Test
    public void testCalculateBMIWithNegativeWeight() {
        BodyMassIndex instance = new BodyMassIndex();
        assertEquals(-25.0f, instance.calculateBMI(-64.0f, 1.6f), 0.01f);
    }

    @Test
    public void testCalculateBMIWithNegativeHeight() {
        BodyMassIndex instance = new BodyMassIndex();
        assertEquals(25.0f, instance.calculateBMI(64.0f, -1.6f), 0.01f);
    }
}
