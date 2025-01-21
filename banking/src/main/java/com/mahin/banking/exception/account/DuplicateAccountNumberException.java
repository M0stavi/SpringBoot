package com.mahin.banking.exception.account;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class DuplicateAccountNumberException extends RuntimeException {
    public DuplicateAccountNumberException(String message){
        super(message+" is duplicate");
    }
}
