package com.sbt.javaschool.rnd.lesson9;

import com.sbt.javaschool.rnd.lesson9.SerializeAnnotations.CacheType;
import com.sbt.javaschool.rnd.lesson9.SerializeAnnotations.SerializeMethodCache;

import java.util.Date;
import java.util.List;

public interface Service {

    @SerializeMethodCache(cacheType = CacheType.MEMORY, fileNamePrefix = "data")
    List<String> run(String item, double value, Date date);
}
