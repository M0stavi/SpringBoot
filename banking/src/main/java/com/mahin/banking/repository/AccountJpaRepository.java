package com.mahin.banking.repository;

import com.mahin.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<Account, Integer> {
    public Account findByAccountNumber(Long accountNumber);
}
