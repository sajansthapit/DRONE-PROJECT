package com.sajansthapit.droneservice.service;

import com.sajansthapit.droneservice.dto.BaseResponse;
import com.sajansthapit.droneservice.dto.DroneDto;

import java.util.List;

public interface DroneService {

    /**
     * Method to save list of drones
     *
     * @param droneDtoList List<DroneDto>
     * @return BaseResponse
     */
    BaseResponse saveAllDrone(List<DroneDto> droneDtoList);

    /**
     * Method that returns the total number of drones in database
     * @return long
     */
    long getDroneCounts();
}
