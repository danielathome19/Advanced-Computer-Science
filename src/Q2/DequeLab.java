package Q2;
import DataStructures.Deque;

public class DequeLab {
    public static void main(String[] args) {
        var deque = new Deque<Integer>();
        for (int i = 0; i < 10; i++)
            deque.enqueueFront(i);
        for (int i = 10; i < 20; i++)
            deque.enqueueRear(i);
        // 9 8 7 6 5 4 3 2 1 0 10 11 12 13 14 15 16 17 18 19

        System.out.println(deque.size());
        System.out.println(deque.peekFront());
        System.out.println(deque.peekRear());
        System.out.println(deque.dequeueFront());
        System.out.println(deque.dequeueRear());
        System.out.println(deque.size());

        var rand = new java.util.Random(42);
        for (int i = 0; i < 50; i++)
            deque.enqueueFront(rand.nextInt(50));
        for (int i = 0; i < 48; i++)
            deque.dequeueFront();

        System.out.println("New size: " + deque.size());
        System.out.println(deque.peekFront());
        System.out.println(deque.peekRear());
    }
}







