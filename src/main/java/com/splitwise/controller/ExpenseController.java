package com.splitwise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.splitwise.bo.Expense;
import com.splitwise.builder.ExpenseBuilder;
import com.splitwise.service.ExpenseService;
import com.splitwise.util.SplitWiseConstants;
import com.splitwise.util.UniqueIdGenerator;
import com.splitwise.validator.ExpenseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RequestMapping("api/v1/expense")
@RestController
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping
    private int createExpense(@RequestBody String param) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(param);
        Map<String, String> expenseParams = mapper.readValue(param, Map.class);
        System.out.println(expenseParams);
        ExpenseBuilder expenseBuilder = new ExpenseBuilder();
        Expense expense = expenseBuilder.setAmount(expenseParams.get(SplitWiseConstants.AMOUNT))
                .setCurrency(expenseParams.get(SplitWiseConstants.CURRENCY))
                .setMessage(expenseParams.get(SplitWiseConstants.MESSAGE))
                .setCreatedBy(expenseParams.get(SplitWiseConstants.CREATED_BY))
                .setParticipantUsers(expenseParams.get(SplitWiseConstants.PARTICIPANTS))
                .setExpenseDate(expenseParams.get(SplitWiseConstants.EXPENSE_Date))
                .setCreateDate(expenseParams.get(SplitWiseConstants.CREATE_DATE))
                .setExpenseId(UniqueIdGenerator.generateExpenseId())
                .setSplitRatio(expenseParams.get(SplitWiseConstants.SPLIT_RATIO)).build();
        return expenseService.addExpense(expense);
    }
}