package com.splitwise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.splitwise.builder.UserBuilder;
import com.splitwise.exception.UserExistsException;
import com.splitwise.exception.UserNotFoundException;
import com.splitwise.service.UserService;
import com.splitwise.util.SplitWiseConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(SplitWiseConstants.USER_ENDPOINT)
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    private ResponseEntity createUser(@RequestBody String param) throws JsonProcessingException {
        logger.info("CREATE USER".concat(param));
        Map<String, String> expenseParams = new ObjectMapper().readValue(param, Map.class);
        try {
            userService.addUser(new UserBuilder().setUserName(expenseParams.get(SplitWiseConstants.USERNAME)).setEmail(expenseParams.get(SplitWiseConstants.EMAIL)).setPhoneNumber(expenseParams.get(SplitWiseConstants.PHONE)));
            return ResponseEntity.ok("User creation was successful");
        } catch (UserExistsException e) {
            return ResponseEntity.badRequest().body("User creation failed - Username exists");
        }
    }

    @GetMapping
    private Object getUser(@RequestBody String param) throws JsonProcessingException, UserNotFoundException {
        logger.info("GET USER: ".concat(param));
        Map<String, String> paramMap = new ObjectMapper().readValue(param, Map.class);
        try {
            return userService.getUser(paramMap.get(SplitWiseConstants.USERNAME));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("User does not exit");
        }
    }
}