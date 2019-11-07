package com.sbt.javaschool.rnd.lesson3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CollectionWorkerTest {
    private static CollectionWorker w;
    private static  String inputString;

    @BeforeAll
    static void init() throws IOException {
        String fName = "c:/tmp/example.txt";
        FileInputStream in = new FileInputStream(fName);

        inputString = "a aaaa aa aaa qwerrtt\ndfdffda sda aaaa dfdf";
        w = new CollectionWorker(
                new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8))
        );
    }

    @Test
    void readFile() {
    }

    @DisplayName("Test Task 1")
    @Test
    void numberDifferentWords() {
        final Integer standardResult = 8;
        assertEquals(w.numberDifferentWords(), standardResult);
    }

    @DisplayName("Test Task 2.1")
    @Test
    void getUniqueWordsOrderBySize() {
        final String expect = "a aa aaa sda aaaa dfdf dfdffda qwerrtt";
        String result = w.getUniqueWordsOrderBySize();
        assertEquals(result, expect);
    }

    @DisplayName("Test Task 2.2")
    @Test
    void getUniqueWordsOrderByAlphabetical() {
        final String expect = "a aa aaa aaaa dfdf dfdffda qwerrtt sda";
        String result = w.getUniqueWordsOrderByAlphabetical();
        assertEquals(result, expect);
    }

    @DisplayName("Test Task 3")
    @Test
    void getStatisticWords() {
        final String expect = "a: 1\naa: 1\naaa: 1\naaaa: 2\ndfdf: 1\ndfdffda: 1\nqwerrtt: 1\nsda: 1";
        String result = w.getStatisticWords();
        assertEquals(result, expect);
    }

    @DisplayName("Test Task 4")
    @Test
    void reverseText() {
        String expect = "dfdffda sda aaaa dfdf\na aaaa aa aaa qwerrtt";

        String result = w.reverseText();
        assertEquals(result, expect);
    }

    @DisplayName("Test Task 6")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void getLine(int idx) {
        String expect;
        switch (idx) {
            case 0:
                expect = "a aaaa aa aaa qwerrtt";
                break;
            case 1:
                expect = "dfdffda sda aaaa dfdf";
                break;
            default:
                expect="";
        }
        if (idx > 1) {
            assertThrows(ArrayIndexOutOfBoundsException.class,()->w.getLine(idx));
        }
        else {
            String result = w.getLine(idx);
            assertEquals(result, expect);
        }




    }
}