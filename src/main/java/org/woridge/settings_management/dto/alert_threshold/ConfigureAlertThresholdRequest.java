package org.woridge.settings_management.dto.alert_threshold;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ConfigureAlertThresholdRequest(

        @NotNull(message = "Warning threshold is required")
        @Min(value = 1, message = "Warning threshold must be at least 1 day")
        int warningThreshold,

        @NotNull(message = "Critical threshold is required")
        @Min(value = 1, message = "Critical threshold must be at least 1 day")
        int criticalThreshold
) {
}
