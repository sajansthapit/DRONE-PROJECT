package com.sajansthapit.droneservice.service.impl;

import com.sajansthapit.droneservice.constants.DroneConstants;
import com.sajansthapit.droneservice.models.DroneRequest;
import com.sajansthapit.droneservice.repository.DroneRequestRepository;
import com.sajansthapit.droneservice.service.DroneRequestService;
import com.sajansthapit.droneservice.util.distance.DistanceCalculator;
import com.sajansthapit.droneservice.util.enumns.DroneRequestStatus;
import com.sajansthapit.droneservice.util.mq.RejectNotificationMessagePublisher;
import com.sajansthapit.droneservice.util.mq.dto.DroneMessageDto;
import com.sajansthapit.droneservice.util.mq.dto.RejectNotificationDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DroneRequestServiceImpl implements DroneRequestService {

    private final DroneRequestRepository droneRequestRepository;
    private final RejectNotificationMessagePublisher rejectNotificationMessagePublisher;

    public DroneRequestServiceImpl(DroneRequestRepository droneRequestRepository, RejectNotificationMessagePublisher rejectNotificationMessagePublisher) {
        this.droneRequestRepository = droneRequestRepository;
        this.rejectNotificationMessagePublisher = rejectNotificationMessagePublisher;
    }

    @Override
    public void save(DroneMessageDto droneMessageDto) {

        if (droneMessageDto.getTotalWeight() > DroneConstants.MAX_MEDICATION_WEIGHT) {
            RejectNotificationDto rejectNotificationDto = new RejectNotificationDto(droneMessageDto.getClientId());
            rejectNotificationMessagePublisher.publishRejectNotification(rejectNotificationDto);
        } else {
            DroneRequest droneRequest = DroneRequest.builder()
                    .requestId(droneMessageDto.getRequestId())
                    .totalWeight(droneMessageDto.getTotalWeight())
                    .clientId(droneMessageDto.getClientId())
                    .latitude(droneMessageDto.getLatitude())
                    .longitude(droneMessageDto.getLongitude())
                    .distance(DistanceCalculator.calculate(droneMessageDto.getLatitude(), droneMessageDto.getLongitude()))
                    .status(DroneRequestStatus.PENDING.getRequestStatus())
                    .build();
            droneRequestRepository.save(droneRequest);
        }
    }

    @Override
    public Optional<DroneRequest> findLatestRequest() {
        return droneRequestRepository.findFirstByStatusOrderByCreatedDateDesc(DroneRequestStatus.PENDING.getRequestStatus());
    }

    @Override
    public DroneRequest updateDroneRequestStatus(DroneRequest droneRequest, String status) {
        droneRequest.setStatus(status);
        return droneRequestRepository.save(droneRequest);
    }
}
