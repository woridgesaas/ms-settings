package org.woridge.settings_management.dto.alert_threshold;

import java.util.UUID;

public record GetAlertThresholdResponse(
        UUID id,
        int warningThreshold,
        int criticalThreshold
) {
}
