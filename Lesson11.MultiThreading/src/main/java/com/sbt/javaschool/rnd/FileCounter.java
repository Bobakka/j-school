package com.sbt.javaschool.rnd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileCounter implements Callable<Map<String, Integer>> {
    private final BufferedReader buffer;
    Map<String, Integer> map;

    public FileCounter(InputStreamReader buffer) {
        this.buffer = new BufferedReader(buffer);
    }

    public void doWork() {

            map = this.buffer
                    .lines()
                    .map(line -> line.split("\\W+"))    //or \\s+ ??
                    .flatMap(Arrays::stream)
                    .collect(Collectors.toMap(key->key, value-> 1, (oldValue, newValue)->oldValue + newValue));
    }

    @Override
    public Map<String, Integer> call() {
        doWork();
        return map;
    }
}
