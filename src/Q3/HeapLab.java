package Q3;
import DataStructures.Heap;

public class HeapLab {
    public static void main(String[] args) {
        // Test min heap
        var minHeap = new Heap<Integer>(true);
        var rand = new java.util.Random();
        for (int i = 0; i < 50; i++) 
            minHeap.insert(rand.nextInt(100));
        System.out.println("Deleting: " + minHeap.delete());
        System.out.println("Peek: " + minHeap.peek());
        System.out.println("Min Heap:");
        while (!minHeap.isEmpty()) 
            System.out.print(minHeap.delete() + " ");
        System.out.println("\n");

        // Test max heap
        var maxHeap = new Heap<Integer>(false);
        for (int i = 0; i < 50; i++) 
            maxHeap.insert(rand.nextInt(100));
        System.out.println("Deleting: " + maxHeap.delete());
        System.out.println("Peek: " + maxHeap.peek());
        System.out.println("Max Heap:");
        while (!maxHeap.isEmpty()) 
            System.out.print(maxHeap.delete() + " ");
        System.out.println();
    }
}
