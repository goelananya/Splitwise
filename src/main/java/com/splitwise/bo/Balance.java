package com.splitwise.bo;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@ToString
public class Balance {
    @Id
    @GeneratedValue
    private Long balanceId;
    private String userOne;
    private String userTwo;
    private Double amount;

    public Balance(String user1, String user2, Double amount) {
        this.userOne = user1;
        this.userTwo = user2;
        this.amount = amount;
    }

    protected Balance() {

    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}