package com.sajansthapit.medicationservice.exceptionhandler.exceptions;

public class HttpFailedException extends RuntimeException{
    public HttpFailedException(String message) {
        super(message);
    }
}
