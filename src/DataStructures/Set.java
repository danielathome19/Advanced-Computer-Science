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
    // Use bitmask to get a positive index (kinda like using Math.abs)

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

    public void remove(T key) {
        int index = getIndex(key);
        Entry<T> current = table[index];
        Entry<T> previous = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null)
                    table[index] = current.next;
                else
                    previous.next = current.next;
                size--;
                return;
            }
            previous = current;
            current = current.next;
        }
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

    // TODO: (optionally) `public Set<T> union(Set<T> other)`, `... intersection(...)`

    public void enumerate() {
        System.out.print("{ ");
        for (Entry<T> entry : table) {
            Entry<T> current = entry;
            while (current != null) {
                System.out.print(current.key + " ");
                current = current.next;
            }
        }
        System.out.println("}");
    }

    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;
            private Entry<T> current = null;

            public boolean hasNext() {
                if (current != null) return true;
                while (index < table.length) {
                    if (table[index] != null) {
                        current = table[index++];
                        return true;
                    }
                    index++;
                }
                return false;
            }

            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T key = current.key;
                current = current.next;
                return key;
            }
        };
    }
}
