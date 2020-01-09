package com.sbt.javaschool.rnd;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
        for (int i = 0; i < 100000; i++) {
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

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 8})
    void useStandardExecutor(int nThreads) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);

        for (int i = 0; i < 100000; i++) {
            executor.execute(new FactorialCalculate());
        }

        executor.awaitTermination(10, TimeUnit.MINUTES);
    }

}