package com.mahin.banking.repository;

import com.mahin.banking.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardJpaRepository extends JpaRepository<Card, Integer> {
    public Card findByCardNumber(Long cardNumber);
}
