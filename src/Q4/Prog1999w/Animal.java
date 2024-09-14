package Q4.Prog1999w;

public abstract class Animal implements IFarm {
    protected double weight;
    protected int id, cornCobs, hayBales, beans, oats;
    
    public Animal(double weight, int cornCobs, int hayBales, int beans, int oats) {
        this.weight = weight;
        this.cornCobs = cornCobs;
        this.hayBales = hayBales;
        this.beans = beans;
        this.oats = oats;
    }

    public int getID() { return id; }
    public double getWeight() { return weight; }
    public int getCornCobs() { return cornCobs; }
    public int getHayBales() { return hayBales; }
    public int getBeans() { return beans; }
    public int getOats() { return oats; }

    public double getCost() { return cornCobs + hayBales + beans + oats; }
    public abstract double getIncome();
    public double getProfit() { return getIncome() - getCost(); }

    public String toString() {
        return String.format("Weight: %.2f, Corn Cobs: %d, Hay Bales: %d, Beans: %d, Oats: %d", 
                             weight, cornCobs, hayBales, beans, oats);
    }
}
