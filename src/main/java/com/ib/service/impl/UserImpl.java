package com.ib.service.impl;

import com.ib.entity.User;
import com.ib.repository.UserRepository;
import com.ib.service.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User check(String username) {
        User obj = userRepository.findByUserName(username);
        return obj;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getInfo(Integer ma) {
        return null;
    }

    @Override
    public boolean add(User obj) {
        return false;
    }

    @Override
    public boolean update(User obj) {
        return false;
    }

    @Override
    public boolean delete(Integer ma) {
        return false;
    }
}
