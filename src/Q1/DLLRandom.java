package Q1;
import DataStructures.DoublyLinkedList;

// Add 10 numbers to a doubly linked list in sorted order and print; make sorted add
public class DLLRandom {
    public static void main(String[] args) {
        var list = new DoublyLinkedList<Integer>();
        for (int i = 0; i < 10; i++) 
            list.addSorted((int) (Math.random() * 100));
        list.print();
    }
}