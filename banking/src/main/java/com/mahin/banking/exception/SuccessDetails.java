package com.mahin.banking.exception;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class SuccessDetails {
    private String message;
    private LocalDateTime localDateTime;

    public SuccessDetails(String message, LocalDateTime localDateTime) {
        this.message = message;
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
