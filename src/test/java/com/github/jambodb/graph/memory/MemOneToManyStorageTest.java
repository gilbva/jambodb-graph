package com.github.jambodb.graph.memory;

import com.github.jambodb.graph.storage.memory.MemOneToManyStorage;
import org.junit.jupiter.api.Test;

import static com.github.jambodb.graph.utils.Utils.count;
import static org.junit.jupiter.api.Assertions.*;

public class MemOneToManyStorageTest {
    @Test
    void test() {
        var st = new MemOneToManyStorage();

        st.connect(1L, 2L);
        assertTrue(st.exists(1L));
        assertFalse(st.exists(2L));

        assertEquals(2L, st.forward(1L));
        assertEquals(null, st.forward(2L));
        assertEquals(0, count(st.backward(1L)));
        assertEquals(1, count(st.backward(2L)));
    }
}
