package com.sajansthapit.medicationservice.service.impl;

import com.sajansthapit.medicationservice.constants.Messages;
import com.sajansthapit.medicationservice.dto.MedicationDto;
import com.sajansthapit.medicationservice.dto.PaginationDto;
import com.sajansthapit.medicationservice.dto.response.GetAllMedicationResponseDto;
import com.sajansthapit.medicationservice.dto.response.SaveMedicationResponseDto;
import com.sajansthapit.medicationservice.exceptionhandler.exceptions.ImageNotFoundException;
import com.sajansthapit.medicationservice.exceptionhandler.exceptions.UniqueViolationException;
import com.sajansthapit.medicationservice.exceptionhandler.exceptions.UnsupportedContentTypeException;
import com.sajansthapit.medicationservice.models.Medication;
import com.sajansthapit.medicationservice.repository.MedicationRepository;
import com.sajansthapit.medicationservice.service.MedicationService;
import com.sajansthapit.medicationservice.util.MultipartFileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Value("${user-defined.root.folder}")
    private String rootFolder;
    private final MedicationRepository medicationRepository;

    public MedicationServiceImpl(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Override
    public SaveMedicationResponseDto saveMedication(MedicationDto medicationDto, MultipartFile image) {
        if (image.getContentType() == null)
            throw new ImageNotFoundException(Messages.IMAGE_NOT_FOUND);

        if (!isSupportedContentType(image.getContentType()))
            throw new UnsupportedContentTypeException(Messages.UNSUPPORTED_CONTENT);

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

    @Override
    public GetAllMedicationResponseDto getAllMedications(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Medication> medicationPage = medicationRepository.findAll(pageable);
        List<MedicationDto> medicationDtoList = medicationPage.map(this::toDto).stream().collect(Collectors.toList());
        PaginationDto paginationDto = PaginationDto.builder()
                .currentPage(medicationPage.getNumber())
                .totalItems(medicationPage.getTotalElements())
                .totalPages(medicationPage.getTotalPages())
                .build();
        return new GetAllMedicationResponseDto(HttpStatus.OK, Messages.GET_ALL_MEDICATION_RESPONSE, medicationDtoList, paginationDto);
    }

    @Override
    public Medication findById(Long id) {
        return medicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(Messages.MEDICATION_NOT_FOUND, id)));
    }

    @Override
    public void updateMedicationQuantity(Long id, Integer newQuantity) {
        Medication medication = findById(id);
        medication.setQuantity(newQuantity);
        medicationRepository.save(medication);
    }

    private MedicationDto toDto(Medication medication) {
        return MedicationDto.builder()
                .id(medication.getId())
                .name(medication.getName())
                .quantity(medication.getQuantity())
                .weight(medication.getWeight())
                .code(medication.getCode())
                .build();
    }

    private boolean isNameUnique(String name) {
        return medicationRepository.findByName(name)
                .isPresent();
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("image/png")
                || contentType.equals("image/jpg")
                || contentType.equals("image/jpeg");
    }
}
