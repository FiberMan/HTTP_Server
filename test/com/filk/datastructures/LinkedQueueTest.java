package com.filk.datastructures;

import com.filk.datastructures.queue.LinkedQueue;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class LinkedQueueTest {

    @Test
    public void foreach() {
        LinkedQueue linkedQueue = new LinkedQueue();

        StringBuilder str = new StringBuilder();
        for (Object value : linkedQueue) {
            str.append(value);
        }
        assertEquals("", str.toString());

        linkedQueue.enqueue("A");
        linkedQueue.enqueue("B");
        linkedQueue.enqueue("C");

        str = new StringBuilder();
        for (Object value : linkedQueue) {
            str.append(value);
        }
        assertEquals("CBA", str.toString());

        str = new StringBuilder();
        for (Iterator iterator = linkedQueue.iterator(); iterator.hasNext(); ) {
            Object value = iterator.next();
            str.append(value);
        }
        assertEquals("CBA", str.toString());

        str = new StringBuilder();
        Iterator iterator = linkedQueue.iterator();
        while (iterator.hasNext()) {
            Object value = iterator.next();
            str.append(value);
        }
        assertEquals("CBA", str.toString());

    }
    @Test(expected = NoSuchElementException.class)
    public void foreach_exception() {
        LinkedQueue linkedQueue = new LinkedQueue();
        Iterator iterator = linkedQueue.iterator();
        Object value = iterator.next();
    }

    @Test
    public void enqueue() {
        LinkedQueue linkedQueue = new LinkedQueue();
        assertEquals(0, linkedQueue.size());
        assertNull(linkedQueue.head());
        linkedQueue.enqueue("A");
        assertEquals("A", linkedQueue.peek());
        assertEquals(1, linkedQueue.size());
        assertEquals("A", linkedQueue.headValue());
        linkedQueue.enqueue("B");
        assertEquals("A", linkedQueue.peek());
        assertEquals(2, linkedQueue.size());
        assertEquals("B", linkedQueue.headValue());
    }

    @Test
    public void dequeue() {
        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("B");
        assertEquals(2, linkedQueue.size());
        assertEquals("B", linkedQueue.headValue());
        assertEquals("A", linkedQueue.dequeue());
        assertEquals(1, linkedQueue.size());
        assertEquals("B", linkedQueue.headValue());
        assertEquals("B", linkedQueue.dequeue());
        assertEquals(0, linkedQueue.size());
        assertNull(linkedQueue.head());
        assertNull(linkedQueue.dequeue());
        assertNull(linkedQueue.peek());
    }

    @Test
    public void removeA() {
        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("B");
        linkedQueue.enqueue("B");
        linkedQueue.enqueue("C");
        linkedQueue.enqueue("C");
        assertEquals(6, linkedQueue.size());
        assertEquals("C", linkedQueue.headValue());
        linkedQueue.removeAll("A");
        assertEquals(4, linkedQueue.size());
        assertEquals("C", linkedQueue.headValue());
        assertEquals("B", linkedQueue.dequeue());
        assertEquals("C", linkedQueue.headValue());
        assertEquals("B", linkedQueue.dequeue());
        assertEquals(2, linkedQueue.size());
        assertEquals("C", linkedQueue.headValue());
        assertEquals("C", linkedQueue.dequeue());
        assertEquals("C", linkedQueue.dequeue());
        assertEquals(0, linkedQueue.size());
        assertNull(linkedQueue.head());
    }

    @Test
    public void removeB() {
        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("B");
        linkedQueue.enqueue("B");
        linkedQueue.enqueue("C");
        linkedQueue.enqueue("C");
        assertEquals(6, linkedQueue.size());
        assertEquals("C", linkedQueue.headValue());
        linkedQueue.removeAll("B");
        assertEquals(4, linkedQueue.size());
        assertEquals("C", linkedQueue.headValue());
        assertEquals("A", linkedQueue.dequeue());
        assertEquals("A", linkedQueue.dequeue());
        assertEquals(2, linkedQueue.size());
        assertEquals("C", linkedQueue.headValue());
        assertEquals("C", linkedQueue.dequeue());
        assertEquals("C", linkedQueue.dequeue());
        assertEquals(0, linkedQueue.size());
        assertNull(linkedQueue.head());
    }

    @Test
    public void removeC() {
        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("B");
        linkedQueue.enqueue("B");
        linkedQueue.enqueue("C");
        linkedQueue.enqueue("C");
        assertEquals(6, linkedQueue.size());
        assertEquals("C", linkedQueue.headValue());
        linkedQueue.removeAll("C");
        assertEquals(4, linkedQueue.size());
        assertEquals("B", linkedQueue.headValue());
        assertEquals("A", linkedQueue.dequeue());
        assertEquals("A", linkedQueue.dequeue());
        assertEquals(2, linkedQueue.size());
        assertEquals("B", linkedQueue.headValue());
        assertEquals("B", linkedQueue.dequeue());
        assertEquals("B", linkedQueue.dequeue());
        assertEquals(0, linkedQueue.size());
        assertNull(linkedQueue.head());
    }
}
