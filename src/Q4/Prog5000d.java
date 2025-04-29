package Q4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import Algorithms.GraphAlgorithms;
import DataStructures.Dictionary;
import DataStructures.GraphList;
import DataStructures.GraphMatrix;

public class Prog5000d {
    public static void main(String[] args) {
        try {
            var file = new Scanner(new File("Langdat/prog5000d.txt"));
            var vertexData = new Dictionary<String, String>();
            var graph = new GraphList<String>();
            // var graph = new GraphMatrix<String>(21);
            boolean foundEdges = false;

            file.nextLine();
            while (file.hasNextLine()) {
                String line = file.nextLine();
                if (line.equals("Edges:")) {
                    foundEdges = true;
                    continue;
                }
                var parts = line.split("\t");
                if (!foundEdges) {
                    graph.addVertex(parts[0]);
                    vertexData.insert(parts[0], parts[1]);
                } else graph.addEdge(parts[0], parts[1]);
            }
            file.close();

            graph.display();

            var distances = GraphAlgorithms.shortestPath(graph, "A");
            String closestCity = "";
            int minDistance = Integer.MAX_VALUE;
            for (var entry : distances.entrySet()) {
                if (entry.value < minDistance && !entry.key.equals("A")
                                              && !entry.key.startsWith("T")) {
                    minDistance = entry.value;
                    closestCity = entry.key;
                }
            }

            System.out.printf("Closest city to %s: %s",
                              vertexData.get("A"), vertexData.get(closestCity));
            System.out.printf("\nDistances from %s:\n", vertexData.get("A"));
            for (var entry : distances.entrySet())
                if (!entry.key.equals("A") && !entry.key.startsWith("T"))
                    System.out.println(vertexData.get(entry.key) + " -> " + entry.value);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
