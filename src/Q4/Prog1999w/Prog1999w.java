package Q4.Prog1999w;
import java.util.Random;
import DataStructures.LinkedList;

/*
Program 1999W
(All Data Structures 2)
This is Giga Farms. You are to randomly create 5 Giga Farms. 
The farms should be saved in a linked list. 
Each farm starts with a number of corn cobs, hay bales, pounds of feed beans and pounds of oats to feed the animals. 
Each farm starts with randomly 10000-15000 corn cobs, 2000-3000 bales of hay, 
    750-1000 pounds of beans and 1750 to 2500 pounds of oats. 
Each farm is to have a Map of between 12 and 15 cows. 
Each farm is to have a Set of between 10 and 20 Turkeys. 
Each farm is to have a circle linked list of between 8 and 10 horses. 
Each farm will have 20 pens (an array) to hold between 12 and 20 pigs. 
Each farm sets its own milk price and the price should be between 20 and 24 cents per pound.

The hay that a farm uses should be used in opposite order that it is purchased (Stack). 
As it is loaded into a barn, the last ones in will be on top and therefore they will be the first ones used. 
Each load of hay brought onto the farm will be between 75 and 100 bales.

Corn is used in the order that it is put into the bin. 
It is loaded into the top of the bin and pulled out of the bottom of the bin, first in, first out (Queue). 
Each wagon of corn coming onto the farm will have between 1000 and 1250 corn cobs in it.


The cow should have a unique 4-digit ID number, which is used to store the cow in the map. 
The cow should then have a weight which should be between 1000 and 1500 pounds, 
    followed by the number of corn cobs they eat, 5-8 a day, followed by the number of hay bales, 1 to 3 a day, 
    followed by the pounds of beans, 2 to 5 pounds a day and the pounds of oats, 1 to 4 pounds a day. 
The final number saved for each cow is the amount of milk produced per day, 20 to 100 pounds.

The Turkeys are in a big pen (Set) and should have a 3-digit ID, 
    their weight which should be between 25 and 35 pounds, the number of corn cobs they eat, 
    between 1 and 3 and then pounds of oats they eat between 2 and 4. 
A Turkeys value is set at between 75c and 99c per pound.

The horses should always be ridden in order, as we get to the last one we come 
    back around to the front horse (Circle linked list). 
Each horse should have a random 4 letter name with the 3rd letter always being 
    a vowel and the other 3 letters being consonants. 
The horse should have a rider cost, a double between 7 and 10.50, 
    followed by the number of corn cobs they eat, 2-4 a day, followed by the number of hay bales, 1 to 3 a day, 
    followed by the pounds of beans, 2 to 5 pounds a day and the pounds of oats, 1 to 4 pounds a day. 

The number of riders for each farm is to have a 3 week (21 day) preset number. 
The first day of the week is Monday. 
You are to set up a 3 x 7 2D array which holds the number of riders for each farm. 
Monday through Friday there will be between 1 and 5 riders each day. 
Saturday there should be between 5 and 10 riders and on Sundays there will be between 3 and 7 riders.

Each animal must have a getcost, which reports all of the feedcost of the animal, 
                      a getincome, which reports how much money this animal produces and 
                      a getprofit, which is a getincome-getcost. 
The animals should also have a get method for each piece of private data.

The pigs like to eat! Each pig weighs between 250 and 300 pounds. 
Each pig eats between 5 and 10 corn per day and 10 to 20 pounds of oats per day and 
    25 to 50 pounds of beans per day. 
A pig’s value is determined by their weight * a random number of 2.25 to 2.75 per pound.


Data Structure needed
Array, ArrayList, Sets, Maps, Linked List, Circle Linked List, Binary Tree, Queue, Stack, String 2D array.

For the farms:
1.  For each farm, which set of animals is the most profitable?
2.  How many pounds of milk did all of the cows produce?
3.  How much do all of the turkeys weigh?
4.  For each farm, which type of feed is the most expensive that is used on a daily basis?
5.  How much does it cost to feed all of the animals for one day on each farm and then all together?
6.  How much income does each farm produce?
7.  How much profit does each farm produce?
8.  The giga farms want to get more profitable. 
        The farms are to sell off the seven worst profitable cows. 
        Each farm is to sell off its worst cow. 
        That means a total of seven cows may be sold off or up to 11 cows are sold off. 
        If the seven worst cows are all on the same farm, those seven are sold as well as 
        the worst cow on each of the other farms. 
        If the worst seven incorporate the worst cow from each farm, then only 7 cows get sold.
9.  Find the least profitable pen of pigs and sell off all but the two most profitable pigs.
10. Find the least profitable rafter (flock) of turkeys and sell off 
    the worst ½ of the worst rafter of turkeys.
11. What is the total weight of all of the animals on all of the farms?
12. We got to get smaller, sell off the one heaviest horse from all the farms, 
        the 2 heaviest cows from each farm (10), the 3 heaviest turkeys from all 
        the farms and 4 heaviest pigs from each farm.
13. What is the total weight of all of the animals on all of the farms.
14. Go through each farm and report the number of days that each farm has enough feed 
        to feed their animals the minimum number of days per set of food and which food item 
        will run out (IE farm A has enough hay for 8 days, corn for 2 days, beans for 3 days 
        and oats for 7 days, they should report that they will run out of corn in 2 days).
15. What is the value of all of the food on each farm (and the total value on all the farms).
16. Each farm should take 1 day of its lowest quantity of food from 
        the farm that has the most of that quantity.
17. What is the value of all of the food on each farm (and the total value on all the farms).
18. What is the profit of all the farms. How many animals are on the farms.
19. The giga farms made bank! Buy another farm.
20. What is the profit of all the farms. How many animals are on the farms.
21. Which farm is the most profitable, sell the farm that is the least profitable.
*/
public class Prog1999w {
    private static final Random rand = new Random(0xC0FFEE);

    public static void main(String[] args) {
        var farms = new LinkedList<Farm>();
        for (int i = 0; i < 5; i++) 
            farms.add(initRandomFarm());
        int farmCtr = 1;
        
        // 1. For each farm, which set of animals is the most profitable?
        for (Farm farm : farms) {
            double cowProfit = 0, turkeyProfit = 0, horseProfit = 0, pigProfit = 0;
            for (var animal : farm.getAllAnimals()) {
                if      (animal instanceof Cow)    cowProfit += animal.getProfit();
                else if (animal instanceof Turkey) turkeyProfit += animal.getProfit();
                else if (animal instanceof Horse)  horseProfit += animal.getProfit();
                else if (animal instanceof Pig)    pigProfit += animal.getProfit();
            }
            double[] profits = { cowProfit, turkeyProfit, horseProfit, pigProfit };
            double maxProfit = profits[0];
            int maxProfitIndex = 0;
            for (int j = 1; j < profits.length; j++) {
                if (profits[j] > maxProfit) {
                    maxProfit = profits[j];
                    maxProfitIndex = j;
                }
            }
            // System.out.println("Profits: " + cowProfit + " " + turkeyProfit + " " + horseProfit + " " + pigProfit);
            String profitAnimal = new String[]{"Cows", "Turkeys", "Horses", "Pigs"}[maxProfitIndex];
            System.out.println(profitAnimal + " are the most profitable on farm " + farmCtr);
            farmCtr++;
        }
        farmCtr = 1;

        // 2. How many pounds of milk did all of the cows produce?
        int totalMilk = 0;
        for (Farm farm : farms) 
            for (Cow cow : farm.cows.valueBag()) 
                totalMilk += cow.getMilk();
        System.out.println("Total Milk: " + totalMilk);

        // 3. How much do all of the turkeys weigh?
        int totalWeight = 0;
        for (Farm farm : farms) 
            for (Turkey turkey : farm.turkeys) 
                totalWeight += turkey.getWeight();
        System.out.println("Total Turkey Weight: " + totalWeight);

        // 4. For each farm, which type of feed is the most expensive that is used on a daily basis?
        for (Farm farm : farms) {
            double cornCost = 0, hayCost = 0, beansCost = 0, oatsCost = 0;
            for (var animal : farm.getAllAnimals()) {
                cornCost += animal.getCornCobs();
                hayCost += animal.getHayBales();
                beansCost += animal.getBeans();
                oatsCost += animal.getOats();
            }
            double[] costs = { cornCost, hayCost, beansCost, oatsCost };
            double maxCost = costs[0];
            int maxCostIndex = 0;
            for (int j = 1; j < costs.length; j++) {
                if (costs[j] > maxCost) {
                    maxCost = costs[j];
                    maxCostIndex = j;
                }
            }
            String expensiveFeed = new String[]{"Corn", "Hay", "Beans", "Oats"}[maxCostIndex];
            String verb = maxCostIndex > 1 ? "are" : "is";
            System.out.printf("%s %s the most expensive feed on farm %d\n", expensiveFeed, verb, farmCtr);
            farmCtr++;
        }
        farmCtr = 1;

        // 5. How much does it cost to feed all of the animals for one day on each farm and then all together?
        double totalCost = 0;
        for (var farm : farms) {
            double cost = farm.getCost();
            System.out.printf("Farm %d Cost: $%.2f\n", farmCtr, cost);
            totalCost += cost;
            farmCtr++;
        }
        System.out.println("Total Cost: $" + totalCost);
        farmCtr = 1;
    }

    public static int rndInt(int min, int max)          { return rand.nextInt(max - min + 1) + min; }
    public static double rndDbl(double min, double max) { return rand.nextDouble() * (max - min) + min; }
    public static Farm initRandomFarm() {
        var farm = new Farm();
        for (int i = 0; i < rndInt(12, 15); i++) {
            var cow = new Cow(rndInt(1000, 9000), 
                      rndDbl(1000, 1500), 
                      rndInt(5, 8), 
                      rndInt(1, 3), 
                      rndInt(2, 5), 
                      rndInt(1, 4), 
                      rndInt(20, 100),
                      farm.getMilkPrice());
            farm.addCow(cow);
        }
        for (int i = 0; i < rndInt(10, 20); i++) {
            var turkey = new Turkey(rndInt(100, 999), 
                        rndDbl(25, 35), 
                        rndInt(1, 3), 
                        rndInt(2, 4), 
                        rndInt(2, 5), 
                        rndInt(1, 4), 
                        rndDbl(0.75, 0.99));
            farm.addTurkey(turkey);
        }
        for (int i = 0; i < rndInt(8, 10); i++) {
            var horse = new Horse(String.format("%c%c%c%c", 
                                                rndInt(0, 25) + 'A', 
                                                rndInt(0, 25) + 'A', 
                                                rndInt(0, 4) + 'A', 
                                                rndInt(0, 25) + 'A'), 
                      rndDbl(7, 10.5), 
                      rndDbl(1000, 1500), 
                      rndInt(2, 4), 
                      rndInt(1, 3), 
                      rndInt(2, 5), 
                      rndInt(1, 4),
                      farm.getNumHorseRiders());
            farm.addHorse(horse);
        }
        for (int i = 0; i < 20; i++) {
            var pig = new Pig(rndDbl(250, 300), 
                      rndInt(5, 10), 
                      rndInt(10, 20), 
                      rndInt(25, 50), 
                      rndInt(25, 50), 
                      rndDbl(2.25, 2.75));
            farm.addPig(pig);
        }
        return farm;
    }
}
