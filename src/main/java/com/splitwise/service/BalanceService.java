package com.splitwise.service;

import com.splitwise.bo.Balance;
import com.splitwise.dao.BalanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

    private final BalanceDao balanceDao;

    @Autowired
    public BalanceService(@Qualifier("balanceDao") BalanceDao balanceDao) {this.balanceDao = balanceDao;}



    public static Balance getBalanceObject(String balanceId) {
     //TODO
        return null;
    }

    public int addBalance(Balance balance) {
        balance.setBalanceId("212");
        return balanceDao.insertBalance(balance);
    }


/*    public static Balance getBalanceBetweenUsers(String userOne, String userTwo) {
        String[] balanceIdList = BalanceBookService.getBalanceIdList(userTwo);
        Balance balance = null;
        for(String balanceId: balanceIdList) {
            balance = (getBalanceObject(balanceId).getUserOne().equals(userOne)|| getBalanceObject(balanceId).getUserTwo().equals(userOne))? getBalanceObject(balanceId):null;
            if(balance!=null) return balance;
        }
        return balance;
    }*/
}
