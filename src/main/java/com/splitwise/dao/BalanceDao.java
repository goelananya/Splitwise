package com.splitwise.dao;

import com.splitwise.bo.Balance;
import org.springframework.data.repository.CrudRepository;

public interface BalanceDao extends CrudRepository<Balance, Long> {
    Balance findByBalanceId(Long balanceId);

}
