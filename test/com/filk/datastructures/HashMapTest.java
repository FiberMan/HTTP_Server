package com.filk.datastructures;

import com.filk.datastructures.map.HashMap;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class HashMapTest {
    @Test
    public void put_get_size() {
        HashMap hashMap = new HashMap(5);

        assertEquals(0, hashMap.size());
        assertEquals(null, hashMap.put("A", "10"));
        assertEquals(null, hashMap.put("B", "20"));
        assertEquals(null, hashMap.put("C", "30"));
        assertEquals("10", hashMap.put("A", "40"));
        assertEquals(null, hashMap.put("D", "50"));
        assertEquals("30", hashMap.put("C", "60"));
        assertEquals("40", hashMap.put("A", "70"));
        assertEquals(null, hashMap.put(null, null));
        assertEquals(5, hashMap.size());

        assertEquals("70", hashMap.get("A"));
        assertEquals("20", hashMap.get("B"));
        assertEquals("60", hashMap.get("C"));
        assertEquals(null, hashMap.get(null));

        assertEquals(null, hashMap.put(null, "null"));
        assertEquals("null", hashMap.get(null));
    }

    @Test
    public void putIfAbsent() {
        HashMap hashMap = new HashMap(5);

        hashMap.put("A", "10");
        hashMap.put("B", "30");
        assertEquals("30", hashMap.putIfAbsent("B", "40"));
        assertEquals(null, hashMap.putIfAbsent("C", "50"));
        assertEquals("50", hashMap.putIfAbsent("C", "60"));

        assertEquals("30", hashMap.get("B"));
        assertEquals("50", hashMap.get("C"));
    }

    @Test
    public void remove() {
        HashMap hashMap = new HashMap(5);

        hashMap.put("A", "10");
        hashMap.put("B", "20");
        hashMap.put("C", "30");
        hashMap.put("D", "40");
        assertEquals("20", hashMap.remove("B"));
        assertEquals(null, hashMap.remove("B"));
        assertEquals("10", hashMap.remove("A"));
        assertEquals("30", hashMap.remove("C"));
        assertEquals(1, hashMap.size());

    }

    @Test
    public void containsKey() {
        HashMap hashMap = new HashMap(5);

        hashMap.put("A", "10");
        hashMap.put("B", "30");

        assertTrue(hashMap.containsKey("A"));
        assertFalse(hashMap.containsKey("C"));
        assertFalse(hashMap.containsKey(null));

        hashMap.put(null, "20");
        assertTrue(hashMap.containsKey(null));
    }

    @Test
    public void foreach() {
        HashMap hashMap = new HashMap(5);

        StringBuilder str = new StringBuilder();
        for (Object value : hashMap) {
            str.append(value);
        }
        assertEquals("", str.toString());

        hashMap.put("A", "10");
        hashMap.put("B", "20");
        hashMap.put("C", "30");

        str = new StringBuilder();
        for (Object value : hashMap) {
            str.append(value);
        }
        assertEquals("<A:10><B:20><C:30>", str.toString());

        str = new StringBuilder();
        for (Iterator iterator = hashMap.iterator(); iterator.hasNext(); ) {
            Object value = iterator.next();
            str.append(value);
        }
        assertEquals("<A:10><B:20><C:30>", str.toString());

        str = new StringBuilder();
        Iterator iterator = hashMap.iterator();
        while (iterator.hasNext()) {
            Object value = iterator.next();
            str.append(value);
        }
        assertEquals("<A:10><B:20><C:30>", str.toString());

        Iterator iterator2 = hashMap.iterator();
        assertEquals("<A:10>", iterator2.next().toString());
        assertEquals("<B:20>", iterator2.next().toString());
        iterator2.remove();
        iterator2.remove();
        iterator2.remove();
        assertEquals("<C:30>", iterator2.next().toString());

        str = new StringBuilder();
        for (Object value : hashMap) {
            str.append(value);
        }
        assertEquals("<A:10><C:30>", str.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void foreach_exception() {
        HashMap hashMap = new HashMap(5);
        Iterator iterator = hashMap.iterator();
        Object value = iterator.next();
    }

    @Test
    public void rebalance() {
        HashMap hashMap = new HashMap(3);

        assertEquals(3, hashMap.getBucketLength());
        hashMap.put("A", "10");
        hashMap.put("B", "20");
        hashMap.put("C", "30");
        hashMap.put("D", "40");
        assertEquals(6, hashMap.getBucketLength());
        hashMap.put("E", "50");
        assertEquals(12, hashMap.getBucketLength());
    }

    @Test
    public void putAll() {
        HashMap hashMap1 = new HashMap(3);
        HashMap hashMap2 = new HashMap(3);

        hashMap1.put("A", "10");
        hashMap1.put("B", "20");
        hashMap1.put("C", "30");

        hashMap2.put("D", "40");
        hashMap2.put("E", "50");
        hashMap2.put("A", "60");

        hashMap1.putAll(hashMap2);

        assertEquals(12, hashMap1.getBucketLength());
        assertEquals(6, hashMap2.getBucketLength());
        assertEquals(5, hashMap1.size());
        assertEquals(3, hashMap2.size());
        assertEquals("60", hashMap1.get("A"));
        assertEquals("20", hashMap1.get("B"));
        assertEquals("40", hashMap1.get("D"));
    }

    @Test
    public void putAllIfAbsent() {
        HashMap hashMap1 = new HashMap(3);
        HashMap hashMap2 = new HashMap(3);

        hashMap1.put("A", "10");
        hashMap1.put("B", "20");
        hashMap1.put("C", "30");

        hashMap2.put("D", "40");
        hashMap2.put("E", "50");
        hashMap2.put("A", "60");

        hashMap1.putAllIfAbsent(hashMap2);

        assertEquals(12, hashMap1.getBucketLength());
        assertEquals(6, hashMap2.getBucketLength());
        assertEquals(5, hashMap1.size());
        assertEquals(3, hashMap2.size());
        assertEquals("10", hashMap1.get("A"));
        assertEquals("20", hashMap1.get("B"));
        assertEquals("40", hashMap1.get("D"));
    }
}
