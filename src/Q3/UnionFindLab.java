package Q3;
import DataStructures.UnionFind;

public class UnionFindLab {  // Island Connectivity Finder
    private final UnionFind unionFind;

    public UnionFindLab(int n) {
        unionFind = new UnionFind(n);  // n Islands
    }

    public void addBridge(int isle1, int isle2) {
        unionFind.union(isle1, isle2);
    }

    public boolean areConnected(int isle1, int isle2) {
        return unionFind.connected(isle1, isle2);
    }

    public int numberOfIslandGroups() {
        return unionFind.count();
    }

    public static void main(String[] args) {
        // Test Case 1
        var icf = new UnionFindLab(5);
        System.out.println(icf.numberOfIslandGroups());  // Expected: 5

        // Test Case 2
        icf.addBridge(0, 1);
        System.out.println(icf.areConnected(0, 1));      // Expected: true
        System.out.println(icf.numberOfIslandGroups());  // Expected: 4

        // Test Case 3
        icf.addBridge(2, 3);
        icf.addBridge(1, 2);
        System.out.println(icf.areConnected(0, 3));      // Expected: true
        System.out.println(icf.numberOfIslandGroups());  // Expected: 2

        // Test Case 4
        icf.addBridge(0, 3);  // Already connected
        System.out.println(icf.numberOfIslandGroups());  // Expected: 2
    }
}
/*
5
true
4
true
2
2
*/
