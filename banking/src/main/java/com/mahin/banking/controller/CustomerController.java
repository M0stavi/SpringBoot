package com.mahin.banking.controller;

import com.mahin.banking.entity.Customer;
import com.mahin.banking.exception.customer.CustomerNotFoundException;
import com.mahin.banking.repository.CustomerJpaRepository;
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
public class CustomerController {
    @Autowired
    private CustomerJpaRepository customerJpaRepository;

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer){
        customerJpaRepository.save(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomer(){
        return customerJpaRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public EntityModel<Customer> getCustomerById(@Valid @PathVariable Integer id){
        Optional<Customer> retrievedCustomer = customerJpaRepository.findById(id);
        if (retrievedCustomer.isEmpty())
            throw new CustomerNotFoundException("id: "+id);
        else {
            EntityModel<Customer> entityModel = EntityModel.of(retrievedCustomer.get());
            WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
                    this.getClass()).getAllCustomer());
            entityModel.add(linkBuilder.withRel("all-customers"));
            return entityModel;
        }

    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@Valid @PathVariable Integer id, @Valid @RequestBody Customer customer){
        Optional<Customer> retrievedCustomer = customerJpaRepository.findById(id);
        if (retrievedCustomer.isEmpty())
            throw new CustomerNotFoundException("Id: "+id);
        else {
            Customer updatedCustomer = retrievedCustomer.get();
            updatedCustomer.setName(customer.getName());
            updatedCustomer.setBirthDate(customer.getBirthDate());
            updatedCustomer.setAddress(customer.getAddress());
            updatedCustomer.setAccountList(customer.getAccountList());
            customerJpaRepository.save(updatedCustomer);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                    buildAndExpand(customer.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomerById(@Valid @PathVariable Integer id){
        Optional<Customer> retrievedCustomer = customerJpaRepository.findById(id);
        if (retrievedCustomer.isEmpty())
            throw new CustomerNotFoundException("id: "+id);
        else {
            customerJpaRepository.delete(retrievedCustomer.get());
        }
    }
}
