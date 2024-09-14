package Q4.Prog1999w;

public class Pig extends Animal implements Comparable<Pig>{
    private double value;

    public Pig(double weight, int cornCobs, int hayBales, int beans, int oats, double value) {
        super(weight, cornCobs, hayBales, beans, oats);
        this.value = value;
    }

    public double getValue() { return value; }
    public double getIncome() { return weight * value; }
    public String toString() {
        return String.format("%s, Value: %.2f", super.toString(), value);
    }

    public int compareTo(Pig o) { return (int) (value - o.value); }
}