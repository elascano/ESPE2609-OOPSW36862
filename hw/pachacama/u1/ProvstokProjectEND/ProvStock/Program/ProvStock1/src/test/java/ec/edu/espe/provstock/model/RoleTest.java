package ec.edu.espe.provstock.model;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class RoleTest {

    public RoleTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of hasPermission method, of class Role.
     */
    @Test
    public void testHasPermissionGood1() {
        System.out.println("hasPermission - Good 1");
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
        System.out.println("hasPermission - Strong 1");
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
        System.out.println("hasPermission - Bad 1");
        Role instance = new Role(1, Role.SALESPERSON, Arrays.asList(Role.VIEW_PRODUCTS, Role.REGISTER_SALE));
        boolean expResult = false;
        boolean result = instance.hasPermission(Role.VOID_SALE);
        assertEquals(expResult, result);
    }
    
}
