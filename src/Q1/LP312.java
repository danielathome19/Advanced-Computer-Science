package Q1;
import Utils.Console;

public class LP312 {
    static class ClLP312 {
        private double[] expenses;
        private double[] percentBudget;

        public ClLP312(double[] expenses) {
            this.expenses = expenses;
            calc();
        }

        private void calc() {
            double total = 0;
            for (double expense : expenses)
                total += expense;
            percentBudget = new double[expenses.length];
            for (int i = 0; i < expenses.length; i++)
                percentBudget[i] = (expenses[i] / total) * 100;
        }

        public double[] getPercentBudget() { return percentBudget; }
    }

    public static void main(String[] args) {
        System.out.println("Enter the amount spent last month on the following items: ");
        double food = Console.input("Food: ");
        double clothing = Console.input("Clothing: ");
        double entertainment = Console.input("Entertainment: ");
        double rent = Console.input("Rent: ");

        var expenses = new double[] { food, clothing, entertainment, rent };
        var help = new ClLP312(expenses);
        var percents = help.getPercentBudget();

        System.out.println("Category\t\tBudget");
        System.out.printf("Food\t\t\t%.2f %%\n", percents[0]);
        System.out.printf("Clothing\t\t%.2f %%\n", percents[1]);
        System.out.printf("Entertainment\t%.2f %%\n", percents[2]);
        System.out.printf("Rent\t\t\t%.2f %%\n", percents[3]);
    }
}
/*
Enter the amount spent last month on the following items:
Food: 350.0
Clothing: 300.0
Entertainment: 200.0
Rent: 1250.0
Category	    Budget
Food		    16.67 %
Clothing	    14.29 %
Entertainment	9.52 %
Rent		    59.52 %
*/