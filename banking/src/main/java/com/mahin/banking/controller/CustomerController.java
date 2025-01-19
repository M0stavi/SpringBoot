package com.mahin.banking.controller;

import com.mahin.banking.entity.Customer;
import com.mahin.banking.repository.CustomerJpaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerJpaRepository customerJpaRepository;

    @PostMapping("/customers")
    public void createUser(@Valid @RequestBody Customer customer){
        customerJpaRepository.save(customer);
    }

    @GetMapping("/users")
    public List<Customer> getAllUser(){
        return customerJpaRepository.findAll();
    }
}
