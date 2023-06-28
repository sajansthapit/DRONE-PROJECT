package com.sajansthapit.shipmentservice.service.impl;

import com.sajansthapit.shipmentservice.dto.BatteryLogDto;
import com.sajansthapit.shipmentservice.models.BatteryLog;
import com.sajansthapit.shipmentservice.repository.BatteryLogRepository;
import com.sajansthapit.shipmentservice.service.BatteryLogService;
import org.springframework.stereotype.Service;

@Service
public class BatteryLogServiceImpl implements BatteryLogService {

    private final BatteryLogRepository batteryLogRepository;

    public BatteryLogServiceImpl(BatteryLogRepository batteryLogRepository) {
        this.batteryLogRepository = batteryLogRepository;
    }

    @Override
    public void save(BatteryLogDto batteryLogDto) {
        BatteryLog batteryLog = BatteryLog.builder()
                .batteryLevel(batteryLogDto.getBatteryLevel())
                .droneId(batteryLogDto.getDroneId())
                .build();
        batteryLogRepository.save(batteryLog);
    }
}
