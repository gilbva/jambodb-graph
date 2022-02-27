package com.github.jambodb.graph.storage;

public interface GraphStorage {
    ElementsStorage getElementsSt();

    AttributeStorage getLabelsSt();

    AttributeValueStorage getPropsSt();

    ManyToManyStorage getNode2Nodes();

    ManyToOneStorage getNode2Edges();

    OneToManyStorage getEdge2Node();
}
