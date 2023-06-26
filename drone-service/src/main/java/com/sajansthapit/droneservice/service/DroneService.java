package com.sajansthapit.droneservice.service;

import com.sajansthapit.droneservice.dto.BaseResponse;
import com.sajansthapit.droneservice.dto.DroneDto;
import com.sajansthapit.droneservice.models.Drone;
import com.sajansthapit.droneservice.models.DroneRequest;

import java.util.List;
import java.util.Optional;

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
     *
     * @return long
     */
    long getDroneCounts();


    /**
     * Method to check the drone request and assign the best available drone if available
     *
     * @param droneRequest DroneRequest
     * @return Drone (Optional)
     */
    Optional<Drone> checkRequestAndAssignDrone(DroneRequest droneRequest);
}
