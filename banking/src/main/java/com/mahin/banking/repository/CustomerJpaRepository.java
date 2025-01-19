package com.mahin.banking.repository;

import com.mahin.banking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<Customer,Integer> {
}
