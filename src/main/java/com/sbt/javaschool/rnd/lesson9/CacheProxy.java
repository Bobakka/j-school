package com.sbt.javaschool.rnd.lesson9;

import com.sbt.javaschool.rnd.lesson9.SerializeAnnotations.CacheType;
import com.sbt.javaschool.rnd.lesson9.SerializeAnnotations.SerializeMethodCache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {

    Object object;
    Map<String, ?> cache = new HashMap<>();
    CacheProxySettings settings = new CacheProxySettings();

    CacheProxy(Object object) {
        this.object = object;
        init();
    }

    void init() {
        for (Method m : object.getClass().getMethods()) {
            if (m.isAnnotationPresent(SerializeMethodCache.class)) {
                settings.setMethodSettings(
                        m,
                        m.getAnnotation(SerializeMethodCache.class).lengthList(),
                        m.getAnnotation(SerializeMethodCache.class).identityBy(),
                        m.getAnnotation(SerializeMethodCache.class).cacheType(),
                        m.getAnnotation(SerializeMethodCache.class).fileNamePrefix().isEmpty() ?
                                m.getName() :
                                m.getAnnotation(SerializeMethodCache.class).fileNamePrefix(),
                        m.getAnnotation(SerializeMethodCache.class).zip()
                );

            }
        }
    }


    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if (!method.isAnnotationPresent(SerializeMethodCache.class))
            return null;

        if (method.getAnnotation(SerializeMethodCache.class).cacheType() == CacheType.FILE) {
            readFileResult(method, objects);
        }
        return  null;
    }

    private void readFileResult(Method method, Object[] args) {
        String fileName = method.getName();
        if (!method.getAnnotation(SerializeMethodCache.class).fileNamePrefix().isEmpty())
            fileName = method.getAnnotation(SerializeMethodCache.class).fileNamePrefix();
//TODO
  /*      if (method.getAnnotation(SerializeMethodCache.class).zip()) {
            try(FileInputStream is = new FileInputStream(fileName),
                ) {
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

}
