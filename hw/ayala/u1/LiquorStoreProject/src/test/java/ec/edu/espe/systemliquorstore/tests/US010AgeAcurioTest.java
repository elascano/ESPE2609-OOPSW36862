package ec.edu.espe.systemliquorstore.tests;

import ec.edu.espe.systemliquorstore.model.Sale;
import org.junit.Test;
import static org.junit.Assert.*;

public class US010AgeAcurioTest {

    @Test
    public void testAge1() {
        System.out.println("calculateAge1");
        Sale instance = new Sale(1, "2026-02-28", "OWNER", "CASH");

        assertEquals(17, instance.calculateAge("2008-02-29"));
    }

    @Test
    public void testAge2() {
        System.out.println("calculateAge2");
        Sale instance = new Sale(1, "2028-02-29", "OWNER", "CASH");

        assertEquals(18, instance.calculateAge("2010-02-28"));
        instance.confirmAge("2010-02-28");
        assertTrue(instance.isAgeVerified());
    }

    @Test
    public void testAge3() {
        System.out.println("calculateAge3");
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");

        assertThrows(IllegalArgumentException.class, () -> instance.confirmAge("2000-02-30"));

        assertFalse(instance.isAgeVerified());
    }

    @Test
    public void testAge4() {
        System.out.println("calculateAge4");
        Sale instance = new Sale(1, "2026-10-25", "OWNER", "CASH");

        assertEquals(26, instance.calculateAge(" 2000-10-25 "));
    }
}