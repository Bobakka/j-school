package com.sbt.javaschool.rnd;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CollectionWorker {

    private TreeSet<String> uniqueWords;
    private ArrayList<String> words;
    private TreeMap<String, Integer> countWords;
    private ArrayList<String> lines;

    public CollectionWorker(InputStream stream) throws IOException {
        uniqueWords =  new TreeSet<>();
        words = new ArrayList<>();
        countWords = new TreeMap<>();
        lines = new ArrayList<>();
        readFile(stream);
    }
    public boolean readFile(InputStream stream) throws IOException {
        Scanner scanner;
        try {
            scanner = new Scanner(stream);
/*read from files as lines,
* after then read words from lines
* and add our containers
 */
            while(scanner.hasNextLine()) {
                String lineValue = scanner.nextLine();
                lines.add(lineValue);
                Scanner scannerLine = new Scanner(lineValue);
                scannerLine.useDelimiter("\\W+");
                while(scannerLine.hasNext()) {
                    String value = scannerLine.next();
                    uniqueWords.add(value.toLowerCase());
                    words.add(value.toLowerCase());
                    if ((countWords.containsKey(value))) {
                        countWords.put(value, countWords.get(value) + 1);
                    } else {
                        countWords.put(value, 1);
                    }
                }
            }
            return true;
        } finally {
            if (stream != null)
                stream.close();
        }

    }

//Task1
    public Integer numberDifferentWords() {
        return uniqueWords.size();
    }
//Task2.1
    public String getUniqueWordsOrderBySize() {

        StringJoiner result = new StringJoiner(" ");
        ArrayList<String> tmp = new ArrayList<>(uniqueWords);
        tmp.sort(Comparator.comparingInt(String::length));
        for (String s : tmp) {
            result.add(s);
        }

        return result.toString();
    }
 //Task2.2
 public String getUniqueWordsOrderByAlphabetical(){
     StringJoiner result = new StringJoiner(" ");
     for(String s : uniqueWords)
         result.add(s);

     return result.toString();
 }
//Task3
    public String getStatisticWords() {
        StringJoiner result = new StringJoiner("\n");
        countWords.forEach((k,v)->result.add(k + ": " + v));

        return result.toString();
    }
//Task4
    public String reverseText() {
        StringJoiner result = new StringJoiner("\n");
        ArrayList<String> tmp = new ArrayList<>(lines);
        Collections.reverse(tmp);

        for (String line : tmp) {
            result.add(line);
        }

        return result.toString();
    }
//Task6
    public String getLine(Integer iLine) throws ArrayIndexOutOfBoundsException{

        if (iLine > lines.size() - 1)
            throw new ArrayIndexOutOfBoundsException();
        return lines.get(iLine);
    }

    public String getLine(List<Integer> numLines) throws ArrayIndexOutOfBoundsException {
        StringJoiner result = new StringJoiner("\n");
        for (Integer iLine : numLines) {
            result.add(getLine(iLine));
        }
        return result.toString();
    }
}
