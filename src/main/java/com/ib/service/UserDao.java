package com.ib.service;

import com.ib.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserDao extends ActionItf<User, Integer> {
    public User check(String username);
}
