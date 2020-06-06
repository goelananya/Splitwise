package com.splitwise.bo;

import com.splitwise.util.UniqueIdGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Long getBalanceId() {
        return balanceId;
    }

    public String getUserOne() {
        return userOne;
    }

    public void setUserOne(String userOne) {
        this.userOne = userOne;
    }

    public String getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(String userTwo) {
        this.userTwo = userTwo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
