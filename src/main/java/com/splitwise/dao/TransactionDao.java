package com.splitwise.dao;

import com.splitwise.bo.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionDao extends CrudRepository<Transaction, Long> {
}
