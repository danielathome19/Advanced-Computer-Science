package DataStructures;

public class LinkedList<T extends Comparable<T>> {  // implements List<T>
    private class Node implements Comparable<Node> {
        T data;
        Node next;

        Node(T value) {
            data = value;
            next = null;
        }

        public int compareTo(Node o) { return data.compareTo(o.data); }
    }

    private Node head;
    private int  size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public boolean add(T element) {
        var newNode = new Node(element);
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
        // if (index >= size) throw new IndexOutOfBoundsException();
        if (index == 0) {
            old = head.data;
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < index-1; i++) current = current.next;
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
        for (Node current = head; current != null; current = current.next)
            System.out.print(current.data + " ");
        System.out.println();
    }

    public int size()        { return size; }
    public boolean isEmpty() { return size == 0; }
}
