package com.sbt.javaschool.rnd.Lesson10.KlassenArbeiten;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.function.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    private static Logger logger = Logger.getLogger("log1");

    public  static interface SomeClass {
        int getValue();

        void doSmth(Predicate<Integer> validator);
    }
    public static class SomeClassImpl implements SomeClass {
        private static Logger logger = Logger.getLogger("log");
        private int value;

        public SomeClassImpl(int value) {
            this.value = value;
        }

        public void doSmth(Predicate<Integer> validator) {
            if (validator.test(value)) {
                logger.info("Ok");
            } else {
                logger.info("Error");
            }
        }

        public int getValue() {
            return value;
        }

    }

    @FunctionalInterface
    public interface Validator {
        boolean test(int value);

        default Validator not() {
            return value -> !test(value);
        }
    }

    @org.junit.jupiter.api.Test
    public void test() {
        Predicate<Integer> greaterThenTen = integer -> integer > 10;

        new SomeClassImpl(1).doSmth(greaterThenTen.and(integer -> integer < 100).negate());


        Supplier<Integer> supplier = () -> 10;
//is equals
        Supplier<Integer> supplier2 = () -> {
            return 10;
        };

        Consumer<Integer> consumer = integer -> System.out.println("input param " + integer);
        consumer.andThen(integer -> System.out.println("input param " + integer)).accept(100);

        Function<Integer, String> function = integer -> "1";

        System.out.println( function.apply(10));

        BiConsumer<String, Integer> biConsumer = (s, integer) -> System.out.println(s + integer);

        SomeClass someClass = new SomeClassImpl(1);

        Supplier<Integer> suplier2 = someClass::getValue;

        Function<Integer, SomeClass> creator = SomeClassImpl::new;
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        Integer integer = null;

        if (integer != null) {

        }

        if (Optional.ofNullable(integer).isPresent()) {

        }

        Optional.ofNullable(integer).ifPresent(p -> System.out.println(p));
    }

    @org.junit.jupiter.api.Test
    public void test3() {

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Optional<Integer> result = Stream
                .iterate(1, i -> ++i)
                .limit(10000)
                .collect(Collectors.reducing(Integer::sum));
//                .forEach(integer -> System.out.println(integer));
        System.out.println(new Timestamp(System.currentTimeMillis()).getNanos() - ts.getNanos());

        Timestamp ts2 = new Timestamp(System.currentTimeMillis());
        Optional<Integer> result2 = Stream
                .iterate(1, i -> ++i)
                .parallel()
                .limit(10000)
                .collect(Collectors.reducing(Integer::sum));
//                .forEach(integer -> System.out.println(integer));
        System.out.println(new Timestamp(System.currentTimeMillis()).getNanos() - ts2.getNanos());


        Stream.of(1,2,3)
                .filter(integer -> integer > 1)
                .map(integer -> String.valueOf(integer))
                .forEach(v -> System.out.println(v.getClass()));
    }
}
