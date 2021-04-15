package com.ib.repository;

import com.ib.entity.EwalletLinked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EwalletLinkedRepository extends JpaRepository<EwalletLinked, Integer> {
}
