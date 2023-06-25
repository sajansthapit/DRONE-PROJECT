package com.sajansthapit.medicationservice.service;

import com.sajansthapit.medicationservice.dto.MedicationDto;
import com.sajansthapit.medicationservice.dto.response.GetAllMedicationResponseDto;
import com.sajansthapit.medicationservice.dto.response.SaveMedicationResponseDto;
import com.sajansthapit.medicationservice.models.Medication;
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

    /**
     * Method to get all medications in pagination
     *
     * @param page Integer
     * @param size Integer
     * @return GetAllMedicationResponseDto
     */
    GetAllMedicationResponseDto getAllMedications(Integer page, Integer size);

    /**
     * Method to get Medication by id if it exists
     *
     * @param id Long
     * @return Medication
     */
    Medication findById(Long id);

    /**
     * Method to update medication with updated quantity
     *
     * @param id          Long
     * @param newQuantity Integer
     */
    void updateMedicationQuantity(Long id, Integer newQuantity);
}
