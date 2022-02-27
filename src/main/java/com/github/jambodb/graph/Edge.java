package com.github.jambodb.graph;

public interface Edge extends Element<Edge> {
    Node from();

    Node to();
}
