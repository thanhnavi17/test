package com.ib.service.impl;

import com.ib.entity.OTP;
import com.ib.repository.OTPRepository;
import com.ib.service.OTPDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OTPImpl implements OTPDao {
    @Autowired
    private OTPRepository otpRepository;

    @Override
    public OTP getOtpByAcc(int id) {
        OTP obj = otpRepository.getOtpByAcc(id);
        return obj;
    }

    @Override
    public List<OTP> getAll() {
        return null;
    }

    @Override
    public OTP getInfo(Integer ma) {
        return null;
    }

    @Override
    public boolean add(OTP obj) {
        return false;
    }

    @Override
    public boolean update(OTP obj) {
        return false;
    }

    @Override
    public boolean delete(Integer ma) {
        return false;
    }
}
