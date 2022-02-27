package com.github.jambodb.graph.storage.memory;

import com.github.jambodb.graph.storage.ManyToManyStorage;

import java.util.Iterator;
import java.util.TreeSet;

import static com.github.jambodb.graph.storage.memory.MemStorageUtils.longIterator;

public class MemManyToManyStorage implements ManyToManyStorage {

    private final TreeSet<ComparableTuple<Long, Long>> forward = new TreeSet<>();

    private final TreeSet<ComparableTuple<Long, Long>> backward = new TreeSet<>();

    @Override
    public void connect(long from, long to) {
        forward.add(new ComparableTuple<>(from, to));
        backward.add(new ComparableTuple<>(to, from));
    }

    @Override
    public void disconnect(long from, long to) {
        forward.add(new ComparableTuple<>(from, to));
        backward.add(new ComparableTuple<>(to, from));
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
    public Iterator<Long> backward(long to) {
        return longIterator(backward, to);
    }
}
