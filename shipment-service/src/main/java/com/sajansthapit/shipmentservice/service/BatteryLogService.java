package com.sajansthapit.shipmentservice.service;

import com.sajansthapit.shipmentservice.dto.BatteryLogDto;

public interface BatteryLogService {

    /**
     * Method to save the battery log
     *
     * @param batteryLogDto BatteryLogDto
     */
    void save(BatteryLogDto batteryLogDto);
}
