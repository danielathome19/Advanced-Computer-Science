package Q1;
import java.util.Scanner;

public class LP312Class {
    static class ClLP312 {
        private double[] expenses;
        private double[] percentBudget;

        public ClLP312(double[] expenses) {
            this.expenses = expenses;
            calcPercentBudget();
        }

        private void calcPercentBudget() {
            var total = 0.0;
            for (var expense : expenses)
                total += expense;

            percentBudget = new double[expenses.length];
            for (var i = 0; i < expenses.length; i++)
                percentBudget[i] = (expenses[i] / total) * 100;
        }

        public double[] getPercentBudget() {
            return percentBudget;
        }
    }

    public static void main(String[] args) {
        var input = new Scanner(System.in);
        
        System.out.println("Enter the amount spent last month on the following items: ");
        System.out.println("Food: ");
        var food = input.nextDouble();
        System.out.println("Clothing: ");
        var clothing = input.nextDouble();
        System.out.println("Entertainment: ");
        var entertainment = input.nextDouble();
        System.out.println("Rent: ");
        var rent = input.nextDouble();

        var expenses = new double[] { food, clothing, entertainment, rent };
        var clLP312 = new ClLP312(expenses);
        var percentBudget = clLP312.getPercentBudget();

        System.out.println("Category\tBudget");
        System.out.printf("Food\t\t%.2f %%\n", percentBudget[0]);
        System.out.printf("Clothing\t%.2f %%\n", percentBudget[1]);
        System.out.printf("Entertainment\t%.2f %%\n", percentBudget[2]);
        System.out.printf("Rent\t\t%.2f %%\n", percentBudget[3]);
    }
}