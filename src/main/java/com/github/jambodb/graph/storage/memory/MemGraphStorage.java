package com.github.jambodb.graph.storage.memory;

import com.github.jambodb.graph.storage.*;

public class MemGraphStorage implements GraphStorage {

    private final MemElementsStorage elementsSt;

    private final AttributeStorage labelsSt;

    private final AttributeValueStorage propsSt;

    private final ManyToManyStorage node2Nodes;

    private final ManyToOneStorage node2Edges;

    private final OneToManyStorage edge2Node;

    public MemGraphStorage() {
        elementsSt = new MemElementsStorage();
        labelsSt = new MemAttributeStorage();
        propsSt = new MemAttributeValueStorage();
        node2Nodes = new MemManyToManyStorage();
        node2Edges = new MemManyToOneStorage();
        edge2Node = new MemOneToManyStorage();
    }

    @Override
    public ElementsStorage getElementsSt() {
        return elementsSt;
    }

    @Override
    public AttributeStorage getLabelsSt() {
        return labelsSt;
    }

    @Override
    public AttributeValueStorage getPropsSt() {
        return propsSt;
    }

    @Override
    public ManyToManyStorage getNode2Nodes() {
        return node2Nodes;
    }

    @Override
    public ManyToOneStorage getNode2Edges() {
        return node2Edges;
    }

    @Override
    public OneToManyStorage getEdge2Node() {
        return edge2Node;
    }
}
