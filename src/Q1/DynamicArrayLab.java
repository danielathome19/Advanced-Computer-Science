package Q1;
import DataStructures.DynamicArray;

public class DynamicArrayLab {
    public static void main(String[] args) {
        System.out.println("========== Dynamic Array ==========");
        var dArray = new DynamicArray<Integer>();
        long start = System.nanoTime();
        for (int i = 0; i < 100_000; i++)
            dArray.add(i);
        long end = System.nanoTime();
        System.out.println("Dynamic Array add: " + (end-start)/1e6 + " ms");  // ns to ms

        start = System.nanoTime();
        for (int i = 0; i < 100_000; i++)
            dArray.remove(0);
        end = System.nanoTime();
        System.out.println("Dynamic Array remove: " + (end-start)/1e6 + " ms");

        start = System.nanoTime();
        dArray.sort();
        end = System.nanoTime();
        System.out.println("Dynamic Array sort: " + (end-start)/1e6 + " ms");

        // TODO: check against ArrayList
        // sort with "arrayList.sort(null);
    }
}
