package com.github.jambodb.graph.memory;

import com.github.jambodb.graph.storage.memory.MemAttributeValueStorage;
import com.github.jambodb.graph.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemAttributeValueStorageTest {
    @Test
    void test() {
        MemAttributeValueStorage st = new MemAttributeValueStorage();

        for(int i = 1; i <= 100; i++) {
            String lb = "Label" + i;
            Assertions.assertEquals(i, st.id(lb));
            Assertions.assertEquals(lb, st.get(i));
            Assertions.assertEquals(0, Utils.count(st.elements(st.id(lb))));
        }

        for(long i = 1L; i <= 1000L; i++) {
            for(int j = 1; j <= 100; j++) {
                String lb = "Label" + j;
                String value = i + "-" + j;
                Assertions.assertFalse(st.has(i, j));
                st.add(i, st.id(lb));
                Assertions.assertTrue(st.has(i, j));
                Assertions.assertEquals(i, Utils.count(st.elements(j)));
                Assertions.assertEquals(j, Utils.count(st.attributes(i)));
                st.set(i, st.id(lb), value);
                Assertions.assertEquals(value, st.get(i, st.id(lb), String.class));
            }
        }

        for(long i = 1L; i <= 1000L; i++) {
            for(int j = 1; j <= 100; j++) {
                String lb = "Label" + j;
                String value = i + "-" + j;
                Assertions.assertEquals(value, st.get(i, st.id(lb), String.class));
                Assertions.assertEquals(1000 - i + 1, Utils.count(st.elements(j)));
                Assertions.assertEquals(100 - j + 1, Utils.count(st.attributes(i)));
                Assertions.assertTrue(st.has(i, j));
                st.remove(i, st.id(lb));
                Assertions.assertFalse(st.has(i, j));
                Assertions.assertEquals(1000 - i, Utils.count(st.elements(j)));
                Assertions.assertEquals(100 - j, Utils.count(st.attributes(i)));
                Assertions.assertNull(st.get(i, st.id(lb), String.class));
                st.remove(i, st.id(lb));
                Assertions.assertFalse(st.has(i, j));
                Assertions.assertEquals(1000 - i, Utils.count(st.elements(j)));
                Assertions.assertEquals(100 - j, Utils.count(st.attributes(i)));
                Assertions.assertNull(st.get(i, st.id(lb), String.class));
            }
        }
    }
}
