package com.github.jambodb.graph.utils;

import java.util.Iterator;

public class Utils {
    public static long count(Iterator<?> list) {
        long count = 0l;
        while (list.hasNext()) {
            list.next();
            count++;
        }
        return count;
    }
}
