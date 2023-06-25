package com.sajansthapit.medicationservice.dto.request.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientMedicationRequestDto {
    private List<MedicationRequestDto> medications;
    private Long clientId;
}

