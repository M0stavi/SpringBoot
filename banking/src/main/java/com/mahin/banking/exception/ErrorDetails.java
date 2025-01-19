package com.mahin.banking.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorDetails {
    private String message;
    private String details;
    private LocalDateTime localDateTime;

    public ErrorDetails(String message, String details, LocalDateTime localDateTime) {
        this.message = message;
        this.details = details;
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
