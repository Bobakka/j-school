package com.sbt.javaschool.rnd;

import com.sbt.javaschool.rnd.KlassenArbeiten.CacheProxy;
import com.sbt.javaschool.rnd.SerializeAnnotations.SerializeMethodCache;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class CacheProxyAnnotation implements CacheProxy {

    Object object;
    Map<Object, CacheProxyResult> cache = new HashMap<>();

    CacheProxyAnnotation(Object object) {
        cache.put(object, new CacheProxyResult());
    }

    public <T> T cache(T obj) {
        return (T)Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                new Class[] {obj.getClass()}, new CacheProxyAnnotation(obj));
    }


    private void init(Object object) {
        Map<Method, CacheProxySettings> cfg = new HashMap<>();
        for (Method m : object.getClass().getMethods()) {
            if (m.isAnnotationPresent(SerializeMethodCache.class)) {
                cfg.put(m, new CacheProxySettings(m.getAnnotation(SerializeMethodCache.class)));
            }
        }
    }


    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if (!cache.containsKey(o))
            return null;

        return cache.get(o).getResult(o, method, objects);
    }


}
