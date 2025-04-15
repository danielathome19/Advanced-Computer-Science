package DataStructures;

/**
 * Weighted Adjacency Matrix
 */
public class GraphMatrix<T extends Comparable<T>> implements Graph<T> {
    private final Dictionary<T, Integer> vertices;
    private final int[][] matrix;
    private int vertexCount;

    public GraphMatrix(int size) {
        vertices = new Dictionary<>();
        matrix = new int[size][size];
        vertexCount = 0;
    }


    public void addVertex(T vertex) {
        if (!vertices.containsKey(vertex))
            vertices.insert(vertex, vertexCount++);
    }

    public void addEdge(T source, T destination) {
        addEdge(source, destination, 1);
    }

    public void addEdge(T source, T destination, int weight) {
        int sourceIndex = vertices.get(source);
        int destinationIndex = vertices.get(destination);
        matrix[sourceIndex][destinationIndex] = weight;
        matrix[destinationIndex][sourceIndex] = weight;
    }

    public void addDirectedEdge(T source, T destination) {
        addDirectedEdge(source, destination, 1);
    }

    public void addDirectedEdge(T source, T destination, int weight) {
        int sourceIndex = vertices.get(source);
        int destinationIndex = vertices.get(destination);
        matrix[sourceIndex][destinationIndex] = weight;
    }

    public void removeVertex(T vertex) {
        if (!vertices.containsKey(vertex)) return;
        int index = vertices.get(vertex);
        for (int i = 0; i < vertexCount; i++) {
            matrix[i][index] = 0;
            matrix[index][i] = 0;
        }
        vertices.remove(vertex);
        vertexCount--;
    }

    public void removeEdge(T source, T destination) {
        int sourceIndex = vertices.get(source);
        int destinationIndex = vertices.get(destination);
        matrix[sourceIndex][destinationIndex] = 0;
        matrix[destinationIndex][sourceIndex] = 0;
    }

    public void removeDirectedEdge(T source, T destination) {
        int sourceIndex = vertices.get(source);
        int destinationIndex = vertices.get(destination);
        matrix[sourceIndex][destinationIndex] = 0;
    }

    public boolean hasVertex(T vertex) {
        return vertices.containsKey(vertex);
    }

    public boolean hasEdge(T source, T destination) {
        int sourceIndex = vertices.get(source);
        int destinationIndex = vertices.get(destination);
        return matrix[sourceIndex][destinationIndex] != 0;
    }

    public void display() {
        System.out.println("Vertices:");
        vertices.enumerate();
        System.out.println("Matrix:");
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }

    public int getEdgeWeight(T source, T destination) {
        int sourceIndex = vertices.get(source);
        int destinationIndex = vertices.get(destination);
        return matrix[sourceIndex][destinationIndex];
    }

    public Dictionary<T, Integer> getVertices() { return vertices; }
    public int[][] getMatrix() { return matrix; }
    public int getVertexCount() { return vertexCount; }

    public DynamicArray<T> getNeighbors(T vertex) {
        DynamicArray<T> neighbors = new DynamicArray<>();
        int index = vertices.get(vertex);
        for (int i = 0; i < vertexCount; i++)
            if (matrix[index][i] != 0)
                neighbors.add(vertices.getKey(i));
        return neighbors;
    }
}
