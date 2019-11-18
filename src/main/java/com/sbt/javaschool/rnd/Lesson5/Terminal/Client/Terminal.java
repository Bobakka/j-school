package com.sbt.javaschool.rnd.Lesson5.Terminal.Client;

import com.sbt.javaschool.rnd.Lesson5.Terminal.Card;

public interface Terminal {
    void connect(String url);
    void putCard(Card card);
    boolean checkPin(Integer pin);
    void balance();
    void takeMoney(Integer sum);
    void appendMoney(Integer sum);
}
