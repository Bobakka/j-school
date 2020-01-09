package com.sbt.javaschool.rnd;

public interface Context {
    int getCompletedTaskCount();
    int getFailedTaskCount();
    int getInterruptedTaskCount();
    void interrupt();
    boolean isFinished();

}
