package org.woridge.settings_management.dto.exceptional_leave;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.woridge.settings_management.enumeration.ExceptionalLeaveName;

import java.util.UUID;

public record UpdateExceptionalLeaveItem(

        @NotNull(message = "Exceptional leave id is required")
        UUID id,

        @NotNull(message = "Exceptional leave name is required")
        ExceptionalLeaveName name,

        @NotNull(message = "Granted days is required")
        @Min(value = 0, message = "Granted days must be at least 0")
        int grantedDays
) {
}
