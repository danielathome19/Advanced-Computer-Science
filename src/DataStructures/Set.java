package DataStructures;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class Set<T extends Comparable<T>> implements Iterable<T> {
    protected static final int DEFAULT_CAPACITY = 16;
    protected static final double DEFAULT_LOAD_FACTOR = 0.75;

    protected static class Entry<T> {
        public T key;
        Entry<T> next;

        Entry(T key) {
            this.key = key;
            this.next = null;
        }

        public int hashCode() { return key.hashCode(); }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            Entry<?> entry = (Entry<?>) o;
            return key.equals(entry.key);
        }
    }

    protected Entry<T>[] table;
    protected int size;

    public Set(int capacity) {
        table = new Entry[capacity];
        size = 0;
    }

    public Set() {
        this(DEFAULT_CAPACITY);
    }

    protected int getIndex(T key) { return (key.hashCode() & 0x7fffffff) % table.length; }
    // Use bitmask to get a positive index (kinda like using math.abs)

    protected void resize(int newCapacity) {
        Entry<T>[] newTable = new Entry[newCapacity];
        for (Entry<T> entry : table) {
            Entry<T> current = entry;
            while (current != null) {
                Entry<T> next = current.next;
                int index = (current.key.hashCode() & 0x7fffffff) % newCapacity;
                // int index = Math.abs(current.key.hashCode() % newCapacity;
                current.next = newTable[index];
                newTable[index] = current;
                current = next;
            }
        }
        table = newTable;
    }

    public void insert(T key) {
        if (contains(key)) return;
        if (size >= table.length * DEFAULT_LOAD_FACTOR) resize(2 * table.length);
        int index = getIndex(key);
        Entry<T> entry = new Entry<>(key);
        if (table[index] == null) {
            table[index] = entry;
        } else {
            Entry<T> current = table[index];
            while (current.next != null) current = current.next;
            current.next = entry;
        }
        size++;
    }

    public boolean contains(T key) {
        Entry<T> current = table[getIndex(key)];
        while (current != null) {
            if (current.key.equals(key)) return true;
            current = current.next;
        }
        return false;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

}
