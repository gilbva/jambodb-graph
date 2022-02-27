package com.github.jambodb.graph.impl;

import com.github.jambodb.graph.Elements;
import com.github.jambodb.graph.storage.GraphStorage;

import java.util.stream.Stream;

public abstract class ElementsImpl<E extends Elements<E>> implements Elements<E> {

    protected final GraphStorage storage;

    protected Stream<Long> elements;

    public ElementsImpl(GraphStorage storage, Stream<Long> elements) {
        this.storage = storage;
        this.elements = elements;
    }

    @Override
    public E is(String... labels) {
        var st = storage.getLabelsSt();
        int[] ids = new int[labels.length];
        for(int i = 0; i < labels.length; i++) {
            ids[i] = st.id(labels[i]);
        }
        elements = elements.filter(x -> st.hasAll(x, ids));
        return getThis();
    }

    @Override
    public <T> E eq(String prop, T value) {
        var st = storage.getPropsSt();
        int id = st.id(prop);
        elements = elements.filter(x -> st.eq(x, id, value));
        return getThis();
    }

    @Override
    public <T> E ne(String prop, T value) {
        var st = storage.getPropsSt();
        int id = st.id(prop);
        elements = elements.filter(x -> st.ne(x, id, value));
        return getThis();
    }

    @Override
    public <T extends Comparable<T>> E gt(String prop, T value) {
        var st = storage.getPropsSt();
        int id = st.id(prop);
        elements = elements.filter(x -> st.gt(x, id, value));
        return getThis();
    }

    @Override
    public <T extends Comparable<T>> E ge(String prop, T value) {
        var st = storage.getPropsSt();
        int id = st.id(prop);
        elements = elements.filter(x -> st.ge(x, id, value));
        return getThis();
    }

    @Override
    public <T extends Comparable<T>> E lt(String prop, T value) {
        var st = storage.getPropsSt();
        int id = st.id(prop);
        elements = elements.filter(x -> st.lt(x, id, value));
        return getThis();
    }

    @Override
    public <T extends Comparable<T>> E le(String prop, T value) {
        var st = storage.getPropsSt();
        int id = st.id(prop);
        elements = elements.filter(x -> st.le(x, id, value));
        return getThis();
    }

    protected abstract E getThis();
}
