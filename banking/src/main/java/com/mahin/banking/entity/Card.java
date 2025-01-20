package com.mahin.banking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mahin.banking.entity.enums.CardType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Long cardNumber;

    @NotNull
    private CardType cardType;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Account account;

    @Size(min = 3, max = 3)
    @NotNull
    private Integer cvv;
}
