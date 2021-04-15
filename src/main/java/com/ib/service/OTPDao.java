package com.ib.service;

import com.ib.entity.OTP;
import org.springframework.stereotype.Service;

@Service
public interface OTPDao extends ActionItf<OTP,Integer> {
    public OTP getOtpByAcc (int id);
}
