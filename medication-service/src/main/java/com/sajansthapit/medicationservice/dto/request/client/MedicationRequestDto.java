package com.sajansthapit.medicationservice.dto.request.client;

import com.sajansthapit.medicationservice.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicationRequestDto {
    private Long id;
    @Min(value = 1, message = Messages.QUANTITY_MIN)
    private Integer quantity;
}
