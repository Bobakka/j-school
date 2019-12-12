package com.sbt.javaschool.rnd.Lesson12.Homework;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ScalableThreadPoolTest {
/*
 * Test @minThread = 2, @maxThread = 8.
 * Execution time 1m 42s.
 * Task - calculate big factorial.
 * So long
 */
    @Test
    void execute() throws InterruptedException {
        ThreadPool threadPool = new ScalableThreadPool(2, 8);
        List<Runnable> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            System.out.println("add new task. Task # " + i);
            threadPool.execute(new FactorialCalculate());
        }
            while (threadPool.numberTask() > 0 && !threadPool.isWorking()) {
                System.out.println("Current queue: "+ threadPool.numberTask());
//                TimeUnit.SECONDS.sleep(1);
            }
        System.out.println("Thread pool info: " + threadPool.numberThreads());
        threadPool.shutdown();
    }
}