package com.sajansthapit.droneservice.repository;

import com.sajansthapit.droneservice.models.DroneRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DroneRequestRepository extends JpaRepository<DroneRequest, Long> {

    Optional<DroneRequest> findFirstByStatusOrderByCreatedDateDesc(String status);
}
