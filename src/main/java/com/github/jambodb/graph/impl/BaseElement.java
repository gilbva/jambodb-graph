package com.github.jambodb.graph.impl;

import com.github.jambodb.graph.Element;
import com.github.jambodb.graph.storage.AttributeStorage;
import com.github.jambodb.graph.storage.AttributeValueStorage;
import com.github.jambodb.graph.storage.GraphStorage;

import java.util.*;

public abstract class BaseElement<E extends Element<E>> implements Element<E> {
    protected long id;

    protected AttributeStorage labelsSt;

    protected AttributeValueStorage propsSt;

    protected GraphStorage storage;

    public BaseElement(long id, GraphStorage storage) {
        this.id = id;
        this.storage = storage;
        this.labelsSt = storage.getLabelsSt();
        this.propsSt = storage.getPropsSt();
    }

    @Override
    public long id() {
        return id;
    }

    @Override
    public E set(String... labels) {
        for (var label : labels) {
            var labelId = labelsSt.id(label);
            labelsSt.add(id, labelId);
        }
        return getThis();
    }

    @Override
    public boolean has(String... labels) {
        for (var label : labels) {
            var labelId = labelsSt.id(label);
            if(!labelsSt.has(id, labelId)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Set<String> labels() {
        Set<String> result = new HashSet<>();
        var labelIds = labelsSt.attributes(id);
        while (labelIds.hasNext()) {
            result.add(labelsSt.get(labelIds.next()));
        }
        return result;
    }

    @Override
    public Set<String> names() {
        Set<String> result = new HashSet<>();
        var propIds = propsSt.attributes(id);
        while (propIds.hasNext()) {
            result.add(propsSt.get(propIds.next()));
        }
        return result;
    }

    @Override
    public Map<String, Object> props() {
        HashMap<String, Object> result = new HashMap<>();
        var propIds = propsSt.attributes(id);
        while (propIds.hasNext()) {
            var propId = propIds.next();
            var key = propsSt.get(propId);
            var value = propsSt.get(id, propId, Object.class);
            result.put(key, value);
        }
        return result;
    }

    @Override
    public <T> T prop(String name, Class<T> type) {
        var propId = propsSt.id(name);
        if(propId < 0) {
            return null;
        }
        return propsSt.get(id, propId, type);
    }

    @Override
    public <T> E prop(String name, T value) {
        var propId = propsSt.id(name);
        propsSt.set(id, propId, value);
        return getThis();
    }

    protected abstract E getThis();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseElement<?> that = (BaseElement<?>) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
