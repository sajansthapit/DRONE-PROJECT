package com.sajansthapit.medicationservice.constants;

public interface Messages {
    String NAME_REQUIRED = "Medication name is required";
    String WEIGHT_REQUIRED = "Medication weight is required";
    String CODE_REQUIRED = "Medication code is required";
    String IMAGE_REQUIRED = "Medication image is required";
    String QUANTITY_REQUIRED = "Medication quantity is required";
    String INVALID_NAME = "Invalid medication name provided";
    String INVALID_CODE = "Invalid medication code provided";
    String QUANTITY_MIN = "Quantity of medication must be greater than 0";
    String WEIGHT_MIN = "Weight of medication must be greater than 1";
    String MEDICATION_SAVED = "Medication saved successfully";
    String UNIQUE_NAME_VIOLATION = "The given name of medication already exists";
}
