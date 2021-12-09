import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class MyALDAQueue<E> implements ALDAQueue<E> {

    private Node first;


    private final int defaultCapacity; //Total
    private int currentCapacity; //Size

    private static class Node<T>{
        T data;
        Node next;

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

        if (size() == 0) {
            first = new Node<>(element);
            currentCapacity++;
        }

        else if (size() >= 1)
            addNode(element, first);
    }
    // Add to the end of the list recursively
    private void addNode(E element, Node current) {

        if (current == null) {
            current = new Node(element);
            currentCapacity++;
        }
        else {
            addNode(element, current.next);
        }
    }


//    private void addIterator(E element) {
//        if (size() == 1) {
//            first.next = new Node(element);
//            addedElement = true;
//            currentCapacity++;
//            System.out.println(size());
//
//        }
//        if (!addedElement) {
//            for(Node temp = first; temp!=null; temp=temp.next) {
//                if (temp.next == null) {
//                    temp.next = new Node(element);
//                    currentCapacity++;
//                    break;
//                }
//            }
//        }
//    }


public String iterateNodesForToString() {
        String toString = "";

        if (first == null) {
            return null;
        }

        toString += "[" + first.data + "]";

        Node temp = first.next;
            while (temp != null) {
                toString = toString.replace("]", ", ");
                toString += temp.data + "]";
                temp = temp.next;
            }
        return toString;
}

    public MyALDAQueue(int defaultCapacity) {

        if (defaultCapacity == 0) {
            throw new IllegalArgumentException();
        }
        if (defaultCapacity < 0) {
            throw new IllegalArgumentException();
        }
        this.defaultCapacity = defaultCapacity;
        this.currentCapacity = 0;
    }




    @Override
    public void addAll(Collection<? extends E> c) {

    }

    @Override
    public E remove() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        Node temp = first;
        first = null;
        currentCapacity--;

        return (E) temp.getData();
    }

    @Override
    public E peek() {
        if (size() == 0) {
            return null;
        }
        if (first == null) {
            return null;
        }
        return (E) first.next;

    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {

        return currentCapacity;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }

        else {
            return false;
        }
    }

    @Override
    public boolean isFull() {
        if (currentCapacity <= defaultCapacity) {
        return false;}
        else {
            return true;}
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
        return 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public String toString() {
        if (iterateNodesForToString() == null) {
            return "[]";
        }
        else
            return iterateNodesForToString();
}
}
