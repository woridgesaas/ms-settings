package org.woridge.settings_management.service;

import org.woridge.settings_management.dto.work_schedule.ConfigureWorkScheduleRequest;
import org.woridge.settings_management.dto.work_schedule.GetWorkScheduleResponse;

public interface WorkScheduleService {

    void configureWorkSchedule(ConfigureWorkScheduleRequest configureWorkScheduleRequest);

    GetWorkScheduleResponse getWorkSchedule();
}
