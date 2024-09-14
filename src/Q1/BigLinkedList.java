package Q1;
import DataStructures.IntLinkedList;

public class BigLinkedList {

    public static void main(String[] args) {
        var list = new IntLinkedList();
        list.addFront(5);
        list.addFront(4);
        list.addFront(3);
        list.addFront(2);
        list.addFront(1);
        list.print();
        list.addLast(6);
        list.addLast(7);
        list.addLast(8);
        list.addLast(9);
        list.addLast(10);
        System.out.println("Number of nodes: " + list.size());
        list.insert(100, 3);
        System.out.println("Is list empty: " + list.isEmpty());
        list.print();
    }
}