package com.mahin.banking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "customer name cannot be null")
    @Size(min = 2, message = "Name must contain at least 2 characters")
    private String name;

    @NotNull(message = "Customer message cannot be null")
    @Size(min = 2, message = "address must contain at least 2 characters")
    private String address;

    @NotNull(message = "customer birth date cannot be null")
    @Past(message = "birthDate must be in the past")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Account> accountList;

    public Customer() {
    }

    public Customer(Integer id, String name, String address, LocalDate birthDate, List<Account> accountList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.accountList = accountList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @Size(min = 2) String getName() {
        return name;
    }

    public void setName(@Size(min = 2) String name) {
        this.name = name;
    }

    public @Size(min = 2) String getAddress() {
        return address;
    }

    public void setAddress(@Size(min = 2) String address) {
        this.address = address;
    }

    public @Past LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@Past LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
