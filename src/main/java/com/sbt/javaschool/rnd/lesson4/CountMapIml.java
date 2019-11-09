package com.sbt.javaschool.rnd.lesson4;

import java.util.*;

public class CountMapIml<T> implements CountMap {

    private Map<T, Integer> counter = new HashMap<>();
    public void add(Object o) {
        counter.computeIfPresent((T)o, (key, value)->++value);
        counter.computeIfAbsent((T)o, key->1);
    }

    public int getCount(Object o) {
        return counter.getOrDefault(o, 0);
    }

    public void addAll(CountMap source) {
        source.toMap().forEach((k, v)->
                counter.merge((T)k, (Integer)v, Integer::sum));

    }

    public Map toMap() {
        return counter;
    }

    public void toMap(Map destination) {
        counter.forEach((k, v)->destination.put(k,v));
    }

    public int size() {
        return counter.size();
    }

    public int remove(Object o) {
        return counter.remove(o);
    }
}
