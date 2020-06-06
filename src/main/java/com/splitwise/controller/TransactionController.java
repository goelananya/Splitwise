package com.splitwise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.splitwise.bo.Transaction;
import com.splitwise.builder.TransactionBuilder;
import com.splitwise.exception.UserNotFoundException;
import com.splitwise.service.TransactionService;
import com.splitwise.util.SplitWiseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("splitwise/transaction")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody String param) throws JsonProcessingException, UserNotFoundException {
        Map<String, String> transactionParams = new ObjectMapper().readValue(param, Map.class);
        return transactionService.addTransaction(new TransactionBuilder().setPayeeId(transactionParams.get(SplitWiseConstants.PAYEE))
                .setRecieverId(transactionParams.get(SplitWiseConstants.RECEIVER))
                .setAmount(Double.parseDouble(transactionParams.get(SplitWiseConstants.AMOUNT)))
                .setMessage(transactionParams.get(SplitWiseConstants.MESSAGE))
                .setTransactionId().setTransactionDate().build());
    }
}
