package com.sajansthapit.shipmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatteryLogDto {
    private Long droneId;
    private Double batteryLevel;
}
