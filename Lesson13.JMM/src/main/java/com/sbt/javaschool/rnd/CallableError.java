package com.sbt.javaschool.rnd;

public class CallableError extends RuntimeException {
    private Exception ex;

    CallableError(Exception ex) {
        this.ex = ex;
    }


}
