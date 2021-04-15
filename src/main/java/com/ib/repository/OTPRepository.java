package com.ib.repository;

import com.ib.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Integer> {
    @Query("SELECT o FROM OTP o WHERE o.acc_id = ?1")
    OTP getOtpByAcc(int id);
}
