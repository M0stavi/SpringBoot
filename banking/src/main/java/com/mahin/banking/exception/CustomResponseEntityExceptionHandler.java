package com.mahin.banking.exception;

import com.mahin.banking.exception.account.AccountNotFoundException;
import com.mahin.banking.exception.account.DuplicateAccountNumberException;
import com.mahin.banking.exception.card.CardNotFoundException;
import com.mahin.banking.exception.customer.CustomerNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    public ResponseEntity handleAllException(Exception ex, WebRequest request) {
        System.out.println("Inside handle all exep");
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false),
                LocalDateTime.now());
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity handleCustomerNotFoundException(Exception ex, WebRequest request) {
        System.out.println("Inside customer not found exep");
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false),
                LocalDateTime.now());
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public final ResponseEntity<Object> handleAccountNotFoundException(Exception ex, WebRequest request) {
        System.out.println("Inside account not found exception");
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false),
                LocalDateTime.now());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateAccountNumberException.class)
    public final ResponseEntity<Object> handleDuplicateAccountException(Exception ex, WebRequest request) {
        System.out.println("Inside duplicate account no exception");
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false),
                LocalDateTime.now());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CardNotFoundException.class)
    public final ResponseEntity<Object> handleCardNotFoundException(Exception ex, WebRequest request){
        System.out.println("Inside card not found exception");
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false),
                LocalDateTime.now());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {

        System.out.println("Inside handle method arg not valid");

        List<FieldError> fieldErrors = ex.getFieldErrors();

        StringBuilder errorMessages = new StringBuilder();
        for (FieldError error : fieldErrors) {
            errorMessages.append(error.getDefaultMessage()).append(", ");
        }


        ErrorDetails errorDetails = new ErrorDetails("total errors: " + ex.getErrorCount() + " errors: " +
                errorMessages, request.getDescription(false),
                LocalDateTime.now());

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);

    }

}