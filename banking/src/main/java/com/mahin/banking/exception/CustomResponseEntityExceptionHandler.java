package com.mahin.banking.exception;

import com.mahin.banking.exception.curtomer.CustomerNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleAllException(Exception ex, WebRequest request){
        System.out.println("Inside handle all exep");
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false),
                LocalDateTime.now());
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {

        System.out.println("Inside handle method arg not valid");

        List<FieldError> fieldErrors = ex.getFieldErrors();

        StringBuilder errorMessages=new StringBuilder();
        for (FieldError error:fieldErrors){
            errorMessages.append(error.getDefaultMessage()).append(", ");
        }



        ErrorDetails errorDetails = new ErrorDetails("total errors: " + ex.getErrorCount() + " errors: " +
                errorMessages, request.getDescription(false),
                LocalDateTime.now());

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);

    }

}