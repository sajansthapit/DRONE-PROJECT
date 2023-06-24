package com.sajansthapit.medicationservice.exceptionhandler.exceptions;

public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException(String message) {
        super(message);
    }
}
