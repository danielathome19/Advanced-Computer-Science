package DataStructures;

import java.util.Iterator;

public class IntLinkedList implements Iterable<Integer> {
    private class Node {
        int data;
        Node next;  // Pointer/reference to the next node

        public Node(int data) {
            this.data = data;
            next = null;
        }
    }

    private Node head;

    public IntLinkedList() { head = null; }

    public void addFront(int data) {
        var newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void addLast(int data) {
        var newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        var current = head;
        while (current.next != null)
            current = current.next;
        current.next = newNode;
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private Node current = head;

            public boolean hasNext() { return current != null; }

            public Integer next() {
                int data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
