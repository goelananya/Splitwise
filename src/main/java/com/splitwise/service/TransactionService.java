package com.splitwise.service;

import com.splitwise.bo.Balance;
import com.splitwise.bo.Transaction;
import com.splitwise.dao.TransactionDao;
import com.splitwise.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionDao transactionDao;
    private final BalanceService balanceService;
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public TransactionService(TransactionDao transactionDao, BalanceService balanceService) {
        this.transactionDao = transactionDao;
        this.balanceService = balanceService;
    }

    public Transaction addTransaction(Transaction transaction) throws UserNotFoundException {
        logger.info("Adding transaction:".concat(transaction.toString()));
        Balance balance = balanceService.getBalanceBetweenUsers(transaction.getPayeeId(), transaction.getReceiverId());
        if (balance == null) return null;
        logger.info("Balance between users before transaction:".concat(balance.toString()));
        if (balance.getUserTwo().equals(transaction.getPayeeId())) {
            balance.setAmount(balance.getAmount() + transaction.getAmount());
        } else {
            balance.setAmount(balance.getAmount() - transaction.getAmount());
        }
        balanceService.addBalance(balance);
        transactionDao.save(transaction);
        logger.info("Balance between users after transaction:".concat(balance.toString()));
        return transaction;
    }
}