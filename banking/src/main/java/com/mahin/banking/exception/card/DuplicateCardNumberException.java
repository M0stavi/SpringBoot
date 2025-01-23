package com.mahin.banking.exception.card;

public class DuplicateCardNumberException extends RuntimeException{
    public DuplicateCardNumberException(String message){
        super(message+" is duplicate");
    }
}
