package Q1;
import java.io.*;
import java.util.*;
import Utils.Console;

public class Prog460a {
    public static void main(String[] args) {
        try {
            var file = new Scanner(new File("Langdat/sort.dat"));
            var nums = new ArrayList<Integer>();
            while (file.hasNext())
                nums.add(file.nextInt());
            file.close();
            
            int num = 0;
            do {
                num = Console.input("Enter a number to search for: ");
                int pos = binarySearch(nums, num);
                System.out.println(pos == -1 ? "Your number does not occur in this list\n" 
                                             : "Your number occurs at position " + (pos + 1) + "\n");
            } while (num != -1);
        } catch (IOException e) { System.out.println("File error"); }
    }

    public static int binarySearch(ArrayList<Integer> nums, int num) {
        int low = 0, high = nums.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums.get(mid) == num) return mid;
            else if (nums.get(mid) < num) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}
