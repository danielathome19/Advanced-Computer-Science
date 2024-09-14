package Q4;
import java.io.*;
import java.util.*;
import DataStructures.PriorityQueue;

class Job implements Comparable<Job> {
    public int job;
    public char priority;

    public Job(int job, char priority) {
        this.job = job;
        this.priority = priority;
    }

    public String toString() { return priority + "" + job; }
    public int compareTo(Job other) { 
        if (this.priority != other.priority)
            // Compare priorities ('A' has highest priority, 'C' lowest)
            return Character.compare(this.priority, other.priority);
        else
            // If priorities are the same, compare by job number
            return Integer.compare(this.job, other.job);
    }
}

public class Prog1060zPrioQ {
    public static void main(String[] args) {
        try {
            var file = new Scanner(new File("Langdat/prg1060z.dat"));
            var pq = new PriorityQueue<Job>(true);  // minHeap=true
            while (file.hasNext()) {
                String info = file.next();
                char command = info.charAt(0);
                if (command == 'W') {
                    // TODO: have students do
                    System.out.println("Processing Queue Report...");
                    var tempQueue = new PriorityQueue<Job>(true);  // Temporary queue to hold jobs
                    while (!pq.isEmpty()) {
                        Job job = pq.dequeue();
                        System.out.println(job);
                        tempQueue.enqueue(job);  // Enqueue back into tempQueue
                    }
                    // Restore the jobs back to the original priority queue
                    while (!tempQueue.isEmpty())
                        pq.enqueue(tempQueue.dequeue());
                    System.out.println("End of Report\n");
                } else if (command == 'P') {
                    // TODO: have students do
                    if (pq.isEmpty()) {
                        System.out.println("Pop denied.  Cause:  Empty Queue\n");
                    } else {
                        System.out.println("Pop accepted. Cause:  Available node.");
                        System.out.println("Currently processing job:  " + pq.dequeue() + "\n");
                    }
                } else {  // Add
                    char priority = info.charAt(1);
                    int job = Integer.parseInt(info.substring(2));
                    pq.enqueue(new Job(job, priority));
                }
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}