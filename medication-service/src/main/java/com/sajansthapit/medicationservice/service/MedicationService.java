package com.sajansthapit.medicationservice.service;

import com.sajansthapit.medicationservice.dto.MedicationDto;
import com.sajansthapit.medicationservice.dto.response.SaveMedicationResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface MedicationService {

    /**
     * Method to save Medication
     *
     * @param medicationDto: MedicationDto
     * @param image:         MultipartFile
     * @return SaveMedicationResponseDto
     */
    SaveMedicationResponseDto saveMedication(MedicationDto medicationDto, MultipartFile image);
}
