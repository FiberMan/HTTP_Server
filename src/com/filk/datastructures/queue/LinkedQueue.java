package com.filk.datastructures.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue implements Iterable {
    private Node head;
    private int size;

    public Node head() {
        return this.head;
    }

    public Object headValue() {
        return this.head.value;
    }

    public int size() {
        return this.size;
    }

    public void enqueue(Object value) {
        Node node = new Node(value);
        node.prev = this.head;
        this.head = node;
        size++;
    }

    public Object dequeue() {
        if (this.head == null) {
            return null;
            //throw new Exception("Queue is empty.");
        }

        Node currentNode = this.head;
        Node removedNode;
        while (true) {
            if (currentNode.prev == null) {
                removedNode = currentNode;
                break;
            }
            if (currentNode.prev.prev == null) {
                removedNode = currentNode.prev;
                currentNode.prev = null;
                break;
            }
            currentNode = currentNode.prev;
        }

        if(this.size == 1) {
            this.head = null;
        }
        this.size--;
        return removedNode.value;

    }

    public Object peek() {
        Node currentNode = this.head;
        while (currentNode != null) {
            if (currentNode.prev == null) {
                return currentNode.value;
            }
            currentNode = currentNode.prev;
        }

        return null;
    }

    public void removeAll(Object value) {
        Node currentNode = this.head;
        Node nextNode = null;
        while (currentNode != null) {
            if (currentNode.value.equals(value)) {
                if (this.head == currentNode) {
                    this.head = currentNode.prev;
                }
                if (nextNode != null) {
                    nextNode.prev = currentNode.prev;
                }
                this.size--;
            } else {
                nextNode = currentNode;
            }
            currentNode = currentNode.prev;
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator {
        Node currentElement = head;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return currentElement != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Object next() {
            if(!hasNext()) {
                throw new NoSuchElementException("Linked Queue has no more elements!");
            }
            Node returnedNode = currentElement;
            currentElement = currentElement.prev;
            return returnedNode.value;
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to {@link #next}.
         * <p>
         * The behavior of an iterator is unspecified if the underlying collection
         * is modified while the iteration is in progress in any way other than by
         * calling this method, unless an overriding class has specified a
         * concurrent modification policy.
         * <p>
         * The behavior of an iterator is unspecified if this method is called
         * after a call to the {@link #forEachRemaining forEachRemaining} method.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *                                       operation is not supported by this iterator
         * @throws IllegalStateException         if the {@code next} method has not
         *                                       yet been called, or the {@code remove} method has already
         *                                       been called after the last call to the {@code next}
         *                                       method
         * @implSpec The default implementation throws an instance of
         * {@link UnsupportedOperationException} and performs no other action.
         */
//        @Override
//        public void remove() {
//
//        }
    }

    private static class Node {
        Object value;
        Node prev;

        Node(Object value) {
            this.value = value;
        }
    }
}