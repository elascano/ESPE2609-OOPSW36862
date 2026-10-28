package ec.edu.espe.provstock.model;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class UserTest {

    /**
     * Test of verifyPassword method, of class User.
     */
    @Test
    public void testVerifyPasswordGood1() {
        Role role = new Role(1, Role.SALESPERSON, Arrays.asList(Role.VIEW_PRODUCTS, Role.REGISTER_SALE));
        User instance = new User(1, "mpaucar", "Matias Paucar", role, "Espe2026");
        boolean expResult = true;
        boolean result = instance.verifyPassword("Espe2026");
        assertEquals(expResult, result);
    }

    /**
     * Test of verifyPassword method, of class User.
     */
    @Test
    public void testVerifyPasswordBad1() {
        Role role = new Role(1, Role.SALESPERSON, Arrays.asList(Role.VIEW_PRODUCTS, Role.REGISTER_SALE));
        User instance = new User(1, "mpaucar", "Matias Paucar", role, "Espe2026");
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = true;
        boolean result = instance.verifyPassword("espe2026");
        assertEquals(expResult, result);
    }

    /**
     * Test of verifyPassword method, of class User.
     */
    @Test
    public void testVerifyPasswordBad2() {
        Role role = new Role(1, Role.SALESPERSON, Arrays.asList(Role.VIEW_PRODUCTS, Role.REGISTER_SALE));
        User instance = new User(1, "mpaucar", "Matias Paucar", role, "Espe2026");
        boolean expResult = false;
        boolean result = instance.verifyPassword(null);
        assertEquals(expResult, result);
    }

    /**
     * Test of resetPassword method, of class User.
     */
    @Test
    public void testResetPasswordStrong1() {
        Role role = new Role(1, Role.SALESPERSON, Arrays.asList(Role.VIEW_PRODUCTS, Role.REGISTER_SALE));
        User instance = new User(1, "mpaucar", "Matias Paucar", role, "Espe2026");
        String newPassword = "12345678";
        instance.resetPassword(newPassword);
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = false;
        boolean result = instance.verifyPassword(newPassword);
        assertEquals(expResult, result);
    }

    /**
     * Test of resetPassword method, of class User.
     */
    @Test
    public void testResetPasswordBad1() {
        Role role = new Role(1, Role.SALESPERSON, Arrays.asList(Role.VIEW_PRODUCTS, Role.REGISTER_SALE));
        User instance = new User(1, "mpaucar", "Matias Paucar", role, "Espe2026");
        String newPassword = "1234567";
        assertThrows(IllegalArgumentException.class, () -> instance.resetPassword(newPassword));
    }

    /**
     * Test of changePassword method, of class User.
     */
    @Test
    public void testChangePasswordGood1() {
        Role role = new Role(1, Role.SALESPERSON, Arrays.asList(Role.VIEW_PRODUCTS, Role.REGISTER_SALE));
        User instance = new User(1, "mpaucar", "Matias Paucar", role, "Espe2026");
        boolean expResult = true;
        boolean result = instance.changePassword("Espe2026", "NuevaClave1", "NuevaClave1");
        assertEquals(expResult, result);
    }

    /**
     * Test of changePassword method, of class User.
     */
    @Test
    public void testChangePasswordBad1() {
        Role role = new Role(1, Role.SALESPERSON, Arrays.asList(Role.VIEW_PRODUCTS, Role.REGISTER_SALE));
        User instance = new User(1, "mpaucar", "Matias Paucar", role, "Espe2026");
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = true;
        boolean result = instance.changePassword("Espe2026", "NuevaClave1", "NuevaClave2");
        assertEquals(expResult, result);
    }

    /**
     * Test of hasPermission method, of class User.
     */
    @Test
    public void testHasPermissionBad1() {
        Role role = new Role(1, Role.SALESPERSON, Arrays.asList(Role.VIEW_PRODUCTS, Role.REGISTER_SALE));
        User instance = new User(1, "mpaucar", "Matias Paucar", role, "Espe2026");
        instance.deactivate();
        boolean expResult = false;
        boolean result = instance.hasPermission(Role.REGISTER_SALE);
        assertEquals(expResult, result);
    }
    
}
