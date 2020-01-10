package com.sbt.javaschool.rnd.KlassenArbeiten;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class CacheProxySimple implements CacheProxy {
    private Map<Integer, Long> r = new HashMap<>();
    private Calculator calc = new CalculatorImpl();

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if (objects == null)
            return null;

        if (r.containsKey(objects[0])) {
            System.out.println("from cache");
            return r.get(objects[0]);
        }

        r.put((Integer)objects[0], (Long)method.invoke(calc, objects));
        System.out.println("calculated");
        return r.get(objects[0]);
    }

    @Override
    public <T> T cache(T obj) {
        return (T) Proxy.newProxyInstance(CalculatorImpl.class.getClassLoader(),
                new Class[]{Calculator.class},
                new CacheProxySimple());
    }
}
