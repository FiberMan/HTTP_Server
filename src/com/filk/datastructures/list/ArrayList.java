package com.filk.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList implements List {
    private Object[] array;
    private int size;

    public static void main(String[] args) {
        List list = new ArrayList();

//        list.add("A");
//        list.add("B");
//        list.add("C");

        for (Object value: list ) {
            System.out.println(value);
        }

        for (Object value: list ) {
            System.out.println("2: " + value);
        }
    }

    private void extend() {
        if (array == null || array.length == size) {
            Object[] newArray = new Object[(int) (size * 1.5) + 1];
            for (int i = 0; i < size; i++)
                newArray[i] = array[i];
            array = newArray;
        }
    }

    // in case of end of space, please grow in 1.5 times
    @Override
    public void add(Object value) {
        extend();
        array[size] = value;
        size++;
    }

    @Override
    public void add(Object value, int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        extend();
        Object[] newArray = new Object[array.length];
        for (int i = 0; i <= size; i++) {
            if (i == index)
                newArray[i] = value;
            else if (i > index)
                newArray[i] = array[i - 1];
            else
                newArray[i] = array[i];
        }
        array = newArray;
        size++;
    }

    @Override
    public Object remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Object removedObject = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        return removedObject;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        return array[index];
    }

    @Override
    public Object set(Object value, int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Object oldObject = array[index];
        array[index] = value;
        return oldObject;
    }

    @Override
    public void clear() {
        array = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object value) {
        for (int i = 0; i < size; i++) {
            if(array[i].toString().equals(value.toString()))
                return true;
        }
        return false;
    }

    @Override
    public int indexOf(Object value) {
        for (int i = 0; i < size; i++) {
            if(array[i].toString().equals(value.toString()))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        for (int i = size -1; i >=0; i--) {
            if(array[i].toString().equals(value.toString()))
                return i;
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
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator {
        int currentIndex = -1;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return size > currentIndex + 1;
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
                throw new NoSuchElementException("Array has no more elements!");
            }

            currentIndex += 1;
            return array[currentIndex];
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
}