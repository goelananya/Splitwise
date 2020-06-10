package com.splitwise.service;

import com.splitwise.bo.BalanceBook;
import com.splitwise.bo.User;
import com.splitwise.builder.UserBuilder;
import com.splitwise.dao.UserDao;
import com.splitwise.exception.UserExistsException;
import com.splitwise.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static UserDao userDao;
    private static BalanceBookService balanceBookService;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserDao userDao, BalanceBookService balanceBookService) {
        this.userDao = userDao;
        this.balanceBookService = balanceBookService;
    }

    public User getUser(String username) throws UserNotFoundException {
        logger.info("Searching for user:".concat(username));
        User user = userDao.findByUsername(username);
        if (user == null) throw new UserNotFoundException(username + " not found");
        return user;
    }

    public User addUser(UserBuilder userBuilder) throws UserExistsException {
        if (userDao.findByUsername(userBuilder.getUserName()) != null)
            throw new UserExistsException("User Exists. PLease choose another username");
        BalanceBook book = balanceBookService.addBalanceBook();
        userBuilder.setBalanceBookId(book.getBalanceBookId());
        User user = userBuilder.build();
        return userDao.save(user);
    }

    public boolean doUsersExist(List<String> usernameList) throws UserNotFoundException {
        boolean result = true;
        try {
            for (String username : usernameList) {
                getUser(username);
            }
        } catch (UserNotFoundException exception) {
            result = false;
        }
        return result;
    }

}