package doubleEndedQueueTest;

import doubleEndedQueue.DequeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DequeNodeTest {

    private DequeNode<Integer> node;

    @BeforeEach
    void init() {
        node = new DequeNode<>(null, null, null);
    }

    @Test
    void setItem_ArgumentIs5() {
        int expectedResult = 5;

        node.setItem(5);

        assertEquals(expectedResult, node.getItem());
    }

    @Test
    void setItem_ArgumentIsNull() {
        node.setItem(null);

        assertNull(node.getItem());
    }

    @Test
    void setNext_ArgumentIsNewNodeWithValue5() {
        int expectedResult = 5;

        node.setNext(new DequeNode(5, null, null));

        assertEquals(expectedResult, node.getNext().getItem());
    }

    @Test
    @DisplayName("Comprueba que tras hacer un setNext() y un setPrevious() el nodo first pasa a ser el previous")
    void setNext_Test1() {

        node.setNext(new DequeNode(5, null, null));
        node.setPrevious(new DequeNode(4, null, null));

        assertTrue(node.getPrevious().isFirstNode());
        assertTrue(node.getNext().isLastNode());
    }

    @Test
    @DisplayName("Comprueba que tras varios setNext() el último nodo añadido es el último")
    void setNext_Test2() {
        int expectedResult = 4;

        node.setNext(new DequeNode(5, null, null));
        node.getNext().setNext(new DequeNode(4, null, null));

        assertTrue(node.isFirstNode());
        assertTrue(node.getNext().getNext().isLastNode());
    }


}
