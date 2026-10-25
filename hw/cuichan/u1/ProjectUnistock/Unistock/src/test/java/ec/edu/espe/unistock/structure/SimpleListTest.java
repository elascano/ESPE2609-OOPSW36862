package ec.edu.espe.unistock.structure;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleListTest {

    private SimpleList<String> instance;

    public SimpleListTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        instance = new SimpleList<>();
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testAddSingleElement() {
        instance.add("Item 1");
        assertEquals(1, instance.size());
        assertFalse(instance.isEmpty());
    }

    @Test
    public void testAddMultipleElements() {
        instance.add("Item 1");
        instance.add("Item 2");
        instance.add("Item 3");
        assertEquals(3, instance.size());
    }

    @Test
    public void testAddNullElement() {
        assertDoesNotThrow(() -> instance.add(null));
        assertEquals(1, instance.size());
    }

    @Test
    public void testRemoveExistingElement() {
        instance.add("Item 1");
        instance.add("Item 2");
        instance.remove("Item 1");
        assertEquals(1, instance.size());
        
        instance.resetIteration();
        assertEquals("Item 2", instance.next());
    }

    @Test
    public void testRemoveNonExistingElement() {
        instance.add("Item 1");
        instance.remove("NonExisting");
        assertEquals(1, instance.size());
    }

    @Test
    public void testRemoveFromEmptyList() {
        assertDoesNotThrow(() -> instance.remove("Item 1"));
        assertEquals(0, instance.size());
    }

    @Test
    public void testRemoveNull() {
        instance.add("Item 1");
        instance.add(null);
        instance.remove(null);
        assertEquals(1, instance.size());
    }

    @Test
    public void testIteratorFlow() {
        instance.add("A");
        instance.add("B");
        
        instance.resetIteration();
        
        assertTrue(instance.hasNext());
        assertEquals("A", instance.next());
        
        assertTrue(instance.hasNext());
        assertEquals("B", instance.next());
        
        assertFalse(instance.hasNext());
        assertNull(instance.next());
    }

    @Test
    public void testResetIteration() {
        instance.add("A");
        instance.add("B");
        
        instance.resetIteration();
        instance.next(); // Moves to B
        
        instance.resetIteration(); // Should reset to start
        assertEquals("A", instance.next());
    }

    @Test
    public void testHasNextOnEmptyList() {
        instance.resetIteration();
        assertFalse(instance.hasNext());
    }

    @Test
    public void testNextOnEmptyList() {
        instance.resetIteration();
        assertNull(instance.next());
    }

    @Test
    public void testSizeOnEmptyList() {
        assertEquals(0, instance.size());
    }

    @Test
    public void testIsEmptyTrue() {
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        instance.add("Item");
        assertFalse(instance.isEmpty());
    }

    @Test
    public void testIsEmptyAfterRemoveAll() {
        instance.add("Item");
        instance.remove("Item");
        assertTrue(instance.isEmpty());
    }
}