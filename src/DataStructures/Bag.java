package DataStructures;


public class Bag<T extends Comparable<T>> extends Set<T> {
    private static class Entry<T> extends Set.Entry<T> {
        int count;

        Entry(T key) {
            super(key);
            this.count = 1;
        }
    }

    public Bag() { super(); }
    public Bag(int capacity) { super(capacity); }

    @Override
    public void insert(T key) {
        if (size >= table.length * DEFAULT_LOAD_FACTOR) this.resize(2 * table.length);
        int index = this.getIndex(key);
        Entry<T> entry = new Entry<>(key);
        if (table[index] == null) {
            table[index] = entry;
            size++;
        } else {
            Entry<T> current = (Entry<T>) table[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.count++;
                    return;
                }
                current = (Entry<T>) current.next;
            }
            if (current.key.equals(key)) current.count++;
            else {
                current.next = entry;
                size++;
            }
        }
    }

    @Override
    public void remove(T key) {
        int index = this.getIndex(key);
        Entry<T> current = (Entry<T>) table[index];
        Entry<T> previous = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (current.count > 1) current.count--;
                else {
                    if (previous == null)
                        table[index] = current.next;
                    else
                        previous.next = current.next;
                    size--;
                }
                return;
            }
            previous = current;
            current = (Entry<T>) current.next;
        }
    }

    public int count(T key) {
        int index = this.getIndex(key);
        Entry<T> current = (Entry<T>) table[index];
        while (current != null) {
            if (current.key.equals(key)) return current.count;
            current = (Entry<T>) current.next;
        }
        return 0;
    }

    @Override
    public void enumerate() {
        System.out.println("{ ");
        for (var entry : table) {
            Entry<T> current = (Entry<T>) entry;
            while (current != null) {
                System.out.println("\t" + current.key + "(" + current.count + ") ");
                current = (Entry<T>) current.next;
            }
        }
        System.out.println("}");
    }

    public Bag<T> union(Bag<T> other) {
        Bag<T> union = new Bag<>(Math.max(this.size, other.size));
        for (var entry : this.table)
            copyInsert(union, (Entry<T>) entry);
        for (var entry : other.table)
            copyInsert(union, (Entry<T>) entry);
        return union;
    }

    private void copyInsert(Bag<T> union, Entry<T> entry) {
        Entry<T> current = entry;
        while (current != null) {
            for (int i = 0; i < current.count; i++)
                union.insert(current.key);
            current = (Entry<T>) current.next;
        }
    }

    public Bag<T> intersection(Bag<T> other) {
        Bag<T> intersection = new Bag<>(Math.min(this.size, other.size));
        for (Set.Entry<T> entry : this.table) {
            Entry<T> current = (Entry<T>) entry;
            while (current != null) {
                int countInOther = other.count(current.key);
                int minCount = Math.min(current.count, countInOther);
                for (int i = 0; i < minCount; i++)
                    intersection.insert(current.key);
                current = (Entry<T>) current.next;
            }
        }
        return intersection;
    }

    public Set<T> getSet() {
        Set<T> set = new Set<>(this.size);
        for (var entry : this.table) {
            Entry<T> current = (Entry<T>) entry;
            while (current != null) {
                set.insert(current.key);
                current = (Entry<T>) current.next;
            }
        }
        return set;
    }
}