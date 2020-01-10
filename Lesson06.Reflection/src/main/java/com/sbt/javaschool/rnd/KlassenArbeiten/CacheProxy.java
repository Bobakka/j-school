package com.sbt.javaschool.rnd.KlassenArbeiten;

import java.lang.reflect.InvocationHandler;

public interface CacheProxy extends InvocationHandler {
    <T> T cache(T obj);
}
