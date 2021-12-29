import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyALDAQueue<E> implements ALDAQueue<E>, Iterable<E> {

    private Node<E> first;
    private final int defaultCapacity;
    private int currentCapacity = 0;

    public MyALDAQueue(int defaultCapacity) {

        if (defaultCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.defaultCapacity = defaultCapacity;
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

    @Override
    public void add(E element) {

        if (element == null)
            throw new NullPointerException();
        if (currentCapacity == defaultCapacity) {
            throw new IllegalStateException();
        }
        if (size() == 0) {
            first = new Node<>(element);
            currentCapacity++;
        } else if (size() >= 1)
            addNode(element, first);
    }

    private void addNode(E element, Node<E> current) {

        if (current.next == null) {
            current.next = new Node<>(element);
            currentCapacity++;
        } else
            addNode(element, current.next);
    }


    public String iterateNodesForToString() {

        if (first == null)
            return null;

        StringBuilder toString = new StringBuilder("[" + first.data);
        Node<E> temp = first;

        for (int i = 1; i < size(); i++) {
            temp = temp.next;
            toString.append(", ").append(temp.data);
        }
        toString.append("]");

        return toString.toString();
    }

    @Override
    public void addAll(Collection<? extends E> c) {
        for (E element : c) {
            add(element);
        }
    }

    @Override
    public E remove() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        Node<E> temp = first;
        if (size() >= 1) {
            first = first.next;
            currentCapacity--;
        }

        return temp.getData();
    }

    @Override
    public E peek() {
        if (size() == 0)
            return null;
        return first.data;
    }

    @Override
    public void clear() {
        if (size() == 0)
            return;
        if (!iterator().hasNext())
            remove();

        first.data = null;
        first = null;
        currentCapacity = 0;
    }

    @Override
    public int size() {
        return currentCapacity;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean isFull() {
        return currentCapacity == defaultCapacity;
    }

    @Override
    public int totalCapacity() {
        return defaultCapacity;
    }

    @Override
    public int currentCapacity() {
        return defaultCapacity - currentCapacity;
    }

    @Override
    public int discriminate(E e) {
        int returnValue = 0;

        if (e == null)
            throw new NullPointerException();
        if (size() == 0) {
            return 0;
        }

        Node<E> temp = first;
        Node<E> tempPre = null;
        int size = size();

        for (int i = 0; i < size; i++) {
            if (temp.data.equals(e)) {
                if (tempPre != null) {
                    tempPre.next = temp.next;
                }
                if (temp == first) {
                    first = first.next;
                }
                currentCapacity--;
                add(temp.data);
                returnValue++;
                if (!temp.data.equals(e)) {
                    tempPre = temp;
                }

            } else {
                tempPre = temp;
            }
            temp = temp.next;
        }
        return returnValue;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<>() {
            Node<E> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        if (iterateNodesForToString() == null) {
            return "[]";
        } else
            return iterateNodesForToString();
    }
}
