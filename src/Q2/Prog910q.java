package Q2;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import Algorithms.SortingAlgorithms;

public class Prog910q {
    public static void main(String[] args) {
        try {
            var file = new Scanner(new File("Langdat/numsort.dat"));
            var nums = new ArrayList<Integer>();

            while (file.hasNextInt()) 
                nums.add(file.nextInt());
            file.close();
            var numsArr = nums.toArray(new Integer[0]);

            SortingAlgorithms.quickSort(numsArr);

            for (var num : numsArr) 
                System.out.println(num);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
