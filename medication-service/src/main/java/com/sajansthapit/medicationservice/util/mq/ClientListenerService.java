package com.sajansthapit.medicationservice.util.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientListenerService {

    @RabbitListener(queues = "client-queue")
    public void getClientMessage(ClientMessage message) {
        log.info("From Queue : {}", message);
    }
}
