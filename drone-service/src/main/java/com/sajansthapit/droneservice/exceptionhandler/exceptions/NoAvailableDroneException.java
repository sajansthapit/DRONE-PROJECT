package com.sajansthapit.droneservice.exceptionhandler.exceptions;

public class NoAvailableDroneException extends RuntimeException{
    public NoAvailableDroneException(String message) {
        super(message);
    }
}
