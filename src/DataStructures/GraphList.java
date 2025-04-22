package DataStructures;

/**
 * Weighted Adjacency List
 */
@SuppressWarnings("unchecked")
public class GraphList<T extends Comparable<T>> implements Graph<T> {
    static class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
        T data;
        Dictionary<Node<T>, Integer> neighbors;  // vertex, weight

        public Node(T data) {
            this.data = data;
            this.neighbors = new Dictionary<>();
        }

        public void addNeighbor(Node<T> node, int weight) {
            if (!hasNeighbor(node)) neighbors.insert(node, weight);
        }

        public boolean hasNeighbor(Node<T> node) { return neighbors.contains(node); }
        public void removeNeighbor(Node<T> node) { neighbors.remove(node); }

        public int compareTo(Node<T> o) { return data.compareTo(o.data); }
        public boolean equals(Node<T> o) { return data.equals(o.data); }
        public int hashCode() { return data.hashCode(); }

        public String toString() {
            StringBuilder nbArray = new StringBuilder("[ ");
            nbArray.append(neighbors.entrySet().size()).append(" ");
            for (Node<T> x : neighbors.keySet())
                nbArray.append(x.data).append(" ");
            nbArray.append("]");
            return "Data: " + data + "\tNeighbors: " + nbArray;
        }
    }

    private final Dictionary<T, Node<T>> vertices;

    public GraphList() { vertices = new Dictionary<>(); }

    public void display() { vertices.enumerate(); }
    private Node<T> getVertex(T data) { return vertices.get(data); }

    public void addVertex(T vertex) {
        if (!hasVertex(vertex))
            vertices.insert(vertex, new Node<>(vertex));
    }

    public void addEdge(T source, T destination) { addEdge(source, destination, 1); }
    public void addEdge(T source, T destination, int weight) {
        Node<T> sourceNode = getVertex(source);
        Node<T> destinationNode = getVertex(destination);
        if (sourceNode != null && destinationNode != null) {
            sourceNode.addNeighbor(destinationNode, weight);
            destinationNode.addNeighbor(sourceNode, weight);
        }
    }

    public void addDirectedEdge(T source, T destination) { addDirectedEdge(source, destination, 1); }
    public void addDirectedEdge(T source, T destination, int weight) {
        Node<T> sourceNode = getVertex(source);
        Node<T> destinationNode = getVertex(destination);
        if (sourceNode != null && destinationNode != null)
            sourceNode.addNeighbor(destinationNode, weight);
    }

    public void removeVertex(T vertex) {
        Node<T> node = getVertex(vertex);
        if (node != null) {
            for (Node<T> neighbor : node.neighbors.keySet())
                neighbor.neighbors.remove(node);
            vertices.remove(vertex);
        }
    }

    public void removeEdge(T source, T destination) {
        Node<T> sourceNode = getVertex(source);
        Node<T> destinationNode = getVertex(destination);
        if (sourceNode != null && destinationNode != null) {
            sourceNode.removeNeighbor(destinationNode);
            destinationNode.removeNeighbor(sourceNode);
        }
    }

    public void removeDirectedEdge(T source, T destination) {
        Node<T> sourceNode = getVertex(source);
        Node<T> destinationNode = getVertex(destination);
        if (sourceNode != null && destinationNode != null)
            sourceNode.removeNeighbor(destinationNode);
    }

    public boolean hasVertex(T vertex) { return vertices.contains(vertex); }
    public boolean hasEdge(T source, T destination) {
        Node<T> sourceNode = getVertex(source);
        Node<T> destinationNode = getVertex(destination);
        return sourceNode != null && destinationNode != null &&
               sourceNode.hasNeighbor(destinationNode);
    }

    public int getEdgeWeight(T source, T destination) {
        Node<T> sourceNode = getVertex(source);
        Node<T> destinationNode = getVertex(destination);
        if (sourceNode != null && destinationNode != null)
            return sourceNode.neighbors.get(destinationNode);
        return Integer.MAX_VALUE;
    }

    Node<T> getNode(T vertex) { return vertices.get(vertex); }
    public int getVertexCount() { return vertices.size(); }

    Dictionary<Node<T>, Integer> getNeighborsNode(T vertex) {
        Node<T> node = getNode(vertex);
        return node != null ? node.neighbors : null;
    }

    public Dictionary<T, Integer> getVertices() {
        Dictionary<T, Integer> temp = new Dictionary<>();
        for (Dictionary.Entry<T, Node<T>> vertex : vertices.entrySet())
            temp.insert(vertex.key, vertex.value.neighbors.size());
        return temp;
    }

    public DynamicArray<T> getNeighbors(T vertex) {
        Node<T> node = getNode(vertex);
        DynamicArray<T> neighbors = new DynamicArray<>();
        if (node == null) return neighbors;
        for (Dictionary.Entry<Node<T>, Integer> neighbor : node.neighbors.entrySet())
            neighbors.add(neighbor.key.data);
        return neighbors;
    }
}
