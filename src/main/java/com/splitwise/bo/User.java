package com.splitwise.bo;

import com.splitwise.builder.UserBuilder;
import com.splitwise.util.UniqueIdGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    private String phoneNumber;
    private String balanceBookId;

    protected User() {
    }

    public User(UserBuilder userBuilder) {
        this.username = userBuilder.getUserName();
        this.email = userBuilder.getEmail();
        this.phoneNumber = userBuilder.getPhoneNumber();
        this.balanceBookId = userBuilder.getBalanceBookId();
        this.id = UniqueIdGenerator.generateUserId();
    }

    public String getId() {
        return id;
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

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", balanceBookId='" + balanceBookId + '\'' +
                '}';
    }
}