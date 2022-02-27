package com.github.jambodb.graph.impl;

import com.github.jambodb.graph.Edge;
import com.github.jambodb.graph.Node;
import com.github.jambodb.graph.storage.*;

public class NodeImpl extends BaseElement<Node> implements Node {

    private final ElementsStorage elementsSt;

    private final ManyToManyStorage node2Nodes;

    private final ManyToOneStorage node2Edges;

    private final OneToManyStorage edge2Node;

    public NodeImpl(long id, GraphStorage storage) {
        super(id, storage);
        elementsSt = storage.getElementsSt();
        node2Nodes = storage.getNode2Nodes();
        node2Edges = storage.getNode2Edges();
        edge2Node = storage.getEdge2Node();
    }

    @Override
    public Edge connect(Node node) {
        long edge = elementsSt.add(ElementType.EDGE);
        node2Nodes.connect(id, node.id());
        node2Edges.connect(id, edge);
        edge2Node.connect(edge, node.id());
        return new EdgeImpl(edge, storage);
    }

    @Override
    protected Node getThis() {
        return this;
    }
}
