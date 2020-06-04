package com.splitwise.bo;

import com.splitwise.builder.ExpenseBuilder;

import java.util.Date;
import java.util.List;

public class Expense {

    private String expenseId;
    private String createdBy;
    private List<String> participantUsers;
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
}