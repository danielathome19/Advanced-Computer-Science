package Q1;
import DataStructures.LinkedList;

public class TestLinkedList {
    public static void main(String[] args) {
        var list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.print();
        list.remove(2);
        list.print();
        list.remove(0);
        list.print();
        list.remove(1);
        list.print();
    }
}
