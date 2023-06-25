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
import com.sajansthapit.medicationservice.util.http.HttpClientWrapper;
import com.sajansthapit.medicationservice.util.mq.MedicationMessagePublisher;
import com.sajansthapit.medicationservice.util.mq.dto.DroneMessageDto;
import org.springframework.beans.factory.annotation.Value;
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
    private final HttpClientWrapper httpClientWrapper;
    private final MedicationMessagePublisher medicationMessagePublisher;

    @Value("${client.url}")
    private String clientUrl;

    public MedicationRequestServiceImpl(MedicationRequestRepository medicationRequestRepository, MedicationService medicationService, HttpClientWrapper httpClientWrapper, MedicationMessagePublisher medicationMessagePublisher) {
        this.medicationRequestRepository = medicationRequestRepository;
        this.medicationService = medicationService;
        this.httpClientWrapper = httpClientWrapper;
        this.medicationMessagePublisher = medicationMessagePublisher;
    }

    @Override
    @Transactional
    public BaseResponse saveClientMedicationRequest(ClientMedicationRequestDto clientMedicationRequestDto) {
        Map<Medication, Integer> medicationQuantityMap = new HashMap<>();
        //check if client exits
        String checkClientUrl = clientUrl.concat(MedicationConstants.CLIENT_CHECK_URL.concat(clientMedicationRequestDto.getClientId().toString()));
        httpClientWrapper.get(checkClientUrl, null, BaseResponse.class, MessageFormat.format(Messages.CLIENT_NOT_FOUND, clientMedicationRequestDto.getClientId()), MedicationConstants.CLIENT_SERVICE);

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

        //send message to drone for new request
        DroneMessageDto droneMessageDto = DroneMessageDto.builder()
                .requestId(uniqueId)
                .totalWeight(totalWeight)
                .clientId(clientMedicationRequestDto.getClientId())
                .build();
        sendMessageToDrone(droneMessageDto);
        return new BaseResponse(HttpStatus.OK, Messages.MEDICATION_REQUEST_SUCCESS);
    }

    private void sendMessageToDrone(DroneMessageDto droneMessageDto) {
        medicationMessagePublisher.publishMessageToDroneService(droneMessageDto);
    }
}
