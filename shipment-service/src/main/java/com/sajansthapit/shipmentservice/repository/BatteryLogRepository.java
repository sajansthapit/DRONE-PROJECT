package com.sajansthapit.shipmentservice.repository;


import com.sajansthapit.shipmentservice.models.BatteryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryLogRepository extends JpaRepository<BatteryLog, Long> {
}
