package com.st.javaschool.rnd.intro;

import com.st.javaschool.rnd.intro.test.MySecondClass;

public class MyFirstClass {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Hello World!");
            return;
        }

        MySecondClass secClass = new MySecondClass();
        secClass.print(args);
    }
}
