package com.sajansthapit.medicationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDto {
    private Long id;
    private String name;
    private Double weight;
    private String code;
    private Integer quantity;
    private String image;
}
