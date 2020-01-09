package com.sbt.javaschool.rnd.task1;

public class CallableError extends RuntimeException {
    private Exception ex;

    CallableError(Exception ex) {
        this.ex = ex;
    }


}
