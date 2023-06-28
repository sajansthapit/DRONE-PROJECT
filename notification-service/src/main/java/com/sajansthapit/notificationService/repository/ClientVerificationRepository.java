package com.sajansthapit.notificationService.repository;

import com.sajansthapit.notificationService.models.ClientVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientVerificationRepository extends JpaRepository<ClientVerification, Long> {

    Optional<ClientVerification> findByClientIdAndDroneIdAndStatus(Long clientId, Long droneId, String status);
}
