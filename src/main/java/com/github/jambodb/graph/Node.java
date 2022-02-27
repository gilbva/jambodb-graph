package com.github.jambodb.graph;

public interface Node extends Element<Node> {
    Edge connect(Node node);


}
