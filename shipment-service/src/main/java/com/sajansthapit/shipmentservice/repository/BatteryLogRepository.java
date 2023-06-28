package com.sajansthapit.shipmentservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryLogRepository extends JpaRepository<com.sajansthapit.batteryservice.models.BatteryLog, Long> {
}
