package org.woridge.settings_management.exception.work_schedule;

public class DailyHoursNotAllowedForFixedScheduleException extends RuntimeException {

    public DailyHoursNotAllowedForFixedScheduleException(String message) {

        super(message);
    }
}
