package com.github.jambodb.graph.impl;

import com.github.jambodb.graph.Edge;
import com.github.jambodb.graph.Node;
import com.github.jambodb.graph.storage.GraphStorage;
import com.github.jambodb.graph.storage.ManyToOneStorage;
import com.github.jambodb.graph.storage.OneToManyStorage;

public class EdgeImpl extends BaseElement<Edge> implements Edge {
    private final ManyToOneStorage node2Edges;

    private final OneToManyStorage edge2Node;

    public EdgeImpl(long id, GraphStorage storage) {
        super(id, storage);
        node2Edges = storage.getNode2Edges();
        edge2Node = storage.getEdge2Node();
    }

    @Override
    protected Edge getThis() {
        return this;
    }

    @Override
    public Node from() {
        long nodeId = node2Edges.backward(id);
        return new NodeImpl(nodeId, storage);
    }

    @Override
    public Node to() {
        long nodeId = edge2Node.forward(id);
        return new NodeImpl(nodeId, storage);
    }
}
