package com.splitwise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.splitwise.bo.Expense;
import com.splitwise.builder.ExpenseBuilder;
import com.splitwise.exception.UserNotFoundException;
import com.splitwise.service.ExpenseService;
import com.splitwise.util.SplitWiseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("splitwise/expense")
@RestController
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping
    private Expense createExpense(@RequestBody String param) throws JsonProcessingException, UserNotFoundException {
        Map<String, String> expenseParams = new ObjectMapper().readValue(param, Map.class);
        return expenseService.addExpense(new ExpenseBuilder().setAmount(expenseParams.get(SplitWiseConstants.AMOUNT))
                .setMessage(expenseParams.get(SplitWiseConstants.MESSAGE))
                .setCreatedBy(expenseParams.get(SplitWiseConstants.CREATED_BY))
                .setParticipantUsers(expenseParams.get(SplitWiseConstants.PARTICIPANTS))
                .setExpenseDate(expenseParams.get(SplitWiseConstants.EXPENSE_DATE))
                .setCreateDate()
                .setExpenseId()
                .setSplitRatio(expenseParams.get(SplitWiseConstants.SPLIT_RATIO)).build());
    }
}