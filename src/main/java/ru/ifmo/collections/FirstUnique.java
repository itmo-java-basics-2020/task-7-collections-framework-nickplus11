package ru.ifmo.collections;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Design a class which contains integers and returns first unique integer (in order of addition).
 * FirstUniqueTest can be used as an example.
 */
public class FirstUnique {

    private HashMap<Integer, Integer> innerMap;
    private Integer indexOfUnique;
    private ArrayList<Integer> innerList;

    public FirstUnique(int[] numbers) {
        innerMap = new HashMap<Integer, Integer>();
        innerList = new ArrayList<Integer>();

        for (var i : numbers) {
            innerList.add(i);
            if (innerMap.containsKey(i)) {
                int count = innerMap.get(i);
                innerMap.put(i, count + 1);
            } else {
                innerMap.put(i, 1);
            }
        }
    }

    private void findNextUnique() {
        int startIndex = indexOfUnique == null ? 0 : indexOfUnique;
        for (int i = startIndex; i < innerList.size(); ++i) {
            if (innerMap.get(innerList.get(i)) == 1) {
                indexOfUnique = i;
                break;
            }
            if (i == innerList.size() - 1) {
                indexOfUnique = null;
            }
        }
    }

    public int showFirstUnique() {
        findNextUnique();
        if (indexOfUnique == null) {
            System.out.println("There is no any unique objects");
            return -1;
        }
        return innerList.get(indexOfUnique);
    }

    public void add(int value) {
        innerList.add(value);
        if (innerMap.containsKey(value)) {
            int count = innerMap.get(value);
            innerMap.put(value, count + 1);
        } else {
            innerMap.put(value, 1);
        }
    }
}
