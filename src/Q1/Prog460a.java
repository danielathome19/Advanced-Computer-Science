package Q1;
import java.io.*;
import java.util.*;
import Utils.Console;
import Algorithms.SearchAlgorithms;

public class Prog460a {
    public static void main(String[] args) {
        try {
            var file = new Scanner(new File("Langdat/sort.dat"));
            var nums = new ArrayList<Integer>();
            while (file.hasNext())
                nums.add(file.nextInt());
            file.close();
            
            int lcv = 0;
            var arr = new Integer[nums.size()];
            for (var n : nums) arr[lcv++] = n;

            int num = 0;
            do {
                num = Console.input("Enter a number to search for: ");
                int pos = SearchAlgorithms.binarySearch(arr, num);
                System.out.println(pos == -1 ? "Your number does not occur in this list\n" 
                                             : "Your number occurs at position " + (pos + 1) + "\n");
            } while (num != -1);
        } catch (IOException e) { System.out.println("File error"); }
    }
}
