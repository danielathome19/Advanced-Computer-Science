package DataStructures;

public class BinarySearchTree<T extends Comparable<T>> {
    protected class Node implements Comparable<Node> {
        T data;
        Node left;
        Node right;

        Node(T data) {
            this.data = data;
            left = null;
            right = null;
        }

        public int compareTo(Node o) { return data.compareTo(o.data); }
    }

    protected Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(T element) {
        root = insert(root, element);
    }

    private Node insert(Node node, T element) {
        if (node == null) return new Node(element);
        if (element.compareTo(node.data) < 0)
            node.left = insert(node.left, element);
        else if (element.compareTo(node.data) > 0)
            node.right = insert(node.right, element);
        return node;
    }

    public void printInOrder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.println(node.data + " ");
        inorder(node.right);
    }
}
