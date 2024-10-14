package Q1;
import DataStructures.DoublyLinkedList;

public class TestDLListRandom {
    public static void main(String[] args) {
        var list = new DoublyLinkedList<Integer>();
        for (int i = 0; i < 20; i++)
            list.add((int) (Math.random() * 100));  // TODO: change to addSorted
        list.print();
        list.printReverse();

        // TODO: test the rest of the DLL methods
    }
}
