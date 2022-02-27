package com.github.jambodb.graph.storage.memory;

import java.util.Iterator;
import java.util.TreeSet;

public class MemStorageUtils {

    public static  <K extends Comparable<K>> Iterator<Long> longIterator(TreeSet<ComparableTuple<K, Long>> map, K key) {
        var start = new ComparableTuple<>(key, Long.MIN_VALUE);
        var end = new ComparableTuple<>(key, Long.MAX_VALUE);
        var set = map.subSet(start, end);
        return set.stream().map(ComparableTuple::getValue2).iterator();
    }

    public static <K extends Comparable<K>> Iterator<Integer> intIterator(TreeSet<ComparableTuple<K, Integer>> map, K key) {
        var start = new ComparableTuple<>(key, Integer.MIN_VALUE);
        var end = new ComparableTuple<>(key, Integer.MAX_VALUE);
        var set = map.subSet(start, end);
        return set.stream().map(ComparableTuple::getValue2).iterator();
    }
}
