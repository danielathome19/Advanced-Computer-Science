package DataStructures;

@SuppressWarnings("unchecked")
public class Heap<T extends Comparable<T>> {
    private static final int DEFAULT_CAPACITY = 16;
    T[] heap;
    private int size;
    private int capacity;
    private final boolean minHeap;

    public Heap() { this(DEFAULT_CAPACITY, false); }
    public Heap(int capacity) { this(capacity, false); }
    public Heap(boolean minHeap) { this(DEFAULT_CAPACITY, minHeap); }
    public Heap(int capacity, boolean minHeap) {
        this.capacity = capacity;
        this.size = 0;
        this.minHeap = minHeap;
        this.heap = (T[]) new Comparable[capacity];
    }

    public void insert(T element) {
        if (size == capacity)
            this.resize(2 * capacity);
        heap[size++] = element;
        this.swim(size - 1);
    }

    public T delete() {
        if (size == 0) return null;
        T element = heap[0];
        heap[0] = heap[--size];
        this.sink(0);
        if (size > 0 && size == capacity / 4)
            this.resize(capacity / 2);
        return element;
    }

    public T peek() { return size == 0 ? null : heap[0]; }
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    void swim(int k) {
        while (k > 0 && this.compare(heap[k], heap[(k - 1) / 2]) > 0) {
            this.swap(k, (k - 1) / 2);
            k = (k - 1) / 2;
        }
    }

    void sink(int k) {
        while (2 * k + 1 < size) {
            int j = 2 * k + 1;
            if (j + 1 < size && this.compare(heap[j], heap[j + 1]) < 0)
                j++;
            if (this.compare(heap[k], heap[j]) >= 0)
                break;
            this.swap(k, j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void resize(int newCapacity) {
        T[] newHeap = (T[]) new Comparable[newCapacity];
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
        capacity = newCapacity;
    }

    int compare(T a, T b) { return minHeap ? b.compareTo(a) : a.compareTo(b); }
}