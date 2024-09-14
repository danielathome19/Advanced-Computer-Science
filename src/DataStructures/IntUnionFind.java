package DataStructures;

public class IntUnionFind {
    private final int[] parent;
    private final int[] rank;
    private int count;

    public IntUnionFind(int size) {
        if (size <= 0) throw new IllegalArgumentException("Size must be greater than 0");
        parent = new int[size];
        rank = new int[size];
        count = size;
        for (int i = 0; i < size; i++)
            makeSet(i);
    }

    private void makeSet(int i) {
        parent[i] = i;
        rank[i] = 0;
    }

    public int find(int p) {
        if (p != parent[p]) parent[p] = find(parent[p]);  // Path compression
        return parent[p];
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP != rootQ) {
            if (rank[rootP] > rank[rootQ]) {
                parent[rootQ] = rootP;
            } else if (rank[rootP] < rank[rootQ]) {
                parent[rootP] = rootQ;
            } else {
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
            count--;
        }
    }

    public int count() { return count; }
    public int size() { return parent.length; }
    public boolean connected(int p, int q) { return find(p) == find(q); }
}
