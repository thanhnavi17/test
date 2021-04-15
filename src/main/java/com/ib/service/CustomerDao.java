package com.ib.service;

import com.ib.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerDao extends ActionItf<Customer, Integer> {
    public Customer findByIdCar(int id);
}
