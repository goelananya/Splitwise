package com.splitwise.dao;

import com.splitwise.bo.Expense;

import java.util.UUID;

public interface ExpenseDao {

    int insertExpense(UUID id, Expense expense);

    default int insertExpense(Expense expense) {
        UUID id = UUID.randomUUID();
        return insertExpense(id, expense);
    }
}