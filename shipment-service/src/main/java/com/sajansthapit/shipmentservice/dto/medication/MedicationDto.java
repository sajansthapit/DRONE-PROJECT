package com.sajansthapit.shipmentservice.dto.medication;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicationDto {
    private Long id;
    private String name;
    private Double weight;
    private String code;
    private Integer quantity;
    private String image;
}
