package com.sajansthapit.droneservice.util.scheduler;

import com.sajansthapit.droneservice.models.DroneRequest;
import com.sajansthapit.droneservice.service.DroneRequestService;
import com.sajansthapit.droneservice.util.enumns.DroneRequestStatus;
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

    public PendingDroneRequestQueue(DroneRequestService droneRequestService) {
        this.droneRequestService = droneRequestService;
    }

    private final Queue<DroneRequest> droneRequestQueue = new ArrayDeque<>(10);


    @Scheduled(fixedRate = 5000)
    private synchronized void checkPendingDroneRequest() {
        if (droneRequestQueue.size() != 10) {
            Optional<DroneRequest> droneRequestOptional = droneRequestService.findLatestRequest();
            droneRequestOptional.ifPresent(droneRequest -> droneRequestQueue.offer(droneRequestService.updateDroneRequestStatus(droneRequest, DroneRequestStatus.PROCESSING.getRequestStatus())));
        }
    }

    private synchronized void assignDroneToRequest(){

    }

}
