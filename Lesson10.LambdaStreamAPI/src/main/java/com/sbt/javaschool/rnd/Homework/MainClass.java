package com.sbt.javaschool.rnd.Homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainClass {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++)
            list.add(i);
        Map<?,?> map = MyStream.of(list)
                .filter(i -> i > 5)
                .transform(i -> String.valueOf(i))
                .toMap(k->k, v -> v.getClass());

        System.out.println(list);
        System.out.println(list.size());
        System.out.println(map);
    }
}
