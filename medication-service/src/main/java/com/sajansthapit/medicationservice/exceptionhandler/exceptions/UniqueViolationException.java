package com.sajansthapit.medicationservice.exceptionhandler.exceptions;

public class UniqueViolationException extends RuntimeException{
    public UniqueViolationException(String message) {
        super(message);
    }
}
