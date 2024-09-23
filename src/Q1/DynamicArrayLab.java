package Q1;
import java.util.ArrayList;
import DataStructures.DynamicArray;

public class DynamicArrayLab {
    public static void main(String[] args) {
        System.out.println("========== Dynamic Array ==========");
        var dynamicArray = new DynamicArray<Integer>();
        long start = System.nanoTime();
        for (int i = 0; i < 100_000; i++)
            dynamicArray.add(i);
        long end = System.nanoTime();
        System.out.println("Dynamic Array add: " + (end - start) / 1e6 + " ms");
        // We use 1e6 to convert nanoseconds to milliseconds

        start = System.nanoTime();
        for (int i = 0; i < 100_000; i++)
            dynamicArray.remove(0);
        end = System.nanoTime();
        System.out.println("Dynamic Array remove: " + (end - start) / 1e6 + " ms");

        start = System.nanoTime();
        dynamicArray.sort();
        end = System.nanoTime();
        System.out.println("Dynamic Array sort: " + (end - start) / 1e6 + " ms");

        System.out.println("========== ArrayList ==========");
        var arrayList = new ArrayList<Integer>();
        start = System.nanoTime();
        for (int i = 0; i < 100_000; i++)
            arrayList.add(i);
        end = System.nanoTime();
        System.out.println("ArrayList add: " + (end - start) / 1e6 + " ms");

        start = System.nanoTime();
        for (int i = 0; i < 100_000; i++)
            arrayList.remove(0);
        end = System.nanoTime();
        System.out.println("ArrayList remove: " + (end - start) / 1e6 + " ms");

        start = System.nanoTime();
        arrayList.sort(null);
        end = System.nanoTime();
        System.out.println("ArrayList sort: " + (end - start) / 1e6 + " ms");
    }
}
