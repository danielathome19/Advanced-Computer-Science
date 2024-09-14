package DataStructures;

public interface Graph<T extends Comparable<T>> {
    void addVertex(T vertex);
    void addEdge(T source, T destination);
    void removeVertex(T vertex);
    void removeEdge(T source, T destination);
    boolean hasVertex(T vertex);
    boolean hasEdge(T source, T destination);
    void display();
    int getEdgeWeight(T source, T destination);
    Dictionary<T, Integer> getVertices();
    DynamicArray<T> getNeighbors(T vertex);
}
