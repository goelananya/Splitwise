package com.splitwise.service;

import com.splitwise.bo.Balance;
import com.splitwise.dao.BalanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

    private final BalanceDao balanceDao;

    @Autowired
    public BalanceService(BalanceDao balanceDao) {
        this.balanceDao = balanceDao;
    }

    public Balance getBalanceById(Long balanceId) {
        return balanceDao.findByBalanceId(balanceId);
    }

    public int addBalance(Balance balance) {
        System.out.println("|||||||||||||GOING GOOD||||||||||||||||||||||||");
        balanceDao.save(balance);
        System.out.println("|||||||||||||COMPLETE TODAY||||||||||||||||||||||||");
        return 1;
    }


    public static Balance getBalanceBetweenUsers(String userOne, String userTwo) {
/*        String[] balanceIdList = BalanceBookService.getBalanceIdList(userTwo);
        Balance balance = null;
        for(String balanceId: balanceIdList) {
            balance = (getBalanceObject(balanceId).getUserOne().equals(userOne)|| getBalanceObject(balanceId).getUserTwo().equals(userOne))? getBalanceObject(balanceId):null;
            if(balance!=null) return balance;
        }
        return balance;*/
        return null;
    }
}
