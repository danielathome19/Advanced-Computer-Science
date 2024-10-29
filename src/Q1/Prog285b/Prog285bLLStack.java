package Q1.Prog285b;

import DataStructures.LinkedListStack;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Prog285bLLStack {
    public static void main(String[] args) {
        try {
            var file = new Scanner(new File("Langdat/prog285b.dat"));
            var stack = new LinkedListStack<Cl285b>();

            System.out.println("Id\tCode\tSales\tCommission");
            while (file.hasNext()) {
                int id   = file.nextInt();
                int c    = file.nextInt();
                double s = file.nextDouble();

                var fred = new Cl285b(id, c, s);
                stack.push(fred);
            }
            file.close();

            while (!stack.isEmpty()) {
                var fred = stack.pop();
                System.out.println(fred);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
