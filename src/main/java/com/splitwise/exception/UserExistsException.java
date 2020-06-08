package com.splitwise.exception;

public class UserExistsException extends Exception {
    public UserExistsException(String s) {
        super(s.concat("Please choose another username"));
    }
}