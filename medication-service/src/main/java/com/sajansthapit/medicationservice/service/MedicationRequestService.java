package com.sajansthapit.medicationservice.service;

import com.sajansthapit.medicationservice.dto.BaseResponse;
import com.sajansthapit.medicationservice.dto.request.client.ClientMedicationRequestDto;
import com.sajansthapit.medicationservice.dto.response.GetAllMedicationsOfRequestDto;

public interface MedicationRequestService {

    /**
     * Method to save the client request to the database
     *
     * @param clientMedicationRequestDto ClientMedicationRequestDto
     * @return BaseResponse
     */
    BaseResponse saveClientMedicationRequest(ClientMedicationRequestDto clientMedicationRequestDto);

    /**
     * Method to get All medications of the given request
     *
     * @param requestId String
     * @return GetAllMedicationsOfRequestDto
     */
    GetAllMedicationsOfRequestDto getAllMedicationsOfRequest(String requestId);
}
