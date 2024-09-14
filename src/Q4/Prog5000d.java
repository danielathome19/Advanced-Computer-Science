package Q4;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import Algorithms.GraphAlgorithms;
import DataStructures.Dictionary;
import DataStructures.GraphList;
import DataStructures.GraphMatrix;

public class Prog5000d {
    /*
    public class Prog5000d {
        public static void main(String[] args) {
            // Benchmark adjacency matrix with Dijkstra
            GraphMatrix<String> graphMatrix = new GraphMatrix<>(5);
            graphMatrix.addVertex("A");
            graphMatrix.addVertex("B");
            graphMatrix.addVertex("C");
            graphMatrix.addVertex("D");
            graphMatrix.addVertex("E");

            graphMatrix.addDirectedEdge("A", "B", 10);
            graphMatrix.addDirectedEdge("A", "C", 3);
            graphMatrix.addDirectedEdge("B", "C", 1);
            graphMatrix.addDirectedEdge("B", "D", 2);
            graphMatrix.addDirectedEdge("C", "B", 4);
            graphMatrix.addDirectedEdge("C", "D", 8);
            graphMatrix.addDirectedEdge("C", "E", 2);
            graphMatrix.addDirectedEdge("D", "E", 7);
            graphMatrix.addDirectedEdge("E", "D", 9);

            graphMatrix.display();

            System.out.println("Testing with GraphMatrix:");
            GraphAlgorithms.shortestPath(graphMatrix, "A", "D");
            System.out.println();

            GraphList<String> graphList = new GraphList<>();
            graphList.addVertex("A");
            graphList.addVertex("B");
            graphList.addVertex("C");
            graphList.addVertex("D");
            graphList.addVertex("E");

            graphList.addDirectedEdge("A", "B", 10);
            graphList.addDirectedEdge("A", "C", 3);
            graphList.addDirectedEdge("B", "C", 1);
            graphList.addDirectedEdge("B", "D", 2);
            graphList.addDirectedEdge("C", "B", 4);
            graphList.addDirectedEdge("C", "D", 8);
            graphList.addDirectedEdge("C", "E", 2);
            graphList.addDirectedEdge("D", "E", 7);
            graphList.addDirectedEdge("E", "D", 9);
            
            graphList.display();

            System.out.println("\nTesting with GraphList:");
            GraphAlgorithms.shortestPath(graphList, "A", "D");
        }
    }
    */
    public static void main(String[] args) {
        try {
            var file = new Scanner(new File("Langdat/prog5000d.txt"));
            var vertexData = new Dictionary<String, String>();
            var graph = new GraphList<String>();
            // var graph = new GraphMatrix<String>(21);
            boolean foundEdges = false;

            file.nextLine();
            while (file.hasNextLine()) {
                var line = file.nextLine();
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
            var closestCity = "";
            var minDistance = Integer.MAX_VALUE;
            for (var entry : distances.entrySet()) {
                if (entry.value < minDistance && !entry.key.equals("A") 
                                              && !entry.key.startsWith("T")) {
                    minDistance = entry.value;
                    closestCity = entry.key;
                }
            }

            System.out.println("Closest city to " + vertexData.get("A") + ": " + vertexData.get(closestCity));
            System.out.println("\nDistances from " + vertexData.get("A") + ":");
            for (var entry : distances.entrySet())
                if (!entry.key.equals("A") && !entry.key.startsWith("T"))
                    System.out.println(vertexData.get(entry.key) + " -> " + entry.value);
        } catch (IOException e) { e.printStackTrace(); }
    }
}
