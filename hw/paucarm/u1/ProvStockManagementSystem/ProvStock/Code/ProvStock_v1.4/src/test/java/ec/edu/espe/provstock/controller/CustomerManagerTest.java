package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class CustomerManagerTest {

    /**
     * Test of getFinalConsumer method, of class CustomerManager.
     */
    @Test
    public void testGetFinalConsumerGood1() {
        CustomerManager instance = new CustomerManager();
        String expResult = Customer.FINAL_CONSUMER_ID;
        String result = instance.getFinalConsumer().getIdentificationNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of registerCustomer method, of class CustomerManager.
     */
    @Test
    public void testRegisterCustomerGood1() {
        CustomerManager instance = new CustomerManager();
        Customer customer = new Customer(0, Customer.CEDULA, "1710034065", "Matias Paucar", "0991234567", "matias@espe.edu.ec", "Sangolqui");
        instance.registerCustomer(customer);
        boolean expResult = true;
        boolean result = instance.isRegistered("1710034065");
        assertEquals(expResult, result);
    }

    /**
     * Test of registerCustomer method, of class CustomerManager.
     */
    @Test
    public void testRegisterCustomerBad1() {
        CustomerManager instance = new CustomerManager();
        Customer customer = new Customer(0, Customer.CEDULA, "1710034065", "Matias Paucar", "0991234567", "matias@espe.edu.ec", "Sangolqui");
        instance.registerCustomer(customer);
        Customer repeated = new Customer(0, Customer.CEDULA, "1710034065", "Otro Cliente", "", "", "");
        //this test fails on purpose: the expected exception is wrong (35% failure requirement)
        assertThrows(IllegalStateException.class, () -> instance.registerCustomer(repeated));
    }

    /**
     * Test of searchByName method, of class CustomerManager.
     */
    @Test
    public void testSearchByNameGood1() {
        CustomerManager instance = new CustomerManager();
        Customer customer = new Customer(0, Customer.CEDULA, "1710034065", "Matias Paucar", "0991234567", "matias@espe.edu.ec", "Sangolqui");
        instance.registerCustomer(customer);
        int expResult = 1;
        int result = instance.searchByName("paucar").size();
        assertEquals(expResult, result);
    }
    
}
