package com.splitwise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.splitwise.bo.Balance;
import com.splitwise.exception.UserNotFoundException;
import com.splitwise.service.BalanceService;
import com.splitwise.util.SplitWiseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("splitwise/balance")
@RestController
public class BalanceController {

    private static BalanceService balanceService;

    @Autowired
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping
    private ResponseEntity getUserBal(@RequestBody String param) throws JsonProcessingException, UserNotFoundException {
        Map<String, String> expenseParams = new ObjectMapper().readValue(param, Map.class);
        try {
            List<Balance> balanceList = balanceService.getBalanceListForUser(expenseParams.get(SplitWiseConstants.USERNAME));
            return ResponseEntity.ok(balanceList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
}