package com.sbt.javaschool.rnd;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class FixedThreadPool implements ThreadPool {
    private volatile boolean isRunning = true;
    private Queue<Runnable> queue = new ConcurrentLinkedQueue<>();
    private final Map<TaskWorker, Thread> threads = new HashMap<>();

    FixedThreadPool(int nThreads) {

        for (int i = 0; i < nThreads; i++) {
            TaskWorker task = new TaskWorker();
            threads.put(task, new Thread(task));
        }

        for (Map.Entry<TaskWorker, Thread> t : threads.entrySet()) {
            t.getValue().start();
        }
    }

    @Override
    public int numberTask() {
        return queue.size();
    }

    @Override
    public int numberThreads() {
        return threads.size();
    }

    @Override
    public boolean isWorking() {
        for (Map.Entry<TaskWorker, Thread> t : threads.entrySet()) {
            if (t.getKey().isWorking)
                return true;
        }
        return false;
    }

    @Override
    public void shutdown() {
        this.isRunning = false;
    }

    @Override
    public void execute(@NotNull Runnable runnable) {
        if (isRunning) {
            queue.offer(runnable);
        }
    }

    private final class TaskWorker implements Runnable {
        volatile boolean isWorking = false;
        @Override
        public void run() {
            while(isRunning) {
                Runnable nextTask = queue.poll();
                if (nextTask != null) {
                    isWorking = true;
                    nextTask.run();
                } else {
                    isWorking = false;
                    try {
                        TimeUnit.MILLISECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        if (Thread.currentThread().isInterrupted()){
                            return;
                        }
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}
