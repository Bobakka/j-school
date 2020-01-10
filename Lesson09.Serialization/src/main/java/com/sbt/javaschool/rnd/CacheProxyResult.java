package com.sbt.javaschool.rnd;

import com.sbt.javaschool.rnd.SerializeAnnotations.SerializeMethodCache;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CacheProxyResult {
    private Map<Method, Result> cachedResult =
            new HashMap<>();

    public Object getResult(Object o, Method method, Object... args) throws InvocationTargetException, IllegalAccessException {

        if (!method.isAnnotationPresent(SerializeMethodCache.class)) {
            return method.invoke(o, args);
        }

        if (cachedResult.containsKey(method) & cachedResult.get(method).isResultForThisArgs(args)) {
            return cachedResult.get(method).get();
        }
        return null;
    }

    private class Result implements Serializable {
        private final Object result;
        private final Object[] args;
        private final boolean checkClasses;

        public Result(Object result, boolean checkClasses, Object... args) {
            this.result =result;
            this.args = args;
            this.checkClasses = checkClasses;
        }

        public boolean isResultForThisArgs(Object... args) {
            if (this.args.length != args.length)
                return false;

            if (checkClasses) {
                for (int i = 0; i < args.length; i++) {
                    if(this.args[i].getClass() != args[i].getClass())
                        return false;
                }
            }

            return true;
        }

        public Object get() {
            return result;
        }
    }
}
