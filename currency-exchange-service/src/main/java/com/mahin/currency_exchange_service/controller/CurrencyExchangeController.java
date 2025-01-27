package com.mahin.currency_exchange_service.controller;

import com.mahin.currency_exchange_service.CurrencyExchangeJpaRepository;
import com.mahin.currency_exchange_service.entity.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeJpaRepository exchangeJpaRepository;

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getCurrencyExchange(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = exchangeJpaRepository.findByCurrencyFromAndCurrencyTo(from, to);
        if (currencyExchange == null)
            throw new RuntimeException();
        else {
            currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
            return currencyExchange;
        }

    }
}
