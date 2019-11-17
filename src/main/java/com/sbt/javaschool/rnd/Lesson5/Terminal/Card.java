package com.sbt.javaschool.rnd.Lesson5.Terminal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.OptionalInt;
import java.util.Random;

public class Card {
    private final Integer id;

    private final String firstName;
    private final String secondName;
    private byte[] pinHash;

    public Card(String firstName, String secondName, Integer pin)
            throws NullPointerException, IllegalArgumentException {
        if (pin == null | firstName.isEmpty() |
            secondName.isEmpty()) {
            throw  new NullPointerException("Illegal arguments");
        }
        if (pin > 9999)
            throw new IllegalArgumentException("Illegal PIN");
        if (firstName.matches(".*\\d.*"))
            throw new IllegalArgumentException("Illegal first name");
        if (secondName.matches(".*\\d.*"))
            throw new IllegalArgumentException("Illegal first name");

        OptionalInt oi = new Random().ints(0, Integer.MAX_VALUE).
                limit(1).findFirst();
        id = oi.isPresent() ?
                oi.getAsInt() : null;
        if (id == null)
            throw new NullPointerException("cant generate id");

        this.firstName = firstName;
        this.secondName = secondName;
        calcPinHash(pin);

    }
    public byte[] getPing() {
        return this.pinHash;
    }
    private void calcPinHash(Integer pin){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pin.byteValue());
            pinHash = md.digest();
        }
        catch (NoSuchAlgorithmException ex) {
            System.out.println("No such algorithm MD5!");
        }
        finally {
            pinHash = null;
        }
    }
}
