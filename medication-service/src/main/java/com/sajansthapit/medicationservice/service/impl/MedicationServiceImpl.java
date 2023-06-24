package com.sajansthapit.medicationservice.service.impl;

import com.sajansthapit.medicationservice.constants.Messages;
import com.sajansthapit.medicationservice.dto.MedicationDto;
import com.sajansthapit.medicationservice.dto.response.SaveMedicationResponseDto;
import com.sajansthapit.medicationservice.exceptionhandler.exceptions.UniqueViolationException;
import com.sajansthapit.medicationservice.models.Medication;
import com.sajansthapit.medicationservice.repository.MedicationRepository;
import com.sajansthapit.medicationservice.service.MedicationService;
import com.sajansthapit.medicationservice.util.MultipartFileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Value("${user-defined.root.folder}")
    private String rootFolder;
    private final MedicationRepository medicationRepository;

    public MedicationServiceImpl(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Override
    @Transactional
    public SaveMedicationResponseDto saveMedication(MedicationDto medicationDto, MultipartFile image) {
        String imagePath = MultipartFileUpload.upload(image, rootFolder);

        Medication medication = Medication.builder()
                .name(medicationDto.getName())
                .weight(medicationDto.getWeight())
                .code(medicationDto.getCode())
                .quantity(medicationDto.getQuantity())
                .image(imagePath)
                .build();
        try {
            Medication savedMedication = medicationRepository.save(medication);
            return new SaveMedicationResponseDto(HttpStatus.CREATED, Messages.MEDICATION_SAVED, savedMedication.getId());
        } catch (DataIntegrityViolationException exception) {
            if (isNameUnique(medicationDto.getName())) {
                throw new UniqueViolationException(Messages.UNIQUE_NAME_VIOLATION);
            }
            throw exception;
        }
    }

    private boolean isNameUnique(String name) {
        return medicationRepository.findByName(name)
                .isPresent();
    }
}
