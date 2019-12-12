package com.sbt.javaschool.rnd.Lesson12.Homework;

import org.jetbrains.annotations.NotNull;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

public interface ThreadPool extends Executor {
    int numberTask();
    int numberThreads();
    boolean isWorking();
    void shutdown();
}
