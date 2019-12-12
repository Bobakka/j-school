package com.sbt.javaschool.rnd.Lesson12.Homework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FixedThreadPoolTest {
/*
 * Test fixed thread:
 * @nThreads=2 Time 718ms
 * @nThreads=4 Time 407ms
 * @nThreads=8 Time 391ms
 */
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 8})
    void execute(int nThreads) {
        ThreadPool threadPool = new FixedThreadPool(nThreads);
        List<Runnable> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            System.out.println("add new task. Task # " + i);
            threadPool.execute(new FactorialCalculate());
        }
        while (threadPool.isWorking()) {
            System.out.println("Current queue: "+ threadPool.numberTask());
//                TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("Thread pool info: " + threadPool.numberThreads());
        threadPool.shutdown();
    }

}