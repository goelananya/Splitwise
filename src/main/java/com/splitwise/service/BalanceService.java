package com.splitwise.service;

import com.splitwise.bo.Balance;
import com.splitwise.bo.BalanceBook;
import com.splitwise.bo.User;
import com.splitwise.dao.BalanceDao;
import com.splitwise.exception.UserNotFoundException;
import com.splitwise.util.SplitWiseConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BalanceService {

    private static BalanceDao balanceDao;
    private static UserService userService;
    private static BalanceBookService balanceBookService;
    private static final Logger logger = LoggerFactory.getLogger(BalanceService.class);

    @Autowired
    public BalanceService(BalanceDao balanceDao, UserService userService, BalanceBookService balanceBookService) {
        this.balanceDao = balanceDao;
        this.userService = userService;
        this.balanceBookService = balanceBookService;
    }

    public Balance getBalanceById(Long balanceId) {
        return balanceDao.findByBalanceId(balanceId);
    }

    public int addBalance(Balance balance) {
        balanceDao.save(balance);
        return 1;
    }

    public int updateBalance(String userOne, String userTwo, Double amount) throws UserNotFoundException {
        Balance balance = getBalanceBetweenUsers(userOne, userTwo);
        if (balance == null) {
            logger.info("First transaction between:".concat(userOne).concat(SplitWiseConstants.SEPARATOR).concat(userTwo));
            balance = new Balance(userTwo, userOne, amount);
            addBalance(balance);
            logger.info(balance.toString().concat(":Added to balance table"));
            logger.info(balance.getBalanceId() + ":Adding to balance book");
            BalanceBook bookOne = balanceBookService.getBalanceBookByBookId(userService.getUser(userOne).getBalanceBookId());
            bookOne.addBalanceId(balance.getBalanceId());
            logger.info("Balance book updated for:".concat(userOne));
            BalanceBook bookTwo = balanceBookService.getBalanceBookByBookId(userService.getUser(userTwo).getBalanceBookId());
            bookTwo.addBalanceId(balance.getBalanceId());
            logger.info("Balance book updated for:".concat(userTwo));
            balanceBookService.updateBalanceBook(bookOne);
            balanceBookService.updateBalanceBook(bookTwo);
        } else {
            logger.info("Existing balance between users:".concat(balance.toString()));
            if (balance.getUserOne().equals(userOne)) {
                balance.setAmount(balance.getAmount() - amount);
            } else {
                balance.setAmount(balance.getAmount() + amount);
            }
            addBalance(balance);
        }
        logger.info("Balance between users after update:".concat(balance.toString()));
        return 0;
    }

    public Balance getBalanceBetweenUsers(String userOne, String userTwo) throws UserNotFoundException {
        logger.info("Searching for existing balance between".concat(userOne).concat(SplitWiseConstants.SEPARATOR).concat(userTwo));
        User user = userService.getUser(userTwo);
        List<String> balanceIdList = balanceBookService.getBalanceBookByBookId(user.getBalanceBookId()).getBalanceIdList();
        if (balanceIdList == null) return null;
        Balance balance = null;
        for (String balanceId : balanceIdList) {
            if (balanceId.equals("")) return null;
            Balance bal = getBalanceById(Long.parseLong(balanceId));
            balance = (bal.getUserOne().equals(userOne) || bal.getUserTwo().equals(userOne)) ? bal : null;
            if (balance != null) return balance;
        }
        return balance;
    }

    public List<Balance> getBalanceListForUser(String username) throws UserNotFoundException {
        List<Balance> balanceList = new ArrayList<>();
        Long balanceBookId = userService.getUser(username).getBalanceBookId();
        logger.info("{username, balance bookId}".concat(username) + balanceBookId);
        BalanceBook book = balanceBookService.getBalanceBookByBookId(balanceBookId);
        if (book.getBalanceIdList() == null) return null;
        for (String balanceId : book.getBalanceIdList()) {
            balanceList.add(getBalanceById(Long.parseLong(balanceId)));
        }
        return balanceList;
    }

    public List<Balance> getAll() {
        List<Balance> balances = new ArrayList<>();
        for (Balance balance : balanceDao.findAll()) {
            balances.add(balance);
        }
        return balances;
    }

}