package Q1;
import java.io.*;
import java.util.*;
import Utils.Console;

public class Prog481a {
    public static void main(String[] args) {
        try {
            var file = new Scanner(new File("Langdat/numsort.dat"));
            var nums = new ArrayList<Integer>();
            while (file.hasNext())
                nums.add(file.nextInt());
            file.close();
            
            System.out.println("Original list: " + nums);
            bubbleSort(nums);
            System.out.println("\nSorted list: " + nums);
        } catch (IOException e) { System.out.println("File error"); }
    }

    public static void bubbleSort(ArrayList<Integer> nums) {
        for (int i = 0; i < nums.size() - 1; i++) {
            for (int j = 0; j < nums.size() - i - 1; j++) {
                if (nums.get(j) > nums.get(j + 1)) {
                    int temp = nums.get(j);
                    nums.set(j, nums.get(j + 1));
                    nums.set(j + 1, temp);
                }
            }
        }
    }
}
