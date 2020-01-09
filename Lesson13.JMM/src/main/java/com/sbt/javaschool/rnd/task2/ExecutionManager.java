package com.sbt.javaschool.rnd.task2;

public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
