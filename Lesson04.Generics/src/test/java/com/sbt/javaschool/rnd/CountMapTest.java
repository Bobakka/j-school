package com.sbt.javaschool.rnd;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountMapTest {

    @Test
    void testReturnMap(){
        CountMapIml<Object> test = new CountMapIml<>();
        List<String> keys = new ArrayList<>(
                Arrays.asList("One", "Two", "Three", "Four"));

        for (String s :keys ) {
            test.add(s);
        }

        Map returnTest = test.toMap();

        assertEquals(returnTest.size(), test.size());

    }

    @Test
    void testGetCountNoObject() {
        CountMapIml test = new CountMapIml<>();
        test.add(1);
        test.add(20);

        assertEquals(test.getCount(3), 0);
    }

    @Test
    void testAdd(){
        CountMapIml test = new CountMapIml<>();

        test.add(1);

        assertEquals(test.size(), 1);
        assertEquals(test.getCount(1), 1);

        test.add(1);

        assertEquals(test.size(), 1);
        assertEquals(test.getCount(1), 2);

        test.add(2);

        assertEquals(test.size(), 2);
        assertEquals(test.getCount(1), 2);
        assertEquals(test.getCount(2), 1);
    }

    @Test
    void testGetCountOneObject() {
        CountMapIml test = new CountMapIml<>();

        for (int i = 0; i < 10; ++i) {
            assertEquals(test.getCount(1), i);
            test.add(1);
        }
    }

    @Test
    void testSize() {
        CountMapIml test = new CountMapIml<>();

        for (int i = 0; i < 10; ++i) {
            assertEquals(test.size(), i);
            test.add(i);
        }
    }

    @Test
    void testAddAll() {
        CountMapIml testDst = new CountMapIml<>();
        CountMapIml testSrc = new CountMapIml<>();

        List<String> keysDst = new ArrayList<>(
                Arrays.asList("One", "Two", "Three", "Four"));

        List<String> keysSrc = new ArrayList<>(
                Arrays.asList("Three", "Four", "Five", "Six"));

        Map<String, Integer> expected = new HashMap<>();

        expected.put("One", 1);
        expected.put("Two", 1);
        expected.put("Three", 2);
        expected.put("Four", 2);
        expected.put("Five", 1);
        expected.put("Six", 1);

        for (String s :keysDst ) {
            testDst.add(s);
        }
        for (String s : keysSrc) {
            testSrc.add(s);
        }

        testDst.addAll(testSrc);

        assertEquals(testDst.size(), 6);

        var eq = new HashSet<String>(keysDst);
        eq.addAll(keysSrc);

        assertEquals(testDst.toMap(), expected);
    }

    @Test
    void testToOtherMap() {
        CountMapIml test = new CountMapIml<>();

        test.add(1);
        test.add(2);
        test.add(1);
        Map<Integer, Integer> actual = new HashMap<>();

        test.toMap(actual);

        assertEquals(test.toMap(), actual);

    }
}