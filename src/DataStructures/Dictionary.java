package DataStructures;

@SuppressWarnings("unchecked")
public class Dictionary<K extends Comparable<K>, V extends Comparable<V>> extends Set<K> {
    public static class Entry<K extends Comparable<K>, 
                              V extends Comparable<V>> extends Set.Entry<K> implements Comparable<Entry<K, V>> {
        public V value;

        Entry(K key, V value) {
            super(key);
            this.value = value;
        }

        public int compareTo(Entry<K, V> o) {
            int keyComparison = this.key.compareTo(o.key);
            return keyComparison != 0 ? keyComparison : this.value.compareTo(o.value);
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<K, V> entry = (Entry<K, V>) o;
            return key.equals(entry.key);
        }

        public int hashCode() { return key.hashCode(); }
    }

    public Dictionary() { super(); }
    public Dictionary(int capacity) { super(capacity); }

    public void insert(K key, V value) {
        if (this.contains(key)) {  // Update value if key already exists
            int index = this.getIndex(key);
            Entry<K, V> current = (Entry<K, V>) table[index];
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = (Entry<K, V>) current.next;
            }
        } else {
            if (size >= table.length * DEFAULT_LOAD_FACTOR) this.resize(2 * table.length);
            int index = this.getIndex(key);
            Entry<K, V> entry = new Entry<>(key, value);
            if (table[index] == null)
                table[index] = entry;
            else {
                Entry<K, V> current = (Entry<K, V>) table[index];
                while (current.next != null) current = (Entry<K, V>) current.next;
                current.next = entry;
            }
            size++;
        }
    }

    public void remove(K key) {
        int index = this.getIndex(key);
        Entry<K, V> current = (Entry<K, V>) table[index];
        Entry<K, V> previous = null;
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
            current = (Entry<K, V>) current.next;
        }
    }

    public V get(K key) {
        int index = this.getIndex(key);
        Entry<K, V> current = (Entry<K, V>) table[index];
        while (current != null) {
            if (current.key.equals(key)) return current.value;
            current = (Entry<K, V>) current.next;
        }
        return null;
    }

    public V getOrDefault(K key, V defaultValue) {
        V value = this.get(key);
        return value != null ? value : defaultValue;
    }

    public K getKey(V value) {
        for (var entry : table) {
            Entry<K, V> current = (Entry<K, V>) entry;
            while (current != null) {
                if (current.value.equals(value)) return current.key;
                current = (Entry<K, V>) current.next;
            }
        }
        return null;
    }

    public boolean containsKey(K key) { return this.contains(key); }
    public boolean containsValue(V value) {
        for (var entry : table) {
            Entry<K, V> current = (Entry<K, V>) entry;
            while (current != null) {
                if (current.value.equals(value)) return true;
                current = (Entry<K, V>) current.next;
            }
        }
        return false;
    }

    public void enumerate() {
        System.out.println("{");
        for (var entry : table) {
            Entry<K, V> current = (Entry<K, V>) entry;
            while (current != null) {
                System.out.println("\t" + current.key + ":\t" + current.value + ",");
                current = (Entry<K, V>) current.next;
            }
        }
        System.out.println("}");
    }

    public Set<K> keySet() {
        Set<K> set = new Set<>(this.size);
        for (var entry : table) {
            Entry<K, V> current = (Entry<K, V>) entry;
            while (current != null) {
                set.insert(current.key);
                current = (Entry<K, V>) current.next;
            }
        }
        return set;
    }

    public Bag<V> valueBag() {
        Bag<V> bag = new Bag<>(this.size);
        for (var entry : table) {
            Entry<K, V> current = (Entry<K, V>) entry;
            while (current != null) {
                bag.insert(current.value);
                current = (Entry<K, V>) current.next;
            }
        }
        return bag;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new Set<>(this.size);
        for (var entry : table) {
            Entry<K, V> current = (Entry<K, V>) entry;
            while (current != null) {
                set.insert(current);
                current = (Entry<K, V>) current.next;
            }
        }
        return set;
    }
}