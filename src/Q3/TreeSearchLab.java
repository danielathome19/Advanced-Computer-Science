package Q3;
import DataStructures.BinarySearchTree;

public class TreeSearchLab {
    public static void main(String[] args) {
        // Write a test set for DFS and BFS
        var tree = new BinarySearchTree<Integer>();

        var rand = new java.util.Random();
        for (int i = 0; i < 100; i++) 
            tree.insert(rand.nextInt(100));
        
        System.out.println("DFS:");
        tree.depthFirstSearch();

        System.out.println("\nBFS:");
        tree.breadthFirstSearch();
    }
}
