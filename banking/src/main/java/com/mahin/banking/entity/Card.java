package com.mahin.banking.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mahin.banking.entity.enums.CardType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@JsonFilter("CvvFilter")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private Long cardNumber;

    @NotNull
    private CardType cardType;

    @Digits(integer = 3, fraction = 0)
    @NotNull
    private Integer cvv;

    private String name;

    private Long balance=0L;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Account account;

    public Card() {
    }

    public Card(Integer id, Long cardNumber, CardType cardType, Account account, Integer cvv) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.account = account;
        this.cvv = cvv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(@NotNull Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public @NotNull CardType getCardType() {
        return cardType;
    }

    public void setCardType(@NotNull CardType cardType) {
        this.cardType = cardType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public @Digits(integer = 3, fraction = 0) @NotNull Integer getCvv() {
        return cvv;
    }

    public void setCvv(@Digits(integer = 3, fraction = 0) @NotNull Integer cvv) {
        this.cvv = cvv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
