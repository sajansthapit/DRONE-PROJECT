package com.sajansthapit.droneservice.service;

import com.sajansthapit.droneservice.dto.BaseResponse;
import com.sajansthapit.droneservice.dto.DroneDto;
import com.sajansthapit.droneservice.dto.DroneUpdateDto;
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

    /**
     * Method to find the drone by id
     *
     * @param id Long
     * @return Drone if exists
     */
    Drone findById(Long id);

    /**
     * Method to update the drone state and battery
     *
     * @param droneUpdateDto DroneUpdateDto
     * @param id             Long
     * @return Drone
     */
    Drone updateDrone(DroneUpdateDto droneUpdateDto, Long id);
}
