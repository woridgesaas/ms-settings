package org.woridge.settings_management.mapper;

import org.woridge.settings_management.dto.work_schedule.ConfigureWorkScheduleRequest;
import org.woridge.settings_management.dto.work_schedule.GetWorkScheduleResponse;
import org.woridge.settings_management.model.WorkSchedule;

public class WorkScheduleMapper {

    public static WorkSchedule toWorkSchedule(ConfigureWorkScheduleRequest configureWorkScheduleRequest) {

        return switch (configureWorkScheduleRequest.type()) {

            case FIXED_HOURS -> WorkSchedule
                    .builder()
                    .type(configureWorkScheduleRequest.type())
                    .entryTime(configureWorkScheduleRequest.entryTime())
                    .exitTime(configureWorkScheduleRequest.exitTime())
                    .build();

            case FLEXIBLE_HOURS -> WorkSchedule
                    .builder()
                    .type(configureWorkScheduleRequest.type())
                    .dailyHours(configureWorkScheduleRequest.dailyHours())
                    .build();
        };
    }

    public static void updateWorkSchedule(WorkSchedule currentWorkSchedule, ConfigureWorkScheduleRequest configureWorkScheduleRequest) {

        currentWorkSchedule.setType(configureWorkScheduleRequest.type());

        switch (configureWorkScheduleRequest.type()) {

            case FIXED_HOURS -> {

                currentWorkSchedule.setEntryTime(configureWorkScheduleRequest.entryTime());
                currentWorkSchedule.setExitTime(configureWorkScheduleRequest.exitTime());
                currentWorkSchedule.setDailyHours(null);
            }

            case FLEXIBLE_HOURS -> {

                currentWorkSchedule.setDailyHours(configureWorkScheduleRequest.dailyHours());
                currentWorkSchedule.setEntryTime(null);
                currentWorkSchedule.setExitTime(null);
            }
        }
    }

    public static GetWorkScheduleResponse toGetWorkScheduleResponse(WorkSchedule currentWorkSchedule) {

        return switch (currentWorkSchedule.getType()) {

            case FIXED_HOURS -> new GetWorkScheduleResponse(
                    currentWorkSchedule.getId(),
                    currentWorkSchedule.getType(),
                    currentWorkSchedule.getEntryTime(),
                    currentWorkSchedule.getExitTime(),
                    null
            );

            case FLEXIBLE_HOURS -> new GetWorkScheduleResponse(
                    currentWorkSchedule.getId(),
                    currentWorkSchedule.getType(),
                    null,
                    null,
                    currentWorkSchedule.getDailyHours()
            );
        };
    }
}
