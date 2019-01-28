package com.filk.datastructures;

import com.filk.datastructures.list.List;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public abstract class AbstractListTest {

    protected abstract List getList();

    @Test
    public void foreach() {
        List list = getList();

        StringBuilder str = new StringBuilder();
        for (Object value : list) {
            str.append(value);
        }
        assertEquals("", str.toString());

        list.add("A");
        list.add("B");
        list.add("C");

        str = new StringBuilder();
        for (Object value : list) {
            str.append(value);
        }
        assertEquals("ABC", str.toString());

        str = new StringBuilder();
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
            Object value = iterator.next();
            str.append(value);
        }
        assertEquals("ABC", str.toString());

        str = new StringBuilder();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object value = iterator.next();
            str.append(value);
        }
        assertEquals("ABC", str.toString());

    }
    @Test(expected = NoSuchElementException.class)
    public void foreach_exception() {
        List list = getList();
        Iterator iterator = list.iterator();
        Object value = iterator.next();
    }

    @Test
    public void add() {
        int expected = 1;

        List list = getList();
        list.add(new String("bla"));

        int actual = list.size();

        assertEquals(expected, actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void add_checkBounds() {
        List list = getList();
        list.add(new String("Bla"), 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void remove_checkBounds() {
        List list = getList();
        list.add(new String("first"));
        list.remove(1);
    }

    @Test
    public void remove() {
        List list = getList();
        String expected = "first";

        list.add(expected);
        list.add(new String("second"));

        String actual = (String) list.remove(0);
        assertEquals(expected, actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_checkBounds() {
        List list = getList();
        list.add(new String("first"));
        list.get(1);
    }

    @Test
    public void get() {
        String expected = "second";
        List list = getList();
        list.add(new String("first"));
        list.add(new String("second"));
        list.add(new String("third"));

        String actual = (String) list.get(1);

        assertEquals(expected, actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void set_checkBounds() {
        List list = getList();
        list.set(new String("Bla"), 0);
    }

    @Test
    public void set() {
        String expected = "two";
        List list = getList();
        list.add(new String("first"));
        list.add(new String("second"));
        list.add(new String("third"));

        list.set(new String("two"), 1);
        String actual = (String) list.get(1);

        assertEquals(expected, actual);
    }

    @Test
    public void clear() {
        int expected = 0;
        List list = getList();
        list.add(new String("first"));
        list.add(new String("second"));
        list.add(new String("third"));

        list.clear();
        int actual = list.size();

        assertEquals(expected, actual);
    }

    @Test
    public void size() {
        int expected = 3;
        List list = getList();
        list.add(new String("first"));
        list.add(new String("second"));
        list.add(new String("third"));

        int actual = list.size();

        assertEquals(expected, actual);
    }

    @Test
    public void isEmpty_no() {
        boolean expected = false;
        List list = getList();
        list.add(new String("first"));
        list.add(new String("second"));
        list.add(new String("third"));

        boolean actual = list.isEmpty();

        assertEquals(expected, actual);
    }

    @Test
    public void isEmpty_yes() {
        boolean expected = true;
        List list = getList();

        boolean actual = list.isEmpty();

        assertEquals(expected, actual);
    }

    @Test
    public void contains() {
        boolean expected = true;
        List list = getList();
        list.add(new String("first"));
        list.add(new String("second"));
        list.add(new String("third"));

        boolean actual = list.contains(new String("second"));

        assertEquals(expected, actual);
    }

    @Test
    public void indexOf() {
        int expected = 2;
        List list = getList();
        list.add(new String("first"));
        list.add(new String("second"));
        list.add(new String("third"));
        list.add(new String("second"));
        list.add(new String("third"));

        int actual = list.indexOf(new String("third"));

        assertEquals(expected, actual);
    }

    @Test
    public void lastIndexOf() {
        int expected = 3;
        List list = getList();
        list.add(new String("first"));
        list.add(new String("second"));
        list.add(new String("third"));
        list.add(new String("second"));
        list.add(new String("third"));

        int actual = list.lastIndexOf(new String("second"));

        assertEquals(expected, actual);
    }
}