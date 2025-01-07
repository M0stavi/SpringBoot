package com.mahin.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyServiceController {
    @Autowired
    private CurrencyServiceConfiguration configuration;

    public CurrencyServiceController(CurrencyServiceConfiguration configuration){
        this.configuration = configuration;
    }

    @RequestMapping("/currency-service")
    public CurrencyServiceConfiguration getConfiguration(){
        return this.configuration;
    }
}
