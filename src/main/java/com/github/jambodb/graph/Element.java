package com.github.jambodb.graph;

import java.util.Map;
import java.util.Set;

public interface Element<E extends Element<E>> {
    long id();

    E set(String... label);

    boolean has(String... label);

    Set<String> labels();

    Set<String> names();

    Map<String, Object> props();

    <T> T prop(String name, Class<T> type);

    <T> E prop(String name, T value);
}
