package com.splitwise.bo;

import com.splitwise.builder.TransactionBuilder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@ToString
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
    }
}
