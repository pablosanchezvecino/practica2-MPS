package doubleEndedQueueTest;

import doubleEndedQueue.DequeNode;
import doubleEndedQueue.DoubleLinkedListQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListQueueTest {

    private DoubleLinkedListQueue cola;

    @BeforeEach
    public void init() {
        cola = new DoubleLinkedListQueue();
    }

    @Test
    public void Test1(){
        cola.append(new DequeNode<>(5, null, null));
        cola.append(new DequeNode<>(8, null, null));
        cola.appendLeft(new DequeNode<>(2, null, null));

        assertEquals(2, cola.peekFirst().getItem());
        assertEquals(8, cola.peekLast().getItem());
    }

    @Test
    public void Test2(){
        cola.append(new DequeNode<>(7, null, null));

        assertEquals(cola.peekLast(), cola.peekFirst());
    }

    @Test
    public void Test3(){
        cola.append(new DequeNode<>(7, null, null));
        cola.deleteFirst();

        assertEquals(0, cola.size());
    }

    @Test
    public void Test4(){
        cola.append(new DequeNode<>(7, null, null));
        cola.deleteLast();

        assertEquals(0, cola.size());
    }
}
