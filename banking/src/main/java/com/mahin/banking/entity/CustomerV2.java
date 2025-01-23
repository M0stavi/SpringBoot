package com.mahin.banking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
public class CustomerV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "first name cannot be null")
    @Size(min = 2, message = "first Name must contain at least 2 characters")
    private String firstName;

    @NotNull(message = "last name cannot be null")
    @Size(min = 2, message = "last Name must contain at least 2 characters")
    private String lastName;

    @NotNull(message = "Customer address cannot be null")
    @Size(min = 2, message = "address must contain at least 2 characters")
    private String address;

    @NotNull(message = "customer birth date cannot be null")
    @Past(message = "birthDate must be in the past")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "customerV2")
    @JsonIgnore
    private List<Account> accountList;

    public CustomerV2() {
    }

    public CustomerV2(Integer id, String firstName, String lastName, String address, LocalDate birthDate, List<Account> accountList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public @NotNull(message = "first name cannot be null") @Size(min = 2, message = "first Name must contain at least 2 characters") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull(message = "first name cannot be null") @Size(min = 2, message = "first Name must contain at least 2 characters") String firstName) {
        this.firstName = firstName;
    }

    public @NotNull(message = "last name cannot be null") @Size(min = 2, message = "last Name must contain at least 2 characters") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull(message = "last name cannot be null") @Size(min = 2, message = "last Name must contain at least 2 characters") String lastName) {
        this.lastName = lastName;
    }

    public @NotNull(message = "Customer message cannot be null") @Size(min = 2, message = "address must contain at least 2 characters") String getAddress() {
        return address;
    }

    public void setAddress(@NotNull(message = "Customer message cannot be null") @Size(min = 2, message = "address must contain at least 2 characters") String address) {
        this.address = address;
    }

    public @NotNull(message = "customer birth date cannot be null") @Past(message = "birthDate must be in the past") LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@NotNull(message = "customer birth date cannot be null") @Past(message = "birthDate must be in the past") LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
