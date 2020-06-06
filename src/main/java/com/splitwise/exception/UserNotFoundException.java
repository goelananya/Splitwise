package com.splitwise.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String s) {
        super(s.concat("The user is not present"));
    }
}