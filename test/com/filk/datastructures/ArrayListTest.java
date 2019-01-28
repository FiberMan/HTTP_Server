package com.filk.datastructures;

import com.filk.datastructures.list.ArrayList;
import com.filk.datastructures.list.List;

public class ArrayListTest extends AbstractListTest {
    @Override
    protected List getList() {
        return new ArrayList();
    }
}
