package com.sajansthapit.shipmentservice.service.impl;

import com.sajansthapit.shipmentservice.constants.Messages;
import com.sajansthapit.shipmentservice.dto.ShipmentUpdateDto;
import com.sajansthapit.shipmentservice.models.ShipmentLog;
import com.sajansthapit.shipmentservice.repository.ShipmentLogRepository;
import com.sajansthapit.shipmentservice.service.ShipmentLogService;
import com.sajansthapit.shipmentservice.util.dto.ShipmentMessageDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.MessageFormat;

@Service
public class ShipmentLogServiceImpl implements ShipmentLogService {
    private final ShipmentLogRepository shipmentLogRepository;

    public ShipmentLogServiceImpl(ShipmentLogRepository shipmentLogRepository) {
        this.shipmentLogRepository = shipmentLogRepository;
    }

    @Override
    public ShipmentLog saveShipmentLog(ShipmentMessageDto shipmentMessageDto) {
        ShipmentLog shipmentLog = ShipmentLog.builder()
                .droneId(shipmentMessageDto.getDroneId())
                .distance(shipmentMessageDto.getDistance())
                .droneState(shipmentMessageDto.getDroneState())
                .battery(shipmentMessageDto.getBattery())
                .build();
        return shipmentLogRepository.save(shipmentLog);
    }

    @Override
    public ShipmentLog findById(Long id) {
        return shipmentLogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(Messages.SHIPMENT_NOT_FOUND, id)));
    }

    @Override
    public ShipmentLog updateShipmentLog(ShipmentUpdateDto shipmentUpdateDto, Long id) {
        ShipmentLog shipmentLog = findById(id);
        if (shipmentUpdateDto.getBattery() != null) {
            shipmentLog.setBattery(shipmentUpdateDto.getBattery());
        }
        if (shipmentUpdateDto.getDroneState() != null) {
            shipmentLog.setDroneState(shipmentUpdateDto.getDroneState());
        }
        return shipmentLogRepository.save(shipmentLog);
    }
}
