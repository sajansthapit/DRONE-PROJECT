package com.sajansthapit.shipmentservice.service;

import com.sajansthapit.shipmentservice.dto.ShipmentUpdateDto;
import com.sajansthapit.shipmentservice.models.ShipmentLog;
import com.sajansthapit.shipmentservice.util.dto.ShipmentMessageDto;

public interface ShipmentLogService {

    /**
     * Method to save the shipment Log
     *
     * @param shipmentMessageDto ShipmentMessageDto
     * @return ShipmentLog
     */
    ShipmentLog saveShipmentLog(ShipmentMessageDto shipmentMessageDto);

    /**
     * Method to find the shipment by id
     *
     * @param id Long
     * @return ShipmentLog
     */
    ShipmentLog findById(Long id);

    /**
     * Method to update hte battery and drone state of shipment log
     *
     * @param shipmentUpdateDto ShipmentUpdateDto
     * @param id                Long
     * @return ShipmentLog
     */
    ShipmentLog updateShipmentLog(ShipmentUpdateDto shipmentUpdateDto, Long id);

}
