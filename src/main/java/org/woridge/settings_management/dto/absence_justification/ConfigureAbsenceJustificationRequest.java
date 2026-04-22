package org.woridge.settings_management.dto.absence_justification;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ConfigureAbsenceJustificationRequest(

        @NotNull(message = "Allowed delay is required")
        @Min(value = 1, message = "Allowed delay must be at least 1 hour")
        int allowedDelayInHours
) {
}
