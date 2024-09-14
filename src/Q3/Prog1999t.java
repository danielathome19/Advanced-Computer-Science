package Q3;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import DataStructures.Set;
import DataStructures.Dictionary;
import DataStructures.LinkedList;
import DataStructures.BinarySearchTree;
import DataStructures.CircularLinkedList;

// record Eel(String firstName, String lastName, int[][] food, LinkedList<String> fishEaten) {}
// record Bunny(String name, int soldTo, Set<String> customers, Dictionary<Integer, String> hats, BinarySearchTree<Integer> numbers) {}

class Eel implements Comparable<Eel> {
    public String firstName;
    public String lastName;
    public int[][] food;
    public LinkedList<String> fishEaten;
    
    public Eel(String firstName, String lastName, int[][] food, LinkedList<String> fishEaten) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.food = food;
        this.fishEaten = fishEaten;
    }

    public String getName() { return firstName + " " + lastName; }

    public int compareTo(Eel eel) {
        return this.fishEaten.size() - eel.fishEaten.size();
    }
}

class Bunny implements Comparable<Bunny> {
    public String name;
    public int soldTo;
    public Set<String> customers;
    public Dictionary<Integer, String> hats;
    public BinarySearchTree<Integer> numbers;
    
    public Bunny(String name, int soldTo, Set<String> customers, Dictionary<Integer, String> hats, BinarySearchTree<Integer> numbers) {
        this.name = name;
        this.soldTo = soldTo;
        this.customers = customers;
        this.hats = hats;
        this.numbers = numbers;
    }

    public int compareTo(Bunny bunny) {
        return this.hats.size() - bunny.hats.size();
    }
}

/*
Program 1999T
(All Data Structures 1)
Your data file will have a bunch of wall eels and frown bunnies.  The first number in the data file will tell you how many wall eels you have at the beginning of the data file.  The rest of the data will all be frown bunnies.  You are to create an array of correct length of wall eels and an ArrayList of frown bunnies.  

Each wall eel will have a first name followed by a last name.  The wall eel will then have 15 ints which should be put into a 3X5 grid.  Each number tells the user how much the eel will eat during a given 3 week sequence, Monday through Friday.   The data that follows will be a list of names, of all of the fish the eels have eaten.  These names should be put into a linked list.  Once you get to a -1 you know the names of the fish are done and the next eel is about to start.
Following the data for the eels is the data for all of the frown bunnies.  Each bunny’s first piece of data is his name.  The next piece of data tells you how many they have sold to.  The following pieces of data is who they have sold rabbits feet to.  You only have to keep track of who they have sold to so that data should be set up in a Set of Strings so that there are no duplicates.  Following the customers will be the number of unique magical hats this bunny owns, each hat will have a magic unique number (that no other bunny uses for a hat) followed by a quality of the hat, therefore this data should be held in a Map.   
Each bunny should have a set of numbers set up for it.  There should be 100 random numbers between 1 and 100000 set up into a binary tree so that they can be searched very easily.

Data Structure needed   Array, ArrayList, Sets, Maps, Linked List, Circle Linked List, Binary Tree




Data Location  prog1999.txt

For the Eels:
Which eel ate the most fish?
How much did it cost to feed all of the eels on the 2nd Tuesday?
If fish cost 1 on Monday, 2 on Tuesday … all the way to 5 on Friday, which eel costs the most to feed?
If fish cost 1 on Monday, 2 on Tuesday … all the way to 5 on Friday, which eel costs the most to feed on week1? Week2? Week3?
What is the name of the longest fish that each eel has eaten, and which eel ate the longest fish?
Did any of the eels eat a fish of the same name?
What day was the most expensive day to feed the eels? Monday, Tuesday, …







For the bunnies
Calculate the total number of unique individuals that the bunnies sold feet to.
Check the numbers in all of the bunny’s trees, total the largest and smallest number of each tree. 
Calculate the total number of Magic Hats for all of the bunnies.  
Calculate the bunny that has the most letters in all of its magic hats.
Which bunny has the single longest name for its magic hat.
Get rid of the bunny with the fewest number of magic hats.
The bunnies had a big sale but Jill and Pill were the only to show up and buy.  Add Jill to each of the Sets of purchasers and Pill to all of the odd bunnies as a purchaser.
The first bunny just purchased a magic hat with a number of 100 which polymorphs and the last bunny bought a magic hat with a number of 101 which shrinks.
The second bunny lost his second magic hat.
Fred is not allowed to be a customer of any bunny any more.  Delete Fred from the set of purchasers from all of the bunnies.
How many of the bunnies has Pill been a customer?
*/


/* prog1999.dat:
5
Fred Zeal 21 45 59 10 12 15 16 18 22 24 25 31 15 48 91  Mara Addison Ian Jazz Alexys Lydia Allie Alyssa -1
Jill Pill 32 61 58 72 83 12 19 21 35 62 82 14 19 74 36 Kaelyn Ian Lydia YaYa Gentry Izzy Ava Annie -1
Rick Smal 45 61 28 36 72 81 45 62 18 48 58 71 13 22 33 Lucy Allie Hannah Ava Addy Grace  Bri Jenna -1
Boby Wine 33 48 57 68 13 92 78 15 46 82 41 73 56 58 44 Izzy Lydia Sydney Lucy Annie Macy Bri Jenna Rylee -1
Patt Anne 22 74 81 92 85 62 53 74 48 59 71 52 83 64 55 Izzy Macy Grace Anna Rick Mike Bobby Julianna Mark Mike Bill -1
Amy 7 Ian Addison Ian Jazz Jazz Lydia Ian 6 1 Lucy 2 Allie 3 Hannah 4 Ava 5 Addy 6 Grace
Joan 8 Izzy Macy Grace Anna Rick Mike Grace Anna 4 7 Bobby 8 Julianna 9 Mark 10 Mike
Jean 6 Kaelyn Ian Lydia YaYa Ian Lydia 8 11 Izzy 12 Macy 13 Grace 14 Anna 15 Rick 16 Mike 17 Bobby 18 Julianna
Vince 5 Karlie Ian Izzy Ian Karlie 6 19 Hannah 20 Ava 21 Addy 22 Grace  23 Bri 24 Jenna
*/


public class Prog1999t {
    public static void main(String[] args) {
        try {
            var file = new Scanner(new File("Langdat/prog1999.txt"));
            
            int eelCount = file.nextInt();
            var eels = new Eel[eelCount];

            for (int i = 0; i < eelCount; i++) {
                String firstName = file.next();
                String lastName = file.next();
                int[][] food = new int[3][5];
                for (int j = 0; j < 3; j++)
                    for (int k = 0; k < 5; k++)
                        food[j][k] = file.nextInt();
                
                var fishEaten = new LinkedList<String>();
                String fish;
                while (!(fish = file.next()).equals("-1"))
                    fishEaten.add(fish);
                
                eels[i] = new Eel(firstName, lastName, food, fishEaten);
            }

            var bunnies = new CircularLinkedList<Bunny>();
            while (file.hasNext()) {
                String name = file.next();
                int soldTo = file.nextInt();
                
                var customers = new Set<String>();
                for (int i = 0; i < soldTo; i++)
                    customers.insert(file.next());
                
                var hats = new Dictionary<Integer, String>();
                int hatCount = file.nextInt();
                for (int i = 0; i < hatCount; i++)
                    hats.insert(file.nextInt(), file.next());
                
                var numbers = new BinarySearchTree<Integer>();
                var rand = new Random();
                for (int i = 0; i < 100; i++)
                    numbers.insert(rand.nextInt(100000) + 1);
                
                bunnies.add(new Bunny(name, soldTo, customers, hats, numbers));
            }
            file.close();

            // Which eel ate the most fish?"
            int mostEatenCnt = 0;
            String mostEaten = "";
            for (var eel : eels) {
                int eaten = 0;
                for (int[] day : eel.food)
                    for (int fish : day)
                        eaten += fish;
                if (eaten > mostEatenCnt) {
                    mostEatenCnt = eaten;
                    mostEaten = eel.getName();
                }
            }
            System.out.print("1.\t" + mostEaten + " ate the most fish, with " + mostEatenCnt + " fish.");
            System.out.println();

            // How much did it cost to feed all of the eels on the 2nd Tuesday?
            int total = 0;
            for (var eel : eels) {
                int[][] eaten = eel.food;
                int fishes = eaten[1][1];
                total += (2 * fishes);
            }
            System.out.println("2.\tIt costed $" + total + " to feed all the eels on the 2nd Tuesday");

            // If fish cost 1 on Monday, 2 on Tuesday … all the way to 5 on Friday, which eel costs the most to feed?
            var fed = new int[3];
            var names = new String[3];

            for (var eel : eels) {
                var cost = new int[3];

                int[][] eaten = eel.food;
                for (int i = 0; i < 5; i++) {
                    cost[0] += eaten[0][i];
                    cost[1] += eaten[1][i];
                    cost[2] += eaten[2][i];
                }
                for (int i = 0; i < 3; i++) {
                    if (cost[i] > fed[i]) {
                        fed[i] = cost[i];
                        names[i] = eel.getName();
                    }
                }
            }
            System.out.print("4.");
            for (int i = 0; i < 3; i++)
                System.out.println("\t" + names[i] + " costed the most to feed on week 1 with $" + fed[i]);

            // What is the name of the longest fish that each eel has eaten, and which eel ate the longest fish?
            int longestFish = 0;
            String longestFishName = "";
            String longestFishOwner = "";
            for (var eel : eels) {
                LinkedList<String> eaten = eel.fishEaten;
                for (String s : eaten) if (s.length() > longestFish){
                    longestFish = s.length();
                    longestFishName = s;
                    longestFishOwner = eel.getName();
                }
            }
            System.out.println("5.\t" + longestFishOwner + " ate the longest fish, " + longestFishName);

            // Did any of the eels eat a fish of the same name?
            boolean sameNameFish = false;
            for (int i = 0; i < 5; i++) {
                LinkedList<String> eaten = eels[i].fishEaten;
                LinkedList<String> eaten2 = eels[i].fishEaten;
                for (var s : eaten)
                    for (String string : eaten2) 
                        if (s.equals(string)) sameNameFish = true;
            }
            System.out.println("6.\tDid any of the eels eat a fish of the same name? " + (sameNameFish ? "Yes" : "No"));

            // What day was the most expensive day to feed the eels? Monday, Tuesday, …
            int[] dayCost = new int[5];
            for (int i = 0; i < eelCount; i++)
                for (int j = 0; j < 3; j++)
                    for (int k = 0; k < 5; k++)
                        dayCost[k] += (k + 1) * eels[i].food[j][k];
            int mostExpensiveDay = 0;
            for (int i = 1; i < 5; i++)
                if (dayCost[i] > dayCost[mostExpensiveDay])
                    mostExpensiveDay = i;
            String expensiveDay = (mostExpensiveDay == 0 ? "Monday" : 
                                   mostExpensiveDay == 1 ? "Tuesday" : 
                                   mostExpensiveDay == 2 ? "Wednesday" : 
                                   mostExpensiveDay == 3 ? "Thursday" : "Friday");
            System.out.println("7.\tMost expensive day to feed the eels: " + expensiveDay);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}