package com.mahin.currency_conversion_service.controller;

import com.mahin.currency_conversion_service.entity.CurrencyConversion;
import com.mahin.currency_conversion_service.proxy.CurrencyConversionProxy;
import org.apache.hc.client5.http.auth.AuthStateCacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyConversionProxy currencyConversionProxy;

    @Autowired
    private Environment environment;
//    http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10

//    {
//        "id": 10001,
//            "from": "USD",
//            "to": "INR",
//            "conversionMultiple": 65.00,
//            "quantity": 10,
//            "totalCalculatedAmount": 650.00,
//            "environment": "8000 instance-id"
//    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getCurrencyConversion(@PathVariable String from,
                                                    @PathVariable String to,
                                                    @PathVariable Double quantity){

        HashMap<String, String > object = new HashMap<>();
        object.put("from", from);
        object.put("to", to);

        ResponseEntity<CurrencyConversion> currencyConversionResponseEntity =
                new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class, object);

        CurrencyConversion currencyConversion = currencyConversionResponseEntity.getBody();

        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(quantity*currencyConversion.getConversionMultiple());
        currencyConversion.setEnvironment(currencyConversion.getEnvironment() + ":" + environment.getProperty("local.server.port"));

//        return new CurrencyConversion(1001L, from, to, quantity, 65.0, quantity*65.0,
//                environment.getProperty("local.server.port"));

        return currencyConversion;

    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getCurrencyConversionFeign(@PathVariable String from,
                                                    @PathVariable String to,
                                                    @PathVariable Double quantity){



        CurrencyConversion currencyConversion = currencyConversionProxy.getCurrencyExchange(from, to);

        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(quantity*currencyConversion.getConversionMultiple());
        currencyConversion.setEnvironment(currencyConversion.getEnvironment() + ":" + environment.getProperty("local.server.port")
        +":"+" from feign");

//        return new CurrencyConversion(1001L, from, to, quantity, 65.0, quantity*65.0,
//                environment.getProperty("local.server.port"));

        return currencyConversion;

    }

}
