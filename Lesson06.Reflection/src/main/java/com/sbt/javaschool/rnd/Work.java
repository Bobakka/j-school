package com.sbt.javaschool.rnd;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import static java.util.Collections.emptyList;

public class Work {

    private Field val;

    public static void main(String[] args) throws  IllegalAccessException {
        System.out.println(HashMap.class.getPackage());

        Class<?> superClass = HashMap.class.getSuperclass();
/*
        Method[] methods = HashMap.class.getMethods();
        for (Method method : methods) {
            System.out.println("Modifier: " + Modifier.toString(method.getModifiers()));
            System.out.println("Method: " + method.toGenericString());
        }
        while (superClass != null) {
            System.out.println("SuperClass: " + superClass);
            System.out.println("Declared interfases: " + superClass.getInterfaces());
            superClass = superClass.getSuperclass();
        }


*/
/*
        Method[] methods = Runtime.class.getMethods();
        for (Method method : methods) {
            System.out.println("Method: " + method.toGenericString());
            Type[] types = method.getActualTypeArguments();

            for (Type ty : types) {
                System.out.println(ty);
            }

        }
*/
//        Runtime<Integer> r = new Runtime<>();

/*
        Class actualClass = r.getClass();
        System.out.println(((ParameterizedType)actualClass.getMethod("strings").getGenericReturnType()).getActualTypeArguments()[0].getTypeName());
        System.out.println(((ParameterizedType)actualClass.getMethod("numbers").getGenericReturnType()).getActualTypeArguments()[0].getTypeName());
        System.out.println(((ParameterizedType)actualClass.getMethod("call").getGenericReturnType()).getActualTypeArguments()[0].getTypeName());

 */

        for (Method m : TestClass.class.getMethods()) {
            if (m.getName().startsWith("get")) {
                System.out.println(m.getName());
            }
        }

        TestClass testClass = new TestClass();
        for(Field val : testClass.getClass().getDeclaredFields()) {
            if (val.get(testClass) != null && Modifier.toString(val.getModifiers()).contains("final") )
                if (val.getName().equals(val.get(testClass).toString())) {
                    System.out.println(val.getName());
                }
        }

        System.out.println();

    }


    public static  class TestClass {
        private Integer t1;
        private String str;
        private Double d;

        public static final String MONDAY = "MONDAY";
        public static final String THUESDAY = "THUESDAY";
        public static final String SATURDAY = "SATURDAY";

        private String one = "one";
        private final String two = "two";
        private static final String zero = "zero";
        private String three = "one";

        public Integer getT1() {
            return t1;
        }

        public void setT1(Integer t1) {
            this.t1 = t1;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        public Double getD() {
            return d;
        }

        public void setD(Double d) {
            this.d = d;
        }
    }

    public static class Runtime<T extends Number> implements Callable<Double> {
        private final List<Integer> integers = emptyList();

        public List<T> numbers() {
            return emptyList();

        }

        public List<String> strings() {
            return emptyList();
        }

        @Override
        public Double call() {
            return 0d;
        }
    }


    }
