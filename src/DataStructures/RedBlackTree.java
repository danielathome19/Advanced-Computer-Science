package DataStructures;
import java.awt.*;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("unused")
public class RedBlackTree<T extends Comparable<T>> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node implements Comparable<Node> {
        T data;
        boolean color;
        Node parent;
        Node left;
        Node right;

        Node(T data) {
            this.data = data;
            color = RED;
            parent = null;
            left = null;
            right = null;
        }

        public int compareTo(Node x) { return data.compareTo(x.data); }
    }

    private final Node NIL;
    private Node root;

    public RedBlackTree() {
        super();
        NIL = new Node(null);
        NIL.color = BLACK;
        root = NIL;
    }

    private boolean isRed(Node node) { return node != null && node.color == RED; }
    private void leftRotate(Node x)  { this.rotate(x, true); }
    private void rightRotate(Node x) { this.rotate(x, false); }

    private void rotate(Node x, boolean isLeftRotate) {
        Node y = isLeftRotate ? x.right : x.left;

        if (isLeftRotate) {
            x.right = y.left;
            if (y.left != NIL) y.left.parent = x;
        } else {
            x.left = y.right;
            if (y.right != NIL) y.right.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;

        if (isLeftRotate) y.left = x;
        else              y.right = x;
        x.parent = y;
    }

    private Node flipColors(Node u, Node k) {
        u.color = BLACK;
        k.parent.color = BLACK;
        k.parent.parent.color = RED;
        return k.parent.parent;
    }

    public void insert(T element) {
        Node node = new Node(element);
        node.left = NIL;   // Property 3
        node.right = NIL;  // " "
        node.color = RED;  // Property 1

        Node y = null;
        Node x = root;

        while (x != NIL) {
            y = x;
            if (x.data.compareTo(element) == 0) return;  // Prevent duplicates
            x = node.compareTo(x) < 0 ? x.left : x.right;
        }

        node.parent = y;
        if (y == null)
            root = node;
        else if (node.data.compareTo(y.data) < 0)
            y.left = node;
        else
            y.right = node;

        // Property 2
        if (node.parent == null) {
            node.color = BLACK;
            return;
        }

        if (node.parent.parent == null) return;
        this.fixInsert(node); // Property 5
    }

    private void fixInsert(Node k) {
        Node u;
        while (k.parent.color == RED) {  // Property 4
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == RED) {  // Case 1: uncle is red
                    k = this.flipColors(u, k);
                } else {
                    if (k == k.parent.left) { // Case 2: uncle is black and k is left child
                        k = k.parent;
                        this.rightRotate(k);
                    }
                    // Case 3: uncle is black and k is right child
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    this.leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;
                if (u.color == RED) {
                    k = this.flipColors(u, k);
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        this.leftRotate(k);
                    }
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    this.rightRotate(k.parent.parent);
                }
            }
            if (k == root) break;
        }
        root.color = BLACK;  // Property 2
    }

    // TODO: SKIP
    public void delete(T element) {
        Node node = this.search(root, element);
        if (node == null) return;
        this.delete(node);
    }

    // TODO: SKIP
    private void delete(Node node) {
        Node x, y = node;
        boolean yOriginalColor = y.color;
        if (node.left == NIL) {
            x = node.right;
            this.transplant(node, node.right);
        } else if (node.right == NIL) {
            x = node.left;
            this.transplant(node, node.left);
        } else {
            y = this.findMin(node.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == node)
                x.parent = y;
            else {
                this.transplant(y, y.right);
                y.right = node.right;
                y.right.parent = y;
            }
            this.transplant(node, y);
            y.left = node.left;
            y.left.parent = y;
            y.color = node.color;
        }
        if (yOriginalColor == BLACK) {
            while (x != root && x.color == BLACK)
                x = (x == x.parent.left) ? this.fixDelete(x, true) : this.fixDelete(x, false);
            x.color = BLACK;
        }
    }

    // TODO: SKIP
    private Node fixDelete(Node x, boolean fixLeft) {
        Node s = fixLeft ? x.parent.right : x.parent.left;
        if (s.color == RED) {
            s.color = BLACK;
            x.parent.color = RED;
            if (fixLeft) {
                this.leftRotate(x.parent);
                s = x.parent.right;
            } else {
                this.rightRotate(x.parent);
                s = x.parent.left;
            }
        }
        if (s.left.color == BLACK && s.right.color == BLACK) {
            s.color = RED;
            x = x.parent;
        } else {
            if ((fixLeft ? s.right.color : s.left.color) == BLACK) {
                if (fixLeft) s.left.color = BLACK;
                else         s.right.color = BLACK;
                s.color = RED;
                if (fixLeft) {
                    this.rightRotate(s);
                    s = x.parent.right;
                } else {
                    this.leftRotate(s);
                    s = x.parent.left;
                }
            }
            s.color = x.parent.color;
            x.parent.color = BLACK;
            if (fixLeft) {
                s.right.color = BLACK;
                this.leftRotate(x.parent);
            } else {
                s.left.color = BLACK;
                this.rightRotate(x.parent);
            }
            x = root;
        }
        return x;
    }

    private void transplant(Node u, Node v) {
        if (u.parent == null)
            root = v;
        else if (u == u.parent.left)
            u.parent.left = v;
        else
            u.parent.right = v;
        v.parent = u.parent;
    }

    public boolean search(T element) {
        Node node = this.search(root, element);
        return node != null;
    }

    private Node search(Node node, T element) {
        if (node == NIL) return null;
        if (element.compareTo(node.data) < 0)
            return this.search(node.left, element);
        if (element.compareTo(node.data) > 0)
            return this.search(node.right, element);
        return node;
    }

    public T min() {
        Node node = this.findMin(root);
        return node.data;
    }

    private Node findMin(Node node) {
        while (node.left != NIL) node = node.left;
        return node;
    }

    public T max() {
        Node node = this.findMax(root);
        return node.data;
    }

    private Node findMax(Node node) {
        while (node.right != NIL) node = node.right;
        return node;
    }

    public T successor(T element) {
        Node node = this.findSuccessor(root, element);
        return node != null ? node.data : null;
    }

    private Node findSuccessor(Node node, T element) {
        if (node == NIL) return null;
        if (element.compareTo(node.data) < 0) {
            Node left = this.findSuccessor(node.left, element);
            return left != null ? left : node;
        }
        return this.findSuccessor(node.right, element);
    }

    public T predecessor(T element) {
        Node node = this.findPredecessor(root, element);
        return node != null ? node.data : null;
    }

    private Node findPredecessor(Node node, T element) {
        if (node == NIL) return null;
        if (element.compareTo(node.data) > 0) {
            Node right = this.findPredecessor(node.right, element);
            return right != null ? right : node;
        }
        return this.findPredecessor(node.left, element);
    }

    public void printInOrder() {
        this.printInOrder(root);
        System.out.println();
    }

    private void printInOrder(Node node) {
        if (node == null || node == NIL) return;
        this.printInOrder(node.left);
        System.out.printf("[%s, %s] ", node.data, node.color == RED ? "R" : "B");
        this.printInOrder(node.right);
    }

    public void display() {
        JFrame frame = new JFrame("Red-Black Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new TreePanel());
        frame.setVisible(true);
    }

    private class TreePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (root != null)
                drawTree(g, root, getWidth() / 2, 30, getWidth() / 4);
        }

        private void drawTree(Graphics g, Node node, int x, int y, int horizontalGap) {
            if (node != null) {
                // Draw left subtree
                int verticalGap = 50;
                if (node.left != NIL) {
                    g.setColor(Color.BLACK);
                    g.drawLine(x, y, x - horizontalGap, y + verticalGap);
                    drawTree(g, node.left, x - horizontalGap, y + verticalGap, horizontalGap / 2);
                }

                // Draw right subtree
                if (node.right != NIL) {
                    g.setColor(Color.BLACK);
                    g.drawLine(x, y, x + horizontalGap, y + verticalGap);
                    drawTree(g, node.right, x + horizontalGap, y + verticalGap, horizontalGap / 2);
                }

                // Draw the node
                g.setColor(node.color == RED ? Color.RED : Color.BLACK);
                int nodeSize = 30;
                g.fillOval(x - nodeSize / 2, y - nodeSize / 2, nodeSize, nodeSize);
                g.setColor(Color.WHITE);
                g.drawString(node.data.toString(), x - 3, y + 4);
            }
        }
    }
}
