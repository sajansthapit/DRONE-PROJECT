package com.sajansthapit.droneservice.service.impl;

import com.sajansthapit.droneservice.models.DroneRequest;
import com.sajansthapit.droneservice.repository.DroneRequestRepository;
import com.sajansthapit.droneservice.service.DroneRequestService;
import com.sajansthapit.droneservice.util.distance.DistanceCalculator;
import com.sajansthapit.droneservice.util.enumns.DroneRequestStatus;
import com.sajansthapit.droneservice.util.mq.dto.DroneMessageDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneRequestServiceImpl implements DroneRequestService {

    private final DroneRequestRepository droneRequestRepository;

    public DroneRequestServiceImpl(DroneRequestRepository droneRequestRepository) {
        this.droneRequestRepository = droneRequestRepository;
    }

    @Override
    public void save(DroneMessageDto droneMessageDto) {
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

        //TODO: check if the distance is greater than max distance and notify client
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
