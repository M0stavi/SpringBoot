package com.mahin.banking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private long accountNumber;

    private long balance;

    @ManyToOne
    @JsonIgnore
    private Customer customer;
}
