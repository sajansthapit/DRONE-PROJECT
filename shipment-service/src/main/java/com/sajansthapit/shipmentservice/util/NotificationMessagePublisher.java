package com.sajansthapit.shipmentservice.util;

import com.sajansthapit.shipmentservice.util.dto.NotificationDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationMessagePublisher {
    @Value("${queue.notification}")
    private String notificationQueue;


    private final RabbitTemplate rabbitTemplate;

    public NotificationMessagePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessageToNotificationService(NotificationDto notificationDto){
        rabbitTemplate.convertAndSend(notificationQueue, notificationDto);
    }
}
