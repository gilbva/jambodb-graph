package com.github.jambodb.graph.impl;

import com.github.jambodb.graph.Edge;
import com.github.jambodb.graph.Edges;
import com.github.jambodb.graph.Nodes;
import com.github.jambodb.graph.storage.GraphStorage;

import java.util.stream.Stream;

public class EdgesImpl extends ElementsImpl<Edges> implements Edges {

    public EdgesImpl(GraphStorage storage, Stream<Long> elements) {
        super(storage, elements);
    }

    @Override
    protected Edges getThis() {
        return this;
    }

    @Override
    public Nodes fromNodes() {
        var st = storage.getNode2Edges();
        return new NodesImpl(storage, elements.map(st::backward));
    }

    @Override
    public Nodes toNodes() {
        var st = storage.getEdge2Node();
        return new NodesImpl(storage, elements.map(st::forward));
    }

    @Override
    public long count() {
        return elements.count();
    }

    @Override
    public Stream<Edge> all() {
        return elements.map(x -> new EdgeImpl(x, storage));
    }
}
