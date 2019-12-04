package com.sbt.javaschool.rnd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthTest {

    @Test
    void ValidatorTest() {
        AuthorizationSetvice auth = new AuthorizationSetvice();
        String validEmailLogin = "some@mail.com";
        String invalidEmailLogin = "abrakadabra";
//validate mail
        assertTrue(auth.validateLogin(validEmailLogin));
        assertFalse(auth.validateLogin(invalidEmailLogin));
    }

    @Test
    void LoginTest() {
        AuthorizationSetvice auth = new AuthorizationSetvice();

        String email = "some@mail.com";
        String password = "VeryLongPassword";

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest();
            md.update(password.getBytes());
            String myHash = Base64.getEncoder().encodeToString(md.digest());
            assertTrue(auth.login(email, myHash));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }




    }
}
