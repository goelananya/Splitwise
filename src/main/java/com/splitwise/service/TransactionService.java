package com.splitwise.service;

import com.splitwise.bo.Balance;
import com.splitwise.bo.Transaction;

public class TransactionService {

    public static String addTransaction(Transaction transaction){

        Balance balance = BalanceService.getBalanceBetweenUsers(transaction.getPayeeId(), transaction.getRecieverId());
        if(balance.getUserTwo().equals(transaction.getPayeeId())){
            balance.setAmount(balance.getAmount()-transaction.getAmount());
        }
        else{
            balance.setAmount(balance.getAmount()+transaction.getAmount());
        }

        return balance.getAmount().toString();
    }

}
