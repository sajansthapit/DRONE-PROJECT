package com.sajansthapit.medicationservice.exceptionhandler.exceptions;

public class UnsupportedContentTypeException extends RuntimeException{
    public UnsupportedContentTypeException(String message) {
        super(message);
    }
}
