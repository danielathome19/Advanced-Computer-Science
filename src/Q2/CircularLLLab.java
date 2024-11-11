package Q2;
import DataStructures.CircularLinkedList;

public class CircularLLLab {
    public static void main(String[] args) {
        var list = new CircularLinkedList<Integer>();
        System.out.println(list.size());
        list.add(5);
        list.add(7);
        list.add(13);
        System.out.println(list.size());

        list.print();

        list.remove(1);
        list.print();

        try {
            list.remove(10000000);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No such index");
        }

        for (var x : list) System.out.print(x + " ");
        System.out.println();
    }
}
/*
0
3
5 7 13
5 13
No such index
5 13
*/