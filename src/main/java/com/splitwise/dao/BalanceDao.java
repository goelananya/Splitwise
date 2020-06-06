package com.splitwise.dao;

import com.splitwise.bo.Balance;

import java.util.UUID;

public interface BalanceDao {
    int insertBalance(UUID id, Balance balance);

    default int  insertBalance(Balance balance) {
        UUID id = UUID.randomUUID();
        return insertBalance(id, balance);
    }

    Balance getBalanceById(String balanceId) {

    }

}
