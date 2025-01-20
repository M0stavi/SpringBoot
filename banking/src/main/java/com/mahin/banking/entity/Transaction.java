package com.mahin.banking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mahin.banking.entity.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Account account;

    @NotNull
    private Long amount;
}
