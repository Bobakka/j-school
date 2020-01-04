package com.sbt.javaschool.rnd.Terminal;

import com.sbt.javaschool.rnd.Terminal.Client.Terminal;
import com.sbt.javaschool.rnd.Terminal.Client.TerminalImpl;

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
