package com.splitwise.service;

import com.splitwise.bo.BalanceBook;
import com.splitwise.bo.User;
import com.splitwise.dao.UserDao;
import com.splitwise.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public User addUser(User user) {
        BalanceBook book = new BalanceBook(user.getBalanceBookId());
        balanceBookService.addBalanceBook(book);
        userDao.save(user);
        logger.info(user.toString().concat("Added to db"));
        return user;
    }
}