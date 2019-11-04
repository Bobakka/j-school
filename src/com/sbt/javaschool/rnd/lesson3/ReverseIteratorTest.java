package com.sbt.javaschool.rnd.lesson3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ReverseIteratorTest {

    @Test
    void testIterator() {
        ArrayList<String> in = new ArrayList<>(
                Arrays.asList("1", "2", "3", "4", "5", "6", "7")
        );

        ReverseIterator<String> it = new ReverseIterator<>(in);
        ArrayList<String> result = new ArrayList<>();
        while(it.hasNext()) {
            result.add(it.next());
        }
        Collections.reverse(in);
        assertLinesMatch(result, in);
    }

    @Test
    void testNullIterator() {
        ArrayList<Integer> in = new ArrayList<>();

        assertThrows(NullPointerException.class, ()->new ReverseIterator<>(in));
    }
}