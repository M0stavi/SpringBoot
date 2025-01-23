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

    @NotNull
    private Long amount;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Account account;

    public Transaction() {
    }

    public Transaction(Integer id, TransactionType transactionType, Account account, Long amount) {
        this.id = id;
        this.transactionType = transactionType;
        this.account = account;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(@NotNull TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public @NotNull Long getAmount() {
        return amount;
    }

    public void setAmount(@NotNull Long amount) {
        this.amount = amount;
    }
}
