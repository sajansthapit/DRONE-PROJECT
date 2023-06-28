package com.sajansthapit.droneservice.util.mq;

import com.sajansthapit.droneservice.util.mq.dto.RejectNotificationDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RejectNotificationMessagePublisher {

    @Value("${queue.reject-notification}")
    private String rejectNotificationQueue;

    private final RabbitTemplate rabbitTemplate;

    public RejectNotificationMessagePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishRejectNotification(RejectNotificationDto rejectNotificationDto) {
        rabbitTemplate.convertAndSend(rejectNotificationQueue, rejectNotificationDto);
    }
}
