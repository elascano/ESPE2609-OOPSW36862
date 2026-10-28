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
 * @author Marcos Mamarandi, Binary Bosses,@ESPE
 */
public class RectanglePerimeterTest {
    
    public RectanglePerimeterTest() {
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
     * Test of calculatePerimeter method, of class RectanglePerimeter.
     */
    
@Test
public void testCalculatePerimeter1() {
    System.out.println("calculatePerimeter1");
    float base = 4.0F;
    float height = 3.0F;
    RectanglePerimeter instance = new RectanglePerimeter();
    float expResult = 14.0F;
    float result = instance.calculatePerimeter(base, height);
    assertEquals(expResult, result, 0);
}

@Test
public void testCalculatePerimeter2() {
    System.out.println("calculatePerimeter2");
    float base = 5.0F;
    float height = 2.0F;
    RectanglePerimeter instance = new RectanglePerimeter();
    float expResult = 14.0F;
    float result = instance.calculatePerimeter(base, height);
    assertEquals(expResult, result, 0);
}

@Test
public void testCalculatePerimeter3() {
    System.out.println("calculatePerimeter3");
    float base = 6.0F;
    float height = 4.0F;
    RectanglePerimeter instance = new RectanglePerimeter();
    float expResult = 20.0F;
    float result = instance.calculatePerimeter(base, height);
    assertEquals(expResult, result, 0);
}

@Test
public void testCalculatePerimeter4() {
    System.out.println("calculatePerimeter4");
    float base = 8.0F;
    float height = 3.0F;
    RectanglePerimeter instance = new RectanglePerimeter();
    float expResult = 22.0F;
    float result = instance.calculatePerimeter(base, height);
    assertEquals(expResult, result, 0);
}

@Test
public void testCalculatePerimeter5() {
    System.out.println("calculatePerimeter5");
    float base = 1.0F;
    float height = 1.0F;
    RectanglePerimeter instance = new RectanglePerimeter();
    float expResult = 4.0F;
    float result = instance.calculatePerimeter(base, height);
    assertEquals(expResult, result, 0);
}

@Test
public void testCalculatePerimeter6() {
    System.out.println("calculatePerimeter6");
    float base = 0.5F;
    float height = 0.5F;
    RectanglePerimeter instance = new RectanglePerimeter();
    float expResult = 2.0F;
    float result = instance.calculatePerimeter(base, height);
    assertEquals(expResult, result, 0);
}

@Test
public void testCalculatePerimeter7() {
    System.out.println("calculatePerimeter7");
    float base = 1000.0F;
    float height = 2000.0F;
    RectanglePerimeter instance = new RectanglePerimeter();
    float expResult = 6000.0F;
    float result = instance.calculatePerimeter(base, height);
    assertEquals(expResult, result, 0);
}

@Test
public void testCalculatePerimeter8() {
    System.out.println("calculatePerimeter8");
    float base = 1.0F;
    float height = 100.0F;
    RectanglePerimeter instance = new RectanglePerimeter();
    float expResult = 202.0F;
    float result = instance.calculatePerimeter(base, height);
    assertEquals(expResult, result, 0);
}

@Test
public void testCalculatePerimeter9() {
    System.out.println("calculatePerimeter9");
    float base = 7.0F;
    float height = 3.0F;
    RectanglePerimeter instance = new RectanglePerimeter();
    float expResult = 20.0F;
    float result = instance.calculatePerimeter(base, height);
    assertEquals(expResult, result, 0);
}

@Test
public void testCalculatePerimeter10() {
    System.out.println("calculatePerimeter10");
    float base = 9.0F;
    float height = 2.0F;
    RectanglePerimeter instance = new RectanglePerimeter();
    float expResult = 22.0F;
    float result = instance.calculatePerimeter(base, height);
    assertEquals(expResult, result, 0);
}

    
}
