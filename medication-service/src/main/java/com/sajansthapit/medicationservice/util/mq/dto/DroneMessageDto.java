package com.sajansthapit.medicationservice.util.mq.dto;


import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DroneMessageDto {
    private String requestId;
    private Double totalWeight;
    private Long clientId;
    private Double latitude;
    private Double longitude;
}
