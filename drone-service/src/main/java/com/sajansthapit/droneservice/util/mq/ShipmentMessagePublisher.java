package com.sajansthapit.droneservice.util.mq;

import com.sajansthapit.droneservice.util.mq.dto.ShipmentMessageDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ShipmentMessagePublisher {

    @Value("${queue.shipment}")
    private String shipmentQueue;

    private final RabbitTemplate rabbitTemplate;

    public ShipmentMessagePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessageToShipment(ShipmentMessageDto shipmentMessageDto) {
        rabbitTemplate.convertAndSend(shipmentQueue, shipmentMessageDto);
    }
}
