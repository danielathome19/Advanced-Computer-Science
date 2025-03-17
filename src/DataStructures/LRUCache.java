package DataStructures;

public class LRUCache<K extends Comparable<K>, V extends Comparable<V>> {
    private int capacity;
    private Dictionary<K, V> cache;
    private DoublyLinkedList<K> list;

    public LRUCache() { this(3); }
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new Dictionary<>(capacity);
        list = new DoublyLinkedList<>();
    }

    public V get(K key) {
        if (!cache.contains(key)) return null;
        V value = cache.get(key);
        list.removeItem(key);
        list.addFront(key);
        return value;
    }

    public void put(K key, V value) {
        if (cache.contains(key)) {
            cache.insert(key, value);
            list.removeItem(key);
            list.addFront(key);
        } else {
            if (cache.size() == capacity) {
                K lruKey = list.pop();
                evict(lruKey);
            }
            cache.insert(key, value);
            list.addFront(key);
        }
    }

    public void evict(K key) {
        cache.remove(key);
        list.removeItem(key);
    }

    public void report() {
        for (K current : list)
            System.out.print(current + ":" + cache.get(current) + " -> ");
        System.out.println("END");
    }
}
