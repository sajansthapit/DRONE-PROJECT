package com.sajansthapit.droneservice.service;

import com.sajansthapit.droneservice.util.mq.dto.DroneMessageDto;

import java.util.List;

public interface DroneRequestService {

    /**
     * Method to save the droneRequest to database
     *
     * @param droneMessageDto DroneMessageDto
     */
    void save(DroneMessageDto droneMessageDto);

}
