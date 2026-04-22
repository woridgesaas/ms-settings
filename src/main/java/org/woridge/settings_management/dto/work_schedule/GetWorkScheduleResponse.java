package org.woridge.settings_management.dto.work_schedule;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.woridge.settings_management.enumeration.WorkScheduleType;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record GetWorkScheduleResponse(
        UUID id,
        WorkScheduleType type,
        LocalTime entryTime,
        LocalTime exitTime,
        BigDecimal dailyHours
) {
}
