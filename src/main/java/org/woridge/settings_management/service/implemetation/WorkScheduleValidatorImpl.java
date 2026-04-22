package org.woridge.settings_management.service.implemetation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.woridge.settings_management.dto.work_schedule.ConfigureWorkScheduleRequest;
import org.woridge.settings_management.exception.work_schedule.*;
import org.woridge.settings_management.model.WorkSchedule;
import org.woridge.settings_management.service.WorkScheduleValidator;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class WorkScheduleValidatorImpl implements WorkScheduleValidator {

    @Value("${work-schedule.min-daily-hours}")
    private double MIN_DAILY_HOURS;

    @Value("${work-schedule.max-daily-hours}")
    private double MAX_DAILY_HOURS;

    @Override
    public void validateWorkSchedule(ConfigureWorkScheduleRequest configureWorkScheduleRequest) {

        switch (configureWorkScheduleRequest.type()) {

            case FIXED_HOURS -> validateFixedHours(configureWorkScheduleRequest);
            case FLEXIBLE_HOURS -> validateHoursToComplete(configureWorkScheduleRequest);
        }
    }

    @Override
    public boolean isWorkScheduleUnchanged(WorkSchedule currentWorkSchedule, ConfigureWorkScheduleRequest configureWorkScheduleRequest) {

        if (currentWorkSchedule.getType() != configureWorkScheduleRequest.type()) {

            return false;
        }

        return switch (configureWorkScheduleRequest.type()) {

            case FIXED_HOURS ->
                    Objects.equals(currentWorkSchedule.getEntryTime(), configureWorkScheduleRequest.entryTime())
                            && Objects.equals(currentWorkSchedule.getExitTime(), configureWorkScheduleRequest.exitTime());

            case FLEXIBLE_HOURS ->
                    Objects.equals(currentWorkSchedule.getDailyHours(), configureWorkScheduleRequest.dailyHours());
        };
    }

    private void validateFixedHours(ConfigureWorkScheduleRequest configureWorkScheduleRequest) {

        if (configureWorkScheduleRequest.entryTime() == null) {

            throw new MissingEntryTimeForFixedScheduleException("Entry time is required when work schedule type is fixed hours");
        }

        if (configureWorkScheduleRequest.exitTime() == null) {

            throw new MissingExitTimeForFixedScheduleException("Exit time is required when work schedule type is fixed hours");
        }

        if (configureWorkScheduleRequest.dailyHours() != null) {

            throw new DailyHoursNotAllowedForFixedScheduleException("Daily hours must not be provided when work schedule type is fixed hours");
        }

        if (!configureWorkScheduleRequest.exitTime().isAfter(configureWorkScheduleRequest.entryTime())) {

            throw new ExitTimeNotAfterEntryTimeException("Exit time must be strictly after entry time");

        }
    }

    private void validateHoursToComplete(ConfigureWorkScheduleRequest configureWorkScheduleRequest) {

        if (configureWorkScheduleRequest.dailyHours() == null) {

            throw new MissingDailyHoursForFlexibleScheduleException("Daily hours are required when work schedule type is flexible hours");
        }

        if (configureWorkScheduleRequest.entryTime() != null || configureWorkScheduleRequest.exitTime() != null) {

            throw new EntryOrExitTimeNotAllowedForFlexibleScheduleException("Entry time and exit time must not be provided when work schedule type is flexible hours");
        }

        if (configureWorkScheduleRequest.dailyHours().compareTo(BigDecimal.valueOf(MIN_DAILY_HOURS)) < 0
                || configureWorkScheduleRequest.dailyHours().compareTo(BigDecimal.valueOf(MAX_DAILY_HOURS)) > 0) {

            throw new DailyHoursOutOfAllowedRangeException("Daily hours must be between " + MIN_DAILY_HOURS + " and " + MAX_DAILY_HOURS + " hours");
        }
    }
}
