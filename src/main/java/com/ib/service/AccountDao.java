package com.ib.service;

import com.ib.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountDao extends ActionItf<Account, Integer> {
    public List<Account> findByCif(String cif);
    public Account findByNameAndCif (String name, String cif);
}
