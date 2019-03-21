package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListAggregatorTest {
    private List<Integer> list;

    @Test
    public void sum() {

        ListAggregator aggregator = new ListAggregator(list);

        int sum = aggregator.sum();

        assertEquals(9, sum);
    }

    @Test
    public void max() {

        ListAggregator aggregator = new ListAggregator(list);

        int max = aggregator.max();

        assertEquals(4, max);
    }

    @Test
    public void min() {

        ListAggregator aggregator = new ListAggregator(list);

        int min = aggregator.min();

        assertEquals(1, min);
    }

    @Test
    public void distinct() {

        ListAggregator aggregator = new ListAggregator(list);

        class ListDeduplicatorStub implements IListDeduplicator {

            @Override
            public List<Integer> deduplicate(IListSorter iListSorter) {
                List<Integer> list1 = new ArrayList<>();
                list1.add(1);
                list1.add(2);
                list1.add(4);
                return list1;
            }

        }

        int distinct = aggregator.distinct(new ListDeduplicatorStub());

        assertEquals(3, distinct);
    }

    private List<Integer> createTestList(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);

        return list;
    }

    @Before
    public void createTestListBefore(){
        list = createTestList();
    }
}