package com.splitwise.bo;

import com.splitwise.builder.UserBuilder;

public class User {
    private String username;
    private String email;
    private String phoneNumber;
    private String balanceBookId;

    public User(UserBuilder userBuilder) {
        this.username = userBuilder.getUserName();
        this.email = userBuilder.getEmail();
        this.phoneNumber = userBuilder.getPhoneNumber();
        this.balanceBookId = userBuilder.getBalanceBookId();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBalanceBookId() {
        return balanceBookId;
    }
}
