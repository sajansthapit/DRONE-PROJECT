package com.sajansthapit.medicationservice.dto.response.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private String name;
    private String email;
    private Double latitude;
    private Double longitude;
}
