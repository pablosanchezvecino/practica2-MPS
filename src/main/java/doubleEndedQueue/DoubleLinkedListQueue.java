package doubleEndedQueue;

public class DoubleLinkedListQueue implements DoubleEndedQueue {

    private DequeNode first;
    private DequeNode last;
    int size;

    public DoubleLinkedListQueue() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void append(DequeNode node) {
        if (node == null) throw new IllegalArgumentException();

        if (size == 0) {
            first = node;
            last = node;
        } else {
            node.setPrevious(last);
            last.setNext(node);
            last = node;
        }
        size++;
    }

    @Override
    public void appendLeft(DequeNode node) {
        if (node == null) throw new IllegalArgumentException();

        if (size == 0) {
            first = node;
            last = node;
        } else {
            first.setPrevious(node);
            node.setNext(first);
            first = node;
        }
        size++;
    }

    @Override
    public void deleteFirst() {
        if (size > 0) {
            if (size == 1) {
                first = null;
                last = null;
            } else {
                DequeNode delete = first;
                first = first.getNext();
                delete = null;
            }
            size--;
        }
    }

    @Override
    public void deleteLast() {
        if (size > 0) {
            if (size == 1) {
                first = null;
                last = null;
            } else {
                DequeNode delete = last;
                last = last.getPrevious();
                delete = null;
            }
            size--;
        }
    }

    @Override
    public DequeNode peekFirst() {
        return first;
    }

    @Override
    public DequeNode peekLast() {
        return last;
    }

    @Override
    public int size() {
        return size;
    }
}
