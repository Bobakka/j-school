package com.sbt.javaschool.rnd.lesson4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtils {

    public static<T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }


    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static<T> List<T> limit(List<? extends T> source, int size) {
        return source.stream().limit(size).collect(Collectors.toList());
    }

    public static<T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static<T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static<T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return c1.parallelStream().allMatch(c2::contains);
    }

    public static<T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        return c1.parallelStream().anyMatch(c2::contains);
    }

    public static<T extends Comparable<T>> List<? super T> range(List<T> list, T min, T max) {
        return list.parallelStream().filter(t -> (t.compareTo(min) >= 0 )
                && (t.compareTo(max) <= 0)).collect(Collectors.toList());
    }

    public static <T extends Comparable<T>>
    List<? super T> range(List<? extends T> list, T min, T max, Comparator<T> comparator) {
        return list.parallelStream().filter(t -> (comparator.compare(t, min) >= 0)
                && (comparator.compare(t, max) <= 0)).collect(Collectors.toList());
    }

}
