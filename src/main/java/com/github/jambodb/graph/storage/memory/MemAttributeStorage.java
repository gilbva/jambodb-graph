package com.github.jambodb.graph.storage.memory;

import com.github.jambodb.graph.storage.AttributeStorage;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

import static com.github.jambodb.graph.storage.memory.MemStorageUtils.intIterator;
import static com.github.jambodb.graph.storage.memory.MemStorageUtils.longIterator;

public class MemAttributeStorage implements AttributeStorage {
    private final AtomicInteger ai = new AtomicInteger();

    private final TreeMap<Integer, String> attrs = new TreeMap<>();

    private final TreeMap<String, Integer> ids = new TreeMap<>();

    private final TreeSet<ComparableTuple<Integer, Long>> attrs2Elems = new TreeSet<>();

    private final TreeSet<ComparableTuple<Long, Integer>> elems2Attr = new TreeSet<>();

    @Override
    public String get(int attr) {
        return attrs.get(attr);
    }

    @Override
    public int id(String attr) {
        Integer id = ids.get(attr);
        if(id == null) {
            id = ai.incrementAndGet();
            attrs.put(id, attr);
            ids.put(attr, id);
        }
        return id;
    }

    @Override
    public void add(long element, int attr) {
        var e2a = new ComparableTuple<>(element, attr);
        var a2e = new ComparableTuple<>(attr, element);

        attrs2Elems.add(a2e);
        elems2Attr.add(e2a);
    }

    @Override
    public void remove(long element, int attr) {
        var e2a = new ComparableTuple<>(element, attr);
        var a2e = new ComparableTuple<>(attr, element);

        attrs2Elems.remove(a2e);
        elems2Attr.remove(e2a);
    }

    @Override
    public boolean has(long element, int attr) {
        var e2a = new ComparableTuple<>(element, attr);
        return elems2Attr.contains(e2a);
    }

    @Override
    public boolean hasAll(long element, int[] attrs) {
        for (var attr : attrs) {
            if(!has(element, attr)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<Integer> attributes(long element) {
        return intIterator(elems2Attr, element);
    }

    @Override
    public Iterator<Long> elements(int attr) {
        return longIterator(attrs2Elems, attr);
    }
}
