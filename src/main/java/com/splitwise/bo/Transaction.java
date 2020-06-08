package com.splitwise.bo;

import com.splitwise.builder.TransactionBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long transactionId;
    private Date transactionDate;
    private String payeeId;
    private String receiverId;
    private String message;
    private Double amount;

    protected Transaction() {
    }

    public Transaction(TransactionBuilder builder) {
        this.transactionDate = builder.getTransactionDate();
        this.payeeId = builder.getPayeeId();
        this.receiverId = builder.getRecieverId();
        this.amount = builder.getAmount();
        this.message = builder.getMessage();
//        this.transactionId = builder.getTransactionId();
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getMessage() {
        return message;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", transactionDate=" + transactionDate +
                ", payeeId='" + payeeId + '\'' +
                ", recieverId='" + receiverId + '\'' +
                ", message='" + message + '\'' +
                ", amount=" + amount +
                '}';
    }
}
