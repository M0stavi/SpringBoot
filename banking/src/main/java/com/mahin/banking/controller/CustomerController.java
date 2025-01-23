package com.mahin.banking.controller;

import com.mahin.banking.entity.Customer;
import com.mahin.banking.entity.CustomerV2;
import com.mahin.banking.exception.customer.CustomerNotFoundException;
import com.mahin.banking.repository.CustomerJpaRepository;
import com.mahin.banking.repository.CustomerV2JpaRepository;
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

    @Autowired
    private CustomerV2JpaRepository customerV2JpaRepository;

    @PostMapping("/customers/v1")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer){
        customerJpaRepository.save(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/customers/v2")
    public ResponseEntity<CustomerV2> createCustomerV2(@Valid @RequestBody CustomerV2 customer){
        customerV2JpaRepository.save(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/customers", params = "version=1")
    public List<Customer> getAllCustomer(){
        return customerJpaRepository.findAll();
    }

    @GetMapping(path = "/customers", params = "version=2")
    public List<CustomerV2> getAllCustomerV2(){
        return customerV2JpaRepository.findAll();
    }

    @GetMapping("/customers/v1/{id}")
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

    @GetMapping("/customers/v2/{id}")
    public EntityModel<CustomerV2> getCustomerV2ById(@Valid @PathVariable Integer id){
        Optional<CustomerV2> retrievedCustomer = customerV2JpaRepository.findById(id);
        if (retrievedCustomer.isEmpty())
            throw new CustomerNotFoundException("id: "+id);
        else {
            EntityModel<CustomerV2> entityModel = EntityModel.of(retrievedCustomer.get());
            WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
                    this.getClass()).getAllCustomerV2());
            entityModel.add(linkBuilder.withRel("all-customers"));
            return entityModel;
        }

    }

    @PutMapping(path = "/customers/{id}", headers = "CUSTOMER-VERSION=1")
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

    @PutMapping(path = "/customers/{id}", headers = "CUSTOMER-VERSION=2")
    public ResponseEntity<Customer> updateCustomerV2(@Valid @PathVariable Integer id, @Valid @RequestBody CustomerV2 customer){
        Optional<CustomerV2> retrievedCustomer = customerV2JpaRepository.findById(id);
        if (retrievedCustomer.isEmpty())
            throw new CustomerNotFoundException("Id: "+id);
        else {
            CustomerV2 updatedCustomer = retrievedCustomer.get();
            updatedCustomer.setFirstName(customer.getFirstName());
            updatedCustomer.setLastName(customer.getLastName());
            updatedCustomer.setBirthDate(customer.getBirthDate());
            updatedCustomer.setAddress(customer.getAddress());
            updatedCustomer.setAccountList(customer.getAccountList());
            customerV2JpaRepository.save(updatedCustomer);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                    buildAndExpand(customer.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    @DeleteMapping(path = "/customers/{id}", produces = "customer/v1+json")
    public void deleteCustomerById(@Valid @PathVariable Integer id){
        Optional<Customer> retrievedCustomer = customerJpaRepository.findById(id);
        if (retrievedCustomer.isEmpty())
            throw new CustomerNotFoundException("id: "+id);
        else {
            customerJpaRepository.delete(retrievedCustomer.get());
        }
    }

    @DeleteMapping(path = "/customers/{id}", produces = "customer/v2+json")
    public void deleteCustomerV2ById(@Valid @PathVariable Integer id){
        Optional<CustomerV2> retrievedCustomer = customerV2JpaRepository.findById(id);
        if (retrievedCustomer.isEmpty())
            throw new CustomerNotFoundException("id: "+id);
        else {
            customerV2JpaRepository.delete(retrievedCustomer.get());
        }
    }
}
