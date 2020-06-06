package com.splitwise.service;

import com.splitwise.bo.BalanceBook;

public class BalanceBookService {

    public static BalanceBook getBalanceBook(String balanceBookId) {
        //TODO
        return null;
    }

    public static String[] getBalanceIdList(String userId) {
        return getBalanceBook(UserService.getUser(userId).getBalanceBookId()).getBalanceIdList().split(",");
    }
}
