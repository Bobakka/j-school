package com.sbt.javaschool.rnd;

import java.util.*;

public class CountMapIml<T> implements CountMap<T> {

    private Map<T, Integer> counter = new HashMap<>();
    public void add(T o) {
        counter.computeIfPresent(o, (key, value)->++value);
        counter.putIfAbsent(o, 1);
    }

    public int getCount(Object o) {
        return counter.getOrDefault(o, 0);
    }

    public void addAll(CountMap<T> source) {
        source.toMap().forEach((k, v)->
                counter.merge(k, v, Integer::sum));

    }

    public Map<T, Integer> toMap() {
        return counter;
    }

    public void toMap(Map<? super T, Integer> destination) {
//        destination = counter;
        counter.forEach(destination::put);
    }

    public int size() {
        return counter.size();
    }

    public int remove(T o) {
        return counter.remove(o);
    }
}
