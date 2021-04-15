package com.ib.service;

import com.ib.entity.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface TransactionDao extends ActionItf<Transaction, Integer> {
}
