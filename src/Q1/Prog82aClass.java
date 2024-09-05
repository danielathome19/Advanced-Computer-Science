package Q1;
import Utils.Console;

public class Prog82aClass {
    static class Cl82a {
        private int limit;
        private int speed;
        private double fine = 0;
        private static final int BASE_FINE = 20;

        public Cl82a(int limit, int speed) {
            this.limit = limit;
            this.speed = speed;
        }

        public void calc() {
            if (speed > limit)
                fine = BASE_FINE + (speed - limit) * 5;
        }

        public double getFine() { return fine; }
    }

    public static void main(String[] args) {
        int speedLimit = Console.input("Enter the speed limit: ");
        int carSpeed = Console.input("Enter the vehicle speed: ");

        var ticket = new Cl82a(speedLimit, carSpeed);
        ticket.calc();
        System.out.printf("Fine: $%.2f\n", ticket.getFine());
    }
}
/*
Enter the speed limit: 30
Enter the vehicle speed: 42
Fine: $80.00
*/
