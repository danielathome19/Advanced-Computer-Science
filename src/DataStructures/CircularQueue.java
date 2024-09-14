package DataStructures;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class CircularQueue<T extends Comparable<T>> {
    protected final T[] queue;
    protected int front;
    protected int rear;
    protected int size;
    protected final int CAPACITY;

    public CircularQueue(int capacity) {
        queue = (T[]) new Comparable[capacity];
        front = 0;
        rear = 0;
        size = 0;
        this.CAPACITY = capacity;
    }

    public void enqueue(T element) {
        if (size == CAPACITY) throw new IllegalStateException("Queue is full");
        queue[rear] = element;
        rear = (rear + 1) % CAPACITY;
        size++;
    }

    public T dequeue() {
        if (size == 0) throw new NoSuchElementException("Queue is empty");
        T element = queue[front];
        front = (front + 1) % CAPACITY;
        size--;
        return element;
    }

    public T peek() {
        if (size == 0) throw new NoSuchElementException("Queue is empty");
        return queue[front];
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
}
