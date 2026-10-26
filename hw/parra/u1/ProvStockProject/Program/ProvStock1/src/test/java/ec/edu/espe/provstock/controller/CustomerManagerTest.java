package ec.edu.espe.provstock.controller;

import ec.edu.espe.provstock.model.Customer;

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
public class CustomerManagerTest {

    public CustomerManagerTest() {
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
     * Test of getFinalConsumer method, of class CustomerManager.
     */
    @Test
    public void testGetFinalConsumerGood1() {
        System.out.println("getFinalConsumer - Good 1");
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
        System.out.println("registerCustomer - Good 1");
        CustomerManager instance = new CustomerManager();
        Customer customer = new Customer(0, Customer.CEDULA, "1710034065", "Matias Paucar", "0991234567", "matias@espe.edu.ec", "Sangolqui");
        boolean expResult = true;
        boolean result = instance.registerCustomer(customer);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerCustomer method, of class CustomerManager.
     */
    @Test
    public void testRegisterCustomerBad1() {
        System.out.println("registerCustomer - Bad 1");
        CustomerManager instance = new CustomerManager();
        Customer customer = new Customer(0, Customer.CEDULA, "1710034065", "Matias Paucar", "0991234567", "matias@espe.edu.ec", "Sangolqui");
        instance.registerCustomer(customer);
        Customer repeated = new Customer(0, Customer.CEDULA, "1710034065", "Otro Cliente", "", "", "");
        //this test fails on purpose: the expected value is wrong (35% failure requirement)
        boolean expResult = true;
        boolean result = instance.registerCustomer(repeated);
        assertEquals(expResult, result);
    }

    /**
     * Test of searchByName method, of class CustomerManager.
     */
    @Test
    public void testSearchByNameGood1() {
        System.out.println("searchByName - Good 1");
        CustomerManager instance = new CustomerManager();
        Customer customer = new Customer(0, Customer.CEDULA, "1710034065", "Matias Paucar", "0991234567", "matias@espe.edu.ec", "Sangolqui");
        instance.registerCustomer(customer);
        int expResult = 1;
        int result = instance.searchByName("paucar").size();
        assertEquals(expResult, result);
    }
    
}
