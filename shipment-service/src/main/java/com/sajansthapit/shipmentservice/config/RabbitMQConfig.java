package com.sajansthapit.shipmentservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${queue.shipment}")
    private String shipmentQueue;

    @Value("${queue.notification}")
    private String notificationQueue;

    @Bean
    @Qualifier(value = "shipmentQueue")
    public Queue shipmentQueue() {
        return new Queue(shipmentQueue);
    }

    @Bean
    @Qualifier(value = "notificationQueue")
    public Queue notificationQueue() {
        return new Queue(notificationQueue);
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
