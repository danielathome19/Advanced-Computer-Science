package Q4;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import DataStructures.GraphList;
import Q2.Cl285b;

public class Prog285bGraph {
    public static void main(String[] args) {
        try {
            var file = new Scanner(new File("Langdat/prog285b.dat"));
            var graph = new GraphList<Cl285b>();

            System.out.println("\tId\tCode\tSales\tCommission");
            while (file.hasNext()) {
                int id = file.nextInt();
                int c = file.nextInt();
                double s = file.nextDouble();

                var fred = new Cl285b(id, c, s);
                graph.addVertex(fred);
            }
            file.close();
            
            graph.display();
        } catch (IOException e) {
            System.out.println("Can't find data file!");
        }
    }
}
