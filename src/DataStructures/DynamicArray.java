package DataStructures;
import java.util.Arrays;
import java.util.Iterator;
import Algorithms.SearchAlgorithms;
import Algorithms.SortingAlgorithms;

@SuppressWarnings("unchecked")
public class DynamicArray<T extends Comparable<T>> implements Iterable<T>, Comparable<DynamicArray<T>> {
    private T[] array;
    private int size;
    private int capacity;

    public DynamicArray() {
        size = 0;
        capacity = 1;
        array = (T[]) new Comparable[capacity];
    }

    public void add(T element) {
        if (size == capacity)
            this.resize(2 * capacity);
        array[size++] = element;
    }

    public T remove(int index) {
        T element = array[index];
        for (int i = index; i < size - 1; i++)
            array[i] = array[i + 1];
        size--;
        if (size == capacity / 4)
            this.resize(capacity / 2);
        return element;
    }

    public T get(int index) { return array[index]; }
    public void set(int index, T element) { array[index] = element; }
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public int indexOf(T element) { return SearchAlgorithms.binarySearch(array, element); }
    public boolean contains(T element) { return this.indexOf(element) != -1; }
    public void print() { System.out.println(Arrays.toString(Arrays.copyOf(array, size))); }
    public void sort() { SortingAlgorithms.insertionSort(array); }  // TODO: replace with quicksort
    public String toString() { return Arrays.toString(Arrays.copyOf(array, size)); }

    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Comparable[newCapacity];
        if (size >= 0) System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
        capacity = newCapacity;
    }

    public static <T extends Comparable<T>> DynamicArray<T> asList(T... elements) {
        var list = new DynamicArray<T>();
        for (T element : elements) list.add(element);
        return list;
    }

    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;
            public boolean hasNext() { return index < size; }
            public T next() { return array[index++]; }
        };
    }

    public int compareTo(DynamicArray<T> o) {
        if (this.size() != o.size()) return this.size() - o.size();
        for (int i = 0; i < this.size(); i++)
            if (!this.get(i).equals(o.get(i)))
                return this.get(i).compareTo(o.get(i));
        return 0;
    }
}
