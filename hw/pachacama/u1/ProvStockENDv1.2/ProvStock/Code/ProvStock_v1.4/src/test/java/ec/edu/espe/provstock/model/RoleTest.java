package ec.edu.espe.provstock.model;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class RoleTest {

    /**
     * Test of hasPermission method, of class Role.
     */
    @Test
    public void testHasPermissionGood1() {
        Role instance = new Role(1, Role.SALESPERSON, Arrays.asList(Role.VIEW_PRODUCTS, Role.REGISTER_SALE));
        boolean expResult = true;
        boolean result = instance.hasPermission(Role.REGISTER_SALE);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasPermission method, of class Role.
     */
    @Test
    public void testHasPermissionStrong1() {
        Role instance = new Role(1, Role.SALESPERSON, new ArrayList<>());
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = true;
        boolean result = instance.hasPermission(Role.VIEW_PRODUCTS);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasPermission method, of class Role.
     */
    @Test
    public void testHasPermissionBad1() {
        Role instance = new Role(1, Role.SALESPERSON, Arrays.asList(Role.VIEW_PRODUCTS, Role.REGISTER_SALE));
        boolean expResult = false;
        boolean result = instance.hasPermission(Role.VOID_SALE);
        assertEquals(expResult, result);
    }
    
}
