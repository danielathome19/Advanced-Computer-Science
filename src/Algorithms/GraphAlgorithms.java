package Algorithms;
import DataStructures.*;

@SuppressWarnings("unchecked")
public class GraphAlgorithms {
    // Dijkstra's Algorithm 
    public static <T extends Comparable<T>> Dictionary<T, Integer> shortestPath(Graph<T> g, T start) { return shortestPath(g, start, start, false); }
    public static <T extends Comparable<T>> Dictionary<T, Integer> shortestPath(Graph<T> g, T start, T goal) {  return shortestPath(g, start, goal, true); }
    public static <T extends Comparable<T>> Dictionary<T, Integer> shortestPath(Graph<T> g, T start, T goal, boolean display) {
        if (!g.hasVertex(start) || !g.hasVertex(goal)) {
            System.out.println("Invalid start or goal vertex");
            return new Dictionary<>();
        }

        PriorityQueue<Tuple> queue = new PriorityQueue<>(true);  // Min Heap Priority Queue
        Dictionary<T, Integer> distances = new Dictionary<>();
        Dictionary<T, T> previous = new Dictionary<>();

        for (T vertex : g.getVertices().keySet())
            distances.insert(vertex, Integer.MAX_VALUE);
        distances.insert(start, 0);
        queue.enqueue(new Tuple(start, 0));

        while (!queue.isEmpty()) {
            Tuple current = queue.dequeue();
            T currentVertex = (T) current.get(0);
            int currentDistance = (int) current.get(1);

            if (currentDistance > distances.get(currentVertex)) continue;
            for (T neighbor : g.getNeighbors(currentVertex)) {
                int weight = g.getEdgeWeight(currentVertex, neighbor);
                int newDistance = currentDistance + weight;
                if (newDistance < distances.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    distances.insert(neighbor, newDistance);
                    previous.insert(neighbor, currentVertex);
                    queue.enqueue(new Tuple(neighbor, newDistance));
                }
            }
        }

        if (!previous.containsKey(goal)) {
            if (display) System.out.println("No path found");
            return distances;
        }

        if (display) { 
            reconstructPath(previous, goal);
            System.out.println("\nTotal cost: " + distances.get(goal));
        }
        return distances;
    }

    static <T extends Comparable<T>> void reconstructPath(Dictionary<T, T> cameFrom, T current) {
        if (current == null) return;
        if (cameFrom.containsKey(current)) {
            reconstructPath(cameFrom, cameFrom.get(current));
            System.out.print(" -> ");
        }
        System.out.print(current);
    }
}
