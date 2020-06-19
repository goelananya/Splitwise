package com.splitwise.service;

import com.splitwise.bo.Balance;
import com.splitwise.bo.BalanceBook;
import com.splitwise.bo.User;
import com.splitwise.dao.BalanceBookDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BalanceBookService {

    private static BalanceBookDao balanceBookDao;
    private static final Logger logger = LoggerFactory.getLogger(BalanceBookService.class);

    @Autowired
    public BalanceBookService(BalanceBookDao balanceBookDao) {
        this.balanceBookDao = balanceBookDao;
    }

    public BalanceBook getBalanceBookByBookId(Long balanceBookId) {
        logger.info("Searching for balance book with id:" + balanceBookId);
        BalanceBook book = balanceBookDao.findByBalanceBookId(balanceBookId);
        logger.info("Found Balance Book for given id:".concat(book.toString()));
        return book;
    }

    public BalanceBook addBalanceBook() {
        BalanceBook balanceBook = balanceBookDao.save(new BalanceBook());
        logger.info("balance book added as:".concat(balanceBook.toString()));
        return balanceBook;
    }

    public List<BalanceBook> getBalanceBooksForAllUsers() {
        List<BalanceBook> balanceBookList = new ArrayList<>();
        for (BalanceBook balanceBook : balanceBookDao.findAll()) {
            balanceBookList.add(balanceBook);
        }
        return balanceBookList;
    }

    public BalanceBook updateBalanceBook(BalanceBook balanceBook) {
        balanceBook = balanceBookDao.save(balanceBook);
        logger.info("balance book updated as:".concat(balanceBook.toString()));
        return balanceBook;
    }

    public BalanceBook updateBalanceBook(Long balanceBookId, Long balanceId) {
        return balanceBookDao.save(updateBalanceBook(getBalanceBookByBookId(balanceBookId).addBalanceId(balanceId)));
    }

    public void updateBalanceBook(User userOne, User userTwo, Balance balance) {
        updateBalanceBook(userOne.getBalanceBookId(), balance.getBalanceId());
        updateBalanceBook(userTwo.getBalanceBookId(), balance.getBalanceId());
    }
}