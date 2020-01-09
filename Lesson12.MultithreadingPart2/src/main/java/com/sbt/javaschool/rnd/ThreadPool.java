package com.sbt.javaschool.rnd;

import java.util.concurrent.Executor;

public interface ThreadPool extends Executor {
    int numberTask();
    int numberThreads();
    boolean isWorking();
    void shutdown();
}
