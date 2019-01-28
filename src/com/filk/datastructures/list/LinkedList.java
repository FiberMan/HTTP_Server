package com.filk.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList implements List {
    private int size;
    private Node first;
    private Node last;

    private void checkBounds(int index, int from, int to) {
        if (index < from || index > to) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node getNode(int index) {
        checkBounds(index, 0, this.size - 1);

        Node node;

        if (index <= (int) this.size / 2) {
            // go forward
            node = this.first;
            for (int i = 0; i < this.size; i++) {
                if (i == index) break;
                node = node.next;
            }
        } else {
            // go backward
            node = this.last;
            for (int i = this.size - 1; i >= 0; i--) {
                if (i == index) break;
                node = node.prev;
            }
        }
        return node;
    }

    @Override
    public void add(Object value) {
        add(value, this.size);
    }

    @Override
    public void add(Object value, int index) {
        checkBounds(index, 0, this.size);

        Node newNode = new Node(value);

        if (this.size == 0) {
            this.first = newNode;
            this.last = newNode;
        } else if (index == 0) {
            newNode.next = this.first;
            this.first.prev = newNode;
            this.first = newNode;
        } else if (index == this.size) {
            newNode.prev = this.last;
            this.last.next = newNode;
            this.last = newNode;
        } else {
            Node currentNode = getNode(index);
            currentNode.prev.next = newNode;
            currentNode.prev = newNode;
        }

        this.size++;
    }

    @Override
    public Object remove(int index) {
        Node removedNode = getNode(index);

        if (this.size == 1) {
            this.first = null;
            this.last = null;
        } else if (index == 0) {
            removedNode.next.prev = null;
            this.first = removedNode.next;
        } else if (index == this.size - 1) {
            removedNode.prev.next = null;
            this.last = removedNode.prev;
        } else {
            removedNode.next.prev = removedNode.prev;
            removedNode.prev.next = removedNode.next;
        }

        this.size--;
        return removedNode.value;
    }

    @Override
    public Object get(int index) {
        return getNode(index).value;
    }

    @Override
    public Object set(Object value, int index) {
        Node updatedNode = getNode(index);
        Object updatedValue = updatedNode.value;
        updatedNode.value = value;

        return updatedValue;
    }

    @Override
    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(Object value) {
        Node currentNode = this.first;
        for (int i = 0; i < this.size; i++) {
            if (currentNode.value.equals(value))
                return i;
            currentNode = currentNode.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        Node currentNode = this.last;
        for (int i = this.size - 1; i >= 0; i--) {
            if (currentNode.value.equals(value))
                return i;
            currentNode = currentNode.prev;
        }
        return -1;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator {
        Node currentElement = first;

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
                throw new NoSuchElementException("Linked List has no more elements!");
            }
            Node returnedNode = currentElement;
            currentElement = currentElement.next;
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
        Node next;

        Node(Object value) {
            this.value = value;
        }
    }
}
