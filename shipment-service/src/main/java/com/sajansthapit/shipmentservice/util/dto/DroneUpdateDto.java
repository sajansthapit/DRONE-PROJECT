package com.sajansthapit.shipmentservice.util.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DroneUpdateDto {
    private String state;
    private Double battery;
}
