package com.sbt.javaschool.rnd.lesson6.Homework;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
               .filter(x->x.getName().startsWith("get"))
               .collect(Collectors.toMap(k->k.getName().substring(3), v->v));


        Map<String, Method> finalFromMethods = fromMethods;
        toMethods = Arrays.stream(from.getClass().getMethods())
                .parallel()
                .filter(x->x.getName().startsWith("set"))
                .filter(x->finalFromMethods
                        .containsKey(x.getName().substring(3)))
                .collect(Collectors.toMap(k->k.getName().substring(3), v->v));

        Map<String, Method> finalToMethods = toMethods;
        fromMethods.forEach((k, v) -> {
            Method m = finalToMethods.get(k);
            if (m != null){
                try {
                    if (m.getParameterTypes()[0].equals(v.getReturnType()) |
                            m.getParameterTypes()[0].equals(v.getReturnType().getSuperclass()))
                    finalToMethods.get(k).invoke(to, v.invoke(from));
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

/*
        for (Method m : from.getClass().getMethods()) {
            if (m.getName().startsWith("get")) {
                fromMethods.put(m, m.getReturnType().getClass());
                getterSetter.put(m.getName(),
                        "set" +m.getName().substring(3,m.getName().length()));
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


 */
    }

}
