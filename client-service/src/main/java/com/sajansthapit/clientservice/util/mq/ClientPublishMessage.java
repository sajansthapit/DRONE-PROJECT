package com.sajansthapit.clientservice.util.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClientPublishMessage {

    private final RabbitTemplate rabbitTemplate;

    public ClientPublishMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessage(ClientMessage clientMessage) {
        rabbitTemplate.convertAndSend("client-queue", clientMessage);
    }
}
