package com.sbt.javaschool.rnd;

import java.util.regex.Pattern;

public class AuthorizationSetvice {
/*
 * This validation for email permitted by RFC5322.
 * Take from stackoverflow.com
 */
    private Pattern emailPattern =
            Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private Accounts accounts;


    public boolean validateLogin(String validLogin) {

        return emailPattern.matcher(validLogin).matches();
    }

    public boolean login(String email, String password) {
        User user = accounts.getUser(email);
        if (user == null)
            return false;
        return user.checkPassword(password);
    }
}
