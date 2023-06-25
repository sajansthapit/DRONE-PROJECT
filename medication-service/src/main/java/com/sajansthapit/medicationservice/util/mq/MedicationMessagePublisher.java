package com.sajansthapit.medicationservice.util.mq;

import com.sajansthapit.medicationservice.util.mq.dto.DroneMessageDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MedicationMessagePublisher {

    @Value("${queue.medication}")
    private String droneQueue;

    private final RabbitTemplate rabbitTemplate;

    public MedicationMessagePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessageToDroneService(DroneMessageDto droneMessageDto){
        rabbitTemplate.convertAndSend(droneQueue, droneMessageDto);
    }
}
