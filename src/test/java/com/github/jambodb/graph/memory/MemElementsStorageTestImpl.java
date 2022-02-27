package com.github.jambodb.graph.memory;

import com.github.jambodb.graph.storage.ElementType;
import com.github.jambodb.graph.storage.memory.MemElementsStorage;
import com.github.jambodb.graph.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemElementsStorageTestImpl {
    @Test
    void test() {
        var st = new MemElementsStorage();

        Assertions.assertFalse(st.list(ElementType.EDGE).hasNext());
        Assertions.assertFalse(st.list(ElementType.NODE).hasNext());

        for(long i = 1; i <= 1000; i++) {
            Assertions.assertNull(st.get(i));
            Assertions.assertEquals(i, st.add(ElementType.NODE));
            Assertions.assertEquals(0l, Utils.count(st.list(ElementType.EDGE)));
            Assertions.assertEquals(i, Utils.count(st.list(ElementType.NODE)));
            Assertions.assertEquals(ElementType.NODE, st.get(i));
        }
        for(long i = 1001; i <= 2000; i++) {
            Assertions.assertEquals(i, st.add(ElementType.EDGE));
            Assertions.assertEquals(i - 1000, Utils.count(st.list(ElementType.EDGE)));
            Assertions.assertEquals(1000, Utils.count(st.list(ElementType.NODE)));
            Assertions.assertEquals(ElementType.EDGE, st.get(i));
        }

        Assertions.assertEquals(1000, Utils.count(st.list(ElementType.NODE)));
        Assertions.assertEquals(1000, Utils.count(st.list(ElementType.EDGE)));

        for(long i = 1; i <= 1000; i++) {
            Assertions.assertEquals(ElementType.NODE, st.get(i));
            st.remove(i);
        }
        for(long i = 1001; i <= 2000; i++) {
            Assertions.assertEquals(ElementType.EDGE, st.get(i));
            st.remove(i);
        }

        Assertions.assertFalse(st.list(ElementType.EDGE).hasNext());
        Assertions.assertFalse(st.list(ElementType.NODE).hasNext());
    }


}
