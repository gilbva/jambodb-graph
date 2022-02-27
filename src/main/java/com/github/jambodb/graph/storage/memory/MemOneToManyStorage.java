package com.github.jambodb.graph.storage.memory;

import com.github.jambodb.graph.storage.OneToManyStorage;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

import static com.github.jambodb.graph.storage.memory.MemStorageUtils.longIterator;

public class MemOneToManyStorage implements OneToManyStorage {
    private final TreeMap<Long, Long> forward = new TreeMap<>();

    private final TreeSet<ComparableTuple<Long, Long>> backward = new TreeSet<>();

    @Override
    public void connect(long from, long to) {
        forward.put(from, to);
        backward.add(new ComparableTuple<>(to, from));
    }

    @Override
    public void disconnect(long from, long to) {
        forward.remove(from);
        backward.remove(new ComparableTuple<>(to, from));
    }

    @Override
    public boolean exists(long from) {
        return forward.containsKey(from);
    }

    @Override
    public Long forward(long from) {
        return forward.get(from);
    }

    @Override
    public Iterator<Long> backward(long to) {
        return longIterator(backward, to);
    }
}
