package com.github.jambodb.graph.impl;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Utilities {
    public static <T> Stream<T> toStream(Iterator<T> it) {
        var sp = Spliterators.spliteratorUnknownSize(it, Spliterator.ORDERED);
        return StreamSupport.stream(sp, false);
    }
}
