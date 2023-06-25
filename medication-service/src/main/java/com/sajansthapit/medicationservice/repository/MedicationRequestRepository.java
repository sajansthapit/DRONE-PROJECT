package com.sajansthapit.medicationservice.repository;

import com.sajansthapit.medicationservice.models.MedicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRequestRepository extends JpaRepository<MedicationRequest, Long> {
}
