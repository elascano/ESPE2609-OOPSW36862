package ec.edu.espe.basicoperations.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
 */
public class SquareTest {
    
    public SquareTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    @Test
    public void testSomeMethod() {

    }

    /**
     * Test of calculateVoltage method, of class Square.
     */
    @Test
    public void testCalculateVoltage() {
        System.out.println("calculateVoltage");
        float current = 4.0F;
        float resistance = 5.0F;
        Square instance = new Square();
        float expResult = 20.0F;
        float result = instance.calculateVoltage(current, resistance);
        assertEquals(expResult, result, 0);

    }

    /**
     * Test of calculateCurrent method, of class Square.
     */
    @Test
    public void testCalculateCurrent() {
        System.out.println("calculateCurrent");
        float voltage = 500.0F;
        float resistance = 2.5F;
        Square instance = new Square();
        float expResult = 200.0F;
        float result = instance.calculateCurrent(voltage, resistance);
        assertEquals(expResult, result, 0);

    }

    /**
     * Test of calculateResistance method, of class Square.
     */
    @Test
    public void testCalculateResistance() {
        System.out.println("calculateResistance");
        float voltage = 500.0F;
        float current = 2.5F;
        Square instance = new Square();
        float expResult = 200.0F;
        float result = instance.calculateResistance(voltage, current);
        assertEquals(expResult, result, 0);

    }
    
}
