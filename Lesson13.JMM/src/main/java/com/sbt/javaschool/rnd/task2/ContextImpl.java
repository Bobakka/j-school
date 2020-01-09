package com.sbt.javaschool.rnd.task2;

import java.util.List;
import java.util.concurrent.Future;

public class ContextImpl implements Context {
    private List<Future<?>> totalTasks;

    ContextImpl(List<Future<?>> totalTasks) {
        this.totalTasks = totalTasks;
    }
    @Override
    public int getCompletedTaskCount() {
        int completedTask = 0;
        for (Future<?> task : totalTasks) {
            if (task.isDone() && !task.isCancelled())
                completedTask++;
        }
        return completedTask;
    }

    @Override
    public int getFailedTaskCount() {
        int failedTask = 0;
        for (Future<?> task : totalTasks) {
            if (!task.isCancelled() & task.isDone()) {
                try {
                    task.get();
                } catch (Exception e) {
                    failedTask++;
                }
            }
        }
        return failedTask;
    }

    @Override
    public int getInterruptedTaskCount() {
        int interruptedTask = 0;
        for (Future<?> task : totalTasks) {
            if (task.isCancelled())
                interruptedTask++;
        }
        return interruptedTask;
    }

    @Override
    public void interrupt() {
        for (Future<?> task : totalTasks) {
            if (!task.isDone())
                task.cancel(true);
        }
    }

    @Override
    public boolean isFinished() {
        if (this.totalTasks.isEmpty())
            return false;
        for (Future<?> task : totalTasks) {
            if (!task.isDone())
                return false;
        }
        return true;
    }
}
