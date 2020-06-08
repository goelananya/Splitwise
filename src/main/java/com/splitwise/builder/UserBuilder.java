package com.splitwise.builder;

import com.splitwise.bo.User;

public class UserBuilder {
    private String userName;
    private String email;
    private String phoneNumber;
    private Long balanceBookId;

    public String getUserName() {
        return userName;
    }

    public UserBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Long getBalanceBookId() {
        return balanceBookId;
    }

    public UserBuilder setBalanceBookId(Long balanceBookId) {
        this.balanceBookId = balanceBookId;
        return this;
    }

    public User build() {
        return new User(this);
    }
}
