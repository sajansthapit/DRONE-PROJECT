package com.sajansthapit.notificationService.utils.mq;

import com.sajansthapit.notificationService.utils.SendMailService;
import com.sajansthapit.notificationService.utils.mq.dto.RejectNotificationDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RejectNotificationListener {

    private final SendMailService sendMailService;

    public RejectNotificationListener(SendMailService sendMailService) {
        this.sendMailService = sendMailService;
    }

    @RabbitListener(queues = "reject-notification-queue")
    public void getRejectNotification(RejectNotificationDto rejectNotificationDto){
        sendMailService.sendRejectEmail(rejectNotificationDto);
    }
}
