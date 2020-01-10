package com.sbt.javaschool.rnd.KlassenArbeiten;

public class CalculatorMain {
    public static void main(String[] args) {
        CacheProxy proxy = new CacheProxySimple();
        Calculator calc = proxy.cache(new CalculatorImpl());

        System.out.println(calc.calc(3));
        System.out.println(calc.calc(5));
        System.out.println(calc.calc(3));
        System.out.println(calc.calc(6));
        System.out.println(calc.calc(5));
    }
}
