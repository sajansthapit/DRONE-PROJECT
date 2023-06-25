package com.sajansthapit.medicationservice.service;

import com.sajansthapit.medicationservice.dto.BaseResponse;
import com.sajansthapit.medicationservice.dto.request.client.ClientMedicationRequestDto;

public interface MedicationRequestService {

    /**
     * Method to save the client request to the database
     *
     * @param clientMedicationRequestDto ClientMedicationRequestDto
     * @return BaseResponse
     */
    BaseResponse saveClientMedicationRequest(ClientMedicationRequestDto clientMedicationRequestDto);
}
