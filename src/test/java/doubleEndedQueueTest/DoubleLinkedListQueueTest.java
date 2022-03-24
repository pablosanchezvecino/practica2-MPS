package doubleEndedQueueTest;

import doubleEndedQueue.DequeNode;
import doubleEndedQueue.DoubleLinkedListQueue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListQueueTest {

    private DoubleLinkedListQueue deque;

    // Helper functions

    @BeforeEach
    public void init() {
        deque = new DoubleLinkedListQueue();
    }

    @AfterEach
    public void finish() {
        deque = null;
    }

    private void appendRight(int numberOfNewElements) {
        int originalSize = deque.size();

        for (int i = originalSize + 1; i <= originalSize + numberOfNewElements; i++) {
            deque.append(new DequeNode<Integer>(i, null, null));
        }
    }

    private void appendLeft(int numberOfNewElements) {
        int originalSize = deque.size();

        for (int i = originalSize + 1; i <= originalSize + numberOfNewElements; i++) {
            deque.appendLeft(new DequeNode<Integer>(i, null, null));
        }
    }

    private void deleteRight(int numberOfElementsToRemove) {
        for (int i = 1; i <= numberOfElementsToRemove; i++) {
            deque.deleteLast();
        }
    }

    private void deleteLeft(int numberOfElementsToRemove) {
        for (int i = 1; i <= numberOfElementsToRemove; i++) {
            deque.deleteFirst();
        }
    }

    private void check(int expectedSize, int expectedFirstValue, int expectedLastValue) {
        int actualSize = deque.size();
        int actualFirstValue = (int)deque.peekFirst().getItem();
        int actualLastValue = (int)deque.peekLast().getItem();

        assertEquals(expectedSize, actualSize);
        assertEquals(expectedFirstValue, actualFirstValue);
        assertEquals(expectedLastValue, actualLastValue);
    }

    // Tests

    @Test
    public void initializationTest() {
        int expectedSize = 0;

        assertEquals(deque.size(), expectedSize);
        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 10, 38 })
    public void sizeTest(int expectedSize) {
        appendRight(expectedSize);
        int actualSize = deque.size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void linksTest() {
        appendRight(20);
        appendLeft(10);
        deleteRight(5);
        deleteLeft(10);


        DequeNode current = deque.peekFirst();
        DequeNode prev = null;

        while (current != deque.peekLast()) {
            //System.out.println(current.getItem());
            prev = current;
            current = current.getNext();
            assertEquals(prev.getNext(), current);
            assertEquals(current.getPrevious(), prev);
        }//System.out.println(current.getItem());


    }

    @Test
    public void appendLeft_dequeIsEmpty() {
        int expectedSize = 1;
        int expectedFirst = 33;
        int expectedLast = 33;

        deque.appendLeft(new DequeNode<>(33, null, null));

        check(expectedSize, expectedFirst, expectedLast);    }

    @Test
    public void appendLeft_dequeIsNotEmpty() {
        int expectedSize = 11;
        int expectedFirst = 0;
        int expectedLast = 10;

        appendRight(10);
        deque.appendLeft(new DequeNode<>(0, null, null));

        check(expectedSize, expectedFirst, expectedLast);
    }

    @Test
    public void appendLeft_argumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> deque.appendLeft(null));
    }

    @Test
    public void append_dequeIsEmpty() {
        int expectedSize = 1;
        int expectedFirst = 99;
        int expectedLast = 99;

        deque.append(new DequeNode<>(99, null, null));

        check(expectedSize, expectedFirst, expectedLast);
    }

    @Test
    public void append_dequeIsNotEmpty() {
        int expectedSize = 15;
        int expectedFirst = 1;
        int expectedLast = 15;

        appendRight(14);

        deque.append(new DequeNode<>(15, null, null));

        check(expectedSize, expectedFirst, expectedLast);    }

    @Test
    public void append_argumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> deque.append(null));
    }

    @Test
    public void deleteFirst_dequeIsEmpty() {
        int expectedSize = 0;

        deque.deleteFirst();

        assertEquals(deque.size(), expectedSize);
        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
    }

    @Test
    public void deleteFirst_dequeHasOneElement() {
        int expectedSize = 0;

        appendRight(1);
        deque.deleteFirst();

        assertEquals(deque.size(), expectedSize);
        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
    }

    @Test
    public void deleteFirst_dequeHasTwoElements() {
        int expectedSize = 1;
        int expectedFirst = 2;
        int expectedLast = 2;

        appendRight(2);
        deque.deleteFirst();

        check(expectedSize, expectedFirst, expectedLast);
    }

    @Test
    public void deleteFirst_dequeHasMoreThanTwoElements() {
        int expectedSize = 19;
        int expectedFirst = 2;
        int expectedLast = 20;

        appendRight(20);
        deque.deleteFirst();

        check(expectedSize, expectedFirst, expectedLast);
    }

    @Test
    public void deleteLast_dequeIsEmpty() {
        int expectedSize = 0;

        deque.deleteLast();

        assertEquals(deque.size(), expectedSize);
        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
    }

    @Test
    public void deleteLast_dequeHasOneElement() {
        int expectedSize = 0;

        appendRight(1);
        deque.deleteLast();

        assertEquals(deque.size(), expectedSize);
        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
    }

    @Test
    public void deleteLast_dequeHasTwoElements() {
        int expectedSize = 1;
        int expectedFirst = 1;
        int expectedLast = 1;

        appendRight(2);
        deque.deleteLast();

        check(expectedSize, expectedFirst, expectedLast);
    }

    @Test
    public void deleteLast_dequeHasMoreThanTwoElements() {
        int expectedSize = 24;
        int expectedFirst = 1;
        int expectedLast = 24;

        appendRight(25);
        deque.deleteLast();

        check(expectedSize, expectedFirst, expectedLast);
    }

    // Complex methods

    @ParameterizedTest
    @CsvSource({"3,2", "5,2", "10,9"})
    public void getAt_ValidArgument(int size, int expectedValue) {
        appendRight(size);
        int actualValue = (int)deque.getAt(expectedValue).getItem();

        assertEquals(expectedValue, actualValue);
    }

    @ParameterizedTest
    @CsvSource({"0,2", "3,3", "5,7"})
    public void getAt_InvalidArgument(int size, int expectedValue) {
        appendRight(size);

        assertThrows(IllegalArgumentException.class, () -> deque.getAt(expectedValue));
    }

    @Test
    public void find_InvalidArgument() {
        assertThrows(IllegalArgumentException.class, () -> deque.find(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 5, 10})
    public void find_ValidAndPresentArgument(int expectedValue) {
        appendRight(10);

        int actualValue = (int)deque.find(expectedValue).getItem();

        assertEquals(expectedValue, actualValue);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 11})
    public void find_ValidAndNotPresentArgument(int expectedValue) {
        appendRight(10);

        DequeNode actualValue = deque.find(expectedValue);

        assertNull(actualValue);
    }






    @Test
    public void delete_InvalidArgument() {
        assertThrows(IllegalArgumentException.class, () -> deque.delete(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 5, 7})
    public void delete_ValidAndPresentArgumentNotFirstAndNotLast(int positionToDelete) {
        appendRight(10);

        DequeNode delete = deque.getAt(positionToDelete);
        DequeNode previous = delete.getPrevious();
        DequeNode next = delete.getNext();

        deque.delete(delete);

        assertEquals(previous, next.getPrevious());
        assertEquals(next, previous.getNext());
        assertEquals(9, deque.size());
    }

    @Test
    public void delete_ValidAndPresentArgumentAndSizeIsOne() {
        appendRight(1);

        deque.delete(deque.peekFirst());

        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
        assertEquals(0, deque.size());
    }

    @Test
    public void delete_ArgumentIsFirstAndSizeIsGreaterThanOne() {
        appendRight(10);

        DequeNode delete = deque.peekFirst();
        DequeNode next = delete.getNext();

        deque.delete(delete);

        assertEquals(deque.peekFirst(),next);
        assertNull(next.getPrevious());
        assertEquals(9, deque.size());
    }

    @Test
    public void delete_ArgumentIsLastAndSizeIsGreaterThanOne() {
        appendRight(10);

        DequeNode delete = deque.peekFirst();
        DequeNode previous = delete.getPrevious();

        deque.delete(delete);

        assertTrue(previous.isLastNode());
        assertEquals(9, deque.size());
    }









}
