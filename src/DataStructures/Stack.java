package DataStructures;

@SuppressWarnings("unchecked")
public class Stack<T extends Comparable<T>> {
    private T[] stack;
    private int size;
    private int capacity;

    public Stack() {
        size = 0;
        capacity = 1;
        stack = (T[]) new Comparable[capacity];
    }

    public void push(T element) {
        if (size == capacity)
            this.resize(2 * capacity);
        stack[size++] = element;
    }

    public T pop() {
        T element = stack[--size];
        if (size == capacity / 4)
            this.resize(capacity / 2);
        return element;
    }

    public T peek() {
        return stack[size - 1];
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    private void resize(int newCapacity) {
        if (newCapacity < 1) newCapacity = 1;
        T[] newStack = (T[]) new Comparable[newCapacity];
        if (size >= 0) System.arraycopy(stack, 0, newStack, 0, size);
        stack = newStack;
        capacity = newCapacity;
    }
}