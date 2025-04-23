package Q4;

import DataStructures.GraphList;
import DataStructures.Set;
import Q1.Prog285b.Cl285b;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Prog285bGraph {
    public static void main(String[] args) {
        try {
            var file = new Scanner(new File("Langdat/prog285b.dat"));
            var graph = new GraphList<Cl285b>();  // or GraphMatrix<>(12);

            System.out.println("Id\tCode\tSales\tCommission");
            while (file.hasNext()) {
                int id   = file.nextInt();
                int c    = file.nextInt();
                double s = file.nextDouble();

                var fred = new Cl285b(id, c, s);
                graph.addVertex(fred);
            }
            file.close();

            var nodes = graph.getVertices().keySet();
            var list = new ArrayList<Cl285b>();
            for (var n : nodes) list.add(n);
            for (int i = 0; i < 7; i++) {
                int r1 = (int)(Math.random() * nodes.size());
                int r2 = (int)(Math.random() * nodes.size());
                if (r1 != r2) graph.addEdge(list.get(r1), list.get(r2));
            }

            graph.display();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
