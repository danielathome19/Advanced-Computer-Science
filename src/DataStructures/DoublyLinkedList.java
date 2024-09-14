package DataStructures;

public class DoublyLinkedList<T extends Comparable<T>> {
    private class Node implements Comparable<Node> {
        T data;
        Node prev;
        Node next;

        Node(T data) {
            this.data = data;
            prev = null;
            next = null;
        }

        public int compareTo(Node o) { return data.compareTo(o.data); }
    }

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(T element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        size++;
    }

    public void addSorted(T element) {
        if (head == null) {
            add(element);
            return;
        }
        Node newNode = new Node(element);
        Node current = head;
        while (current != null && current.compareTo(newNode) < 0) 
            current = current.next;
        if (current == head) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (current == null) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (index == 0) {
            head = head.next;
            head.prev = null;
        } else if (index == size - 1) {
            tail = tail.prev;
            tail.next = null;
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) current = current.next;
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size--;
    }

    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current.data;
    }

    public void set(int index, T element) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node current = head;
        for (int i = 0; i < index; i++) current = current.next;
        current.data = element;
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
}