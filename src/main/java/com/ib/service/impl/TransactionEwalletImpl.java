package com.ib.service.impl;

import com.ib.entity.TransactionEwallet;
import com.ib.exception.InternalServerException;
import com.ib.repository.TransactionEwalletRepository;
import com.ib.service.TransactionEwalletDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionEwalletImpl implements TransactionEwalletDao {
    @Autowired
    TransactionEwalletRepository teRepository;

    @Override
    public List<TransactionEwallet> getAll() {
        return null;
    }

    @Override
    public TransactionEwallet getInfo(Integer ma) {
        return null;
    }

    @Override
    public boolean add(TransactionEwallet obj) {
        try {
            teRepository.save(obj);
            return  true;
        } catch (Exception ex) {
            throw new InternalServerException("Lỗi khi cập nhật Transaction Ewallet");
        }
    }

    @Override
    public boolean update(TransactionEwallet obj) {
        return false;
    }

    @Override
    public boolean delete(Integer ma) {
        return false;
    }
}
