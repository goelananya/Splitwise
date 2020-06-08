package com.splitwise.service;

import com.splitwise.bo.Expense;
import com.splitwise.dao.ExpenseDao;
import com.splitwise.exception.UserNotFoundException;
import com.splitwise.util.SplitWiseConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseService.class);
    private static ExpenseDao expenseDao;
    private static BalanceService balanceService;

    @Autowired
    public ExpenseService(ExpenseDao expenseDao, BalanceService balanceService) {
        this.balanceService = balanceService;
        this.expenseDao = expenseDao;
    }

    public Expense addExpense(Expense expense) throws UserNotFoundException {
        logger.info("Updating expense table with".concat(expense.toString()));
        logger.info("Successfully updated expense table with".concat(expense.toString()));
        String payee = expense.getCreatedBy();
        List<String> borrowerList = expense.getParticipantUsersList();
        List<Double> share = getParticipantShare(expense.getSplitRatio(), expense.getAmount());
        for (int i = 0; i < borrowerList.size(); i++) {
            logger.info("Updating balance for:".concat(payee).concat(SplitWiseConstants.SEPARATOR).concat(borrowerList.get(i)));
            logger.info(borrowerList.get(i).concat("need to pay").concat(Double.toString(share.get(i))));
            balanceService.updateBalance(payee, borrowerList.get(i), share.get(i));
        }
        expenseDao.save(expense);
        return expense;
    }

    public List<Double> getParticipantShare(String ratio, Double amount) {
        String[] arr = ratio.split(":");
        List<Double> shareList = new ArrayList<>();
        int count = arr.length;
        Double total = 0.0;
        for (String str : arr) {
            total = total + Double.parseDouble(str);
        }
        for (String str : arr) {
            shareList.add(Double.parseDouble(str) * amount / total);
        }
        return shareList;
    }

}