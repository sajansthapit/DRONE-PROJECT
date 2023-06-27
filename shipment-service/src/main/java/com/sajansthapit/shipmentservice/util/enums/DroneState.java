package com.sajansthapit.shipmentservice.util.enums;

public enum DroneState {
    IDLE("IDLE"),
    LOADING("LOADING"),
    LOADED("LOADED"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED"),
    RETURNING("RETURNING");

    private final String state;

    DroneState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
