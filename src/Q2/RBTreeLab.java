package Q2;
import DataStructures.RedBlackTree;

public class RBTreeLab {
    public static void main(String[] args) {
        var t1 = new RedBlackTree<String>();
        t1.insert("The");
        t1.insert("quick");
        t1.insert("brown");
        t1.insert("fox");
        t1.insert("apple");
        t1.insert("cat");
        t1.insert("hat");
        t1.printInOrder();
        System.out.println();

        var t2 = new RedBlackTree<Integer>();
        t2.insert(30);
        t2.insert(40);
        t2.insert(15);
        t2.insert(25);
        t2.insert(90);
        t2.insert(80);
        t2.insert(70);
        t2.insert(85);
        t2.insert(15);
        t2.insert(72);
        t2.printInOrder();
        t2.display();
        System.out.println();
    }
}

/*
[The, B] [apple, R] [brown, B] [cat, B] [fox, R] [hat, R] [quick, B]

[15, B] [25, R] [30, B] [40, R] [70, B] [72, R] [80, R] [85, R] [90, B]
*/