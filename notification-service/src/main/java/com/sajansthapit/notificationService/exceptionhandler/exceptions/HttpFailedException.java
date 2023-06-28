package com.sajansthapit.notificationService.exceptionhandler.exceptions;

public class HttpFailedException extends RuntimeException{
    public HttpFailedException(String message) {
        super(message);
    }
}
