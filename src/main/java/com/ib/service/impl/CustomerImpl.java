package com.ib.service.impl;

import com.ib.entity.Account;
import com.ib.entity.Customer;
import com.ib.repository.AccountRepository;
import com.ib.repository.CustomerRepository;
import com.ib.service.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerImpl implements CustomerDao {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Customer> getAll() {
        List<Customer> lst = customerRepository.findAll();
        return lst;
    }

    @Override
    public Customer getInfo(Integer ma) {

        return null;
    }

    @Override
    public boolean add(Customer obj) {
        return false;
    }

    @Override
    public boolean update(Customer obj) {
        return false;
    }

    @Override
    public boolean delete(Integer ma) {
        return false;
    }

    @Override
    public Customer findByIdCar(int id) {
        Customer obj = customerRepository.findByIdCard(id);
        return obj;
    }
}
