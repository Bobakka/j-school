package com.sbt.javaschool.rnd.lesson6.KlassenArbeiten;

public class CalculatorImpl implements Calculator {

    @Override
    public Long calc(Integer factorial) {
        Long res = 1L;
        if (factorial == 0)
            return res;
        for (int i = 1; i <= factorial; ++i) {
            res *= i;
        }
        return res;
    }

}

