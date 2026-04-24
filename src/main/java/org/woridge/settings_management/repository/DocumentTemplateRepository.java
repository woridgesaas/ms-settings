package org.woridge.settings_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.woridge.settings_management.model.DocumentTemplate;

import java.util.UUID;

@Repository
public interface DocumentTemplateRepository extends JpaRepository<DocumentTemplate, UUID> {
}
