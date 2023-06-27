package com.sajansthapit.shipmentservice.exceptionhandlers.exceptions;

public class HttpFailedException extends RuntimeException{
    public HttpFailedException(String message) {
        super(message);
    }
}
