package com.github.jambodb.graph.storage.memory;

public class ComparableTuple<K1 extends Comparable<K1>, K2 extends Comparable<K2>> implements Comparable<ComparableTuple<K1, K2>> {
    private K1 value1;

    private K2 value2;

    public ComparableTuple(K1 value1, K2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public K1 getValue1() {
        return value1;
    }

    public void setValue1(K1 value1) {
        this.value1 = value1;
    }

    public K2 getValue2() {
        return value2;
    }

    public void setValue2(K2 value2) {
        this.value2 = value2;
    }

    @Override
    public int compareTo(ComparableTuple<K1, K2> o) {
        int cmp = value1.compareTo(o.value1);
        if(cmp == 0) {
            return value2.compareTo(o.value2);
        }
        return cmp;
    }
}
