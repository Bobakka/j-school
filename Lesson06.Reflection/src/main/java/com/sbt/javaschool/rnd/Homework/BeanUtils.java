package com.sbt.javaschool.rnd.Homework;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     * */

    public static void assign(@NotNull Object to, @NotNull Object from) {
        Map<String, Method> fromMethods;
        Map<String, Method> toMethods;

       fromMethods = Arrays.stream(from.getClass().getMethods())
               .parallel()
               .filter(x-> Modifier.isPublic(x.getModifiers()))
               .filter(x->x.getName().startsWith("get"))
               .collect(Collectors.toMap(k->k.getName().substring(3), v->v));

//why need next line for lambda???
        Map<String, Method> finalFromMethods = fromMethods;
        toMethods = Arrays.stream(to.getClass().getMethods())
                .parallel()
                .filter(x-> Modifier.isPublic(x.getModifiers()))
                .filter(x->x.getName().startsWith("set"))
                .filter(x->finalFromMethods
                        .containsKey(x.getName().substring(3)))
                .collect(Collectors.toMap(k->k.getName().substring(3), v->v));

        for (String k : fromMethods.keySet()) {
            if (!toMethods.containsKey(k) && !fromMethods.containsKey(k)) {
                continue;
            }
            Method m = toMethods.get(k);
            if (m == null)
                continue;
            try {
                if (m.getParameterTypes()[0].equals(fromMethods.get(k).getReturnType()) |
                        m.getParameterTypes()[0].equals(fromMethods.get(k).getReturnType().getSuperclass()))
                    toMethods.get(k).invoke(to, fromMethods.get(k).invoke(from));
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

}
