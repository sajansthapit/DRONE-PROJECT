package com.sajansthapit.shipmentservice.util;


import com.sajansthapit.shipmentservice.service.ShipmentLogService;
import com.sajansthapit.shipmentservice.util.dto.ShipmentMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShipmentMessageListener {

    private final ShipmentLogService shipmentLogService;

    public ShipmentMessageListener(ShipmentLogService shipmentLogService) {
        this.shipmentLogService = shipmentLogService;
    }

    @RabbitListener(queues = "shipment-queue")
    public void getShipmentMessage(ShipmentMessageDto shipmentMessageDto) {
        //save log to db
        shipmentLogService.saveShipmentLog(shipmentMessageDto);

        log.info("From shipment queue: {}", shipmentMessageDto);
    }


}
