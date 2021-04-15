package com.ib.service.impl;

import com.ib.entity.Transaction;
import com.ib.exception.InternalServerException;
import com.ib.repository.TransactionRepository;
import com.ib.service.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionImpl implements TransactionDao {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAll() {
        return null;
    }

    @Override
    public Transaction getInfo(Integer ma) {
        return null;
    }

    @Override
    public boolean add(Transaction obj) {
        try {
            transactionRepository.save(obj);
            return  true;
        } catch (Exception ex) {
            throw new InternalServerException("Lỗi khi cập nhật Transaction");
        }
    }

    @Override
    public boolean update(Transaction obj) {
        return false;
    }

    @Override
    public boolean delete(Integer ma) {
        return false;
    }
}
