package com.sbt.javaschool.rnd;

import com.sbt.javaschool.rnd.SerializeAnnotations.CacheType;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class CacheProxySettings implements Serializable {

    Map<Method, MethodSettings> settings = new HashMap<>();

    CacheProxySettings() {}

    boolean containsMethod(Method method) {
        return settings.containsKey(method);
    }

    MethodSettings annotations(Method method) {
        return settings.get(method);
    }

    void setMethodSettings(Method method
                       , Integer maxLengthList
                       , Class[] identityBy
                       , CacheType type
                       , String fileNamePrefix
                       , boolean zip) {
        settings.put(method, new MethodSettings(
                maxLengthList, identityBy, type, fileNamePrefix, zip));
    }

    private class MethodSettings implements Serializable {
        private Integer maxLengthList;
        private Class[] identityBy;
        private CacheType type;
        private String fileNamePrefix;
        private boolean zip;

        private MethodSettings() {

        }

        private MethodSettings(Integer maxLengthList, Class[] identityBy, CacheType type, String fileNamePrefix, boolean zip) {
            this.maxLengthList = maxLengthList;
            this.identityBy = identityBy;
            this.type = type;
            this.fileNamePrefix = fileNamePrefix;
            this.zip = zip;
        }


    }
}
