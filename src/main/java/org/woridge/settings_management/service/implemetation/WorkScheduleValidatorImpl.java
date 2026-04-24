package org.woridge.settings_management.service.implemetation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.woridge.settings_management.dto.work_schedule.ConfigureWorkScheduleRequest;
import org.woridge.settings_management.exception.work_schedule.*;
import org.woridge.settings_management.model.WorkSchedule;
import org.woridge.settings_management.properties.WorkScheduleProperties;
import org.woridge.settings_management.service.WorkScheduleValidator;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@AllArgsConstructor
public class WorkScheduleValidatorImpl implements WorkScheduleValidator {

    private final WorkScheduleProperties workScheduleProperties;

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

        if (configureWorkScheduleRequest.dailyHours().compareTo(BigDecimal.valueOf(workScheduleProperties.getMIN_DAILY_HOURS())) < 0
                || configureWorkScheduleRequest.dailyHours().compareTo(BigDecimal.valueOf(workScheduleProperties.getMAX_DAILY_HOURS())) > 0) {

            throw new DailyHoursOutOfAllowedRangeException("Daily hours must be between " + workScheduleProperties.getMIN_DAILY_HOURS() + " and " + workScheduleProperties.getMAX_DAILY_HOURS() + " hours");
        }
    }
}
