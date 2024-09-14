package Q4.Prog1999w;
import java.util.Random;
import java.util.ArrayList;
import DataStructures.Set;
import DataStructures.Queue;
import DataStructures.Stack;
import DataStructures.Dictionary;
import DataStructures.CircularLinkedList;

class CornCob implements Comparable<CornCob> { public int compareTo(CornCob o) { return 0; } }
class HayBale implements Comparable<HayBale> { public int compareTo(HayBale o) { return 0; } }

public class Farm implements Comparable<Farm>, IFarm {
    // private int cornCobs, hayBales, beans, oats;
    private final static Random rand = new Random(0xDEADBEEF);
    private double milkPrice;
    private int beans, oats;
    private int riders[][] = new int[3][7];
    private Stack<CornCob> cornCobs;
    private Queue<HayBale> hayBales;
    Dictionary<Integer, Cow> cows;
    Set<Turkey> turkeys;
    CircularLinkedList<Horse> horses;
    Pig[] pens;

    public Farm() {
        // cornCobs  = rand.nextInt(5000) + 10000;
        // hayBales  = rand.nextInt(1000) + 2000;
        cornCobs  = new Stack<>();
        hayBales  = new Queue<>();
        for (int i = 0; i < rand.nextInt(5000) + 10000; i++) 
            cornCobs.push(new CornCob());
        for (int i = 0; i < rand.nextInt(1000) + 2000; i++) 
            hayBales.enqueue(new HayBale());
        beans     = rand.nextInt(250) + 750;
        oats      = rand.nextInt(750) + 1750;
        cows      = new Dictionary<>();
        turkeys   = new Set<>();
        horses    = new CircularLinkedList<>();
        pens      = new Pig[20];
        milkPrice = rand.nextDouble() * 4 + 20;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 7; j++)
                riders[i][j] = (j < 5)  ? rand.nextInt(5) + 1 : 
                               (j == 5) ? rand.nextInt(5) + 5 : 
                                          rand.nextInt(5) + 3;
    }

    public void addCow(Cow cow) { cows.insert(cow.getID(), cow); }
    public void addTurkey(Turkey turkey) { turkeys.insert(turkey); }
    public void addHorse(Horse horse) { horses.add(horse); }
    public void addPig(Pig pig) {
        for (int i = 0; i < pens.length; i++) {
            if (pens[i] == null) {
                pens[i] = pig;
                break;
            }
        }
    }

    public void stockCorn() {
        for (int i = 0; i < rand.nextInt(250) + 1000; i++) 
            addCornCob();
    }

    public void stockHay() {
        for (int i = 0; i < rand.nextInt(25) + 75; i++) 
            addHayBale();
    }

    public void addCornCob()     { cornCobs.push(new CornCob()); }
    public void addHayBale()     { hayBales.enqueue(new HayBale()); }
    public void removeCornCob()  { cornCobs.pop(); }
    public void removeHayBale()  { hayBales.dequeue(); }
    
    public int    getCornCobs()  { return cornCobs.size(); }
    public int    getHayBales()  { return hayBales.size(); }
    public int    getBeans()     { return beans; }
    public int    getOats()      { return oats; }
    public double getMilkPrice() { return milkPrice; }
    
    public int getNumHorseRiders() {
        int total = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 7; j++)
                total += riders[i][j];
        return total;
    }

    public double getCost() {
        double cost = 0;
        for (var animal : getAllAnimals()) 
            cost += animal.getCost();
        return cost;
    }

    public double getIncome() {
        double income = 0;
        for (var animal : getAllAnimals()) 
            income += animal.getIncome();
        return income;
    }

    public ArrayList<Animal> getAllAnimals() {
        var animals = new ArrayList<Animal>();
        for (Cow cow : cows.valueBag()) animals.add(cow);
        for (Turkey turkey : turkeys) animals.add(turkey);
        for (Horse horse : horses) animals.add(horse);
        for (Pig pig : pens) animals.add(pig);
        return animals;
    }

    public double getProfit() { return getIncome() - getCost(); }

    public int compareTo(Farm o) { return (int) (getProfit() - o.getProfit()); }
}
