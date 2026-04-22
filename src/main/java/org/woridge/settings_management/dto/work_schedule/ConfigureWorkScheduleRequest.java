package org.woridge.settings_management.dto.work_schedule;

import jakarta.validation.constraints.NotNull;
import org.woridge.settings_management.enumeration.WorkScheduleType;

import java.math.BigDecimal;
import java.time.LocalTime;

public record ConfigureWorkScheduleRequest(

        @NotNull(message = "Work schedule type is required")
        WorkScheduleType type,

        LocalTime entryTime,
        LocalTime exitTime,
        BigDecimal dailyHours
) {
}
