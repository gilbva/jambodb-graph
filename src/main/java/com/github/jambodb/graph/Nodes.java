package com.github.jambodb.graph;

import java.util.stream.Stream;

public interface Nodes extends Elements<Nodes> {
    Nodes outNodes();

    Nodes inNodes();

    Edges outEdges();

    Edges inEdges();

    long count();

    Stream<Node> all();
}
