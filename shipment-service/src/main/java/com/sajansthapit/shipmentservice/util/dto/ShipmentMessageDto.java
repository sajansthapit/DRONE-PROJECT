package com.sajansthapit.shipmentservice.util.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentMessageDto {
    private Long droneId;
    private Double distance;
    private String droneState;
    private Double battery;
}
