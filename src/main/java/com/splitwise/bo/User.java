package com.splitwise.bo;

import com.splitwise.builder.UserBuilder;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    private String email;
    private String phoneNumber;
    private Long balanceBookId;

    protected User() {
    }

    public User(UserBuilder userBuilder) {
        this.username = userBuilder.getUserName();
        this.email = userBuilder.getEmail();
        this.phoneNumber = userBuilder.getPhoneNumber();
        this.balanceBookId = userBuilder.getBalanceBookId();
    }

    public Long getId() {
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

    public Long getBalanceBookId() {
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