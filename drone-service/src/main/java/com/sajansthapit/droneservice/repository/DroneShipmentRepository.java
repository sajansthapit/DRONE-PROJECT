package com.sajansthapit.droneservice.repository;

import com.sajansthapit.droneservice.models.DroneShipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneShipmentRepository extends JpaRepository<DroneShipment, Long> {
}
