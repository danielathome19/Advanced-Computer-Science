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

    public void delete(T element) {
        root = delete(root, element);
    }

    private Node delete(Node node, T element) {
        if (node == null) return null;
        if (element.compareTo(node.data) < 0)
            node.left = delete(node.left, element);
        else if (element.compareTo(node.data) > 0)
            node.right = delete(node.right, element);
        else {
            if (node.left  == null) return node.right;
            if (node.right == null) return node.left;
            Node min = findMin(node.right);
            node.data = min.data;
            node.right = delete(node.right, min.data);
        }
        return node;
    }

    public T getRootData() {
        return root.data;
    }

    public boolean search(T element) {
        return search(root, element);
    }

    private boolean search(Node node, T element) {
        if (node == null) return false;
        if (element.compareTo(node.data) < 0)
            return search(node.left, element);
        else if (element.compareTo(node.data) > 0)
            return search(node.right, element);
        return true;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node findMax(Node node) {
        while (node.right != null) node = node.right;
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

    public void invert() { root = invert(root); }
    public int  height() { return height(root); }
    public int  width()  { return width(root);  }

    private Node invert(Node node) {
        if (node == null) return null;
        Node left = invert(node.left);
        node.left = invert(node.right);
        node.right = left;
        return node;
    }

    private int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private int width(Node node) {
        if (node == null) return 0;
        Queue<Node> queue = new Queue<>();
        queue.enqueue(node);
        int max = 0;
        while (!queue.isEmpty()) {
            int count = queue.size();
            max = Math.max(max, count);
            while (count --> 0) {
                Node current = queue.dequeue();
                if (current.left != null)  queue.enqueue(current.left);
                if (current.right != null) queue.enqueue(current.right);
            }
        }
        return max;
    }
}
