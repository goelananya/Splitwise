package com.splitwise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.splitwise.bo.Transaction;
import com.splitwise.builder.TransactionBuilder;
import com.splitwise.exception.UserNotFoundException;
import com.splitwise.service.TransactionService;
import com.splitwise.util.SplitWiseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping(SplitWiseConstants.TRANSACTION_ENDPOINT)
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity addTransaction(@RequestBody String param) throws JsonProcessingException, UserNotFoundException {
        Map<String, String> paramMap = new ObjectMapper().readValue(param, Map.class);
        try {
            Transaction transaction = transactionService.addTransaction(new TransactionBuilder().setPayeeId(paramMap.get(SplitWiseConstants.PAYEE))
                    .setRecieverId(paramMap.get(SplitWiseConstants.RECEIVER))
                    .setAmount(Double.parseDouble(paramMap.get(SplitWiseConstants.AMOUNT)))
                    .setMessage(paramMap.get(SplitWiseConstants.MESSAGE))
                    .setTransactionId().setTransactionDate().build());
            if (transaction == null)
                return ResponseEntity.ok("Transaction not recorded. You do not have any pending balance!");
            return ResponseEntity.ok("Success!");
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
}
