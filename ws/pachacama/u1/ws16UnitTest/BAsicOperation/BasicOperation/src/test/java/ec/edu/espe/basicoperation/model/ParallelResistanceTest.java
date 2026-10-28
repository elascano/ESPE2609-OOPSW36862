/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espe.basicoperation.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Andres Pachacama, Byte Work, @ESPE
 */
public class ParallelResistanceTest {

    public ParallelResistanceTest() {
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
     * Test 1: two equal resistances, the result is half of one of them.
     */
    @Test
    public void testTwoEqualResistances() {
        System.out.println("two equal resistances");
        float resistance1 = 100.0F;
        float resistance2 = 100.0F;
        ParallelResistance instance = new ParallelResistance();
        float expResult = 50.0F;
        float result = instance.calculateEquivalentResistance(resistance1, resistance2);
        assertEquals(expResult, result, 0.0001F);

    }

    /**
     * Test 2: two small equal resistances.
     */
    @Test
    public void testTwoSmallEqualResistances() {
        System.out.println("two small equal resistances");
        float resistance1 = 2.0F;
        float resistance2 = 2.0F;
        ParallelResistance instance = new ParallelResistance();
        float expResult = 1.0F;
        float result = instance.calculateEquivalentResistance(resistance1, resistance2);
        assertEquals(expResult, result, 0.0001F);

    }

    /**
     * Test 3: a single resistance, the result is the same resistance.
     */
    @Test
    public void testSingleResistance() {
        System.out.println("single resistance");
        float resistance1 = 47.0F;
        ParallelResistance instance = new ParallelResistance();
        float expResult = 47.0F;
        float result = instance.calculateEquivalentResistance(resistance1);
        assertEquals(expResult, result, 0.0001F);

    }

    /**
     * Test 4: three different resistances (10, 20 and 30 ohms).
     */
    @Test
    public void testThreeDifferentResistances() {
        System.out.println("three different resistances");
        float resistance1 = 10.0F;
        float resistance2 = 20.0F;
        float resistance3 = 30.0F;
        ParallelResistance instance = new ParallelResistance();
        float expResult = 5.4545F;
        float result = instance.calculateEquivalentResistance(resistance1, resistance2, resistance3);
        assertEquals(expResult, result, 0.0001F);

    }

    /**
     * Test 5: four equal resistances, the result is one of them divided by four.
     */
    @Test
    public void testFourEqualResistances() {
        System.out.println("four equal resistances");
        float resistance1 = 200.0F;
        float resistance2 = 200.0F;
        float resistance3 = 200.0F;
        float resistance4 = 200.0F;
        ParallelResistance instance = new ParallelResistance();
        float expResult = 50.0F;
        float result = instance.calculateEquivalentResistance(resistance1, resistance2, resistance3, resistance4);
        assertEquals(expResult, result, 0.0001F);

    }

    /**
     * Test 6: a very big and a very small resistance, the result must be
     * smaller than the smallest resistance.
     */
    @Test
    public void testBigAndSmallResistances() {
        System.out.println("big and small resistances");
        float resistance1 = 1000000.0F;
        float resistance2 = 1.0F;
        ParallelResistance instance = new ParallelResistance();
        float expResult = 0.999999F;
        float result = instance.calculateEquivalentResistance(resistance1, resistance2);
        assertEquals(expResult, result, 0.0001F);
        assertTrue(result < resistance2);

    }

    /**
     * Test 7: decimal resistances (1.5 and 3.0 ohms).
     */
    @Test
    public void testDecimalResistances() {
        System.out.println("decimal resistances");
        float resistance1 = 1.5F;
        float resistance2 = 3.0F;
        ParallelResistance instance = new ParallelResistance();
        float expResult = 1.0F;
        float result = instance.calculateEquivalentResistance(resistance1, resistance2);
        assertEquals(expResult, result, 0.0001F);

    }

    /**
     * Test 8: a resistance of zero must throw an exception.
     */
    @Test
    public void testZeroResistance() {
        System.out.println("zero resistance");
        float resistance1 = 0.0F;
        float resistance2 = 100.0F;
        ParallelResistance instance = new ParallelResistance();
        assertThrows(IllegalArgumentException.class, () -> {
            instance.calculateEquivalentResistance(resistance1, resistance2);
        });

    }

    /**
     * Test 9: a negative resistance must throw an exception.
     */
    @Test
    public void testNegativeResistance() {
        System.out.println("negative resistance");
        float resistance1 = -50.0F;
        float resistance2 = 100.0F;
        ParallelResistance instance = new ParallelResistance();
        assertThrows(IllegalArgumentException.class, () -> {
            instance.calculateEquivalentResistance(resistance1, resistance2);
        });

    }

    /**
     * Test 10: calling the method without resistances must throw an exception.
     */
    @Test
    public void testNoResistances() {
        System.out.println("no resistances");
        ParallelResistance instance = new ParallelResistance();
        assertThrows(IllegalArgumentException.class, () -> {
            instance.calculateEquivalentResistance();
        });

    }

}
