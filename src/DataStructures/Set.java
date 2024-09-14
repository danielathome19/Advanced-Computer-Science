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

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;
            Entry<?> entry = (Entry<?>) obj;
            return this.key.equals(entry.key);
        }

        public int hashCode() { return key.hashCode(); }
    }

    protected Entry<T>[] table;
    protected int size;

    public Set() {
        this(DEFAULT_CAPACITY);
    }

    public Set(int capacity) {
        table = new Entry[capacity];
        size = 0;
    }

    public void insert(T key) {
        if (this.contains(key)) return;
        if (size >= table.length * DEFAULT_LOAD_FACTOR) this.resize(2 * table.length);
        int index = this.getIndex(key);
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
        int index = this.getIndex(key);
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
        int index = this.getIndex(key);
        Entry<T> current = table[index];
        while (current != null) {
            if (current.key.equals(key)) return true;
            current = current.next;
        }
        return false;
    }

    public Set<T> union(Set<T> other) {
        Set<T> union = new Set<>(Math.max(this.size, other.size));
        for (Entry<T> entry : this.table) {
            Entry<T> current = entry;
            while (current != null) {
                union.insert(current.key);
                current = current.next;
            }
        }
        for (Entry<T> entry : other.table) {
            Entry<T> current = entry;
            while (current != null) {
                union.insert(current.key);
                current = current.next;
            }
        }
        return union;
    }

    public Set<T> intersection(Set<T> other) {
        Set<T> intersection = new Set<>(Math.min(this.size, other.size));
        for (Entry<T> entry : this.table) {
            Entry<T> current = entry;
            while (current != null) {
                if (other.contains(current.key)) intersection.insert(current.key);
                current = current.next;
            }
        }
        return intersection;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    protected int getIndex(T key) { return (key.hashCode() & 0x7fffffff) % table.length; }  // Bitmask for +index

    protected void resize(int newCapacity) {
        Entry<T>[] newTable = new Entry[newCapacity];
        for (Entry<T> entry : table) {
            Entry<T> current = entry;
            while (current != null) {
                Entry<T> next = current.next;
                int index = (current.key.hashCode() & 0x7fffffff) % newCapacity;
                current.next = newTable[index];
                newTable[index] = current;
                current = next;
            }
        }
        table = newTable;
    }

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
    
            @Override
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
    
            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T key = current.key;
                current = current.next;
                return key;
            }
        };
    }    
}