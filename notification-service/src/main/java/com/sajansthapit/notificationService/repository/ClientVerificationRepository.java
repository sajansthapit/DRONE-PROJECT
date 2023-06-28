package com.sajansthapit.notificationService.repository;

import com.sajansthapit.notificationService.models.ClientVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientVerificationRepository extends JpaRepository<ClientVerification, Long> {
}
