package com.splitwise.controller;

import com.splitwise.bo.Expense;
import com.splitwise.builder.ExpenseBuilder;
import com.splitwise.util.SplitWiseConstants;
import com.splitwise.util.UniqueIdGenerator;
import com.splitwise.validator.ExpenseValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class ExpenseController {

    @ResponseBody
    @PostMapping("/add-expense")
    private String createExpense(@RequestParam Map<String, String> expenseParams) {
        ExpenseBuilder expenseBuilder = new ExpenseBuilder();
        Expense expense = expenseBuilder.setAmount(Double.parseDouble(expenseParams.get(SplitWiseConstants.AMOUNT)))
                .setCurrency(expenseParams.get(SplitWiseConstants.CURRENCY))
                .setMessage(expenseParams.get(SplitWiseConstants.MESSAGE))
                .setCreatedBy(expenseParams.get(SplitWiseConstants.CREATED_BY))
                .setParticipantUsers(Arrays.asList(expenseParams.get(SplitWiseConstants.PARTICIPANTS).split(",")))
                .setExpenseDate(expenseParams.get(SplitWiseConstants.EXPENSE_Date))
                .setCreateDate(expenseParams.get(SplitWiseConstants.CREATE_DATE))
                .setExpenseId(UniqueIdGenerator.generateExpenseId())
                .setSplitRatio(expenseParams.get(SplitWiseConstants.SPLIT_RATIO)).build();
        return ExpenseValidator.validateExpense(expense);
    }
}