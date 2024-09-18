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
                int pos = SearchAlgorithms.binarySearchRecursive(arr, num);
                System.out.println(pos == -1 ? "Your number does not occur in the list\n"
                                             : "Your number occurs at index " + (pos+1) + "\n");
            } while (num != -1);

        } catch (IOException e) { e.printStackTrace(); }
    }
}
/*
Enter a number to search for: 5
Your number occurs at index 6

Enter a number to search for: 10
Your number does not occur in the list

Enter a number to search for: 2
Your number occurs at index 3

Enter a number to search for: -1
Your number does not occur in the list
*/