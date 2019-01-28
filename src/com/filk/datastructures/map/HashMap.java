package com.filk.datastructures.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMap implements Map {
    private ArrayList[] buckets;
    private static final double LOAD_FACTOR = 0.75;
    private static final int INITIAL_BUCKET_LENGTH = 5;

    public HashMap() {
        this(INITIAL_BUCKET_LENGTH);
    }

    public HashMap(int newBucketLength) {
        this.buckets = new ArrayList[newBucketLength];
        for (int i = 0; i < this.buckets.length; i++) {
            buckets[i] = new ArrayList();
        }
    }

    @Override
    public Object put(Object key, Object value) {
        int bucketIndex = getIndex(key);

        for (Object entry : buckets[bucketIndex]) {
            Entry currentEntry = (Entry) entry;
            if (equalsWithNulls(currentEntry.key, key)) {
                Object updatedValue = currentEntry.value;
                currentEntry.value = value;
                return updatedValue;
            }
        }

        Entry entry = new Entry(key, value);
        buckets[bucketIndex].add(entry);

        rebalanceCheck();
        return null;
    }

    @Override
    public Object putIfAbsent(Object key, Object value) {
        Entry oldEntry = getEntry(key);
        if (oldEntry == null) {
            Entry newEntry = new Entry(key, value);
            buckets[getIndex(key)].add(newEntry);
            rebalanceCheck();
            return null;
        } else {
            return oldEntry.value;
        }
    }

    @Override
    public Object putAll(Map map) {
        for (Object entry : map) {
            Entry currentEntry = (Entry) entry;
            put(currentEntry.key, currentEntry.value);
        }
        return buckets;
    }

    @Override
    public Object putAllIfAbsent(Map map) {
        for (Object entry : map) {
            Entry currentEntry = (Entry) entry;
            putIfAbsent(currentEntry.key, currentEntry.value);
        }
        return buckets;
    }

    private Entry getEntry(Object key) {
        for (Object entry : buckets[getIndex(key)]) {
            Entry currentEntry = (Entry) entry;
            if (equalsWithNulls(currentEntry.key, key)) {
                return currentEntry;
            }
        }
        return null;
    }

    @Override
    public Object get(Object key) {
        return getEntry(key).value;
    }

    @Override
    public Object remove(Object key) {
        int bucketIndex = getIndex(key);
        for (Object entry : buckets[bucketIndex]) {
            Entry currentEntry = (Entry) entry;
            if (equalsWithNulls(currentEntry.key, key)) {
                Object deletedValue = currentEntry.value;
                buckets[bucketIndex].remove(entry);
                return deletedValue;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        return getEntry(key) != null;
    }

    @Override
    public int size() {
        int size = 0;
        for (int i = 0; i < buckets.length; i++) {
            size += buckets[i].size();
        }
        return size;
    }

    @Override
    public Iterator iterator() {
        return new HashMapIterator();
    }

    public int getBucketLength() {
        return buckets.length;
    }

    private int getIndex(Object key) {
        return (key == null) ? 0 : key.hashCode() % buckets.length;
    }

    private static boolean equalsWithNulls(Object a, Object b) {
        if (a == b) return true;
        if ((a == null) || (b == null)) return false;
        return a.equals(b);
    }

    private void rebalanceCheck() {
        if(size() > LOAD_FACTOR * buckets.length) {
            HashMap newHashMap = new HashMap(buckets.length * 2);
            buckets = (ArrayList[]) newHashMap.putAll(this);
        }
    }


    private class HashMapIterator implements Iterator {
        int currentBucketID = 0;
        int currentEntityID = -1;
        int entityCounter = 0;
        int size = size();
        boolean canBeRemoved = false;

        @Override
        public boolean hasNext() {
            return size > entityCounter;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException("HashMap has no more elements!");
            }

            while (buckets[currentBucketID].size() == currentEntityID + 1) {
                currentBucketID++;
                currentEntityID = -1;
            }

            currentEntityID++;
            entityCounter++;
            canBeRemoved = true;
            return buckets[currentBucketID].get(currentEntityID);
        }

        @Override
        public void remove() {
            if(canBeRemoved) {
                buckets[currentBucketID].remove(currentEntityID);
                currentEntityID--;
                canBeRemoved = false;
            }
        }
    }

    private static class Entry {
        Object key;
        Object value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "<" + key + ":" + value + ">";
        }
    }
}
