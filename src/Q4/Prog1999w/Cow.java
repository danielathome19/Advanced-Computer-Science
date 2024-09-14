package Q4.Prog1999w;

public class Cow extends Animal implements Comparable<Cow> {
    private int milk;
    private double milkPrice;

    public Cow(int id, double weight, int cornCobs, int hayBales, int beans, 
               int oats, int milk, double milkPrice) {
        super(weight, cornCobs, hayBales, beans, oats);
        this.id = id;
        this.milk = milk;
        this.milkPrice = milkPrice;
    }

    public int getMilk() { return milk; }
    public double getIncome() { return milk * milkPrice; }
    public String toString() {
        return String.format("ID: %d, %s, Milk: %d", 
                             id, super.toString(), milk);
    }

    public int compareTo(Cow o) { return id - o.id; }
}