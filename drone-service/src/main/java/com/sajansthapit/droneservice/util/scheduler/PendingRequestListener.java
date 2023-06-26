package com.sajansthapit.droneservice.util.scheduler;

import com.sajansthapit.droneservice.service.DroneRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PendingRequestListener {

    private final DroneRequestService droneRequestService;

    public PendingRequestListener(DroneRequestService droneRequestService) {
        this.droneRequestService = droneRequestService;
    }

    @Scheduled(fixedRate = 5000) // Polling every 5 seconds
    public void checkIdleDrones() {
        log.info("Every 5 seconds");
    }
}
