package com.splitwise.builder;

import com.splitwise.bo.Transaction;
import com.splitwise.util.UniqueIdGenerator;
import lombok.Getter;

import java.util.Calendar;
import java.util.Date;

@Getter
public class TransactionBuilder {
    private Date transactionDate;
    private String transactionId;
    private String payeeId;
    private String recieverId;
    private String message;
    private double amount;

    public TransactionBuilder setTransactionDate() {
        this.transactionDate = Calendar.getInstance().getTime();
        return this;
    }

    public TransactionBuilder setTransactionId() {
        this.transactionId = UniqueIdGenerator.generateTransactionId();
        return this;
    }

    public TransactionBuilder setPayeeId(String payeeId) {
        this.payeeId = payeeId;
        return this;
    }

    public TransactionBuilder setRecieverId(String recieverId) {
        this.recieverId = recieverId;
        return this;
    }

    public TransactionBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public TransactionBuilder setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public Transaction build() {
        return new Transaction(this);
    }
}