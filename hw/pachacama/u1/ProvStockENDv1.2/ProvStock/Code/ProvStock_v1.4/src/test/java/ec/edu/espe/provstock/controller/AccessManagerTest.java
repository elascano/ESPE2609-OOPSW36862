package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.Role;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class AccessManagerTest {

    /**
     * Test of login method, of class AccessManager.
     */
    @Test
    public void testLoginGood1() {
        AccessManager instance = new AccessManager();
        boolean expResult = true;
        boolean result = instance.login("owner", "Owner2026");
        assertEquals(expResult, result);
    }

    /**
     * Test of login method, of class AccessManager.
     */
    @Test
    public void testLoginBad1() {
        AccessManager instance = new AccessManager();
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = true;
        boolean result = instance.login("owner", "owner2026");
        assertEquals(expResult, result);
    }

    /**
     * Test of createUser method, of class AccessManager.
     */
    @Test
    public void testCreateUserBad1() {
        AccessManager instance = new AccessManager();
        Role role = instance.findRole(Role.SALESPERSON);
        assertThrows(SecurityException.class, () -> instance.createUser("mpaucar", "Matias Paucar", role, "Espe2026"));
    }

    /**
     * Test of checkPermission method, of class AccessManager.
     */
    @Test
    public void testCheckPermissionGood1() {
        AccessManager instance = new AccessManager();
        instance.login("owner", "Owner2026");
        instance.createUser("mpaucar", "Matias Paucar", instance.findRole(Role.SALESPERSON), "Espe2026");
        instance.logout();
        instance.login("mpaucar", "Espe2026");
        boolean expResult = false;
        boolean result = instance.checkPermission(Role.VOID_SALE);
        assertEquals(expResult, result);
    }
    
}
