package com.sajansthapit.medicationservice.controller;

import com.sajansthapit.medicationservice.dto.MedicationDto;
import com.sajansthapit.medicationservice.dto.response.SaveMedicationResponseDto;
import com.sajansthapit.medicationservice.service.MedicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/medication")
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PostMapping
    public ResponseEntity<SaveMedicationResponseDto> save(@RequestParam("name") String name,
                                                          @RequestParam("code") String code,
                                                          @RequestParam("weight") Double weight,
                                                          @RequestParam("quantity") Integer quantity,
                                                          @RequestParam("image") MultipartFile image
    ) {
        MedicationDto medicationDto = MedicationDto.builder()
                .name(name)
                .code(code)
                .weight(weight)
                .quantity(quantity)
                .build();
        SaveMedicationResponseDto response = medicationService.saveMedication(medicationDto, image);
        return new ResponseEntity<>(response, response.getStatus());
    }
}