package Q4;
import java.util.Arrays;
import DataStructures.Dictionary;
import DataStructures.GraphMatrix;

public class AdjMatrixLab {
    public static <T extends Comparable<T>> void graphColoring(GraphMatrix<T> graph) {
        int vertexCount = graph.getVertexCount();
        int[][] matrix = graph.getMatrix();
        Dictionary<T, Integer> vertices = graph.getVertices();

        // Array to store colors assigned to all vertices. Initially, all vertices are uncolored (-1).
        int[] result = new int[vertexCount];
        Arrays.fill(result, -1);

        // Assign the first color to the first vertex.
        result[0] = 0;

        // Temporary array to store available colors. False value means the color is available.
        boolean[] available = new boolean[vertexCount];

        // Assign colors to the remaining vertices.
        for (int u = 1; u < vertexCount; u++) {
            // Initially mark all colors as available for this vertex.
            Arrays.fill(available, true);

            // Process all adjacent vertices and mark their colors as unavailable.
            for (int v = 0; v < vertexCount; v++)
                if (matrix[u][v] != 0 && result[v] != -1)
                    available[result[v]] = false;  // Color is unavailable for this vertex.
   
            // Find the first available color.
            int color;
            for (color = 0; color < vertexCount; color++)
                if (available[color]) break;

            // Assign the found color to vertex u.
            result[u] = color;
        }

        // Print the results: vertex and its assigned color.
        System.out.println("Vertex Colors:");
        for (int u = 0; u < vertexCount; u++)
            System.out.println("Vertex " + vertices.getKey(u) + " ---> Color " + result[u]);
    }
    
    public static void main(String[] args) {
        var graph = new GraphMatrix<String>(6);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("B", "D");
        graph.addEdge("B", "F");
        graph.addEdge("C", "E");
        graph.addEdge("C", "F");
        graph.addEdge("E", "F");

        graph.display();
        System.out.println();

        graphColoring(graph);
    }

    // Alternatively...
    public static <T extends Comparable<T>> boolean graphColoring(GraphMatrix<T> graph, int maxColors) {
        int vertexCount = graph.getVertexCount();
        int[][] matrix = graph.getMatrix();
        Dictionary<T, Integer> vertices = graph.getVertices();
    
        // Array to store colors assigned to all vertices. Initially, all vertices are uncolored (-1).
        int[] result = new int[vertexCount];
        Arrays.fill(result, -1);  // No vertex is colored initially
    
        // Assign the first color to the first vertex.
        result[0] = 0;
    
        // Temporary array to store availability of colors. False value means the color is available.
        boolean[] available = new boolean[maxColors];
    
        // Assign colors to the remaining vertices.
        for (int u = 1; u < vertexCount; u++) {
            // Reset the available colors to true (all colors available initially).
            Arrays.fill(available, true);
    
            // Process all adjacent vertices and mark their colors as unavailable.
            for (int v = 0; v < vertexCount; v++) {
                // Check if u is adjacent to v and if v is already colored
                if (matrix[u][v] != 0 && result[v] != -1) {
                    if (result[v] < maxColors) {
                        available[result[v]] = false;  // Mark the color used by vertex v as unavailable
                    }
                }
            }
    
            // Find the first available color.
            int color;
            for (color = 0; color < maxColors; color++) {
                if (available[color]) {
                    break;
                }
            }
    
            // If no available color is found within the maxColors limit, return false (failure).
            if (color == maxColors) {
                System.out.println("Failed to color the graph with " + maxColors + " colors.");
                return false;
            }
    
            // Assign the found color to vertex u.
            result[u] = color;
        }
    
        // Print the final results: vertex and its assigned color.
        System.out.println("Vertex Colors:");
        for (int u = 0; u < vertexCount; u++) {
            System.out.println("Vertex " + vertices.getKey(u) + " ---> Color " + result[u]);
        }
    
        return true;  // Success: the graph was successfully colored with maxColors or fewer colors.
    }
    
}
