package com.splitwise.dao;

import com.splitwise.bo.BalanceBook;
import org.springframework.data.repository.CrudRepository;

public interface BalanceBookDao extends CrudRepository<BalanceBook, String> {
    BalanceBook findByBalanceBookId(Long balanceBookId);

}