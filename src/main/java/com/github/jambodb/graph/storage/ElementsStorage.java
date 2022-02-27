package com.github.jambodb.graph.storage;

import java.util.Iterator;

public interface ElementsStorage {
    long add(ElementType type);

    void remove(long id);

    ElementType get(long id);

    Iterator<Long> list(ElementType type);
}
