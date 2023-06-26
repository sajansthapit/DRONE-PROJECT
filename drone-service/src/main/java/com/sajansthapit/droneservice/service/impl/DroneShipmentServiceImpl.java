package com.sajansthapit.droneservice.service.impl;

import com.sajansthapit.droneservice.models.Drone;
import com.sajansthapit.droneservice.models.DroneRequest;
import com.sajansthapit.droneservice.models.DroneShipment;
import com.sajansthapit.droneservice.repository.DroneShipmentRepository;
import com.sajansthapit.droneservice.service.DroneShipmentService;
import org.springframework.stereotype.Service;

@Service
public class DroneShipmentServiceImpl implements DroneShipmentService {

    private final DroneShipmentRepository droneShipmentRepository;

    public DroneShipmentServiceImpl(DroneShipmentRepository droneShipmentRepository) {
        this.droneShipmentRepository = droneShipmentRepository;
    }

    @Override
    public void saveDroneShipment(DroneRequest droneRequest, Drone drone) {
        DroneShipment droneShipment = DroneShipment.builder()
                .drone(drone)
                .droneRequest(droneRequest)
                .build();
        droneShipmentRepository.save(droneShipment);
    }
}
