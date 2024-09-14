package DataStructures;
import java.util.NoSuchElementException;

public class CircularDeque<T extends Comparable<T>> extends CircularQueue<T> {
    public CircularDeque(int capacity) {
        super(capacity);
    }

    public void enqueueRear(T element) {
        if (size == CAPACITY) throw new IllegalStateException("Queue is full");
        rear = (rear - 1 + CAPACITY) % CAPACITY;
        queue[rear] = element;
        size++;
    }

    public T dequeueRear() {
        if (size == 0) throw new NoSuchElementException("Queue is empty");
        T element = queue[rear];
        rear = (rear + 1) % CAPACITY;
        size--;
        return element;
    }

    public T peekRear() {
        if (size == 0) throw new NoSuchElementException("Queue is empty");
        return queue[(rear - 1 + CAPACITY) % CAPACITY];
    }
}
