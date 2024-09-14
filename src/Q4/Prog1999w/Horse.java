package Q4.Prog1999w;

public class Horse extends Animal implements Comparable<Horse> {
    private String name;
    private double riderCost;
    private int numRiders;

    public Horse(String name, double riderCost, double weight, int cornCobs, 
                 int hayBales, int beans, int oats, int numRiders) {
        super(weight, cornCobs, hayBales, beans, oats);
        this.name = name;
        this.riderCost = riderCost;
        this.numRiders = numRiders;
    }

    public String getName() { return name; }
    public double getIncome() { return riderCost * numRiders; }
    public String toString() {
        return String.format("Name: %s, %s, Rider Cost: %.2f", 
                             name, super.toString(), riderCost);
    }

    public int compareTo(Horse o) { return name.compareTo(o.name); }
}
