package com.sajansthapit.droneservice.dto;

import org.springframework.http.HttpStatus;

public class GetBatteryLevelDto extends BaseResponse {
    private Double batteryLevel;

    public GetBatteryLevelDto(HttpStatus status, String message, Double batteryLevel) {
        super(status, message);
        this.batteryLevel = batteryLevel;
    }

    public GetBatteryLevelDto(Double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public Double getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
}
