package doubleEndedQueue;

import java.util.Comparator;

public class DoubleLinkedListQueue<T> implements DoubleEndedQueue<T> {

    private DequeNode<T> first;
    private DequeNode last;
    int size;

    public DoubleLinkedListQueue() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void append(DequeNode node) {
        if (node == null || node.getItem() == null) throw new IllegalArgumentException();

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
        if (node == null || node.getItem() == null) throw new IllegalArgumentException();

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

    @Override
    public DequeNode getAt(int position) {
        if (position >= size) {
            throw new IllegalArgumentException();
        }

        DequeNode aux = null;

        if (position <= size / 2) {
            aux = first;
            for (int i = 1; i < position; i++) {
                aux = aux.getNext();
            }
        } else {
            aux = last;
            for (int i = size; i > position; i--) {
                aux = aux.getPrevious();
            }
        }
        return aux;
    }

    @Override
    public DequeNode find(Object item) {
        if (item == null) throw new IllegalArgumentException();

        boolean found = false;

        DequeNode aux = first;

        while (!found && aux != null) {
            if (aux.getItem().equals(item)) {
                found = true;
            } else {
                aux = aux.getNext();
            }
        }
        return aux;
    }

    @Override
    public void delete(DequeNode node) {
        if (node == null) throw new IllegalArgumentException();

        DequeNode previous = node.getPrevious();
        DequeNode next = node.getNext();

        if (find(node.getItem()) != null) {
            if (previous == null && next == null) {
                first = null;
                last = null;
            } else if (previous == null) {
                first = next;
                first.setPrevious(null);
            } else if (next == null) {
                previous.setNext(null);
                last = previous;
            } else {
                previous.setNext(next);
                next.setPrevious(previous);
            }
            node = null;
            size--;
        }
    }

    public void sort(Comparator<?> comparator) {
        sortAux(comparator);
    }

    private <T> void sortAux(Comparator<T> comparator) {
        if (size > 1) {
            for (int i = 0; i < size; i++ ) {
                DequeNode currentNode = first;
                DequeNode next = first.getNext();
                for (int j = 0; j < size - 1; j++) {
                    if (comparator.compare((T) currentNode.getItem(), (T) next.getItem()) > 0) {
                        Object temp = currentNode.getItem();
                        currentNode.setItem(next.getItem());
                        next.setItem(temp);
                    }
                    currentNode = next;
                    next = next.getNext();
                }
            }
        }
    }

}




