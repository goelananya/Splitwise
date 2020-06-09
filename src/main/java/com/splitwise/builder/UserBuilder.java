package com.splitwise.builder;

import com.splitwise.bo.User;
import lombok.Getter;

@Getter
public class UserBuilder {
    private String userName;
    private String email;
    private String phoneNumber;
    private Long balanceBookId;

    public UserBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder setBalanceBookId(Long balanceBookId) {
        this.balanceBookId = balanceBookId;
        return this;
    }

    public User build() {
        return new User(this);
    }
}
