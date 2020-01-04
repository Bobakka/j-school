package com.sbt.javaschool.rnd.SerializeAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface SerializeMethodCache {
    CacheType cacheType() default CacheType.MEMORY;
    String fileNamePrefix() default "";
    boolean zip() default false;
    Class[] identityBy() default {};
    int lengthList() default  10_000;
}
