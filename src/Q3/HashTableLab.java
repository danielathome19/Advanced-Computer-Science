package Q3;

import java.util.LinkedList;

public class HashTableLab {
    static abstract class HashTable<T> {
        protected LinkedList<T>[] table;

        @SuppressWarnings("unchecked")
        public HashTable(int size) {
            table = new LinkedList[size];
            for (int i = 0; i < size; i++)
                table[i] = new LinkedList<>();
        }

        protected abstract int hash(T key);

        public void insert(T key) {
            int index = hash(key);
            if (!table[index].contains(key))
                table[index].add(key);
        }

        public boolean search(T key) {
            int index = hash(key);
            return table[index].contains(key);
        }

        public void display() {
            for (int i = 0; i < table.length; i++)
                System.out.println("Index " + i + ": " + table[i]);
        }
    }

    static class IntHashTable extends HashTable<Integer> {
        public IntHashTable(int size) { super(size); }

        protected int hash(Integer key) {
            return Math.abs(key) % table.length;
        }
    }

    static class StringHashTable extends HashTable<String> {
        public StringHashTable(int size) { super(size); }

        protected int hash(String key) {
            int hash = 0;
            for (char c : key.toCharArray())
                hash = (hash * 31 + c) % table.length;
            return hash;
        }
    }

    public static void main(String[] args) {
        System.out.println("Int Hash Table:");
        var iTable = new IntHashTable(5);
        iTable.insert(10);
        iTable.insert(15);
        iTable.insert(20);
        iTable.insert(26);
        iTable.insert(30);
        iTable.display();
        System.out.println("Has 20: " + iTable.search(20));
        System.out.println("Has 50: " + iTable.search(50));

        System.out.println("\nString Hash Table:");
        var sTable = new StringHashTable(5);
        sTable.insert("apple");
        sTable.insert("banana");
        sTable.insert("orange");
        sTable.insert("mango");
        sTable.insert("grape");
        sTable.display();
        System.out.println("Has 'apple': " + sTable.search("apple"));
        System.out.println("Has 'starfruit': " + sTable.search("starfruit"));
    }
}

/*
Int Hash Table:
Index 0: [10, 15, 20, 30]
Index 1: [26]
Index 2: []
Index 3: []
Index 4: []
Has 20: true
Has 50: false

String Hash Table:
Index 0: [apple, mango]
Index 1: [orange]
Index 2: [grape]
Index 3: []
Index 4: [banana]
Has 'apple': true
Has 'starfruit': false
*/