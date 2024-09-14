package Q3;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import DataStructures.Set;
import Q2.Cl285b;

public class Prog285bSet {
    public static void main(String[] args) {
        try {
            var file = new Scanner(new File("Langdat/prog285b.dat"));
            var set = new Set<Cl285b>();

            System.out.println("Id\tCode\tSales\tCommission");
            while (file.hasNext()) {
                int id = file.nextInt();
                int c = file.nextInt();
                double s = file.nextDouble();

                var fred = new Cl285b(id, c, s);
                set.insert(fred);
            }
            file.close();

            var iter = set.iterator();
            while (iter.hasNext()) {
                var fred = iter.next();
                System.out.println(fred);
            }
        } catch (IOException e) {
            System.out.println("Can't find data file!");
        }
    }
}
