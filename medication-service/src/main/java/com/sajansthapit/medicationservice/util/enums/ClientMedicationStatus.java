package com.sajansthapit.medicationservice.util.enums;

public enum ClientMedicationStatus {
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED"),
    COMPLETED("COMPLETED");

    private final String status;

    ClientMedicationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
