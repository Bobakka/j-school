package com.sbt.javaschool.rnd;

import com.sbt.javaschool.rnd.SerializeAnnotations.CacheType;
import com.sbt.javaschool.rnd.SerializeAnnotations.SerializeMethodCache;

import java.util.Date;
import java.util.List;

public interface Service {

    @SerializeMethodCache(cacheType = CacheType.MEMORY, fileNamePrefix = "data")
    List<String> run(String item, double value, Date date);
}
