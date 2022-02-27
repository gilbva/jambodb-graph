package com.github.jambodb.graph.storage;

import java.util.Iterator;

public interface ManyToOneStorage {
    void connect(long from, long to);

    void disconnect(long from, long to);

    boolean exists(long from, long to);

    Iterator<Long> forward(long from);

    long backward(long to);
}
