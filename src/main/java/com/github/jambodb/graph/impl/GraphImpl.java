package com.github.jambodb.graph.impl;

import com.github.jambodb.graph.Edges;
import com.github.jambodb.graph.Graph;
import com.github.jambodb.graph.Node;
import com.github.jambodb.graph.Nodes;
import com.github.jambodb.graph.storage.ElementType;
import com.github.jambodb.graph.storage.GraphStorage;

public class GraphImpl implements Graph {

    private final GraphStorage storage;

    public GraphImpl(GraphStorage storage) {
        this.storage = storage;
    }

    @Override
    public Node get(long id) {
        if(storage.getElementsSt().get(id) != ElementType.NODE) {
            return null;
        }
        return new NodeImpl(id, storage);
    }

    @Override
    public Node add() {
        var id = storage.getElementsSt().add(ElementType.NODE);
        return get(id);
    }

    @Override
    public Nodes nodes() {
        return new NodesImpl(storage, Utilities.toStream(storage.getElementsSt().list(ElementType.NODE)));
    }

    @Override
    public Edges edges() {
        return new EdgesImpl(storage, Utilities.toStream(storage.getElementsSt().list(ElementType.EDGE)));
    }

    public GraphStorage getStorage() {
        return storage;
    }
}
