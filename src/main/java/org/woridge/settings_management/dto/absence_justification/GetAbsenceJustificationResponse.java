package org.woridge.settings_management.dto.absence_justification;

import java.util.UUID;

public record GetAbsenceJustificationResponse(
        UUID id,
        int allowedDelayInHours
) {
}
