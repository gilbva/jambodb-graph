package com.github.jambodb.graph.storage.memory;

import com.github.jambodb.graph.storage.AttributeValueStorage;

import java.util.Objects;
import java.util.TreeMap;

public class MemAttributeValueStorage extends MemAttributeStorage implements AttributeValueStorage {

    private final TreeMap<ComparableTuple<Long, Integer>, Object> values = new TreeMap<>();

    @Override
    public <T> T get(long element, int attr, Class<T> cls) {
        var tuple = new ComparableTuple<>(element, attr);
        var value = values.get(tuple);
        return cls.cast(value);
    }

    @Override
    public <T> void set(long element, int attr, T value) {
        var tuple = new ComparableTuple<>(element, attr);
        values.put(tuple, value);
    }

    public void remove(long element, int attr) {
        super.remove(element, attr);
        var tuple = new ComparableTuple<>(element, attr);
        values.remove(tuple);
    }

    @Override
    public <T> boolean eq(Long element, int attr, T value) {
        var v = get(element, attr, Object.class);
        return Objects.equals(value, v);
    }

    @Override
    public <T> boolean ne(Long element, int attr, T value) {
        var v = get(element, attr, Object.class);
        return !Objects.equals(value, v);
    }

    @Override
    public <T> boolean gt(Long element, int attr, T value) {
        var result = compare(element, attr, value);
        return result != null && result > 0;
    }

    @Override
    public <T> boolean ge(Long element, int attr, T value) {
        var result = compare(element, attr, value);
        return result != null && result >= 0;
    }

    @Override
    public <T> boolean lt(Long element, int attr, T value) {
        var result = compare(element, attr, value);
        return result != null && result < 0;
    }

    @Override
    public <T> boolean le(Long element, int attr, T value) {
        var result = compare(element, attr, value);
        return result != null && result <= 0;
    }

    public <T> Integer compare(Long element, int attr, T value) {
        var v = get(element, attr, Object.class);
        if(v != null && value != null) {
            if(value instanceof  Comparable) {
                return ((Comparable)v).compareTo(value);
            }
        }
        return null;
    }
}
