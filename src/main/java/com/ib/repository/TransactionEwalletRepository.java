package com.ib.repository;

import com.ib.entity.TransactionEwallet;
import com.ib.entity.TransactionEwalletId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionEwalletRepository extends JpaRepository<TransactionEwallet, TransactionEwalletId> {
}
