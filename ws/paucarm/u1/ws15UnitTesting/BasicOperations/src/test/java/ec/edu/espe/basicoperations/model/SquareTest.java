package ec.edu.espe.basicoperations.model;

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
public class SquareTest {
    
    public SquareTest() {
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
     * Test of computeArea method, of class Square.
     */
    @Test
    public void testComputeAreaGood1() {
        System.out.println("computeArea - Good 1");
        float side = 2.0F;
        Square instance = new Square();
        float expResult = 4.0F;
        float result = instance.computeArea(side);
        assertEquals(expResult, result, 0);
        
    }
    @Test
    public void testComputeAreaGood2() {
        System.out.println("computeArea - Good 2");
        float side = 5.0F;
        Square instance = new Square();
        float expResult = 25.0F;
        float result = instance.computeArea(side);
        assertEquals(expResult, result, 0);
    }
    @Test
    public void testComputeAreaGood3() {
        System.out.println("computeArea - Good 3");
        float side = 12.0F;
        Square instance = new Square();
        float expResult = 144.0F;
        float result = instance.computeArea(side);
        assertEquals(expResult, result, 0);
    }
    
    @Test
    public void testComputeAreaStrong1() {
        System.out.println("computeArea - Strong 1");
        float side = 0.0F;
        Square instance = new Square();
        float expResult = 0.0F;
        float result = instance.computeArea(side);
        assertEquals(expResult, result, 0);
    }
    @Test
    public void testComputeAreaStrong2() {
        System.out.println("computeArea - Strong 2");
        float side = 2.5F;
        Square instance = new Square();
        float expResult = 6.25F;
        float result = instance.computeArea(side);
        assertEquals(expResult, result, 0.0001);
    }
    @Test
    public void testComputeAreaStrong3() {
        System.out.println("computeArea - Strong 3");
        float side = 0.5F;
        Square instance = new Square();
        float expResult = 0.25F;
        float result = instance.computeArea(side);
        assertEquals(expResult, result, 0.0001);
    }
    @Test
    public void testComputeAreaStrong4() {
        System.out.println("computeArea - Strong 4");
        float side = 9.9F;
        Square instance = new Square();
        float expResult = 98.01F;
        float result = instance.computeArea(side);
        assertEquals(expResult, result, 0.01);
    }
    @Test
    public void testComputeAreaBad1() {
        System.out.println("computeArea - Bad 1");
        float side = 3.5F;
        Square instance = new Square();
        float expResult = 12.25F;
        float result = instance.computeArea(side);
        assertEquals(expResult, result, 0);
    }
    @Test
    public void testComputeAreaBad2() {
        System.out.println("computeArea - Bad 2");
        float side = 1.5F;
        Square instance = new Square();
        float expResult = 2.251F;
        float result = instance.computeArea(side);
        assertEquals(expResult, result, 0);
    }
    @Test
    public void testComputeAreaBad3() {
        System.out.println("computeArea - Bad 3");
        float side = 8.0F;
        Square instance = new Square();
        float expResult = 64.0F;
        float result = instance.computeArea(side);
        assertEquals(expResult, result, 0);
    }
    
}
