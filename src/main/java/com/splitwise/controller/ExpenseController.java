package com.splitwise.controller;

import com.splitwise.bo.Expense;
import com.splitwise.builder.ExpenseBuilder;
import com.splitwise.util.UniqueIdGenerator;
import com.splitwise.validator.ExpenseValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
public class ExpenseController {

    @ResponseBody
    @PostMapping("/add-expense")
    private boolean createExpense(@RequestParam String amount, @RequestParam String currency, @RequestParam String message, @RequestParam String createdBy, @RequestParam String participants, @RequestParam String expenseDate, @RequestParam String createDate){
        ExpenseBuilder expenseBuilder = new ExpenseBuilder();
        Expense expense = expenseBuilder.setAmount(Double.parseDouble(amount)).setCurrency(currency).setMessage(message).setCreatedBy(createdBy).setParticipantUsers(Arrays.asList(participants.split(","))).setExpenseDate(expenseDate).setCreateDate(createDate).setExpenseId(UniqueIdGenerator.generateExpenseId()).build();
        return ExpenseValidator.validateExpense(expense);
    }


}
