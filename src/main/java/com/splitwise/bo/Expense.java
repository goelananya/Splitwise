package com.splitwise.bo;

import com.splitwise.builder.ExpenseBuilder;
import com.splitwise.util.SplitWiseConstants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
public class Expense {

    @Id
    @GeneratedValue
    private Long expenseId;
    private String createdBy;
    private String participantUsersList;
    private String splitRatio;
    private String message;
    private double amount;
    private Date createDate;
    private Date expenseDate;

    protected Expense() {
    }

    public Expense(ExpenseBuilder expenseBuilder) {
        this.createdBy = expenseBuilder.getCreatedBy();
        this.participantUsersList = expenseBuilder.getParticipantUsers();
        this.message = expenseBuilder.getMessage();
        this.amount = expenseBuilder.getAmount();
        this.createDate = expenseBuilder.getCreateDate();
        this.expenseDate = expenseBuilder.getExpenseDate();
        this.splitRatio = expenseBuilder.getSplitRatio();
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public List<String> getParticipantUsersList() {
        return Arrays.asList(participantUsersList.split(SplitWiseConstants.SEPARATOR));
    }

    public String getSplitRatio() {
        return splitRatio;
    }

    public String getMessage() {
        return message;
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

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId='" + expenseId + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", participantUsers='" + participantUsersList + '\'' +
                ", splitRatio='" + splitRatio + '\'' +
                ", message='" + message + '\'' +
                ", amount=" + amount +
                ", createDate=" + createDate +
                ", expenseDate=" + expenseDate +
                '}';
    }
}