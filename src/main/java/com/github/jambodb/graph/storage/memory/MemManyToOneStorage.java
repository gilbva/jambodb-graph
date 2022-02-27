package com.github.jambodb.graph.storage.memory;

import com.github.jambodb.graph.storage.ManyToOneStorage;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

import static com.github.jambodb.graph.storage.memory.MemStorageUtils.longIterator;

public class MemManyToOneStorage implements ManyToOneStorage {
    private final TreeSet<ComparableTuple<Long, Long>> forward = new TreeSet<>();

    private final TreeMap<Long, Long> backward = new TreeMap<>();

    @Override
    public void connect(long from, long to) {
        forward.add(new ComparableTuple<>(from, to));
        backward.put(to, from);
    }

    @Override
    public void disconnect(long from, long to) {
        forward.remove(new ComparableTuple<>(from, to));
        backward.remove(to);
    }

    @Override
    public boolean exists(long from, long to) {
        return forward.contains(new ComparableTuple<>(from, to));
    }

    @Override
    public Iterator<Long> forward(long from) {
        return longIterator(forward, from);
    }

    @Override
    public long backward(long to) {
        return backward.get(to);
    }
}
