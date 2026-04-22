package org.woridge.settings_management.service;

import org.woridge.settings_management.dto.work_schedule.ConfigureWorkScheduleRequest;
import org.woridge.settings_management.model.WorkSchedule;

public interface WorkScheduleValidator {

    void validateWorkSchedule(ConfigureWorkScheduleRequest configureWorkScheduleRequest);

    boolean isWorkScheduleUnchanged(WorkSchedule currentWorkSchedule, ConfigureWorkScheduleRequest configureWorkScheduleRequest);
}
