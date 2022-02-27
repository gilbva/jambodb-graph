package com.github.jambodb.graph;

public interface Graph {
    Node get(long id);

    Node add();

    Nodes nodes();

    Edges edges();
}
