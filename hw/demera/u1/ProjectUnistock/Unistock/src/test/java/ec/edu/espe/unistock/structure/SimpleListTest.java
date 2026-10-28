package ec.edu.espe.unistock.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleListTest {

    private SimpleList<String> list;

    @BeforeEach
    void setUp() {
        list = new SimpleList<>();
    }

    @Test
    void testAddSingleElement() {
        list.add("Elemento 1");
        assertEquals(1, list.size());
    }

    @Test
    void testAddMultipleElements() {
        list.add("Elemento 1");
        list.add("Elemento 2");
        list.add("Elemento 3");
        assertEquals(3, list.size());
    }

    @Test
    void testAddNullElementThrowsException() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> list.add(null));
        assertEquals("El elemento no puede ser nulo.", exception.getMessage());
    }

    @Test
    void testAddPreservesInsertionOrder() {
        list.add("A");
        list.add("B");
        list.resetIteration();
        assertEquals("A", list.next());
        assertEquals("B", list.next());
    }

    @Test
    void testAddChangesIsEmptyStatus() {
        assertTrue(list.isEmpty());
        list.add("Cuaderno");
        assertFalse(list.isEmpty());
    }

    @Test
    void testAddDuplicateElementsAllowed() {
        list.add("Repetido");
        list.add("Repetido");
        assertEquals(2, list.size());
    }

    @Test
    void testAddCustomObject() {
        SimpleList<Integer> intList = new SimpleList<>();
        intList.add(100);
        assertEquals(1, intList.size());
    }

    @Test
    void testAddElementSetsHeadAndTailOnFirstAdd() {
        list.add("Primero");
        list.resetIteration();
        assertTrue(list.hasNext());
        assertEquals("Primero", list.next());
    }

    @Test
    void testAddSequentialElementsCount() {
        for (int i = 0; i < 10; i++) {
            list.add("Item " + i);
        }
        assertEquals(10, list.size());
    }

    @Test
    void testAddEmptyStringAllowed() {
        list.add("");
        assertEquals(1, list.size());
    }

    @Test
    void testRemoveHeadElement() {
        list.add("A");
        list.add("B");
        list.remove("A");
        assertEquals(1, list.size());
        list.resetIteration();
        assertEquals("B", list.next());
    }

    @Test
    void testRemoveTailElement() {
        list.add("A");
        list.add("B");
        list.remove("B");
        assertEquals(1, list.size());
        list.resetIteration();
        assertEquals("A", list.next());
    }

    @Test
    void testRemoveMiddleElement() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.remove("B");
        assertEquals(2, list.size());
        list.resetIteration();
        assertEquals("A", list.next());
        assertEquals("C", list.next());
    }

    @Test
    void testRemoveNonExistentElementDoesNothing() {
        list.add("A");
        list.remove("Z");
        assertEquals(1, list.size());
    }

    @Test
    void testRemoveOnlyElementLeavesListEmpty() {
        list.add("Unico");
        list.remove("Unico");
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testRemoveFromEmptyListDoesNothing() {
        list.remove("Inexistente");
        assertEquals(0, list.size());
    }

    @Test
    void testRemoveElementUpdatesCursorIfPointsToRemoved() {
        list.add("A");
        list.add("B");
        list.resetIteration();
        list.remove("A");
        assertEquals("B", list.next());
    }

    @Test
    void testRemoveNullDoesNothing() {
        list.add("A");
        list.remove(null);
        assertEquals(1, list.size());
    }

    @Test
    void testRemoveFirstOccurrenceOnly() {
        list.add("A");
        list.add("A");
        list.remove("A");
        assertEquals(1, list.size());
    }

    @Test
    void testRemoveTailAndAddAgain() {
        list.add("A");
        list.add("B");
        list.remove("B");
        list.add("C");
        assertEquals(2, list.size());
        list.resetIteration();
        assertEquals("A", list.next());
        assertEquals("C", list.next());
    }

    @Test
    void testResetIterationResetsCursorToHead() {
        list.add("A");
        list.add("B");
        list.resetIteration();
        assertEquals("A", list.next());
        list.resetIteration();
        assertEquals("A", list.next());
    }

    @Test
    void testResetIterationOnEmptyList() {
        assertDoesNotThrow(() -> list.resetIteration());
        assertFalse(list.hasNext());
    }

    @Test
    void testResetIterationAllowsMultiplePasses() {
        list.add("A");
        list.resetIteration();
        assertTrue(list.hasNext());
        list.next();
        assertFalse(list.hasNext());
        list.resetIteration();
        assertTrue(list.hasNext());
    }

    @Test
    void testResetIterationAfterPartialTraversal() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.resetIteration();
        list.next();
        list.resetIteration();
        assertEquals("A", list.next());
    }

    @Test
    void testResetIterationAfterFullTraversal() {
        list.add("X");
        list.resetIteration();
        while (list.hasNext()) {
            list.next();
        }
        list.resetIteration();
        assertTrue(list.hasNext());
    }

    @Test
    void testResetIterationDoesNotModifySize() {
        list.add("A");
        list.resetIteration();
        assertEquals(1, list.size());
    }

    @Test
    void testResetIterationConsistentBehavior() {
        list.add("A");
        list.resetIteration();
        list.resetIteration();
        assertEquals("A", list.next());
    }

    @Test
    void testResetIterationAfterAdd() {
        list.add("A");
        list.resetIteration();
        list.add("B");
        list.resetIteration();
        assertEquals("A", list.next());
        assertEquals("B", list.next());
    }

    @Test
    void testResetIterationAfterRemove() {
        list.add("A");
        list.add("B");
        list.remove("A");
        list.resetIteration();
        assertEquals("B", list.next());
    }

    @Test
    void testResetIterationSingleElementList() {
        list.add("Unico");
        list.resetIteration();
        assertEquals("Unico", list.next());
    }

    @Test
    void testHasNextReturnsTrueWhenCursorNotNull() {
        list.add("A");
        list.resetIteration();
        assertTrue(list.hasNext());
    }

    @Test
    void testHasNextReturnsFalseWhenCursorAtEnd() {
        list.add("A");
        list.resetIteration();
        list.next();
        assertFalse(list.hasNext());
    }

    @Test
    void testHasNextReturnsFalseOnEmptyList() {
        list.resetIteration();
        assertFalse(list.hasNext());
    }

    @Test
    void testHasNextDoesNotAdvanceCursor() {
        list.add("A");
        list.resetIteration();
        assertTrue(list.hasNext());
        assertTrue(list.hasNext());
        assertEquals("A", list.next());
    }

    @Test
    void testHasNextMultipleCalls() {
        list.add("A");
        list.resetIteration();
        for (int i = 0; i < 5; i++) {
            assertTrue(list.hasNext());
        }
    }

    @Test
    void testHasNextSequentialElements() {
        list.add("A");
        list.add("B");
        list.resetIteration();
        assertTrue(list.hasNext());
        list.next();
        assertTrue(list.hasNext());
        list.next();
        assertFalse(list.hasNext());
    }

    @Test
    void testHasNextAfterClearOrRemoveAll() {
        list.add("A");
        list.remove("A");
        list.resetIteration();
        assertFalse(list.hasNext());
    }

    @Test
    void testHasNextInitialStateWithoutReset() {
        list.add("A");
        assertFalse(list.hasNext());
    }

    @Test
    void testHasNextConsistentWithNext() {
        list.add("A");
        list.resetIteration();
        if (list.hasNext()) {
            assertNotNull(list.next());
        }
    }

    @Test
    void testHasNextAfterResetIteration() {
        list.add("A");
        list.resetIteration();
        assertTrue(list.hasNext());
    }

    @Test
    void testNextReturnsElementAndAdvancesCursor() {
        list.add("A");
        list.add("B");
        list.resetIteration();
        assertEquals("A", list.next());
        assertEquals("B", list.next());
    }

    @Test
    void testNextThrowsExceptionWhenNoMoreElements() {
        list.resetIteration();
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> list.next());
        assertEquals("No hay más elementos.", exception.getMessage());
    }

    @Test
    void testNextThrowsExceptionAfterReachingEnd() {
        list.add("A");
        list.resetIteration();
        list.next();
        assertThrows(NoSuchElementException.class, () -> list.next());
    }

    @Test
    void testNextReturnsExactInjectedObjects() {
        String obj = "Prueba";
        list.add(obj);
        list.resetIteration();
        assertSame(obj, list.next());
    }

    @Test
    void testNextConsecutiveCalls() {
        list.add("1");
        list.add("2");
        list.add("3");
        list.resetIteration();
        assertEquals("1", list.next());
        assertEquals("2", list.next());
        assertEquals("3", list.next());
    }

    @Test
    void testNextOnSingleElementList() {
        list.add("A");
        list.resetIteration();
        assertEquals("A", list.next());
    }

    @Test
    void testNextWithGenericTypes() {
        SimpleList<Double> doubleList = new SimpleList<>();
        doubleList.add(10.5);
        doubleList.resetIteration();
        assertEquals(10.5, doubleList.next());
    }

    @Test
    void testNextAfterResetIteration() {
        list.add("A");
        list.resetIteration();
        list.next();
        list.resetIteration();
        assertEquals("A", list.next());
    }

    @Test
    void testNextIterationLoop() {
        list.add("A");
        list.add("B");
        list.resetIteration();
        int count = 0;
        while (list.hasNext()) {
            assertNotNull(list.next());
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    void testNextExceptionMessageExactMatch() {
        NoSuchElementException ex = assertThrows(NoSuchElementException.class, () -> list.next());
        assertTrue(ex.getMessage().contains("No hay más elementos."));
    }

    @Test
    void testSizeInitiallyZero() {
        assertEquals(0, list.size());
    }

    @Test
    void testSizeIncrementsOnAdd() {
        list.add("A");
        assertEquals(1, list.size());
        list.add("B");
        assertEquals(2, list.size());
    }

    @Test
    void testSizeDecrementsOnRemove() {
        list.add("A");
        list.add("B");
        list.remove("A");
        assertEquals(1, list.size());
    }

    @Test
    void testSizeUnchangedWhenRemovingNonExistent() {
        list.add("A");
        list.remove("B");
        assertEquals(1, list.size());
    }

    @Test
    void testSizeZeroAfterRemovingAll() {
        list.add("A");
        list.remove("A");
        assertEquals(0, list.size());
    }

    @Test
    void testSizeUnchangedOnTraversal() {
        list.add("A");
        list.add("B");
        list.resetIteration();
        list.next();
        list.next();
        assertEquals(2, list.size());
    }

    @Test
    void testSizeConsistentCall() {
        list.add("A");
        assertEquals(list.size(), list.size());
    }

    @Test
    void testSizeLargeList() {
        for (int i = 0; i < 100; i++) {
            list.add("Elem " + i);
        }
        assertEquals(100, list.size());
    }

    @Test
    void testSizePrimitiveIntType() {
        int size = list.size();
        assertEquals(0, size);
    }

    @Test
    void testSizeAfterFailedAddAttempts() {
        assertThrows(NullPointerException.class, () -> list.add(null));
        assertEquals(0, list.size());
    }

    @Test
    void testIsEmptyTrueInitially() {
        assertTrue(list.isEmpty());
    }

    @Test
    void testIsEmptyFalseAfterAdd() {
        list.add("A");
        assertFalse(list.isEmpty());
    }

    @Test
    void testIsEmptyTrueAfterRemovingOnlyElement() {
        list.add("A");
        list.remove("A");
        assertTrue(list.isEmpty());
    }

    @Test
    void testIsEmptyConsistentWithZeroSize() {
        assertEquals(list.size() == 0, list.isEmpty());
    }

    @Test
    void testIsEmptyConsistentCall() {
        assertTrue(list.isEmpty());
        assertTrue(list.isEmpty());
    }

    @Test
    void testIsEmptyFalseWithMultipleElements() {
        list.add("A");
        list.add("B");
        assertFalse(list.isEmpty());
    }

    @Test
    void testIsEmptyAfterResetIteration() {
        list.add("A");
        list.resetIteration();
        assertFalse(list.isEmpty());
    }

    @Test
    void testIsEmptyAfterFullTraversal() {
        list.add("A");
        list.resetIteration();
        list.next();
        assertFalse(list.isEmpty());
    }

    @Test
    void testIsEmptyFalseWhenSizeIsGreaterThanZero() {
        list.add("A");
        assertTrue(list.size() > 0);
        assertFalse(list.isEmpty());
    }

    @Test
    void testIsEmptyAfterFailedAdd() {
        assertThrows(NullPointerException.class, () -> list.add(null));
        assertTrue(list.isEmpty());
    }
}