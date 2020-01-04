package com.sbt.javaschool.rnd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileWorker {
    private final ExecutorService executorService ;
    List<Future<Map<String, Integer>>> results = new ArrayList<>();
    Map<String, Integer> mergeResult = new HashMap<>();

    public FileWorker(int numberThreads) {
        this.executorService = Executors.newFixedThreadPool(numberThreads);
    }

    public void startWork(List<InputStreamReader> files) {
        for (InputStreamReader s : files) {
            FileCounter counter = new FileCounter(s);
            results.add(executorService.submit(counter));
        }
    }

    public Map<String, Integer> getResult() {
        if (!results.isEmpty()) {
            mergeResultFunc();
        }
        return mergeResult;
    }

    private void mergeResultFunc() {
        try {
            for (Future<Map<String, Integer>> res : results) {
                Map <String, Integer> mapRes = res.get();
                mapRes.forEach((key, value)-> mergeResult.merge(key,value, (v1, v2)->v1 + v2));
            }
            results.clear();
            executorService.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        if (!results.isEmpty()) {
            mergeResultFunc();
        }
        System.out.println(mergeResult);
    }
}
