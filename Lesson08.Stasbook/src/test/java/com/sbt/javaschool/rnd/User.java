package com.sbt.javaschool.rnd;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class User {
    private String email;
    private String hashPassword;
    public boolean checkPassword(String password) {
        return hashPassword.equals(password);
    }

    public static String getHash(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest();
            md.update(str.getBytes());
            String myHash = Base64.getEncoder().encodeToString(md.digest());
            return myHash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
