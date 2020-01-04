package com.sbt.javaschool.rnd;

import java.util.Map;

public interface CountMap<T> {
    void add( T o);
    int getCount(Object o);
    void addAll(CountMap<T> source);
    Map<T, Integer> toMap();
    void toMap(Map<? super T, Integer> destination);
    int size();
    int remove(T o);
}
