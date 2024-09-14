package DataStructures;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ListIterator;

// With test, implements List interface
public class LinkedList<T extends Comparable<T>> implements List<T> {
    private class Node implements Comparable<Node> {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            next = null;
        }

        public int compareTo(Node o) { return data.compareTo(o.data); }
    }

    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public boolean add(T element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) current = current.next;
            current.next = newNode;
        }
        size++;
        return true;
    }

    public T remove(int index) {
        T old;
        if (index == 0) {
            old = head.data;
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) current = current.next;
            old = current.next.data;
            current.next = current.next.next;
        }
        size--;
        return old;
    }

    public T get(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current.data;
    }

    public T set(int index, T element) {
        T old;
        Node current = head;
        for (int i = 0; i < index; i++) current = current.next;
        old = current.data;
        current.data = element;
        return old;
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    /* ========== List interface methods ========== */
    public boolean remove(Object o) {
        return this.remove(this.indexOf(o)) != null;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object element : c)
            if (!this.contains(element)) return false;
        return true;
    }

    public boolean addAll(Collection<? extends T> c) {
        for (T element : c) this.add(element);
        return true;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        for (T element : c) this.add(index++, element);
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        for (Object element : c) this.remove(element);
        return true;
    }

    public boolean retainAll(Collection<?> c) {
        for (int i = 0; i < size; i++)
            if (!c.contains(this.get(i))) this.remove(i--);
        return true;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++)
            if (this.get(i).equals(o)) return i;
        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--)
            if (this.get(i).equals(o)) return i;
        return -1;
    }

    public ListIterator<T> listIterator() {
        return this.subList(0, size).listIterator();
    }

    public ListIterator<T> listIterator(int index) {
        return this.subList(index, size).listIterator();
    }

    public List<T> subList(int fromIndex, int toIndex) {
        List<T> subList = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++)
            subList.add(this.get(i));
        return subList;
    }

    public void add(int index, T element) {
        if (index == 0) {
            Node newNode = new Node(element);
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) current = current.next;
            Node newNode = new Node(element);
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < size; i++)
            if (this.get(i).equals(o)) return true;
        return false;
    }

    public Iterator<T> iterator() {
        return this.subList(0, size).iterator();
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++)
            array[i] = this.get(i);
        return array;
    }

    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) a = (T1[]) new Object[size];
        for (int i = 0; i < size; i++)
            a[i] = (T1) this.get(i);
        return a;
    }
}