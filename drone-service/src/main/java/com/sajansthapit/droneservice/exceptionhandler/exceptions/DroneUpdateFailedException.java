package com.sajansthapit.droneservice.exceptionhandler.exceptions;

public class DroneUpdateFailedException extends RuntimeException{

    public DroneUpdateFailedException(String message) {
        super(message);
    }
}
