package com.sajansthapit.droneservice.util.mq;

import com.sajansthapit.droneservice.service.DroneRequestService;
import com.sajansthapit.droneservice.util.mq.dto.DroneMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MedicationListenerService {

    private final DroneRequestService droneRequestService;

    public MedicationListenerService(DroneRequestService droneRequestService) {
        this.droneRequestService = droneRequestService;
    }

    @RabbitListener(queues = "medication-queue")
    public void getMedicationRequestMessage(DroneMessageDto droneMessageDto) {
        log.info("From drone queue: {}", droneMessageDto);
        droneRequestService.save(droneMessageDto);
    }
}
