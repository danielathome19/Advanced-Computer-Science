package DataStructures;
import java.util.LinkedList;

public class LinkedListStack<T extends Comparable<T>> {
    private final LinkedList<T> stack;

    public LinkedListStack() { stack = new LinkedList<>(); }

    public void push(T element) { stack.add(element); }
    public T pop() { return stack.removeLast(); }
    public T peek() { return stack.getLast(); }
    public int size() { return stack.size(); }
    public boolean isEmpty() { return stack.isEmpty(); }
}