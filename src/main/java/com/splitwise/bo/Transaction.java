package com.splitwise.bo;

import com.splitwise.builder.TransactionBuilder;

import java.util.Date;

public class Transaction {
    private String transactionId;
    private Date transactionDate;
    private String payeeId;
    private String recieverId;
    private String message;
    private Double amount;

    public Transaction(TransactionBuilder builder) {
        this.transactionDate = builder.getTransactionDate();
        this.payeeId = builder.getPayeeId();
        this.recieverId = builder.getRecieverId();
        this.amount = builder.getAmount();
        this.message = builder.getMessage()!=null?builder.getMessage():null;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
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

    public Double getAmount() {
        return amount;
    }
}
