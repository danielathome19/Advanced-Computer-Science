package DataStructures;

/* ==========  Graph (Undirected Weighted Adjacency List with BFS, DFS, Dijkstra, Bellman-Ford) ========== */
@SuppressWarnings("unchecked")
public class GraphList<T extends Comparable<T>> implements Graph<T> {
    static class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
        T data;
        Dictionary<Node<T>, Integer> neighbors;

        public Node(T data) {
            this.data = data;
            this.neighbors = new Dictionary<>();
        }

        public void addNeighbor(Node<T> node, int weight) {
            if (!this.neighbors.contains(node))
                this.neighbors.insert(node, weight);
        }

        public boolean hasNeighbor(Node<T> node) { return this.neighbors.contains(node); }
        public void removeNeighbor(Node<T> node) { this.neighbors.remove(node); }

        public String toString() {
            StringBuilder neighborArray = new StringBuilder("[ ");
            neighborArray.append(neighbors.entrySet().size() + " ");
            for (var x : neighbors.keySet())
                neighborArray.append(x.data).append(" ");
            neighborArray.append("]");
            return "Data: " + this.data + "\tNeighbors: " + neighborArray;
        }

        public int compareTo(Node<T> g) { return this.data.compareTo(g.data); }
        public boolean equals(Object o) { return this.data.equals(((Node<T>) o).data); }
        public int hashCode() { return this.data.hashCode(); }
    }

    private final Dictionary<T, Node<T>> vertices;

    public GraphList() { vertices = new Dictionary<>(); }
    
    public void display() { vertices.enumerate(); }

    private Node<T> getVertex(T data) { return vertices.get(data); }

    public void addVertex(T vertex) {
        if (!vertices.containsKey(vertex))
            vertices.insert(vertex, new Node<>(vertex));
    }

    public void addEdge(T source, T destination) { this.addEdge(source, destination, 1); }
    public void addEdge(T source, T destination, int weight) {
        Node<T> sourceNode = getVertex(source);
        Node<T> destinationNode = getVertex(destination);
        if (sourceNode != null && destinationNode != null) {
            sourceNode.addNeighbor(destinationNode, weight);
            destinationNode.addNeighbor(sourceNode, weight);
        }
    }

    public void addDirectedEdge(T source, T destination) { this.addDirectedEdge(source, destination, 1); }
    public void addDirectedEdge(T source, T destination, int weight) {
        Node<T> sourceNode = getVertex(source);
        Node<T> destinationNode = getVertex(destination);
        if (sourceNode != null && destinationNode != null)
            sourceNode.addNeighbor(destinationNode, weight);
    }

    public void removeVertex(T vertex) {
        var node = (Node<T>) vertices.get(vertex);
        if (node != null) {
            for (var neighbor : node.neighbors.keySet())
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
        return sourceNode != null && destinationNode != null && sourceNode.hasNeighbor(destinationNode);
    }

    Node<T> getNode(T vertex) { return vertices.get(vertex); }

    Dictionary<Node<T>, Integer> getNeighborsNode(T vertex) {
        Node<T> node = vertices.get(vertex);
        return node != null ? node.neighbors : null;
    }

    public DynamicArray<T> getNeighbors(T vertex) {
        Node<T> node = vertices.get(vertex);
        DynamicArray<T> neighbors = new DynamicArray<>();
        if (node == null) return neighbors;
        for (var neighbor : node.neighbors.entrySet())
            neighbors.add(neighbor.key.data);
        return neighbors;
    }

    public Dictionary<T, Integer> getVertices() {
        Dictionary<T, Integer> verticesT = new Dictionary<>();
        for (var vertex : this.vertices.entrySet())
            verticesT.insert(vertex.key, vertex.value.neighbors.size());
        return verticesT;
    }

    public int getEdgeWeight(T source, T destination) {
        Node<T> sourceNode = getVertex(source);
        Node<T> destinationNode = getVertex(destination);
        if (sourceNode != null && destinationNode != null)
            return sourceNode.neighbors.get(destinationNode);
        return Integer.MAX_VALUE;
    }

    public int getVertexCount() { return vertices.size(); }
}
