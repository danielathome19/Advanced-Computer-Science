package DataStructures;

public class Deque<T extends Comparable<T>> extends Queue<T> {
    public Deque() { super(); }

    public void enqueueFront(T element) {
        if (size == capacity)
            this.resize(2 * capacity);
        front = (front - 1 + capacity) % capacity;
        queue[front] = element;
        size++;
    }

    public T dequeueFront() {
        T element = queue[front];
        front = (front + 1) % capacity;
        size--;
        if (size == capacity / 4)
            this.resize(capacity / 2);
        return element;
    }

    public T peekFront() { return queue[front]; }
}