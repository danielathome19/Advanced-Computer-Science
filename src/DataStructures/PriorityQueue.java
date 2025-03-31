package DataStructures;

public class PriorityQueue<T extends Comparable<T>> {
    private final Heap<T> heap;

    public PriorityQueue() { heap = new Heap<>(); }
    public PriorityQueue(boolean minHeap) { heap = new Heap<>(minHeap); }

    public void enqueue(T element) { heap.insert(element);  }
    public T dequeue()             { return heap.delete();  }
    public T peek()                { return heap.peek();    }
    public int size()              { return heap.size();    }
    public boolean isEmpty()       { return heap.isEmpty(); }

    public boolean contains(T element) {
        return getIndex(element) != -1;
    }

    private int getIndex(T element) {
        int index = -1;
        for (int i = 0; i < heap.size(); i++) {
            if (heap.heap[i].equals(element)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void remove(T element) {
        int index = getIndex(element);
        if (index != -1) {
            T lastElement = heap.delete();
            if (index < heap.size()) {
                heap.heap[index] = lastElement;
                heap.sink(index);
                heap.swim(index);
            }
        }
    }

    public void changePriority(T element, T newPriority) {
        int index = getIndex(element);
        if (index != -1) {
            heap.heap[index] = newPriority;
            if (heap.compare(newPriority, element) > 0)
                heap.sink(index);
            else
                heap.swim(index);
        }
    }
}
