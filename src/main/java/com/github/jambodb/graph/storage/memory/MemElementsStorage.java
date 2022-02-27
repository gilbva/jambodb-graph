package com.github.jambodb.graph.storage.memory;

import com.github.jambodb.graph.storage.ElementsStorage;
import com.github.jambodb.graph.storage.ElementType;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

import static com.github.jambodb.graph.storage.memory.MemStorageUtils.longIterator;

public class MemElementsStorage implements ElementsStorage {

    private final AtomicLong ai = new AtomicLong();

    private final TreeMap<Long, ElementType> types = new TreeMap<>();

    private final TreeSet<ComparableTuple<ElementType, Long>> elements = new TreeSet<>();

    @Override
    public long add(ElementType type) {
        long id = ai.incrementAndGet();
        types.put(id, type);
        elements.add(new ComparableTuple<>(type, id));
        return id;
    }

    @Override
    public void remove(long id) {
        var type = types.get(id);
        if(type != null) {
            elements.remove(new ComparableTuple<>(type, id));
            types.remove(id);
        }
    }

    @Override
    public ElementType get(long id) {
        return types.get(id);
    }

    @Override
    public Iterator<Long> list(ElementType type) {
        return longIterator(elements, type);
    }
}
