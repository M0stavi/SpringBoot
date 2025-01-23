package com.mahin.banking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private Long accountNumber;

    @NotNull
    private Long balance;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Customer customer;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private CustomerV2 customerV2;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<Card> cards;

    public Account() {
    }

    public Account(Integer id, long accountNumber, long balance, Customer customer, List<Transaction> transactions, List<Card> cards) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customer = customer;
        this.transactions = transactions;
        this.cards = cards;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(@NotNull Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    @NotNull
    public Long getBalance() {
        return balance;
    }

    public void setBalance(@NotNull Long balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
