package com.splitwise.service;

import com.splitwise.bo.Balance;
import com.splitwise.bo.Expense;
import com.splitwise.dao.BalanceDao;
import com.splitwise.dao.ExpenseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {


    private final ExpenseDao expenseDao;
    private final BalanceService balanceService;

    @Autowired
    public ExpenseService(@Qualifier("fakeDao") ExpenseDao expenseDao, BalanceService balanceService) {
        this.balanceService = balanceService;
        this.expenseDao = expenseDao;
    }
    /**
     * @param expense
     * @return
     */
    public int addExpense(Expense expense){
//        return expenseDao.insertExpense(expense);
        String payee = expense.getCreatedBy();
        String ratio = expense.getSplitRatio();
        String[] borrowers = expense.getParticipantUsers().split(",");
        for(String borrower: borrowers) {
            Balance balance = BalanceService.getBalanceBetweenUsers(payee, borrower);
            if(balance==null) {
                balance = new Balance(payee, borrower, getShare(expense.getSplitRatio(), expense.getAmount(), 1));
                balanceService.addBalance(balance);

                //add new balance object
                //add balanceid to balancebook
            }
            else {
                //update balance book
            }
        }
        return 0;
    }

    public static Double getShare(String ratio, Double amount, int index){
        //TODO
        return null;
    }

}
