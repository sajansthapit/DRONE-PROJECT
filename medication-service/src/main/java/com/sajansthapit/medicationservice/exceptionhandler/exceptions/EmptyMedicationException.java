package com.sajansthapit.medicationservice.exceptionhandler.exceptions;

public class EmptyMedicationException extends RuntimeException{
    public EmptyMedicationException(String message) {
        super(message);
    }
}
