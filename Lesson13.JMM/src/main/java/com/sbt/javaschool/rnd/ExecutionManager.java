package com.sbt.javaschool.rnd;

public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
