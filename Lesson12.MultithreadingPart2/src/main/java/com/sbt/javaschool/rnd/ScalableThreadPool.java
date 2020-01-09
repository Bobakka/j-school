package com.sbt.javaschool.rnd;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ScalableThreadPool implements ThreadPool {
    private volatile boolean isRunning = true;
    private Queue<Runnable> queue = new ConcurrentLinkedDeque<>();
    private final int minThreads;
    private final int maxThreads;
    private Map<TaskWorker, Thread> threads = new ConcurrentHashMap<>();
    private final Thread clearThread;

    ScalableThreadPool(int minThreads, int maxThreads) {
        this.minThreads = minThreads;
        this.maxThreads = maxThreads;
        for (int i = 0; i < minThreads; i++) {
            TaskWorker task = new TaskWorker();
            threads.put(task, new Thread(task));
        }
        threads.forEach((k, v) -> v.start());

        clearThread = new Thread(new ClearWorker());
        clearThread.start();
    }

    public int numberTask() {
        return queue.size();
    }

    @Override
    public int numberThreads() {
        return threads.size();
    }

    public boolean isWorking() {
        for (TaskWorker taskWorkers : threads.keySet()) {
            if (taskWorkers.isWorking()) {
                return true;
            }
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
            addNewThreads();
            System.out.println("Size queue: " + queue.size());
            System.out.println("Number working threads: " + threads.size());
            System.out.println("Number working threads: " + threads.size());
        }
    }

    private void addNewThreads() {
        if (threads.size() < maxThreads) {
            System.out.println("Add new thread");
            TaskWorker task = new TaskWorker();
            threads.put(task, new Thread(task));
            threads.get(task).start();
        }
    }


    private final class TaskWorker implements Runnable {
        volatile boolean isWorking = false;

        public boolean isWorking() {
            return this.isWorking;
        }

        @Override
        public void run() {
            while (isRunning) {
                Runnable nextTask = queue.poll();
                if (nextTask != null) {
                    isWorking = true;
                    nextTask.run();
                } else {
                    isWorking = false;

                }
            }
        }
    }

    private final class ClearWorker implements Runnable {

        @Override
        public void run() {
            Lock lock = new ReentrantLock();
            while (isRunning) {

                if (threads.size() <= minThreads) {
                    try {
                        TimeUnit.MICROSECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                lock.lock();
                for (Iterator<Map.Entry<TaskWorker, Thread>> it = threads.entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry<TaskWorker, Thread> entry = it.next();
                    if (threads.size() > minThreads) {
                        if (!entry.getKey().isWorking()) {
                            System.out.println("Thread " +
                                    entry.getValue().getId() + " will be removed");
                            entry.getValue().interrupt();
                            it.remove();
                        }
                    }
                }
                lock.unlock();

            }
        }
    }
}
