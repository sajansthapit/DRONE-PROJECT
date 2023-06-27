package com.sajansthapit.shipmentservice.service.impl;

import com.sajansthapit.shipmentservice.models.ShipmentLog;
import com.sajansthapit.shipmentservice.repository.ShipmentLogRepository;
import com.sajansthapit.shipmentservice.service.ShipmentLogService;
import com.sajansthapit.shipmentservice.util.dto.ShipmentMessageDto;
import org.springframework.stereotype.Service;

@Service
public class ShipmentLogServiceImpl implements ShipmentLogService {
    private final ShipmentLogRepository shipmentLogRepository;

    public ShipmentLogServiceImpl(ShipmentLogRepository shipmentLogRepository) {
        this.shipmentLogRepository = shipmentLogRepository;
    }

    @Override
    public void saveShipmentLog(ShipmentMessageDto shipmentMessageDto) {
        ShipmentLog shipmentLog = ShipmentLog.builder()
                .droneId(shipmentMessageDto.getDroneId())
                .distance(shipmentMessageDto.getDistance())
                .droneState(shipmentMessageDto.getDroneState())
                .battery(shipmentMessageDto.getBattery())
                .build();
        shipmentLogRepository.save(shipmentLog);
    }
}
