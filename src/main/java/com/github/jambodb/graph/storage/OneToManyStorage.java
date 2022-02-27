package com.github.jambodb.graph.storage;

import java.util.Iterator;

public interface OneToManyStorage {
    void connect(long from, long to);

    void disconnect(long from, long to);

    boolean exists(long from);

    Long forward(long from);

    Iterator<Long> backward(long to);
}
