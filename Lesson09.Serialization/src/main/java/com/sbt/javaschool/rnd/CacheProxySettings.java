package com.sbt.javaschool.rnd;

import com.sbt.javaschool.rnd.SerializeAnnotations.CacheType;
import com.sbt.javaschool.rnd.SerializeAnnotations.SerializeMethodCache;

import java.io.Serializable;


public class CacheProxySettings implements Serializable {

    private final int maxLengthList;
    private final Class[] identityBy;
    private final CacheType type;
    private final String fileNamePrefix;
    private final boolean zip;

    CacheProxySettings(SerializeMethodCache annotation) {
        this.maxLengthList = annotation.lengthList();
        this.identityBy = annotation.identityBy();
        this.type = annotation.cacheType();
        this.fileNamePrefix = annotation.fileNamePrefix();
        this.zip = annotation.zip();
    }

    public int getMaxLengthList() {
        return maxLengthList;
    }

    public Class[] getIdentityBy() {
        return identityBy;
    }

    public CacheType getType() {
        return type;
    }

    public String getFileNamePrefix() {
        return fileNamePrefix;
    }

    public boolean isZip() {
        return zip;
    }
}
