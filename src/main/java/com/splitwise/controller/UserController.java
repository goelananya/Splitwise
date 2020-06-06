package com.splitwise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.splitwise.bo.User;
import com.splitwise.builder.UserBuilder;
import com.splitwise.exception.UserNotFoundException;
import com.splitwise.service.UserService;
import com.splitwise.util.SplitWiseConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("splitwise/user")
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    private User createUser(@RequestBody String param) throws JsonProcessingException {
        logger.info("CREATE USER".concat(param));
        Map<String, String> expenseParams = new ObjectMapper().readValue(param, Map.class);
        return userService.addUser(new UserBuilder().setUserName(expenseParams.get(SplitWiseConstants.USERNAME)).setEmail(expenseParams.get(SplitWiseConstants.EMAIL)).setPhoneNumber(expenseParams.get(SplitWiseConstants.PHONE)).setBalanceBookId().build());
    }

    @GetMapping
    private User getUser(@RequestBody String param) throws JsonProcessingException, UserNotFoundException {
        logger.info("GET USER: ".concat(param));
        Map<String, String> expenseParams = new ObjectMapper().readValue(param, Map.class);
        return userService.getUser(expenseParams.get(SplitWiseConstants.USERNAME));
    }
}