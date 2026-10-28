/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espe.basicoperations.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamic, @ESPE
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
     * Test of calculateSphereArea method, of class Operation.
     */
    @Test
    public void testCalculateSphereAreaNumber() {
        System.out.println("calculateSphereArea");
        double radius = 2;
        double expResult = 50.265;
        double result = Operation.calculateSphereArea(radius);
        assertEquals(expResult, result, 0);
    }
    @Test
    public void testCalculateSphereAreaDecimal() {
        System.out.println("calculateSphereArea");
        double radius = 4.5;
        double expResult = 254.469;
        double result = Operation.calculateSphereArea(radius);
        assertEquals(expResult, result, 0);
    }
    @Test
    public void testCalculateSphereAreaNegative() {
        System.out.println("calculateSphereArea");
        double radius = -7;
        double expResult = 0;
        double result = Operation.calculateSphereArea(radius);
        assertEquals(expResult, result, 0);
    }
    @Test
    public void testCalculateSphereAreaLongNumber() {
        System.out.println("calculateSphereArea");
        double radius = 800;
        double expResult = 8042477.193;
        double result = Operation.calculateSphereArea(radius);
        assertEquals(expResult, result, 0);
    }
    @Test
    public void testCalculateSphereAreaLongDecimal() {
        System.out.println("calculateSphereArea");
        double radius = 5.36982;
        double expResult = 362.351;
        double result = Operation.calculateSphereArea(radius);
        assertEquals(expResult, result, 0);
    }
    @Test
    public void testCalculateSphereAreaLetter() {
        System.out.println("calculateSphereArea");
        double radius = 'r';
        double expResult = 0;
        double result = Operation.calculateSphereArea(radius);
        assertEquals(expResult, result, 0);
    }
    @Test
    public void testCalculateSphereAreaNumber0() {
        System.out.println("calculateSphereArea");
        double radius = 0.0;
        double expResult = 0.0;
        double result = Operation.calculateSphereArea(radius);
        assertEquals(expResult, result, 0);
    }
    @Test
    public void testCalculateSphereAreaNumberPowered() {
        System.out.println("calculateSphereArea");
        double radius = Math.pow(1, 3);
        double expResult = 12.566;
        double result = Operation.calculateSphereArea(radius);
        assertEquals(expResult, result, 0);
    }
    @Test
    public void testCalculateSphereAreaPi() {
        System.out.println("calculateSphereArea");
        double radius = 'π';
        double expResult = 124.025;
        double result = Operation.calculateSphereArea(radius);
        assertEquals(expResult, result, 0);
    }
    @Test
    public void testCalculateSphereAreaNull() {
        System.out.println("calculateSphereArea");
        double radius = Double.NaN;
        double expResult = 0;
        double result = Operation.calculateSphereArea(radius);
        assertEquals(expResult, result, 0);
    }
    
}
