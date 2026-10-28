package ec.edu.espe.provstock.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class CustomerTest {

    /**
     * Test of validate method, of class Customer.
     */
    @Test
    public void testValidateGood1() {
        Customer instance = new Customer(1, Customer.CEDULA, "1710034065", "Matias Paucar", "0991234567", "matias@espe.edu.ec", "Sangolqui");
        boolean expResult = true;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class Customer.
     */
    @Test
    public void testValidateGood2() {
        Customer instance = new Customer(1, Customer.RUC, "1790011674001", "Electro Andes S.A.", "0991234567", "matias@espe.edu.ec", "Sangolqui");
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class Customer.
     */
    @Test
    public void testValidateGood3() {
        Customer instance = new Customer(1, Customer.PASSPORT, "AB123456", "John Smith", "0991234567", "matias@espe.edu.ec", "Sangolqui");
        boolean expResult = true;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class Customer.
     */
    @Test
    public void testValidateBad1() {
        Customer instance = new Customer(1, Customer.CEDULA, "1710034064", "Matias Paucar", "0991234567", "matias@espe.edu.ec", "Sangolqui");
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class Customer.
     */
    @Test
    public void testValidateBad2() {
        Customer instance = new Customer(1, Customer.CEDULA, "1710034065", " ", "0991234567", "matias@espe.edu.ec", "Sangolqui");
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = true;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class Customer.
     */
    @Test
    public void testValidateBad3() {
        Customer instance = new Customer(1, "DNI", "1710034065", "Matias Paucar", "0991234567", "matias@espe.edu.ec", "Sangolqui");
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of isFinalConsumer method, of class Customer.
     */
    @Test
    public void testIsFinalConsumerGood1() {
        Customer instance = new Customer(1, Customer.RUC, Customer.FINAL_CONSUMER_ID, "CONSUMIDOR FINAL", "", "", "");
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = false;
        boolean result = instance.isFinalConsumer();
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class Customer.
     */
    @Test
    public void testUpdateBad1() {
        Customer instance = new Customer(1, Customer.RUC, Customer.FINAL_CONSUMER_ID, "CONSUMIDOR FINAL", "", "", "");
        assertThrows(IllegalStateException.class, () -> instance.update("Otro nombre", "", "", ""));
    }

    /**
     * Test of deactivate method, of class Customer.
     */
    @Test
    public void testDeactivateGood1() {
        Customer instance = new Customer(1, Customer.CEDULA, "1710034065", "Matias Paucar", "0991234567", "matias@espe.edu.ec", "Sangolqui");
        instance.deactivate();
        boolean expResult = false;
        boolean result = instance.isActive();
        assertEquals(expResult, result);
    }
    
}
