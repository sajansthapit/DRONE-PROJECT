package com.sajansthapit.medicationservice.exceptionhandler.exceptions;

public class InsufficientMedicationQuantityException extends RuntimeException{
    public InsufficientMedicationQuantityException(String message) {
        super(message);
    }
}
