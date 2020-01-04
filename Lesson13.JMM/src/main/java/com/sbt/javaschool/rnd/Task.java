package com.sbt.javaschool.rnd;

import java.util.concurrent.Callable;

public class Task<T> {
    T result;
    private final Callable callable;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() throws CallableError {
        if (result != null)
            return result;

        synchronized (callable) {
            try {
                result = (T) callable.call();
            } catch (Exception e) {
                throw new CallableError(e);
            }
        }
        return result;
    }
}
