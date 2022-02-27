package com.github.jambodb.graph;

import com.github.jambodb.graph.impl.GraphImpl;
import com.github.jambodb.graph.storage.memory.MemGraphStorage;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphTest {
    private Random random = new Random();

    @Test
    public void testLabelQueries() {
        Graph graph = new GraphImpl(new MemGraphStorage());
        assertEquals(0, graph.nodes().count());

        Map<Long, Node> nodes = new HashMap<>();
        List<String> labels = createLabels();
        Map<Long, List<String>> nodeLabels = new HashMap<>();
        Map<String, List<Long>> labelNodes = new HashMap<>();

        for (int i = 0; i < 1000; i++) {
            var node = graph.add();
            nodes.put(node.id(), node);
            assertEquals(nodes.size(), graph.nodes().count());
        }

        for (var id : nodes.keySet()) {
            assertEquals(nodes.get(id), graph.get(id));
            List<String> randomLabels = randomObjects(labels);
            nodeLabels.put(id, randomLabels);
            for (String label : randomLabels) {
                graph.get(id).set(label);
                labelNodes.computeIfAbsent(label, x -> new ArrayList<>());
                labelNodes.get(label).add(id);
            }

            assertArrayEquals(toSortedArray(randomLabels), toSortedArray(graph.get(id).labels()));
        }

        for(var entry : labelNodes.entrySet()) {
            var query = graph.nodes().is(entry.getKey()).all();
            var nodeIds = toSortedArray(query.map(Element::id).collect(Collectors.toList()));

            assertArrayEquals(toSortedArray(entry.getValue()), nodeIds);
        }
    }

    private Object[] toSortedArray(Collection<?> labels) {
        return new TreeSet<Object>(labels).toArray();
    }

    private List<String> randomObjects(List<String> labels) {
        Set<String> result = new TreeSet<>();
        int count = random.nextInt(labels.size());
        for(int i = 0; i < count; i++) {
            result.add(labels.get(random.nextInt(labels.size())));
        }
        return new ArrayList<>(result);
    }

    private List<String> createLabels() {
        TreeSet<String> labels = new TreeSet<>();
        for(int i = 0; i < 100; i++) {
            labels.add(UUID.randomUUID().toString());
        }
        return new ArrayList<>(labels);
    }
}
