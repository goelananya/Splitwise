package com.splitwise.builder;

import com.splitwise.bo.Expense;
import com.splitwise.util.UniqueIdGenerator;
import lombok.Getter;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@ToString
@Getter
public class ExpenseBuilder {
    private String expenseId;
    private String createdBy;
    private String participantUsers;
    private String message;
    private double amount;
    private Date createDate;
    private Date expenseDate;
    private String splitRatio;

    public Expense build() {
        return new Expense(this);
    }

    public ExpenseBuilder setExpenseId() {
        this.expenseId = UniqueIdGenerator.generateExpenseId();
        return this;
    }

    public ExpenseBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public ExpenseBuilder setParticipantUsers(String participantUsers) {
        this.participantUsers = participantUsers;
        return this;
    }

    public ExpenseBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public ExpenseBuilder setAmount(String amount) {
        this.amount = Double.parseDouble(amount);
        return this;
    }

    public ExpenseBuilder setSplitRatio(String splitRatio) {
        this.splitRatio = splitRatio;
        return this;
    }

    public ExpenseBuilder setCreateDate() {
        this.createDate = Calendar.getInstance().getTime();
        return this;
    }

    public ExpenseBuilder setExpenseDate(String expenseDate) {
        try {
            this.expenseDate = new SimpleDateFormat("dd/MM/yyyy").parse(expenseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }
}