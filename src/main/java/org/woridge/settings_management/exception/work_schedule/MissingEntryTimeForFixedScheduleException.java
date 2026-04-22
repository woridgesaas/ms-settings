package org.woridge.settings_management.exception.work_schedule;

public class MissingEntryTimeForFixedScheduleException extends RuntimeException {

    public MissingEntryTimeForFixedScheduleException(String message) {

        super(message);
    }
}
