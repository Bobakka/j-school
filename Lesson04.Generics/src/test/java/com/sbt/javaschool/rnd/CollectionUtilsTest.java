package com.sbt.javaschool.rnd;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilsTest {

    @Test
    void testAddAll() {
        List<Integer> src = new ArrayList<>(Arrays.asList(1,2,3,4));
        List<Integer> dst = new ArrayList<>(Collections.singletonList(10));

        CollectionUtils.addAll(src, dst);

        assertEquals(dst.size(), 5);
        assertEquals(dst, Arrays.asList(10,1,2,3,4));
    }

    @Test
    void testNewArrayList() {
        List<String> expect = CollectionUtils.newArrayList();

        assertNotNull(expect);
    }

    @Test
    void testIndexOf() {
        List<Integer> test = new ArrayList<>(Arrays.asList(1,2,3,45,6,7));
        int idx = CollectionUtils.indexOf(test, 3);

        assertEquals(idx, 2);
    }

    @Test
    void testLimit() {
        List<Integer> actual = new ArrayList<>(Arrays.asList(1,2,3));
        List<Integer> test = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
        List<Integer> expected =  CollectionUtils.limit(test, 3);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test
    void testAdd() {
        List<String> expected = new ArrayList<>();
        CollectionUtils.add(expected, "some value");
        CollectionUtils.add(expected, "bzzzz");

        assertEquals(expected, Arrays.asList("some value", "bzzzz"));
    }

    @Test
    void testRemoveAll() {
        List<String> test1 = new ArrayList<>(Arrays.asList("1","2", "3"));
        List<String> test2 = new ArrayList<>(Arrays.asList("2", "3"));

        CollectionUtils.removeAll(test1, test2);

        assertEquals(test1, Collections.singletonList("1"));
    }

    @Test
    void testContainsAll() {
        List<String> test1 = new ArrayList<>(Arrays.asList("1","2", "3", "4"));
        List<String> test2 = new ArrayList<>(Arrays.asList("2", "3"));
        List<String> test3 = new ArrayList<>(Arrays.asList("2","4", "3", "1"));
        List<String> test4 = new ArrayList<>(Arrays.asList("2","4", "3", "1", "6"));

        assertFalse(CollectionUtils.containsAll(test1, test2));
        assertTrue(CollectionUtils.containsAll(test1, test3));
        assertTrue(CollectionUtils.containsAll(test1, test4));
    }

    @Test
    void testContainsAny() {
        List<String> test1 = new ArrayList<>(Arrays.asList("1","2", "3", "4"));
        List<String> test2 = new ArrayList<>(Arrays.asList("7", "8","9"));
        List<String> test3 = new ArrayList<>(Arrays.asList("2","4", "13", "11"));

        assertTrue(CollectionUtils.containsAny(test1, test3));
        assertFalse(CollectionUtils.containsAny(test1, test2));
    }

    @Test
    void testRange() {
        assertEquals(
                CollectionUtils.range(
                        Arrays.asList(1,2,3,4,5,6,7),
                        3,
                        6
            ),
                Arrays.asList(3,4,5,6)
        );
    }

    @Test
    void testRangeComparator() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        assertEquals(
                CollectionUtils.range(
                        Arrays.asList(1,2,3,4,5,6,7),
                        3,
                        6,
                        comparator
                ),
                Arrays.asList(3,4,5,6)
        );


    }
}