package com.splitwise.dao;

import com.splitwise.bo.Expense;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fakeDao")
public class ExpenseDataAccessService implements ExpenseDao{

    private static List<Expense> expenseDB = new ArrayList<>();

    @Override
    public int insertExpense(UUID id, Expense expense) {
        expenseDB.add(expense);
        return 1;
    }
}
