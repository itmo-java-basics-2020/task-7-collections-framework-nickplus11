package ru.ifmo.collections;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Design a class to find the kth largest element in a stream. k is from 1 to numbers.length.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Constructor accepts an integer k and an integer array numbers, which contains initial elements from the stream.
 * For each call to the method KthLargest.add(), return the element representing the kth largest element in the stream.
 */
public class KthLargest {
    PriorityQueue<Integer> priorityQueue;
    int k;

    public KthLargest(int k, int[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException();
        }

        this.k = k;
        priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                }
                if (o1 < o2) {
                    return 1;
                }
                return 0;
            }
        });

        for (var n : numbers) {
            priorityQueue.add(n);
        }

        while (priorityQueue.size() > k) {
            priorityQueue.remove();
        }
    }

    public int add(int val) {
        priorityQueue.add(val);
        priorityQueue.remove();
        return priorityQueue.element();
    }
}