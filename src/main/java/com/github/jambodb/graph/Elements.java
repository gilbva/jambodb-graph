package com.github.jambodb.graph;

public interface Elements<E extends Elements<E>> {
    E is(String... label);

    <T> E eq(String prop, T value);

    <T> E ne(String prop, T value);

    <T extends Comparable<T>> E gt(String prop, T value);

    <T extends Comparable<T>> E ge(String prop, T value);

    <T extends Comparable<T>> E lt(String prop, T value);

    <T extends Comparable<T>> E le(String prop, T value);
}
