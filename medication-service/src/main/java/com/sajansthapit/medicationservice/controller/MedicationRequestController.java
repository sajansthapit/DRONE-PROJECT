package com.sajansthapit.medicationservice.controller;

import com.sajansthapit.medicationservice.dto.BaseResponse;
import com.sajansthapit.medicationservice.dto.request.client.ClientMedicationRequestDto;
import com.sajansthapit.medicationservice.service.MedicationRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/medication/request")
public class MedicationRequestController {

    private final MedicationRequestService medicationRequestService;

    public MedicationRequestController(MedicationRequestService medicationRequestService) {
        this.medicationRequestService = medicationRequestService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestBody @Valid ClientMedicationRequestDto clientMedicationRequestDto){
        BaseResponse response = medicationRequestService.saveClientMedicationRequest(clientMedicationRequestDto);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
