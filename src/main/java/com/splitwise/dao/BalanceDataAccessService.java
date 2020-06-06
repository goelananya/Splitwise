package com.splitwise.dao;

import com.splitwise.bo.Balance;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("balanceDao")
public class BalanceDataAccessService implements BalanceDao{
    private static List<Balance> list = new ArrayList<>();

    @Override
    public int insertBalance(UUID id, Balance balance) {
        list.add(balance);
        return 1;
    }


}
