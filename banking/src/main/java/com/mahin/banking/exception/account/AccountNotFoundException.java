package com.mahin.banking.exception.account;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String message){
        super("account: "+ message + " not found");
    }
}
