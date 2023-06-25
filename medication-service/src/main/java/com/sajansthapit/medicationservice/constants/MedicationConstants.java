package com.sajansthapit.medicationservice.constants;

public interface MedicationConstants {
    String MEDICATION_NAME_REGEX = "^[A-Za-z0-9-_]+$";
    String MEDICATION_CODE_REGEX = "^[A-Z0-9_]+$";
    String HOME_FOLDER = System.getProperty("user.home");
    Integer MAX_MEDICATION_WEIGHT = 500;
}
