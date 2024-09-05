package Utils;
import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    @SuppressWarnings("unchecked")
    public static <T> T input(String prompt) {
        System.out.print(prompt);
        if (scanner.hasNextInt())
            return (T) Integer.valueOf(scanner.nextInt());
        else if (scanner.hasNextDouble())
            return (T) Double.valueOf(scanner.nextDouble());
        else
            return (T) scanner.next();
    }

    public static void print(Object... args) {
        for (Object arg : args)
            System.out.print(arg);
    }

    public static void println(Object... args) {
        for (Object arg : args)
            System.out.print(arg);
        System.out.println();
    }

    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    public static void close() {
        scanner.close();
    }

    public static void main(String[] args) {
        int i = Console.input("Enter an integer: ");
        double d = Console.input("Enter a double: ");
        String s = Console.input("Enter a string: ");
        Console.println("i = ", i, ", d = ", d, ", s = ", s);
    }
}