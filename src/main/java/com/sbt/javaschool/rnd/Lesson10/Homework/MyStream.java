package com.sbt.javaschool.rnd.Lesson10.Homework;

import java.util.*;
import java.util.function.*;


public class MyStream<T> {
    private Collection<T> collection;
    private List<Object> conveyer;
    private MyStream prev;

    public MyStream() {
        this.conveyer = new ArrayList<>();
    }

    public MyStream(Collection<T> in) {
        this.collection = new ArrayList<>(in);
        this.conveyer = new ArrayList<>();
    }

    public MyStream(Collection<T> in, List<Object> c) {
        this.collection = in;
        this.conveyer = c;
    }

    static public <T>  MyStream<T> of(Collection<T> input) {
        return new MyStream<T>(input);
    }

    public MyStream<T> filter(Predicate<? super T> f) {
        conveyer.add(f);
        return this;
    }

    public <R> MyStream<R> transform(Function<? super T, ? extends R> mapper) {
        conveyer.add(mapper);
        MyStream<R> s = new MyStream(this.collection, this.conveyer);
        s.prev = this;
        return s;
    }
//terminal
    public <K,U> Map<K,U> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper) {
        terminate();

        Map<K,U> map = new HashMap<>();
        for (T it : collection) {
            map.put(keyMapper.apply(it), valueMapper.apply(it));
        }
        return map;
    }

    private void terminate() {
        for (Object o : conveyer) {
            switch (Lambdas.fromClass(o.getClass().getInterfaces()[0])) {
                case ClassPredicate:
                    predicate(o);
                    break;
                case ClassFunction:
                    function(o);
                    break;
                default:
                    return;
            }
        }
    }

    private void predicate(Object o) {

        collection.removeIf(((Predicate) o));
    }
    private <R> void function(Object o) {
            List<T> tmp = new ArrayList<>();
        for (R value : (Iterable<R>) prev.collection) {
            tmp.add((T) ((Function) o).apply(value));
        }
        this.collection = tmp;
    }

    private enum Lambdas {
        ClassPredicate(Predicate.class),
        ClassFunction(Function.class),
        ClassConsumer(Consumer.class),
        ClassSupplier(Supplier.class),
        ClassUnknown(null)
        ;

        private final Class<?> targetClass;

        Lambdas(Class<?> targetClass) {
            this.targetClass = targetClass;
        }

        public static Lambdas fromClass(Class<?> clazz) {
            for (Lambdas l : values()) {
                if (l.targetClass == clazz)
                    return l;
            }
            return ClassUnknown;
        }
    }


}
