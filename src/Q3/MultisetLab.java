package Q3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import DataStructures.Bag;

public class MultisetLab {
    public static void main(String[] args) {
        try {
            var file = new Scanner(new File("Langdat/words"));
            var bag = new Bag<String>();
            var rnd = new java.util.Random(42);

            while (file.hasNext()) {
                String word = file.next().toLowerCase();
                for (int i = 0; i < rnd.nextInt(1, 11); i++)
                    bag.insert(word);
            }
            file.close();

            bag.enumerate();
            // TODO: display top 10 most common words; use additional DSAs as needed

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
