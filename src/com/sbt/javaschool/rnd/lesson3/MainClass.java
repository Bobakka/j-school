package com.sbt.javaschool.rnd.lesson3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) throws IOException {
        InputStream stream;

        MainClass myClass = new MainClass();
        if (args.length > 0) {
            stream = new FileInputStream(args[0]);
        }
        else {
            stream = myClass.getClass().getResourceAsStream("example.txt");
        }

        if (stream == null)
            return;

        try {
            CollectionWorker worker = new CollectionWorker(stream);

            System.out.println("Number of unique words: " + worker.numberDifferentWords().toString());
            System.out.println("\n\n");
            System.out.println("Sort unique words by length: " + worker.getUniqueWordsOrderBySize());
            System.out.println("\n\n");
            System.out.println("Sort unique words by abc: " + worker.getUniqueWordsOrderByAlphabetical());
            System.out.println("\n\n");
            System.out.println("Statistic words:\n" + worker.getStatisticWords());
            System.out.println("\n\n");
            System.out.println("Reverse lines: \n" + worker.reverseText());
            System.out.println("\n\n");
            System.out.print("Get custom lines: ");
            Scanner scanner = new Scanner(System.in);
//            scanner.useDelimiter("\\W+");
            ArrayList<Integer> lines = new ArrayList<>();
            Scanner lineScanner = new Scanner(scanner.nextLine());
            while (lineScanner.hasNextInt()) {
                lines.add(lineScanner.nextInt());
            }
            System.out.println(worker.getLine(lines));
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }




    }
}
