package ec.edu.espe.systemliquorstore.tests;

import ec.edu.espe.systemliquorstore.model.Sale;
import org.junit.Test;
import static org.junit.Assert.*;

public class US010AgeTest {
    @Test
    public void testAge5() {
        System.out.println("calculateAge5");
        String birthDate = "2000-02-30";
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");
        boolean expResult = true;
        boolean result = false;
        try {
            instance.calculateAge(birthDate);
        } catch (IllegalArgumentException exception) {
            result = true;
        }
        assertEquals(expResult, result);
        assertFalse(instance.isAgeVerified());
    }

    @Test
    public void testAge6() {
        System.out.println("calculateAge6");
        String birthDate = "2026-10-26";
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");
        boolean expResult = true;
        boolean result = false;
        try {
            instance.calculateAge(birthDate);
        } catch (IllegalArgumentException exception) {
            result = true;
        }
        assertEquals(expResult, result);
        assertFalse(instance.isAgeVerified());
    }

    @Test
    public void testAge7() {
        System.out.println("calculateAge7");
        String birthDate = "";
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");
        boolean expResult = true;
        boolean result = false;
        try {
            instance.calculateAge(birthDate);
        } catch (IllegalArgumentException exception) {
            result = true;
        }
        assertEquals(expResult, result);
        assertFalse(instance.isAgeVerified());
    }
    @Test public void testAge8() {
        System.out.println("calculateAgeExactly18");
        String birthDate = "2008-10-25";
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");
        instance.confirmAge(birthDate);
        assertTrue(instance.isAgeVerified());
    }

    @Test public void testAge9() {
        System.out.println("calculateAgeUnder18");
        String birthDate = "2010-05-15";
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");
        assertThrows(IllegalStateException.class, () -> instance.confirmAge(birthDate));
        assertFalse(instance.isAgeVerified());
    }

    @Test public void testAge10() {
        System.out.println("completeWithoutAgeVerification");
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");
        
        boolean expResult = true;
        boolean result = false;
        try {
            instance.complete(new java.util.ArrayList<>(), new java.util.ArrayList<>());
        } catch (IllegalStateException ex) {
            result = true;
        }
        assertEquals(expResult, result);
    }
}
