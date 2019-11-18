package com.sbt.javaschool.rnd.lesson6.Homework;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
        Map<Class, Method> fromMethods = new HashMap<>();
        Map<Class, Method> toMethods = new HashMap<>();

        for (Method m : from.getClass().getMethods()) {
            if (m.getName().startsWith("get")) {
                fromMethods.put(m.getReturnType().getClass(), m);
            }
        }

        for (Method m : to.getClass().getMethods()) {
            if (m.getName().startsWith("set")) {
                toMethods.put(m.getParameterTypes()[0].getClass(), m);
            }
        }

        fromMethods.forEach((k, v) -> {
            if (toMethods.containsKey(k) |
                toMethods.containsKey(k.getSuperclass())) {
                try {
                    toMethods.get(k).invoke(to, v.invoke(from));
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
