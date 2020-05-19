package ru.ifmo.collections;

import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.PriorityQueue;

/**
 * Represents LRU cache with fixed maximum capacity.
 * <p>
 * get() should return null if there is no value for a given key.
 * elements() should return number of elements in cache.
 * <p>
 * This class should not have any other public methods.
 * <p>
 * Implementing this cache in (almost) the same manner as it was implemented during the lecture will result in extra points.
 */
public class LruCache<K, V> {
    private class KeyValueTime {
        private K key;
        private V value;
        private Date birthTime;

        public KeyValueTime(K key, V value, Date time) {
            this.birthTime = time;
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private Hashtable<K, KeyValueTime> innerHashTable;
    private PriorityQueue<KeyValueTime> innerQueue;

    public LruCache(int capacity) {
        this.capacity = capacity;
        innerHashTable = new Hashtable<>();
        innerQueue = new PriorityQueue<>(capacity, kvtComparator);
    }

    private Comparator<KeyValueTime> kvtComparator = (o1, o2) -> {
        if (o1.birthTime.before(o2.birthTime)) {
            return -1;
        }
        if (o1.birthTime.after(o2.birthTime)) {
            return 1;
        }
        return 0;
    };

    public V get(K key) {
        if (innerHashTable.containsKey(key)) {
            innerQueue.remove(innerHashTable.get(key));
            put(key, innerHashTable.get(key).value);
            return innerHashTable.get(key).value;
        }
        return null;
    }

    public void put(K key, V value) {
        if (innerQueue.size() == capacity) {
            KeyValueTime kvt = innerQueue.remove();
            innerHashTable.remove(kvt.key);
        }
        var kvt = new KeyValueTime(key, value, new Date());
        innerQueue.add(kvt);
        innerHashTable.put(key, kvt);
    }

    public int elements() {
        return innerHashTable.size();
    }
}