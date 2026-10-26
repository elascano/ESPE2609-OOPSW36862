package ec.edu.espe.provstock.model;

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
public class SupplierTest {

    public SupplierTest() {
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
     * Test of validate method, of class Supplier.
     */
    @Test
    public void testValidateGood1() {
        System.out.println("validate - Good 1");
        Supplier instance = new Supplier(1, "1790011674001", "Electro Andes S.A.", "Carlos Parra", "022345678", "ventas@electroandes.com", "Quito");
        boolean expResult = true;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class Supplier.
     */
    @Test
    public void testValidateBad1() {
        System.out.println("validate - Bad 1");
        Supplier instance = new Supplier(1, "1790011674000", "Electro Andes S.A.", "Carlos Parra", "022345678", "ventas@electroandes.com", "Quito");
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = true;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class Supplier.
     */
    @Test
    public void testUpdateBad1() {
        System.out.println("update - Bad 1");
        Supplier instance = new Supplier(1, "1790011674001", "Electro Andes S.A.", "Carlos Parra", "022345678", "ventas@electroandes.com", "Quito");
        String email = "ventas.electroandes.com";
        assertThrows(IllegalArgumentException.class, () -> instance.update("Electro Andes S.A.", "022345678", email, "Quito"));
    }
    
}
