package com.sbt.javaschool.rnd.lesson6.KlassenArbeiten;

import java.lang.reflect.Proxy;

public class CalculatorMain {
    public static void main(String[] args) {
        Calculator calc =
                (Calculator) Proxy.newProxyInstance(CalculatorImpl.class.getClassLoader(),
                        new Class[]{Calculator.class},
                        new CacheProxy());

        System.out.println(calc.calc(3));
        System.out.println(calc.calc(5));
        System.out.println(calc.calc(3));
        System.out.println(calc.calc(6));
        System.out.println(calc.calc(5));
    }
}
