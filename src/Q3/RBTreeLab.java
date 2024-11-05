package Q3;
import DataStructures.RedBlackTree;

public class RBTreeLab {
    public static void main(String[] args) {
        RedBlackTree<String> testOne = new RedBlackTree<String>();
        testOne.insert("The");
        testOne.insert("quick");
        testOne.insert("brown");
        testOne.insert("fox");
        testOne.insert("apple");
        testOne.insert("cat");
        testOne.insert("hat");
        testOne.printInOrder();
        System.out.println();

        RedBlackTree<Integer> testTwo = new RedBlackTree<Integer>();
        testTwo.insert(30);
        testTwo.insert(40);
        testTwo.insert(15);
        testTwo.insert(25);
        testTwo.insert(90);
        testTwo.insert(80);
        testTwo.insert(70);
        testTwo.insert(85);
        testTwo.insert(15);
        testTwo.insert(72);
        testTwo.printInOrder();
        testTwo.display();
        System.out.println();

        RedBlackTree<String> testThree = new RedBlackTree<String>();
        testThree.insert("Now");
        testThree.insert("is");
        testThree.insert("time");
        testThree.insert("for");
        testThree.insert("all");
        testThree.insert("good");
        testThree.insert("men");
        testThree.insert("to");
        testThree.insert("come");
        testThree.insert("to");
        testThree.insert("the");
        testThree.insert("aid");
        testThree.insert("of");
        testThree.insert("the");
        testThree.insert("party");
        testThree.printInOrder();
        System.out.println();
    }
}
