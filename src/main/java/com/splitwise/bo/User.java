package com.splitwise.bo;

import com.splitwise.builder.UserBuilder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
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
}