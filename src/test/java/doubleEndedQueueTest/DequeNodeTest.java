package doubleEndedQueueTest;

import doubleendedqueue.DequeNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Contains tests for DoubleLinkedListQueue class
 */
@DisplayName("Tests for DequeNode class")
class DequeNodeTest {

    /**
     * Tests node creation with all attributes set to null
     */
    @Test
    @DisplayName("Node creation with all attributes set to null")
    void createNodeNotConnectedAndContainingNullItem() {
        DequeNode<Integer> node = new DequeNode<>(null, null, null);

        assertNull(node.getPrevious());
        assertNull(node.getNext());
        assertNull(node.getItem());
    }

    /**
     * Tests node creation with all attributes with not null attributes
     */
    @Test
    @DisplayName("Node creation with item = 1, previous = (node with item = 1) and next = (node with item = 3))")
    void createNodeContainingAValueAndConnected() {
        DequeNode<Integer> previous = new DequeNode<>(1, null, null);
        DequeNode<Integer> next = new DequeNode<>(3, null, null);
        DequeNode<Integer> node = new DequeNode<>(2, next, previous);

        assertEquals(2, node.getItem());
        assertEquals(1, node.getPrevious().getItem());
        assertEquals(3, node.getNext().getItem());
    }

    /**
     * Tests setting null as the next node
     */
    @Test
    @DisplayName("Setting null as next")
    void setNextNodeToNull() {
        DequeNode<Integer> node = new DequeNode<>(2, null, null);

        node.setNext(null);

        assertNull(node.getNext());
    }

    /**
     * Tests setting another node as the next node
     */
    @Test
    @DisplayName("Setting a (node with item = 3) as next")
    void setNodeAsNext() {
        DequeNode<Integer> node = new DequeNode<>(2, null, null);
        DequeNode<Integer> next = new DequeNode<>(3, null, null);

        node.setNext(next);

        assertEquals(3, node.getNext().getItem());
    }

    /**
     * Tests setting null as the previous node
     */
    @Test
    @DisplayName("Setting null as previous")
    void setPreviousNodeToNull() {
        DequeNode<Integer> previous = new DequeNode<>(1, null, null);
        DequeNode<Integer> node = new DequeNode<>(2, null, previous);

        node.setPrevious(null);

        assertNull(node.getPrevious());
    }

    /**
     * Tests setting another node as the previous node
     */
    @Test
    @DisplayName("Setting a (node with item = 1) as previous")
    void setNodeAsPrevious() {
        DequeNode<Integer> node = new DequeNode<>(2, null, null);
        DequeNode<Integer> previous = new DequeNode<>(1, null, null);

        node.setPrevious(previous);

        assertEquals(1, node.getPrevious().getItem());
    }

    /**
     * Tests obtaining the item from a node containing a null item
     */
    @Test
    @DisplayName("Obtaining null as the item from a node containing a null item")
    void obtainItemFromNodeContainingNullItem() {
        DequeNode<Integer> node = new DequeNode<>(null, null, null);

        assertNull(node.getItem());
    }

    /**
     * Tests obtaining the item from a node containing an item
     */
    @Test
    @DisplayName("Obtaining 2 as the item from a node containing item = 2")
    void obtainItemFromNodeContainingAValue() {
        DequeNode<Integer> node = new DequeNode<>(2, null, null);

        assertEquals(2, node.getItem());
    }

    /**
     * Tests obtaining the next node from a node with no next node
     */
    @Test
    @DisplayName("Obtaining null as the next node from a node with no next node")
    void obtainNextFromNodeWithNoNext() {
        DequeNode<Integer> node = new DequeNode<>(2, null, null);

        assertNull(node.getNext());
    }

    /**
     * Tests obtaining the next node from a node that has a next node
     */
    @Test
    @DisplayName("Obtaining next as the next node from a node with next node = next")
    void obtainNextFromNodeWithNext() {
        DequeNode<Integer> next = new DequeNode<>(3, null, null);
        DequeNode<Integer> node = new DequeNode<>(2, next, null);

        assertEquals(next, node.getNext());
    }

    /**
     * Tests obtaining the previous node from a node with no previous node
     */
    @Test
    @DisplayName("Obtaining null as the previous node from a node with no previous node")
    void obtainPreviousFromNodeWithNoPrevious() {
        DequeNode<Integer> node = new DequeNode<>(2, null, null);

        assertNull(node.getPrevious());
    }

    /**
     * Tests obtaining the previous node from a node that has a previous node
     */
    @Test
    @DisplayName("Obtaining previous as the previous node from a node with previous node = previous")
    void obtainPreviousFromNodeWithPrevious() {
        DequeNode<Integer> previous = new DequeNode<>(1, null, null);
        DequeNode<Integer> node = new DequeNode<>(2, null, previous);

        assertEquals(previous, node.getPrevious());
    }

    /**
     * Tests isFirstNode() on a node with no previous node
     */
    @Test
    @DisplayName("isFirstNode() on node with no previous node returns true")
    void isFirstNode_NodeHasNoPrevious_ReturnTrue() {
        DequeNode<Integer> node = new DequeNode<>(null, null, null);

        assertTrue(node.isFirstNode());
    }

    /**
     * Tests isFirstNode() on a node with a previous node
     */
    @Test
    @DisplayName("isFirstNode() on node with previous node returns false")
    void isFirstNode_NodeHasPrevious_ReturnFalse() {
        DequeNode<Integer> previous = new DequeNode<>(1, null, null);
        DequeNode<Integer> node = new DequeNode<>(null, null, previous);

        assertFalse(node.isFirstNode());
    }

    /**
     * Tests isLastNode() on a node with no next node
     */
    @Test
    @DisplayName("isLastNode() on node with no next node returns true")
    void isLastNode_NodeHasNoNext_ReturnTrue() {
        DequeNode<Integer> node = new DequeNode<>(2, null, null);

        assertTrue(node.isLastNode());
    }

    /**
     * Tests isLastNode() on a node with a next node
     */
    @Test
    @DisplayName("isLastNode() on node with next node returns false")
    void isLastNode_NodeHasNext_ReturnFalse() {
        DequeNode<Integer> next = new DequeNode<>(3, null, null);
        DequeNode<Integer> node = new DequeNode<>(null, next, null);

        assertFalse(node.isLastNode());
    }

    /**
     * Tests isNotATerminalNode() on a node with neither a previous node nor a next node
     */
    @Test
    @DisplayName("isNotATerminalNode() on a node with neither a previous node nor a next node returns false")
    void isNotATerminalNode_NodeHasNeitherPreviousNorNext_ReturnFalse() {
        DequeNode<Integer> node = new DequeNode<>(2, null, null);

        assertFalse(node.isNotATerminalNode());
    }

    /**
     * Tests isNotATerminalNode() on a node with a previous node, but without a next node
     */
    @Test
    @DisplayName("isNotATerminalNode() on a node with a previous node, but without a next node returns false")
    void isNotATerminalNode_NodeHasPreviousButNotNext_ReturnFalse() {
        DequeNode<Integer> previous = new DequeNode<>(1, null, null);
        DequeNode<Integer> node = new DequeNode<>(2, null, previous);

        assertFalse(node.isNotATerminalNode());
    }

    /**
     * Tests isNotATerminalNode() on a node with a next node, but without a previous node
     */
    @Test
    @DisplayName("isNotATerminalNode() on a node with a next node, but without a previous node returns false")
    void isNotATerminalNode_NodeHasNextButNotPrevious_ReturnFalse() {
        DequeNode<Integer> next = new DequeNode<>(3, null, null);
        DequeNode<Integer> node = new DequeNode<>(2, next, null);

        assertFalse(node.isNotATerminalNode());
    }

    /**
     * Tests isNotATerminalNode() on a node with both a previous node and a next node
     */
    @Test
    @DisplayName("isNotATerminalNode() on a node with both a previous node and a next node returns true")
    void isNotATerminalNode_NodeHasPreviousAndNext_ReturnFalse() {
        DequeNode<Integer> previous = new DequeNode<>(1, null, null);
        DequeNode<Integer> next = new DequeNode<>(3, null, null);
        DequeNode<Integer> node = new DequeNode<>(2, next, previous);

        assertTrue(node.isNotATerminalNode());
    }






}
