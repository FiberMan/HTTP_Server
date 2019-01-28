package com.filk.datastructures;

import com.filk.datastructures.list.LinkedList;
import com.filk.datastructures.list.List;

public class LinkedListTest extends AbstractListTest {
    @Override
    protected List getList() {
        return new LinkedList();
    }
}
