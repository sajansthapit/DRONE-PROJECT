package com.sajansthapit.droneservice.exceptionhandler.exceptions;

public class UniqueViolationException extends RuntimeException{
    public UniqueViolationException(String message) {
        super(message);
    }
}
