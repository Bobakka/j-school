package com.sbt.javaschool.rnd;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileReaderTest {
    private static List<InputStreamReader> files = new ArrayList<>();
    @BeforeEach
    void initResourse() {
        files.add(new InputStreamReader(FileReaderTest.class
                .getClassLoader().getResourceAsStream("example1.txt")));
        files.add(new InputStreamReader(FileReaderTest.class
                .getClassLoader().getResourceAsStream("example2.txt")));
        files.add(new InputStreamReader(FileReaderTest.class
                .getClassLoader().getResourceAsStream("example3.txt")));
        files.add(new InputStreamReader(FileReaderTest.class
                .getClassLoader().getResourceAsStream("example4.txt")));
        files.add(new InputStreamReader(FileReaderTest.class
                .getClassLoader().getResourceAsStream("example5.txt")));
        files.add(new InputStreamReader(FileReaderTest.class
                .getClassLoader().getResourceAsStream("bigExample1.txt")));
        files.add(new InputStreamReader(FileReaderTest.class
                .getClassLoader().getResourceAsStream("biExample2.txt")));
    }

    @Test
    void MultiThread()  {
        FileWorker fileWorker = new FileWorker(8);
        fileWorker.startWork(files);

        Map<String, Integer> results = fileWorker.getResult();
/*
 * Check words from files in our result
 */
        initResourse();
        for (InputStreamReader in : files) {
            new BufferedReader(in)
                    .lines()
                    .map(line -> line.split("\\W+"))    //or \\s+ ??
                    .flatMap(Arrays::stream)
                    .forEach(k-> assertTrue(results.containsKey(k)));
        }

    }

   @Test
    void oneThread() {
       FileWorker fileWorker = new FileWorker(1);
       fileWorker.startWork(files);

       Map<String, Integer> results = fileWorker.getResult();
/*
* Check words from files in our result
*/
        initResourse();
       for (InputStreamReader in : files) {
           new BufferedReader(in)
                   .lines()
                   .map(line -> line.split("\\W+"))    //or \\s+ ??
                   .flatMap(Arrays::stream)
                   .forEach(k-> assertTrue(results.containsKey(k)));
       }

   }
}