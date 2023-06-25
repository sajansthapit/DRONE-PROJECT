package com.sajansthapit.droneservice.util.scheduler;

import com.sajansthapit.droneservice.service.DroneRequestService;
import com.sajansthapit.droneservice.service.DroneService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PendingRequestListener {

    private final DroneRequestService droneRequestService;

    public PendingRequestListener(DroneRequestService droneRequestService) {
        this.droneRequestService = droneRequestService;
    }

    @Scheduled(fixedRate = 5000) // Polling every 5 seconds
    public void checkIdleDrones() {

    }
}
