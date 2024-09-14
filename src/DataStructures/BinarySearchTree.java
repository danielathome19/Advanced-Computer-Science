package DataStructures;

@SuppressWarnings("unused")
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
        root = this.insert(root, element);
    }

    private Node insert(Node node, T element) {
        if (node == null) return new Node(element);
        if (element.compareTo(node.data) < 0)
            node.left = this.insert(node.left, element);
        else if (element.compareTo(node.data) > 0)
            node.right = this.insert(node.right, element);
        return node;
    }

    public void delete(T element) {
        root = this.delete(root, element);
    }

    private Node delete(Node node, T element) {
        if (node == null) return null;
        if (element.compareTo(node.data) < 0)
            node.left = this.delete(node.left, element);
        else if (element.compareTo(node.data) > 0)
            node.right = this.delete(node.right, element);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node min = this.findMin(node.right);
            node.data = min.data;
            node.right = this.delete(node.right, min.data);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node findMax(Node node) {
        while (node.right != null) node = node.right;
        return node;
    }

    public boolean search(T element) {
        return this.search(root, element);
    }

    private boolean search(Node node, T element) {
        if (node == null) return false;
        if (element.compareTo(node.data) < 0)
            return this.search(node.left, element);
        if (element.compareTo(node.data) > 0)
            return this.search(node.right, element);
        return true;
    }

    public void printlnInorder() { this.inorder(root, true); }
    public void printInorder() {
        this.inorder(root, false);
        System.out.println();
    }

    private void inorder(Node node, boolean linebreak) {
        if (node == null) return;
        this.inorder(node.left, linebreak);
        System.out.print(node.data + (linebreak ? "\n" : " "));
        this.inorder(node.right, linebreak);
    }

    public void printPreorder() {
        this.preorder(root);
        System.out.println();
    }

    private void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        this.preorder(node.left);
        this.preorder(node.right);
    }

    public void printPostorder() {
        this.postorder(root);
        System.out.println();
    }

    private void postorder(Node node) {
        if (node == null) return;
        this.postorder(node.left);
        this.postorder(node.right);
        System.out.print(node.data + " ");
    }

    public void breadthFirstSearch() {
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node node = queue.dequeue();
            System.out.print(node.data + " ");
            if (node.left != null) queue.enqueue(node.left);
            if (node.right != null) queue.enqueue(node.right);
        }
        System.out.println();
    }

    public void depthFirstSearch() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.data + " ");
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        System.out.println();
    }

    public int height() {
        return this.height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(this.height(node.left), this.height(node.right));
    }

    public int width() {
        return this.width(root);
    }

    private int width(Node node) {
        if (node == null) return 0;
        Queue<Node> queue = new Queue<>();
        queue.enqueue(node);
        int max = 0;
        while (!queue.isEmpty()) {
            int count = queue.size();
            max = Math.max(max, count);
            while (count-- > 0) {
                Node current = queue.dequeue();
                if (current.left != null) queue.enqueue(current.left);
                if (current.right != null) queue.enqueue(current.right);
            }
        }
        return max;
    }

    public void invert() {
        root = this.invert(root);
    }

    private Node invert(Node node) {
        if (node == null) return null;
        Node left = this.invert(node.left);
        node.left = this.invert(node.right);
        node.right = left;
        return node;
    }
}