package com.splitwise.dao;

import com.splitwise.bo.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseDao extends CrudRepository<Expense, Long> {

    Expense findByExpenseId(String expenseId);
}