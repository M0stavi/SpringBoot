package com.mahin.banking.exception.card;

public class CardNotFoundException extends RuntimeException{
    public CardNotFoundException(String message){
        super(message+" not found");
    }
}
