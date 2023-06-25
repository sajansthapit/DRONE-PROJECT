package com.sajansthapit.medicationservice.exceptionhandler.exceptions;

public class WeightOverLimitException extends RuntimeException{
    public WeightOverLimitException(String message) {
        super(message);
    }
}
