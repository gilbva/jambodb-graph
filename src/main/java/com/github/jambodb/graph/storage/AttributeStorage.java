package com.github.jambodb.graph.storage;

import java.util.Iterator;

public interface AttributeStorage {
    String get(int attr);

    int id(String attr);

    void add(long element, int attr);

    void remove(long element, int attr);

    boolean has(long element, int attr);

    boolean hasAll(long element, int[] attr);

    Iterator<Integer> attributes(long element);

    Iterator<Long> elements(int attr);
}
