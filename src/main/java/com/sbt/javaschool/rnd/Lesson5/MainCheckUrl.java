package com.sbt.javaschool.rnd.Lesson5;

import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainCheckUrl {
    public static void main(String[] args) {
        while(true) {
            System.out.print("Enter URL: ");
            Scanner in = new Scanner(System.in);
            String url = in.nextLine();

            if (url.toLowerCase().startsWith("quit"))
                break;

            if (readContent(url)) {
                break;
            }
            else {
                System.out.println("Write url again!(If you want end program, " +
                        "write quit) ");
            }
        }
    }

    private static boolean readContent(String url_) {
        try {
            URI uri = new URI(url_);
            if (Desktop.isDesktopSupported() &&
                    Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(uri);
                return true;
            }
            System.out.println("No support desktop.");
            return false;
        }
        catch (URISyntaxException | MalformedInputException e) {
            System.out.println("Incorrect url. Try again");
            return false;
        }
        catch (IOException e) {
            System.out.println(String.format("Cant connect to url (%s). Try again", url_));
            return false;
        }

    }
}
