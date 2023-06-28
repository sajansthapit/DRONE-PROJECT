package com.sajansthapit.notificationService.exceptionhandler.exceptions;

public class InvalidOtpException extends RuntimeException{

    public InvalidOtpException(String message) {
        super(message);
    }
}
