package com.mahin.banking.repository;

import com.mahin.banking.entity.Customer;
import com.mahin.banking.entity.CustomerV2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerV2JpaRepository extends JpaRepository<CustomerV2,Integer> {
}
