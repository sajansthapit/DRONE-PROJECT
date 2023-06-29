package com.sajansthapit.medicationservice.repository;

import com.sajansthapit.medicationservice.models.MedicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicationRequestRepository extends JpaRepository<MedicationRequest, Long> {

    List<MedicationRequest> findAllByUniqueId(String uniqueId);
}
