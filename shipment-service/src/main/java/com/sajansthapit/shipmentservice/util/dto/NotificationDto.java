package com.sajansthapit.shipmentservice.util.dto;


import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private Long clientId;
    private Long droneId;
    private String droneState;
}
