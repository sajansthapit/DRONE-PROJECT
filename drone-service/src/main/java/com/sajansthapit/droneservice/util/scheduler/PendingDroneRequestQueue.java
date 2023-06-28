package com.sajansthapit.droneservice.util.scheduler;

import com.sajansthapit.droneservice.dto.DroneUpdateDto;
import com.sajansthapit.droneservice.models.Drone;
import com.sajansthapit.droneservice.models.DroneRequest;
import com.sajansthapit.droneservice.service.DroneRequestService;
import com.sajansthapit.droneservice.service.DroneService;
import com.sajansthapit.droneservice.service.DroneShipmentService;
import com.sajansthapit.droneservice.util.enumns.DroneRequestStatus;
import com.sajansthapit.droneservice.util.enumns.DroneState;
import com.sajansthapit.droneservice.util.mq.ShipmentMessagePublisher;
import com.sajansthapit.droneservice.util.mq.dto.ShipmentMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

@Slf4j
@Component
public class PendingDroneRequestQueue {

    private final DroneRequestService droneRequestService;
    private final DroneService droneService;
    private final DroneShipmentService droneShipmentService;

    private final ShipmentMessagePublisher shipmentMessagePublisher;

    public PendingDroneRequestQueue(DroneRequestService droneRequestService, DroneService droneService, DroneShipmentService droneShipmentService, ShipmentMessagePublisher shipmentMessagePublisher) {
        this.droneRequestService = droneRequestService;
        this.droneService = droneService;
        this.droneShipmentService = droneShipmentService;
        this.shipmentMessagePublisher = shipmentMessagePublisher;
    }

    private final Queue<DroneRequest> droneRequestQueue = new ArrayDeque<>(10);


    @Scheduled(fixedRate = 5000)
    private synchronized void checkPendingDroneRequest() {
        if (droneRequestQueue.size() != 10) {
            Optional<DroneRequest> droneRequestOptional = droneRequestService.findLatestRequest();
            droneRequestOptional.ifPresent(droneRequest -> droneRequestQueue.offer(droneRequestService.updateDroneRequestStatus(droneRequest, DroneRequestStatus.PROCESSING.getRequestStatus())));
        }
        if (droneRequestQueue.size() != 0) {
            assignDroneToRequest();
        }
    }

    private synchronized void assignDroneToRequest() {
        DroneRequest droneRequest = droneRequestQueue.poll();
        Optional<Drone> droneOptional = droneService.checkRequestAndAssignDrone(droneRequest);
        if (droneOptional.isEmpty()) droneRequestQueue.offer(droneRequest);
        else {
            DroneUpdateDto updateDto = new DroneUpdateDto(DroneState.LOADING.getState(), droneOptional.get().getBattery());
            Drone drone = droneService.updateDrone(updateDto, droneOptional.get().getId());
            droneShipmentService.saveDroneShipment(droneRequest, drone);
            droneRequestService.updateDroneRequestStatus(droneRequest, DroneRequestStatus.COMPLETED.getRequestStatus());

            ShipmentMessageDto shipmentMessageDto = ShipmentMessageDto.builder()
                    .droneId(drone.getId())
                    .distance(droneRequest.getDistance())
                    .battery(drone.getBattery())
                    .droneState(DroneState.LOADED.getState())
                    .clientId(droneRequest.getClientId())
                    .requestId(droneRequest.getRequestId())
                    .build();
            droneService.updateDrone(new DroneUpdateDto(DroneState.LOADED.getState(), drone.getBattery()), drone.getId());
            shipmentMessagePublisher.publishMessageToShipment(shipmentMessageDto);
        }

    }

}
