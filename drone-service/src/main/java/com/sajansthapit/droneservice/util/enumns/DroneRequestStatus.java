package com.sajansthapit.droneservice.util.enumns;

public enum DroneRequestStatus {
    PENDING("PENDING"),
    COMPLETED("COMPLETED");

    private final String requestStatus;

    DroneRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestStatus() {
        return requestStatus;
    }
}
