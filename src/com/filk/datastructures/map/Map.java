package com.filk.datastructures.map;

public interface Map extends Iterable {
    Object put(Object key, Object value);

    Object putIfAbsent(Object key, Object value);

    Object putAll(Map map);

    Object putAllIfAbsent(Map map);

    Object get(Object key);

    Object remove(Object key);

    boolean containsKey(Object key);

    int size();
}