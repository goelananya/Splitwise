package com.splitwise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@RequestMapping("splitwise/transaction")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity addTransaction(@RequestBody String param) throws JsonProcessingException, UserNotFoundException {
        Map<String, String> transactionParams = new ObjectMapper().readValue(param, Map.class);
        try {
            transactionService.addTransaction(new TransactionBuilder().setPayeeId(transactionParams.get(SplitWiseConstants.PAYEE))
                    .setRecieverId(transactionParams.get(SplitWiseConstants.RECEIVER))
                    .setAmount(Double.parseDouble(transactionParams.get(SplitWiseConstants.AMOUNT)))
                    .setMessage(transactionParams.get(SplitWiseConstants.MESSAGE))
                    .setTransactionId().setTransactionDate().build());
            return ResponseEntity.ok("Success!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
}
