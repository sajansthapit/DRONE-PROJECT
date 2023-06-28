package com.sajansthapit.notificationService.utils;

public enum ClientVerificationStatus {
    PENDING("PENDING"),
    VERIFIED("VERIFIED"),
    REJECTED("REJECTED");

    private final String status;

    ClientVerificationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
