package Q3;
import DataStructures.IntUnionFind;

public class UnionFindLab {  // Island Connectivity Finder
    private final IntUnionFind unionFind;

    // Constructor that initializes the union-find structure with n islands
    public UnionFindLab(int n) {
        unionFind = new IntUnionFind(n);
    }

    // Adds a bridge between two islands
    public void addBridge(int island1, int island2) {
        unionFind.union(island1, island2);
    }

    // Checks if two islands are connected
    public boolean areConnected(int island1, int island2) {
        return unionFind.connected(island1, island2);
    }

    // Returns the number of island groups
    public int numberOfIslandGroups() {
        return unionFind.count();
    }

    public static void main(String[] args) {
        // Test case 1
        var icf = new UnionFindLab(5);
        System.out.println(icf.numberOfIslandGroups());  // Expected: 5

        // Test case 2
        icf.addBridge(0, 1);
        System.out.println(icf.areConnected(0, 1));     // Expected: true
        System.out.println(icf.numberOfIslandGroups()); // Expected: 4

        // Test case 3
        icf.addBridge(2, 3);
        icf.addBridge(1, 2);
        System.out.println(icf.areConnected(0, 3));     // Expected: true
        System.out.println(icf.numberOfIslandGroups()); // Expected: 2

        // Test case 4
        icf.addBridge(0, 3);  // Already connected
        System.out.println(icf.numberOfIslandGroups()); // Expected: 2
    }
}
