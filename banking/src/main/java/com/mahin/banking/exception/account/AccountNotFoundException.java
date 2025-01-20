package com.mahin.banking.exception.account;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String message){
        super("account: "+ message + " not found");
    }
}
