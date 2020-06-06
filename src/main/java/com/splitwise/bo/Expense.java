package com.splitwise.bo;

import com.splitwise.builder.ExpenseBuilder;

import java.util.Date;

public class Expense {

    private String expenseId;
    private String createdBy;
    private String participantUsers;
    private String splitRatio;
    private String message;
    private String currency;
    private double amount;
    private Date createDate;
    private Date expenseDate;

    public Expense(ExpenseBuilder expenseBuilder) {
        this.expenseId=expenseBuilder.getExpenseId();
        this.createdBy=expenseBuilder.getCreatedBy();
        this.participantUsers=expenseBuilder.getParticipantUsers();
        this.message=expenseBuilder.getMessage();
        this.currency=expenseBuilder.getCurrency();
        this.amount=expenseBuilder.getAmount();
        this.createDate=expenseBuilder.getCreateDate();
        this.expenseDate = expenseBuilder.getExpenseDate();
        this.splitRatio = expenseBuilder.getSplitRatio();
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getParticipantUsers() {
        return participantUsers;
    }

    public String getSplitRatio() {
        return splitRatio;
    }

    public String getMessage() {
        return message;
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }
}