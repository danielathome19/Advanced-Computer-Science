package Q2;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import DataStructures.Queue;

public class Prog285bQueue {
    public static void main(String[] args) {
         try {
            var file = new Scanner(new File("Langdat/prog285b.dat"));
            var queue = new Queue<Cl285b>();

            System.out.println("Id\tCode\tSales\tCommission");
            while (file.hasNext()) {
                int id = file.nextInt();
                int c = file.nextInt();
                double s = file.nextDouble();

                var fred = new Cl285b(id, c, s);
                queue.enqueue(fred);
            }
            file.close();

            while (!queue.isEmpty()) {
                var fred = queue.dequeue();
                System.out.println(fred);
            }
        } catch (IOException e) {
            System.out.println("Can't find data file!");
        }
    }
}
