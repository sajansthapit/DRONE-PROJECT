package com.sajansthapit.medicationservice.controller;

import com.sajansthapit.medicationservice.dto.BaseResponse;
import com.sajansthapit.medicationservice.dto.request.client.ClientMedicationRequestDto;
import com.sajansthapit.medicationservice.dto.response.GetAllMedicationsOfRequestDto;
import com.sajansthapit.medicationservice.service.MedicationRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/medication/request")
public class MedicationRequestController {

    private final MedicationRequestService medicationRequestService;

    public MedicationRequestController(MedicationRequestService medicationRequestService) {
        this.medicationRequestService = medicationRequestService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestBody @Valid ClientMedicationRequestDto clientMedicationRequestDto) {
        BaseResponse response = medicationRequestService.saveClientMedicationRequest(clientMedicationRequestDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/{uniqueId}")
    public ResponseEntity<GetAllMedicationsOfRequestDto> getAllMedicationsOfRequestId(@PathVariable(value = "uniqueId") String uniqueId) {
        GetAllMedicationsOfRequestDto response = medicationRequestService.getAllMedicationsOfRequest(uniqueId);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
