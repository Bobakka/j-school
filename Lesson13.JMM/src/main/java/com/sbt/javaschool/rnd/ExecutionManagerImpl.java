package com.sbt.javaschool.rnd;

import java.util.*;
import java.util.concurrent.*;

public class ExecutionManagerImpl implements ExecutionManager {
    private List<Runnable> tasks;
    private Context context;
    private Timer timer = new Timer();
    private final ExecutorService threadPool
            = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    ExecutionManagerImpl() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        List<Future<?>> futures = new ArrayList<>();

        for (Runnable task : tasks)
            futures.add(this.threadPool.submit(task));

        this.context = new ContextImpl(futures);
        this.timer.schedule(new ScheduledTask(callback), 0, 1);

        return this.context;
    }

    private class ScheduledTask extends TimerTask {
        private Runnable callback;
        ScheduledTask(Runnable callback) {
            this.callback = callback;
        }

        @Override
        public void run() {
            if (context.isFinished()) {
                timer.cancel();
                callback.run();
            }
        }
    }
}
