package com.sajansthapit.droneservice.service;

import com.sajansthapit.droneservice.models.DroneRequest;
import com.sajansthapit.droneservice.util.mq.dto.DroneMessageDto;

import java.util.List;
import java.util.Optional;

public interface DroneRequestService {

    /**
     * Method to save the droneRequest to database
     *
     * @param droneMessageDto DroneMessageDto
     */
    void save(DroneMessageDto droneMessageDto);

    /**
     * Method to find the latest drone request in database
     *
     * @return DroneRequest (Optional)
     */
    Optional<DroneRequest> findLatestRequest();

    /**
     * Method to update the drone request status
     *
     * @param droneRequest DroneRequest
     * @param status       Status
     *
     * @return DroneRequest (updated)
     */
     DroneRequest updateDroneRequestStatus(DroneRequest droneRequest, String status);

}
