package com.splitwise.builder;

import com.splitwise.bo.Transaction;

import java.util.Date;

public class TransactionBuilder {
    private Date transactionDate;
    private String transactionId;
    private String payeeId;
    private String recieverId;
    private String message;
    private double amount;

    public TransactionBuilder setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public TransactionBuilder setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public String getRecieverId() {
        return recieverId;
    }

    public String getMessage() {
        return message;
    }

    public double getAmount() {
        return amount;
    }

    public Transaction build() {
        return new Transaction(this);
    }
}