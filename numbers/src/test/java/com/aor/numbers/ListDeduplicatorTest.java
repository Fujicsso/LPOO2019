package com.aor.numbers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListDeduplicatorTest {

    class ListSorterStub implements IListSorter {

        @Override
        public List<Integer> sort() {
            List<Integer> list1 = new ArrayList<>();
            list1.add(1);
            list1.add(2);
            list1.add(2);
            list1.add(4);
            return list1;
        }
    }

    @Test
    public void deduplicate(IListSorter iListSorter) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(4);

        ListDeduplicator deduplicator = new ListDeduplicator(list);
        List<Integer> distinct = deduplicator.deduplicate(iListSorter);

        assertEquals(expected, distinct);
    }
}