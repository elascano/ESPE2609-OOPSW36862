package ec.edu.espe.basicoperations.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author David Clavón, Infinite Loops, @ESPE
 */
public class OperationTest {
    
    public OperationTest() {
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
     * Test of add method, of class Operation.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        float addend1 = 1.2F;
        float addend2 = 3.4F;
        Operation instance = new Operation();
        float expResult = 4.6F;
        float result = instance.add(addend1, addend2);
        assertEquals(expResult, result, 0);
        
    }

    /**
     * Test of substract method, of class Operation.
     */
    @Test
    public void testSubstract() {
        System.out.println("substract");
        float minuhed = 2.0F;
        float subtrahend = 4.0F;
        Operation instance = new Operation();
        float expResult = -2.0F;
        float result = instance.substract(minuhed, subtrahend);
        assertEquals(expResult, result, 0);
        
    }

    /**
     * Test of multiply method, of class Operation.
     */
    @Test
    public void testMultiply() {
        System.out.println("multiply");
        float multiplicand = 3.0F;
        float multiplier = 2.0F;
        Operation instance = new Operation();
        float expResult = 6.0F;
        float result = instance.multiply(multiplicand, multiplier);
        assertEquals(expResult, result, 0);
       
    }

    /**
     * Test of divide method, of class Operation.
     */
    @Test
    public void testDivide() {
        System.out.println("divide");
        float dividend = 6.0F;
        float divisor = 2.0F;
        Operation instance = new Operation();
        float expResult = 3.0F;
        float result = instance.divide(dividend, divisor);
        assertEquals(expResult, result, 0);
  
    }
    
}
