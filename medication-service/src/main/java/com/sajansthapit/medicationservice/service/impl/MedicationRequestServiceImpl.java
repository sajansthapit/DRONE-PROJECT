package com.sajansthapit.medicationservice.service.impl;

import com.sajansthapit.medicationservice.constants.MedicationConstants;
import com.sajansthapit.medicationservice.constants.Messages;
import com.sajansthapit.medicationservice.dto.BaseResponse;
import com.sajansthapit.medicationservice.dto.request.client.ClientMedicationRequestDto;
import com.sajansthapit.medicationservice.dto.request.client.MedicationRequestDto;
import com.sajansthapit.medicationservice.exceptionhandler.exceptions.EmptyMedicationException;
import com.sajansthapit.medicationservice.exceptionhandler.exceptions.InsufficientMedicationQuantityException;
import com.sajansthapit.medicationservice.exceptionhandler.exceptions.WeightOverLimitException;
import com.sajansthapit.medicationservice.models.Medication;
import com.sajansthapit.medicationservice.models.MedicationRequest;
import com.sajansthapit.medicationservice.repository.MedicationRequestRepository;
import com.sajansthapit.medicationservice.service.MedicationRequestService;
import com.sajansthapit.medicationservice.service.MedicationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MedicationRequestServiceImpl implements MedicationRequestService {

    private final MedicationRequestRepository medicationRequestRepository;
    private final MedicationService medicationService;

    public MedicationRequestServiceImpl(MedicationRequestRepository medicationRequestRepository, MedicationService medicationService) {
        this.medicationRequestRepository = medicationRequestRepository;
        this.medicationService = medicationService;
    }

    @Override
    @Transactional
    public BaseResponse saveClientMedicationRequest(ClientMedicationRequestDto clientMedicationRequestDto) {
        Map<Medication, Integer> medicationQuantityMap = new HashMap<>();

        if (clientMedicationRequestDto.getMedications().size() == 0)
            throw new EmptyMedicationException(Messages.EMPTY_MEDICATION);

        double totalWeight = 0;

        for (MedicationRequestDto medicationRequestDto : clientMedicationRequestDto.getMedications()) {
            Medication medication = medicationService.findById(medicationRequestDto.getId());
            medicationQuantityMap.put(medication, medicationRequestDto.getQuantity());

            //check if the given medication meets the quantity
            if (medication.getQuantity() < medicationRequestDto.getQuantity())
                throw new InsufficientMedicationQuantityException(MessageFormat.format(Messages.INSUFFICIENT_MEDICATION, medication.getName()));

            totalWeight = totalWeight + (medication.getWeight() * medicationRequestDto.getQuantity());
        }

        //validate total weight
        if (totalWeight > MedicationConstants.MAX_MEDICATION_WEIGHT)
            throw new WeightOverLimitException(MessageFormat.format(Messages.WEIGHT_OVER_LIMIT, MedicationConstants.MAX_MEDICATION_WEIGHT));


        //save
        String uniqueId = UUID.randomUUID().toString();
        for (Map.Entry<Medication, Integer> entry : medicationQuantityMap.entrySet()) {
            MedicationRequest medicationRequest = MedicationRequest.builder()
                    .clientId(clientMedicationRequestDto.getClientId())
                    .medication(entry.getKey())
                    .quantity(entry.getValue())
                    .uniqueId(uniqueId)
                    .build();
            medicationRequestRepository.save(medicationRequest);

            //decrease quantity in medication
            Integer newQuantity = entry.getKey().getQuantity() - entry.getValue();
            medicationService.updateMedicationQuantity(entry.getKey().getId(), newQuantity);

        }
        return new BaseResponse(HttpStatus.OK, Messages.MEDICATION_REQUEST_SUCCESS);
    }
}
