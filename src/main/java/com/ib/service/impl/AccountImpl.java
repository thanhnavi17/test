package com.ib.service.impl;

import com.ib.entity.Account;
import com.ib.repository.AccountRepository;
import com.ib.service.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountImpl implements AccountDao {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> findByCif(String cif) {
        return null;
    }

    @Override
    public Account findByNameAndCif(String name, String cif) {
        Account obj = accountRepository.findAccount(name, cif);
        return obj;
    }

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public Account getInfo(Integer ma) {
        return null;
    }

    @Override
    public boolean add(Account obj) {
        return false;
    }

    @Override
    public boolean update(Account obj) {
        return false;
    }

    @Override
    public boolean delete(Integer ma) {
        return false;
    }
}
