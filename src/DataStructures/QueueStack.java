package DataStructures;

/* Stack using Queue */
public class QueueStack<T extends Comparable<T>> {
    private final Queue<T> queue;

    public QueueStack() { queue = new Queue<>(); }

    public void push(T element) {
        queue.enqueue(element);
        for (int i = 0; i < queue.size() - 1; i++)
            queue.enqueue(queue.dequeue());
    }

    public T pop() { return queue.dequeue(); }
    public T peek() { return queue.peek(); }
    public int size() { return queue.size(); }
    public boolean isEmpty() { return queue.isEmpty(); }
}