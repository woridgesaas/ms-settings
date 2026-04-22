package org.woridge.settings_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.woridge.settings_management.model.AlertThreshold;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlertThresholdRepository extends JpaRepository<AlertThreshold, UUID> {

    Optional<AlertThreshold> findFirstBy();
}
