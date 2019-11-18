package com.sbt.javaschool.rnd.lesson6.KlassenArbeiten;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {
    private Map<Integer, Long> r = new HashMap<>();
    private Calculator calc = new CalculatorImpl();

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if (objects == null)
            return null;

        if (r.containsKey((Integer)objects[0])) {
            System.out.println("from cache");
            return r.get((Integer)objects[0]);
        }

        r.put((Integer)objects[0], (Long)method.invoke(calc, objects));
        System.out.println("calculated");
        return r.get((Integer)objects[0]);
    }
}
