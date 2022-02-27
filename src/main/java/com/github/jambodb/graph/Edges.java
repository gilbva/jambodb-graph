package com.github.jambodb.graph;

import java.util.stream.Stream;

public interface Edges extends Elements<Edges> {
    Nodes fromNodes();

    Nodes toNodes();

    long count();

    Stream<Edge> all();
}
