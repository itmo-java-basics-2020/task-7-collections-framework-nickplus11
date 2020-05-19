package ru.ifmo.collections;

import java.util.*;

/**
 * Represents sorted set of unique values.
 * create() returns a SortedSet instance with natural ordering. (i.e. from smallest to largest in case of integer numbers)
 * from() is used to create a SortedSet instance with given Comparator.
 * Instances of a class can be created using only these two methods above.
 * <p>
 * This class should not be abstract and should be capable of adding/removing single/multiple elements.
 * It has two more methods: getSorted() and getReversed() which return an array of set contents in forward and reverse order respectively.
 * <p>
 * NB! This class must have only map(s) as an internal data structure(s).
 *
 * @param <T> set contents type
 */
public class SortedSet<T> extends AbstractSet<T> {
    private final Map<T, Integer> contents;

    private SortedSet(TreeMap<T, Integer> tm) {
        contents = tm;
    }

    public static <T> SortedSet<T> create() {
        return new SortedSet<>(new TreeMap<>());
    }

    public static <T> SortedSet<T> from(Comparator<T> comparator) {
        return new SortedSet<>(new TreeMap<>(comparator));
    }

    @Override
    public boolean add(T t) {
        if (contents.containsKey(t)) {
            return false;
        }
        contents.put(t, null);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean isSuccessful = true;
        for (var e : c) {
            if (!add(e)) {
                isSuccessful = false;
            }
        }
        return isSuccessful;
    }

    @Override
    public boolean remove(Object o) {
        if (o != null) {
            contents.remove(o);
            return true;
        }
        return false;
    }

    public boolean removeAll(T... args) {
        boolean isSuccessful = true;
        for (var a : args) {
            if (!remove(a)) {
                isSuccessful = false;
            }
        }
        return isSuccessful;
    }

    public List<T> getSorted() {
        return new ArrayList<T>(contents.keySet());
    }

    public List<T> getReversed() {
        var result = new ArrayList<T>();
        var sorted = getSorted();
        for (int i = sorted.size() - 1; i >= 0; --i) {
            result.add(sorted.get(i));
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return contents.keySet().iterator();
    }

    @Override
    public int size() {
        return contents.size();
    }
}
