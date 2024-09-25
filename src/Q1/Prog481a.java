package Q1;
import java.io.*;
import java.util.*;
import DataStructures.DynamicArray;
import Algorithms.SortingAlgorithms;

@SuppressWarnings("unchecked")
public class Prog481a {
    public static void main(String[] args) {
        try {
            var file = new Scanner(new File("Langdat/numsort.dat"));
            var nums = new DynamicArray<Integer>();
            while (file.hasNext())
                nums.add(file.nextInt());
            file.close();
            var numsArray = nums.toArray();

            System.out.println("Original list: " + nums);
            SortingAlgorithms.bubbleSort(numsArray);
            System.out.println("\nSorted list: " + Arrays.toString(numsArray));
        } catch (IOException e) { System.out.println("File error"); }
    }
}
