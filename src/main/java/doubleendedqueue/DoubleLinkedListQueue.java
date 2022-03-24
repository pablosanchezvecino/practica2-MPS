package doubleendedqueue;

import java.util.Comparator;

public class DoubleLinkedListQueue<T> implements DoubleEndedQueue<T> {

    private DequeNode<T> first;
    private DequeNode<T> last;
    int size;

    public DoubleLinkedListQueue() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void append(DequeNode<T> node) {
        if (node == null || node.getItem() == null) throw new IllegalArgumentException();

        if (size == 0) {
            first = node;
        } else {
            node.setPrevious(last);
            last.setNext(node);
        }
        last = node;
        size++;
    }

    @Override
    public void appendLeft(DequeNode<T> node) {
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
                first = first.getNext();
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
                last = last.getPrevious();
            }
            size--;
        }
    }

    @Override
    public DequeNode<T> peekFirst() {
        return first;
    }

    @Override
    public DequeNode<T> peekLast() {
        return last;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public DequeNode<T> getAt(int position) {
        if (position >= size) {
            throw new IllegalArgumentException();
        }

        DequeNode<T> aux;

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
    public DequeNode<T> find(Object item) {
        if (item == null) throw new IllegalArgumentException();

        boolean found = false;

        DequeNode<T> aux = first;

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
    public void delete(DequeNode<T> node) {
        if (node == null) throw new IllegalArgumentException();

        DequeNode<T> previous = node.getPrevious();
        DequeNode<T> next = node.getNext();

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
            size--;
        }
    }

    @Override
    public void sort(Comparator<T> comparator) {
        if (size > 1) {
            for (int i = 0; i < size; i++) {
                DequeNode<T> currentNode = first;
                DequeNode<T> next = first.getNext();
                for (int j = 0; j < size - 1; j++) {
                    if (comparator.compare(currentNode.getItem(), next.getItem()) > 0) {
                        T temp = currentNode.getItem();
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




