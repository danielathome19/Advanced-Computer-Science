package Q1;
import java.util.Scanner;

public class Prog52a {
    public static void main(String[] args) {
        var input = new Scanner(System.in);

        System.out.print("Enter length: ");
        int len = input.nextInt();

        System.out.print("Enter width: ");
        int wid = input.nextInt();

        int area = len * wid;
        int perim = 2 * len + 2 * wid;

        System.out.println("Area = " + area);
        System.out.println("Perimeter = " + perim);
    }
}
/*
Enter length: 5
Enter width: 10
Area = 50
Perimeter = 30
*/
