package com.github.jambodb.graph.storage;

public interface AttributeValueStorage extends AttributeStorage {
    <T> T get(long element, int attr, Class<T> cls);

    <T> void set(long element, int attr, T value);

    <T> boolean eq(Long x, int id, T value);

    <T> boolean ne(Long x, int id, T value);

    <T> boolean gt(Long x, int id, T value);

    <T> boolean ge(Long x, int id, T value);

    <T> boolean lt(Long x, int id, T value);

    <T> boolean le(Long x, int id, T value);
}
