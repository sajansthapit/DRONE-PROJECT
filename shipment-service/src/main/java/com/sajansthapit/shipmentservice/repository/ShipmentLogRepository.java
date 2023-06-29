package com.sajansthapit.shipmentservice.repository;

import com.sajansthapit.shipmentservice.models.ShipmentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipmentLogRepository extends JpaRepository<ShipmentLog, Long> {

    Optional<ShipmentLog> findByDroneIdAndDroneState(Long droneId, String droneState);
}
