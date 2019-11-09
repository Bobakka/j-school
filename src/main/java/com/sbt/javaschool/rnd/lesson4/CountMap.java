package com.sbt.javaschool.rnd.lesson4;

import java.util.Map;

public interface CountMap<T> {
    void add( Object o);
    int getCount(Object o);
    void addAll(CountMap source);
    Map toMap();
    void toMap(Map destination);
    int size();
    int remove(Object o);
}
