package com.mahin.banking.controller;

import com.mahin.banking.entity.Account;
import com.mahin.banking.entity.Customer;
import com.mahin.banking.exception.account.AccountNotFoundException;
import com.mahin.banking.exception.customer.CustomerNotFoundException;
import com.mahin.banking.repository.AccountJpaRepository;
import com.mahin.banking.repository.CustomerJpaRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private CustomerJpaRepository customerJpaRepository;

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    public AccountController(CustomerJpaRepository customerJpaRepository, AccountJpaRepository accountJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
        this.accountJpaRepository = accountJpaRepository;
    }

    @PostMapping("/customers/{id}/accounts")
    public ResponseEntity<Object> createAccount(@Valid @PathVariable Integer id, @Valid @RequestBody Account account){
        Optional<Customer> retrievedCustomer = customerJpaRepository.findById(id);
        if (retrievedCustomer.isEmpty())
            throw new CustomerNotFoundException("id: "+id);
        else {
            account.setCustomer(retrievedCustomer.get());
            accountJpaRepository.save(account);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                    buildAndExpand(account.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    @GetMapping("/customers/{id}/accounts")
    public List<Account> getAllAccountOfCustomer(@Valid @PathVariable Integer id){
        Optional<Customer> retrievedCustomer = customerJpaRepository.findById(id);
        if (retrievedCustomer.isEmpty())
            throw new CustomerNotFoundException("id: "+id);
        else {
           return retrievedCustomer.get().getAccountList();
        }

    }

    @GetMapping("/customers/{customerId}/accounts/{accountNumber}")
    public EntityModel<Object> getAccountOfCustomer(@Valid @PathVariable Integer customerId, @Valid @PathVariable Long accountNumber){
        Optional<Customer> retrievedCustomer = customerJpaRepository.findById(customerId);
        if (retrievedCustomer.isEmpty())
            throw new CustomerNotFoundException("id: "+customerId);
        else {
            for (Account account:retrievedCustomer.get().getAccountList()){
                if (account.getAccountNumber() == accountNumber){
                    System.out.println("Path var acc no: "+accountNumber+" current acc no: "+account.getAccountNumber());
                    EntityModel<Object> entityModel = EntityModel.of(account);
                    WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).
                            getAllAccountOfCustomer(customerId));
                    entityModel.add(linkBuilder.withRel("all-accounts"));
                    return entityModel;
                }
            }
            throw new AccountNotFoundException("account Number: "+ accountNumber);
        }

    }
}
