package org.woridge.settings_management.service;

import org.woridge.settings_management.dto.absence_justification.ConfigureAbsenceJustificationRequest;
import org.woridge.settings_management.dto.absence_justification.GetAbsenceJustificationResponse;

public interface AbsenceJustificationService {

    void configureAbsenceJustification(ConfigureAbsenceJustificationRequest configureAbsenceJustificationRequest);

    GetAbsenceJustificationResponse getAbsenceJustification();
}
