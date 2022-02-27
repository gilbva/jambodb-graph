package com.github.jambodb.graph.impl;

import com.github.jambodb.graph.Edges;
import com.github.jambodb.graph.Node;
import com.github.jambodb.graph.Nodes;
import com.github.jambodb.graph.storage.GraphStorage;

import java.util.stream.Stream;

public class NodesImpl extends ElementsImpl<Nodes> implements Nodes {

    public NodesImpl(GraphStorage storage, Stream<Long> elements) {
        super(storage, elements);
    }

    @Override
    protected Nodes getThis() {
        return this;
    }

    @Override
    public Nodes outNodes() {
        var st = storage.getNode2Nodes();
        return new NodesImpl(storage, elements.map(st::forward).flatMap(Utilities::toStream));
    }

    @Override
    public Nodes inNodes() {
        var st = storage.getNode2Nodes();
        return new NodesImpl(storage, elements.map(st::backward).flatMap(Utilities::toStream));
    }

    @Override
    public Edges outEdges() {
        var st = storage.getNode2Edges();
        return new EdgesImpl(storage, elements.map(st::forward).flatMap(Utilities::toStream));
    }

    @Override
    public Edges inEdges() {
        var st = storage.getEdge2Node();
        return new EdgesImpl(storage, elements.map(st::backward).flatMap(Utilities::toStream));
    }

    @Override
    public long count() {
        return elements.count();
    }

    @Override
    public Stream<Node> all() {
        return elements.map(x -> new NodeImpl(x, storage));
    }
}
