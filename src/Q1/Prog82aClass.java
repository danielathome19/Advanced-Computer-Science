package Q1;
import java.util.Scanner;

public class Prog82aClass {
    static class Cl82a {
        private int limit;
        private int speed;
        private double fine = 0;
        static final int BASE_FINE = 20;
    
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
        var input = new Scanner(System.in);
        System.out.println("Enter the speed limit: ");
        int speedLimit = input.nextInt();
        System.out.println("Enter the vehicle speed: ");
        int carSpeed = input.nextInt();
        
        var ticket = new Cl82a(speedLimit, carSpeed);
        ticket.calc();
        System.out.printf("Fine: $%.2f\n", ticket.getFine());
    }
}