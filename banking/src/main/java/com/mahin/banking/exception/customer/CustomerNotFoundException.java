package com.mahin.banking.exception.customer;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message){
        super("customer: " + message + " not found");
    }
}
