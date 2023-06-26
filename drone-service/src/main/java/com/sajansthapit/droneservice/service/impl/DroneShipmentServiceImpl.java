package com.sajansthapit.droneservice.service.impl;

import com.sajansthapit.droneservice.repository.DroneShipmentRepository;
import com.sajansthapit.droneservice.service.DroneShipmentService;
import org.springframework.stereotype.Service;

@Service
public class DroneShipmentServiceImpl implements DroneShipmentService {

    private final DroneShipmentRepository droneShipmentRepository;

    public DroneShipmentServiceImpl(DroneShipmentRepository droneShipmentRepository) {
        this.droneShipmentRepository = droneShipmentRepository;
    }
}
