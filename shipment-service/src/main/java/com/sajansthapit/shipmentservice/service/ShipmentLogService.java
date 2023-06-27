package com.sajansthapit.shipmentservice.service;

import com.sajansthapit.shipmentservice.util.dto.ShipmentMessageDto;

public interface ShipmentLogService {

    /**
     * Method to save the shipment Log
     *
     * @param shipmentMessageDto ShipmentMessageDto
     */
    void saveShipmentLog(ShipmentMessageDto shipmentMessageDto);
}
