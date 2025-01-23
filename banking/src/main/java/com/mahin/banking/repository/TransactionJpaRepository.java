package com.mahin.banking.repository;

import com.mahin.banking.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionJpaRepository extends JpaRepository<Transaction, Integer> {
}
