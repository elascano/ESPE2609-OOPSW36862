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
 * @author Demian Armas, Logical Lords, @ESPE
 */
public class AreaOfCircleTest {
    
    public AreaOfCircleTest() {
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
     * Test of calculatetAreaOfCircle method, of class AreaOfCircle.
     */
    @Test
    public void testCalculatetAreaOfCircleRadio0() {
        System.out.println("calculatetAreaOfCircle");
        float radio = 1.0F;
        AreaOfCircle instance = new AreaOfCircle();
        float expResult = 3.1415927F;
        float result = instance.calculatetAreaOfCircle(radio);
        assertEquals(expResult, result, 0);
        
    }
    
    @Test
    public void testCalculatetAreaOfCircleRadio1() {
        System.out.println("calculatetAreaOfCircle");
        float radio = 2.0F;
        AreaOfCircle instance = new AreaOfCircle();
        float expResult = 12.5663706F;
        float result = instance.calculatetAreaOfCircle(radio);
        assertEquals(expResult, result, 0);
        
    }
    
    @Test
    public void testCalculatetAreaOfCircleRadio2() {
        System.out.println("calculatetAreaOfCircle");
        float radio = 0.5F;
        AreaOfCircle instance = new AreaOfCircle();
        float expResult = 0.7853982F;
        float result = instance.calculatetAreaOfCircle(radio);
        assertEquals(expResult, result, 0);
        
    }
    
    @Test
    public void testCalculatetAreaOfCircleRadio3() {
        System.out.println("calculatetAreaOfCircle");
        float radio = 1.0F;
        AreaOfCircle instance = new AreaOfCircle();
        float expResult = 3.1415927F;
        float result = instance.calculatetAreaOfCircle(radio);
        assertEquals(expResult, result, 0);
        
    }
    
    @Test
    public void testCalculatetAreaOfCircleRadio4() {
        System.out.println("calculatetAreaOfCircle");
        float radio = 2.0F;
        AreaOfCircle instance = new AreaOfCircle();
        float expResult = 12.5663706F;
        float result = instance.calculatetAreaOfCircle(radio);
        assertEquals(expResult, result, 0);
        
    }
    
    @Test
    public void testCalculatetAreaOfCircleRadio5() {
        System.out.println("calculatetAreaOfCircle");
        float radio = 0.5F;
        AreaOfCircle instance = new AreaOfCircle();
        float expResult = 0.7853982F;
        float result = instance.calculatetAreaOfCircle(radio);
        assertEquals(expResult, result, 0);
        
    }
    
    @Test
    public void testCalculatetAreaOfCircleRadio6() {
        System.out.println("calculatetAreaOfCircle");
        float radio = -1.0F;
        AreaOfCircle instance = new AreaOfCircle();
        float expResult = -1;
        float result = instance.calculatetAreaOfCircle(radio);
        assertEquals(expResult, result, 0);
        
    }
    
    @Test
    public void testCalculatetAreaOfCircleRadio7() {
        System.out.println("calculatetAreaOfCircle");
        float radio = -2.5F;
        AreaOfCircle instance = new AreaOfCircle();
        float expResult = 19.64F;
        float result = instance.calculatetAreaOfCircle(radio);
        assertEquals(expResult, result, 0);
        
    }
    
    @Test
    public void testCalculatetAreaOfCircleRadio8() {
        System.out.println("calculatetAreaOfCircle");
        float radio = -3.0F;
        AreaOfCircle instance = new AreaOfCircle();
        float expResult = 28.274334F;
        float result = instance.calculatetAreaOfCircle(radio);
        assertEquals(expResult, result, 0);
        
    }
    @Test
    public void testCalculatetAreaOfCircleRadio9() {
        System.out.println("calculatetAreaOfCircle");
        float radio = 0.0F;
        AreaOfCircle instance = new AreaOfCircle();
        float expResult = 0.0F;
        float result = instance.calculatetAreaOfCircle(radio);
        assertEquals(expResult, result, 0);
        
    }
    
    
}
