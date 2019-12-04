package com.sbt.javaschool.rnd;

import java.util.HashMap;
import java.util.Map;

public class Accounts {
    Map<String, User> storage = new HashMap<>();

    public User getUser(String email) {
        return storage.get(email);
    }
}
