package Q4.Prog1999w;

public class Turkey extends Animal implements Comparable<Turkey> {
    private double value;

    public Turkey(int id, double weight, int cornCobs, int hayBales, int beans, int oats, double value) {
        super(weight, cornCobs, hayBales, beans, oats);
        this.id = id;
        this.value = value;
    }

    public double getValue() { return value; }
    public double getIncome() { return weight * value; }
    public String toString() {
        return String.format("ID: %d, %s, Value: %.2f", 
                             id, super.toString(), value);
    }

    public int compareTo(Turkey o) { return id - o.id; }
}
