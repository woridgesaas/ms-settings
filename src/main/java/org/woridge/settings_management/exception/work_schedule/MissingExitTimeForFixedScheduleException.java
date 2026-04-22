package org.woridge.settings_management.exception.work_schedule;

public class MissingExitTimeForFixedScheduleException extends RuntimeException {

    public MissingExitTimeForFixedScheduleException(String message) {

        super(message);
    }
}
