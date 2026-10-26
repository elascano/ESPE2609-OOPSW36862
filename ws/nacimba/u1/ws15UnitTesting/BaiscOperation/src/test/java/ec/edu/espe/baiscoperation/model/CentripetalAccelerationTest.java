/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espe.baiscoperation.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Nacimba Mateo, Group5, @ESPE
 */
public class CentripetalAccelerationTest {
    
    public CentripetalAccelerationTest() {
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
     * Test of calculate method, of class CentripetalAcceleration.
     */
    @Test
    public void testCalculate() {
        System.out.println("calculate");
        float velocity = 0.0F;
        float radius = 0.0F;
        CentripetalAcceleration instance = new CentripetalAcceleration();
        float expResult = 0.0F;
        float result = instance.calculate(velocity, radius);
        assertEquals(expResult, result, 0);
       
    }
    
    @Test
public void testCalculate1() {
    // PASA: 10² / 5 = 20
    System.out.println("calculate1");
    float velocity = 10.0F;
    float radius = 5.0F;
    CentripetalAcceleration instance = new CentripetalAcceleration();
    float expResult = 20.0F;
    float result = instance.calculate(velocity, radius);
    assertEquals(expResult, result, 0);
}

@Test
public void testCalculate2() {
    // PASA: velocidad 0 → aceleración 0
    System.out.println("calculate2");
    float velocity = 0.0F;
    float radius = 5.0F;
    CentripetalAcceleration instance = new CentripetalAcceleration();
    float expResult = 0.0F;
    float result = instance.calculate(velocity, radius);
    assertEquals(expResult, result, 0);
}

@Test
public void testCalculate3() {
    // PASA: 3² / 4 = 2.25
    System.out.println("calculate3");
    float velocity = 3.0F;
    float radius = 4.0F;
    CentripetalAcceleration instance = new CentripetalAcceleration();
    float expResult = 2.25F;
    float result = instance.calculate(velocity, radius);
    assertEquals(expResult, result, 0);
}

@Test
public void testCalculate4() {
    // PASA: 6² / 3 = 12
    System.out.println("calculate4");
    float velocity = 6.0F;
    float radius = 3.0F;
    CentripetalAcceleration instance = new CentripetalAcceleration();
    float expResult = 12.0F;
    float result = instance.calculate(velocity, radius);
    assertEquals(expResult, result, 0);
}

@Test
public void testCalculate5() {
    // PASA: (-4)² / 2 = 8
    System.out.println("calculate5");
    float velocity = -4.0F;
    float radius = 2.0F;
    CentripetalAcceleration instance = new CentripetalAcceleration();
    float expResult = 8.0F;
    float result = instance.calculate(velocity, radius);
    assertEquals(expResult, result, 0);
}

@Test
public void testCalculate6() {
    // PASA: 5² / 2 = 12.5
    System.out.println("calculate6");
    float velocity = 5.0F;
    float radius = 2.0F;
    CentripetalAcceleration instance = new CentripetalAcceleration();
    float expResult = 12.5F;
    float result = instance.calculate(velocity, radius);
    assertEquals(expResult, result, 0);
}

@Test
public void testCalculate7() {
    // PASA: 20² / 10 = 40
    System.out.println("calculate7");
    float velocity = 20.0F;
    float radius = 10.0F;
    CentripetalAcceleration instance = new CentripetalAcceleration();
    float expResult = 40.0F;
    float result = instance.calculate(velocity, radius);
    assertEquals(expResult, result, 0);
}

@Test
public void testCalculate8() {
    // PASA: 10² / -5 = -20 (radio negativo da resultado negativo)
    System.out.println("calculate8");
    float velocity = 10.0F;
    float radius = -5.0F;
    CentripetalAcceleration instance = new CentripetalAcceleration();
    float expResult = -20.0F;
    float result = instance.calculate(velocity, radius);
    assertEquals(expResult, result, 0);
}
    
}
