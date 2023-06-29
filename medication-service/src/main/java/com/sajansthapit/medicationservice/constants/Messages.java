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
    String UNSUPPORTED_CONTENT = "Only image file are accepted";
    String EXCEEDS_SIZE_LIMIT = "Maximum 2MB image file are supported";
    String IMAGE_NOT_FOUND = "Image Not Found";
    String GET_ALL_MEDICATION_RESPONSE = "Medications list fetched successfully";
    String MEDICATION_NOT_FOUND = "Medication for the id {0} not found";
    String INSUFFICIENT_MEDICATION = "Quantity for the given medicine {0} is not available";
    String WEIGHT_OVER_LIMIT = "Total weight of medication is greater than {0}";
    String MEDICATION_REQUEST_SUCCESS = "The request for the medication has been made";
    String EMPTY_MEDICATION = "Medication List can't be empty";
    String FAILED_TO_CALL_SERVICE = "Failed to call {0}";
    String CLIENT_NOT_FOUND = "Client with id {0} not found";
    String MEDICATION_REQUEST_NOT_FOUND = "Medication Request for given requestId not found";

}
