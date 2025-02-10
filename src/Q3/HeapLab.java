package Q3;
import DataStructures.Heap;

public class HeapLab {
    public static void main(String[] args) {
        var rand = new java.util.Random();
        var minh = new Heap<Integer>(true);
        for (int i = 0; i < 50; i++)
            minh.insert(rand.nextInt(100));
        System.out.println("Delete: " + minh.delete());
        System.out.println("Peek: " + minh.peek());
        System.out.println("Min Heap:");
        while (!minh.isEmpty())
            System.out.print(minh.delete() + " ");
        System.out.println("\n");

        var maxh = new Heap<Integer>(false);
        for (int i = 0; i < 50; i++)
            maxh.insert(rand.nextInt(100));
        System.out.println("Delete: " + maxh.delete());
        System.out.println("Peek: " + maxh.peek());
        System.out.println("Max Heap:");
        while (!maxh.isEmpty())
            System.out.print(maxh.delete() + " ");
        System.out.println();
    }
}
