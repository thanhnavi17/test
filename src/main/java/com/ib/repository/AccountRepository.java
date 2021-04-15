package com.ib.repository;

import com.ib.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT a FROM Account a WHERE a.cif = ?1")
    List<Account> findByCif(String cif);

    @Query("SELECT a FROM Account a WHERE a.account_number = :num AND a.cif = :cif")
    Account findAccount(@Param("num") String num, @Param("cif") String cif);
}
