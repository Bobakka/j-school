package com.sbt.javaschool.rnd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void checkThrow() {
        Task<Integer> task = new Task<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                throw new Exception();
            }
        });
//TODO: Check throw
//        assertThrows(CallableError, )
    }

    @Test
    void checkGet() {
        Task<Integer> task = new Task<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Integer result = (int)Math.random();
                return result;
            }
        });

        List<Integer> list = new ArrayList<>();


        final Integer[] resultFromThread1 = new Integer[1];
        final Integer[] resultFromThread2 = new Integer[1];
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                resultFromThread1[0] = task.get();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                resultFromThread2[0] = task.get();
            }
        });

        thread1.start();
        thread2.start();

        assertEquals(resultFromThread1[0], resultFromThread2[0]);
    }
}