package doubleEndedQueueTest;

import doubleendedqueue.DequeNode;
import doubleendedqueue.DoubleLinkedListQueue;
import doubleendedqueue.IntegerComparator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for DoubleLinkedListQueue class")
class DoubleLinkedListQueueTest {

    private DoubleLinkedListQueue<Integer> deque;

    // Helper functions

    @BeforeEach
    public void init() {
        deque = new DoubleLinkedListQueue<>();
    }

    @AfterEach
    public void finish() {
        deque = null;
    }

    private void appendRight(int numberOfNewElements) {
        int originalSize = deque.size();

        for (int i = originalSize + 1; i <= originalSize + numberOfNewElements; i++) {
            deque.append(new DequeNode<>(i, null, null));
        }
    }

    private void appendLeft(int numberOfNewElements) {
        int originalSize = deque.size();

        for (int i = originalSize + 1; i <= originalSize + numberOfNewElements; i++) {
            deque.appendLeft(new DequeNode<>(i, null, null));
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
        int actualFirstValue = deque.peekFirst().getItem();
        int actualLastValue = deque.peekLast().getItem();

        assertEquals(expectedSize, actualSize);
        assertEquals(expectedFirstValue, actualFirstValue);
        assertEquals(expectedLastValue, actualLastValue);
    }

    // Tests

    @Test
    @DisplayName("Initialization")
    void initializationTest() {
        int expectedSize = 0;

        assertEquals(deque.size(), expectedSize);
        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
    }


    @ParameterizedTest(name = "size() on deque with size = {0} returns {0}")
    @ValueSource(ints = {0, 1, 2, 10, 38})
    @DisplayName("Size")
    void sizeTest(int expectedSize) {
        appendRight(expectedSize);
        int actualSize = deque.size();

        assertEquals(expectedSize, actualSize);
    }


    @Test
    @DisplayName("Links")
    void linksTest() {
        appendRight(20);
        appendLeft(10);
        deleteRight(5);
        deleteLeft(10);

        DequeNode<Integer> current = deque.peekFirst();
        DequeNode<Integer> prev;

        while (current != deque.peekLast()) {
            //System.out.println(current.getItem());
            prev = current;
            current = current.getNext();
            assertEquals(prev.getNext(), current);
            assertEquals(current.getPrevious(), prev);
        }//System.out.println(current.getItem());
    }

    @Test
    @DisplayName("appendLeft() on empty deque")
    void appendLeft_dequeIsEmpty() {
        int expectedSize = 1;
        int expectedFirst = 33;
        int expectedLast = 33;

        deque.appendLeft(new DequeNode<>(33, null, null));

        check(expectedSize, expectedFirst, expectedLast);
    }

    @Test
    @DisplayName("appendLeft() on nonempty deque")
    void appendLeft_dequeIsNotEmpty() {
        int expectedSize = 11;
        int expectedFirst = 0;
        int expectedLast = 10;

        appendRight(10);
        deque.appendLeft(new DequeNode<>(0, null, null));

        check(expectedSize, expectedFirst, expectedLast);
    }

    @Test
    @DisplayName("appendLeft(null) throws IllegalArgumentException")
    void appendLeft_argumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> deque.appendLeft(null));
    }

    @Test
    @DisplayName("appendLeft(node containing null item) throws IllegalArgumentException")
    void appendLeft_argumentIsNodeWithNullItem() {
        DequeNode<Integer> node = new DequeNode<>(null, null, null);
        assertThrows(IllegalArgumentException.class, () -> deque.appendLeft(node));
    }

    @Test
    @DisplayName("append() on empty deque")
    void append_dequeIsEmpty() {
        int expectedSize = 1;
        int expectedFirst = 99;
        int expectedLast = 99;

        deque.append(new DequeNode<>(99, null, null));

        check(expectedSize, expectedFirst, expectedLast);
    }

    @Test
    @DisplayName("append() on nonempty deque")
    void append_dequeIsNotEmpty() {
        int expectedSize = 15;
        int expectedFirst = 1;
        int expectedLast = 15;

        appendRight(14);

        deque.append(new DequeNode<>(15, null, null));

        check(expectedSize, expectedFirst, expectedLast);
    }

    @Test
    @DisplayName("append(null) throws IllegalArgumentException")
    void append_argumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> deque.append(null));
    }

    @Test
    @DisplayName("append(node containing null item) throws IllegalArgumentException")
    void append_argumentIsNodeWithNullItem() {
        DequeNode<Integer> node = new DequeNode<>(null, null, null);
        assertThrows(IllegalArgumentException.class, () -> deque.append(node));
    }

    @Test
    @DisplayName("deleteFirst() on empty deque")
    void deleteFirst_dequeIsEmpty() {
        int expectedSize = 0;

        deque.deleteFirst();

        assertEquals(deque.size(), expectedSize);
        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
    }

    @Test
    @DisplayName("deleteFirst() on deque with size = 1")
    void deleteFirst_dequeHasOneElement() {
        int expectedSize = 0;

        appendRight(1);
        deque.deleteFirst();

        assertEquals(deque.size(), expectedSize);
        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
    }

    @Test
    @DisplayName("deleteFirst() on deque with size = 2")
    void deleteFirst_dequeHasTwoElements() {
        int expectedSize = 1;
        int expectedFirst = 2;
        int expectedLast = 2;

        appendRight(2);
        deque.deleteFirst();

        check(expectedSize, expectedFirst, expectedLast);
    }

    @Test
    @DisplayName("deleteFirst() on deque with size > 2")
    void deleteFirst_dequeHasMoreThanTwoElements() {
        int expectedSize = 19;
        int expectedFirst = 2;
        int expectedLast = 20;

        appendRight(20);
        deque.deleteFirst();

        check(expectedSize, expectedFirst, expectedLast);
    }

    @Test
    @DisplayName("deleteLast() on empty deque")
    void deleteLast_dequeIsEmpty() {
        int expectedSize = 0;

        deque.deleteLast();

        assertEquals(deque.size(), expectedSize);
        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
    }

    @Test
    @DisplayName("deleteLast() on deque with size = 1")
    void deleteLast_dequeHasOneElement() {
        int expectedSize = 0;

        appendRight(1);
        deque.deleteLast();

        assertEquals(deque.size(), expectedSize);
        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
    }

    @Test
    @DisplayName("deleteLast() on deque with size = 2")
    void deleteLast_dequeHasTwoElements() {
        int expectedSize = 1;
        int expectedFirst = 1;
        int expectedLast = 1;

        appendRight(2);
        deque.deleteLast();

        check(expectedSize, expectedFirst, expectedLast);
    }

    @Test
    @DisplayName("deleteLast() on deque with size > 2")
    void deleteLast_dequeHasMoreThanTwoElements() {
        int expectedSize = 24;
        int expectedFirst = 1;
        int expectedLast = 24;

        appendRight(25);
        deque.deleteLast();

        check(expectedSize, expectedFirst, expectedLast);
    }

    // Complex methods

    @ParameterizedTest(name = "getAt({1}) on deque with size = {0}")
    @CsvSource({"3,2", "5,2", "10,9"})
    @DisplayName("getAt() with valid arguments")
    void getAt_ValidArgument(int size, int expectedValue) {
        appendRight(size);
        int actualValue = deque.getAt(expectedValue).getItem();

        assertEquals(expectedValue, actualValue);
    }

    @ParameterizedTest(name = "getAt({1}) on deque with size = {0} throws IllegalArgumentException")
    @CsvSource({"0,2", "3,3", "5,7"})
    @DisplayName("getAt() with invalid arguments throws IllegalArgumentException")
    void getAt_InvalidArgument(int size, int expectedValue) {
        appendRight(size);

        assertThrows(IllegalArgumentException.class, () -> deque.getAt(expectedValue));
    }

    @Test
    @DisplayName("find(null) throws IllegalArgumentException")
    void find_InvalidArgument() {
        assertThrows(IllegalArgumentException.class, () -> deque.find(null));
    }

    @ParameterizedTest(name = "find(node at position {0}) on deque with size = 10")
    @ValueSource(ints = {2, 5, 10})
    @DisplayName("find() with valid and present arguments")
    void find_ValidAndPresentArgument(int expectedValue) {
        appendRight(10);

        int actualValue = deque.find(expectedValue).getItem();

        assertEquals(expectedValue, actualValue);
    }

    @ParameterizedTest(name = "find(node at position {0}) on deque with size = 10 throws IllegalArgumentException")
    @ValueSource(ints = {0, 11})
    @DisplayName("find() with valid but absent arguments")
    void find_ValidAndNotPresentArgument(int expectedValue) {
        appendRight(10);

        DequeNode<Integer> actualValue = deque.find(expectedValue);

        assertNull(actualValue);
    }

    @Test
    @DisplayName("delete(null) throws IllegalArgumentException")
    void delete_InvalidArgument() {
        assertThrows(IllegalArgumentException.class, () -> deque.delete(null));
    }

    @ParameterizedTest(name = "delete(node at position {0}) from deque with size = 10")
    @ValueSource(ints = {2, 5, 7})
    @DisplayName("delete() on non terminal node")
    void delete_ValidAndPresentArgumentNotFirstAndNotLast(int positionToDelete) {
        appendRight(10);

        DequeNode<Integer> delete = deque.getAt(positionToDelete);
        DequeNode<Integer> previous = delete.getPrevious();
        DequeNode<Integer> next = delete.getNext();

        deque.delete(delete);

        assertEquals(previous, next.getPrevious());
        assertEquals(next, previous.getNext());
        assertEquals(9, deque.size());
    }

    @Test
    @DisplayName("delete(only node) from deque with size = 1")
    void delete_ValidAndPresentArgumentAndSizeIsOne() {
        appendRight(1);

        deque.delete(deque.peekFirst());

        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
        assertEquals(0, deque.size());
    }

    @Test
    @DisplayName("delete(first node) from deque with size = 10")
    void delete_ArgumentIsFirstAndSizeIsGreaterThanOne() {
        appendRight(10);

        DequeNode<Integer> delete = deque.peekFirst();
        DequeNode<Integer> next = delete.getNext();

        deque.delete(delete);

        assertEquals(deque.peekFirst(), next);
        assertNull(next.getPrevious());
        assertEquals(9, deque.size());
    }

    @Test
    @DisplayName("delete(last node) from deque with size = 10")
    void delete_ArgumentIsLastAndSizeIsGreaterThanOne() {
        appendRight(10);

        DequeNode<Integer> delete = deque.peekLast();
        DequeNode<Integer> previous = delete.getPrevious();

        deque.delete(delete);

        assertTrue(previous.isLastNode());
        assertEquals(9, deque.size());
    }

    @Test
    @DisplayName("Sort")
    void sortTest() {

        List<Integer> expectedOrder = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> actualOrder = new ArrayList<>();

        appendRight(3);
        appendLeft(5);
        appendRight(2);

        deque.sort(new IntegerComparator());

        DequeNode<Integer> current = deque.peekFirst();
        while (current != null) {
            actualOrder.add(current.getItem());
            current = current.getNext();
        }

        assertEquals(expectedOrder, actualOrder);
    }


}
