package Q3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import DataStructures.Set;
import DataStructures.Dictionary;
import DataStructures.LinkedList;
import DataStructures.BinarySearchTree;
import DataStructures.CircularLinkedList;

record Eel(
        String firstName,
        String lastName,
        int[][] food,
        LinkedList<String> fishEaten
) implements Comparable<Eel> {
    public String getName() { return firstName + " " + lastName; }

    public int compareTo(Eel eel) {
        return this.fishEaten.size() - eel.fishEaten.size();
    }
}

record Bunny(
        String name,
        int soldTo,
        Set<String> customers,
        Dictionary<Integer, String> hats,
        BinarySearchTree<Integer> numbers
) implements Comparable<Bunny> {
    public int compareTo(Bunny bunny) {
        return this.hats.size() - bunny.hats.size();
    }
}

public class Prog1999t {
    public static void main(String[] args) {
        try {
            var file = new Scanner(new File("Langdat/prog1999t.txt"));

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
