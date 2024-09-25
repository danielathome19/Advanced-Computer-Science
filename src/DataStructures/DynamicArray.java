package DataStructures;
import java.util.Arrays;
import java.util.Iterator;
import Algorithms.SearchAlgorithms;
import Algorithms.SortingAlgorithms;

@SuppressWarnings({"unchecked", "rawtypes", "unused"})
public class DynamicArray<T extends Comparable<T>> implements Iterable<T> {
    private T[] array;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 16;

    public DynamicArray() {
        size = 0;
        capacity = DEFAULT_CAPACITY;
        array = (T[]) new Comparable[capacity];
    }

    private void resize(int newCapacity) {
        var newArray = (T[]) new Comparable[newCapacity];
        if (size >= 0) System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
        capacity = newCapacity;
    }

    public void add(T element) {
        if (size == capacity) resize(2 * capacity);
        array[size++] = element;
    }

    public T remove(int index) {
        T element = array[index];
        for (int i = index; i < size-1; i++)
            array[i] = array[i+1];
        size--;
        if (size == capacity/4) resize(capacity/2);
        return element;
    }

    public T get(int index) { return array[index]; }
    public void set(int index, T element) { array[index] = element; }
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public int indexOf(T element) { return SearchAlgorithms.linearSearch(array, element); }
    public boolean contains(T element) { return indexOf(element) != -1; }
    public void sort() { SortingAlgorithms.insertionSort(array, size); }  // TODO: replace w/ quicksort
    public String toString() { return Arrays.toString(Arrays.copyOf(array, size)); }
    public Comparable[] toArray() { return Arrays.copyOf(array, size); }

    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;
            public boolean hasNext() { return index < size; }
            public T next() { return array[index++]; }
        };
    }
}
