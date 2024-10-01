package DataStructures;

public class IntLinkedList {
    private class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    private Node head;

    public IntLinkedList() { head = null; }

    public void addFront(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null)
            current = current.next;
        current.next = newNode;
    }

    public void insert(int data, int index) {
        if (index < 0) return;
        if (index == 0) {
            addFront(data);
            return;
        }
        Node newNode = new Node(data);
        Node current = head;
        for (int i = 1; i < index - 1; i++) {
            if (current == null) return;
            current = current.next;
        }
        if (current == null) return;
        newNode.next = current.next;
        current.next = newNode;
    }

    public void remove(int data) {
        if (head == null) return;
        if (head.data == data) {
            head = head.next;
            return;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.data == data) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public boolean contains(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) return true;
            current = current.next;
        }
        return false;
    }

    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public boolean isEmpty() { return head == null; }
}
