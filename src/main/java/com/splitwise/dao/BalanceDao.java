package com.splitwise.dao;

import com.splitwise.bo.Balance;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BalanceDao extends CrudRepository<Balance, Long> {
    Balance findByBalanceId(Long balanceId);

}
