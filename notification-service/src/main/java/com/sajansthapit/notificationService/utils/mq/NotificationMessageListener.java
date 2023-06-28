package com.sajansthapit.notificationService.utils.mq;


import com.sajansthapit.notificationService.models.ClientVerification;
import com.sajansthapit.notificationService.service.ClientVerificationService;
import com.sajansthapit.notificationService.utils.mq.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationMessageListener {

    private final ClientVerificationService clientVerificationService;

    public NotificationMessageListener(ClientVerificationService clientVerificationService) {
        this.clientVerificationService = clientVerificationService;
    }

    @RabbitListener(queues = "notification-queue")
    public void getNotificationMessage(NotificationDto notificationDto){
        ClientVerification clientVerification = clientVerificationService.save(notificationDto);
    }
}
