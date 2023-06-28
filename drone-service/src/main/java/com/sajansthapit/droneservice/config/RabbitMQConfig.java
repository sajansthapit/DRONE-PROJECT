package com.sajansthapit.droneservice.config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${queue.medication}")
    private String medicationQueue;

    @Value("${queue.shipment}")
    private String shipmentQueue;

    @Value("${queue.reject-notification}")
    private String rejectNotificationQueue;

    @Bean
    @Qualifier("medicationQueue")
    public Queue medicationQueue() {
        return new Queue(medicationQueue);
    }

    @Bean
    @Qualifier("shipmentQueue")
    public Queue shipmentQueue() {
        return new Queue(shipmentQueue);
    }

    @Bean
    @Qualifier("rejectNotificationQueue")
    public Queue rejectNotificationQueue(){
        return new Queue(rejectNotificationQueue);
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
