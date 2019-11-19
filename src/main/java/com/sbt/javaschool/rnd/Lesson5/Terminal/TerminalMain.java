package com.sbt.javaschool.rnd.Lesson5.Terminal;

import com.sbt.javaschool.rnd.Lesson5.Terminal.Client.Terminal;
import com.sbt.javaschool.rnd.Lesson5.Terminal.Client.TerminalImpl;

public class TerminalMain {
    public static void main(String[] args) {
        Terminal t = new TerminalImpl();

        t.connect("ws://localhost:9090/gs-terminal");

        t.putCard(new Card("Ivan", "Ivanov", 1234));

        if (t.checkPin(5432)) {
            t.balance();
        }

    }
}
