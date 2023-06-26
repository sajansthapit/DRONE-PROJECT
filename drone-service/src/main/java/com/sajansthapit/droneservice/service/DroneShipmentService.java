package com.sajansthapit.droneservice.service;

import com.sajansthapit.droneservice.models.Drone;
import com.sajansthapit.droneservice.models.DroneRequest;

public interface DroneShipmentService {

    /**
     * Method to save drone shipment
     * @param droneRequest DroneRequest
     * @param drone Drone
     */
    void saveDroneShipment(DroneRequest droneRequest, Drone drone);
}
