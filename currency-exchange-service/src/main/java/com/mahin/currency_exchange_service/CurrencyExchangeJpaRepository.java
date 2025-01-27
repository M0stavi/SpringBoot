package com.mahin.currency_exchange_service;

import com.mahin.currency_exchange_service.entity.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeJpaRepository extends JpaRepository<CurrencyExchange, Long> {
    CurrencyExchange findByCurrencyFromAndCurrencyTo(String currencyFrom, String currencyTo);
}
