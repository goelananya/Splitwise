package com.splitwise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.splitwise.builder.ExpenseBuilder;
import com.splitwise.exception.UserNotFoundException;
import com.splitwise.service.ExpenseService;
import com.splitwise.util.SplitWiseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping(SplitWiseConstants.EXPENSE_ENDPOINT)
@RestController
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping
    private ResponseEntity createExpense(@RequestBody String param) throws JsonProcessingException, UserNotFoundException {
        Map<String, String> expenseParams = new ObjectMapper().readValue(param, Map.class);
        try {
            expenseService.addExpense(new ExpenseBuilder().setAmount(expenseParams.get(SplitWiseConstants.AMOUNT))
                    .setMessage(expenseParams.get(SplitWiseConstants.MESSAGE))
                    .setCreatedBy(expenseParams.get(SplitWiseConstants.CREATED_BY))
                    .setParticipantUsers(expenseParams.get(SplitWiseConstants.PARTICIPANTS))
                    .setExpenseDate(expenseParams.get(SplitWiseConstants.EXPENSE_DATE))
                    .setCreateDate()
                    .setExpenseId()
                    .setSplitRatio(expenseParams.get(SplitWiseConstants.SPLIT_RATIO)).build());
            return ResponseEntity.ok("Expense added");
        } catch (UserNotFoundException exp) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
}